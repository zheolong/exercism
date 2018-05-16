package com.qihoo360.TopHundredDriver;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Comparator;

import com.qihoo360.TopHundredDriver.MRDPUtils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.fs.FileSystem;

import org.la4j.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Hello world!
 *
 */
public class TopHundredDriver 
{
    public static class TopComparator implements Comparator
    {
        public int compare(Object o1, Object o2)
        {
            Pair p1 = (Pair)o1;
            Pair p2 = (Pair)o2;
            Long l1 = (Long)p1.getLeft();
            Long l2 = (Long)p2.getLeft();
            Long r1 = (Long)p1.getRight();
            Long r2 = (Long)p2.getRight();
            Integer c1 = l1.compareTo(l2);
            Integer c2 = r1.compareTo(r2);
            return c1 != 0 ? c1: c2;
        }
    }

	public static class SOTopTenMapper extends
			Mapper<Object, Text, LongWritable, Text> {
		// Our output key and value Writables
		private ArrayList<TreeMap<Pair<Long, Long>, Pair<Long,Text>>> repToRecordMaps = new ArrayList<TreeMap<Pair<Long, Long>, Pair<Long,Text>>>();
        private JSONArray userArray = new JSONArray();  
        private TopComparator topComparator = new TopComparator();
        private Integer userNum = 0;
        private Integer topNum = 1;

		@Override
        protected void setup(Context context) throws IOException, InterruptedException {
            Configuration conf = context.getConfiguration();

            topNum = Integer.parseInt(conf.get("topNum"));

            Object obj = JSONValue.parse(conf.get("userListJsonText"));
            userArray = (JSONArray)obj;
            userNum = userArray.size();

            for (Integer i = 0; i < userNum; i++) {
                repToRecordMaps.add(new TreeMap<Pair<Long, Long>, Pair<Long,Text>>(topComparator));
            }
        }

		@Override
		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
            Log log = LogFactory.getLog(TopHundredDriver.class);

			// Parse the input string into a nice map
			Map parsed = MRDPUtils.transformJSONToMap(value.toString());
			if (parsed == null) {
				return;
			}

			Long userId = (Long)parsed.get("id");
			LinkedList eigenvector = (LinkedList)parsed.get("eigenvector");

			// Get will return null if the key is not there
			if (userId == null || eigenvector == null) {
				// skip this record
				return;
			}

            log.info("Your message");

            Iterator<JSONObject> iterator = userArray.iterator();
            Integer i = 0;
            for (; i < userArray.size(); i++) {
                JSONObject obj_tmp = iterator.next();
                Long userId2 = (Long)obj_tmp.get("id");
                JSONArray e1 = (JSONArray)obj_tmp.get("eigenvector");
                Long inp = MRDPUtils.getInnerProduct(e1, eigenvector);

                // generate json
                JSONObject obj = new JSONObject();
                JSONArray pair = new JSONArray();
                pair.add(userId2);
                pair.add(userId);
                obj.put("id_pair", pair);
                obj.put("inner_product", inp);
                StringWriter out = new StringWriter();
                obj.writeJSONString(out);
                String jsonText = out.toString();

                // construct heap
                repToRecordMaps.get(i).put(Pair.of(inp, userId), Pair.of(userId2, new Text(jsonText)));

                if (repToRecordMaps.get(i).size() > topNum) {
                    repToRecordMaps.get(i).remove(repToRecordMaps.get(i).firstKey());
                }
            }
		}

		@Override
		protected void cleanup(Context context) throws IOException,
				InterruptedException {
            for (TreeMap<Pair<Long, Long>, Pair<Long, Text>> repToRecordMap: repToRecordMaps)
            {
                for (Pair<Long, Text> p : repToRecordMap.values()) {
                    context.write(new LongWritable(p.getLeft()), p.getRight());
                }
            }
		}
	}

	public static class SOTopTenReducer extends
			Reducer<LongWritable, Text, NullWritable, Text> {

		private ArrayList<TreeMap<Pair<Long, Long>, Text>> repToRecordMaps = new ArrayList<TreeMap<Pair<Long, Long>, Text>>();
        private TopComparator topComparator = new TopComparator();
        private JSONArray userArray = new JSONArray();  
        private Integer userNum = 0;
        private Integer topNum = 1;

		@Override
        protected void setup(Context context) throws IOException, InterruptedException {
            Configuration conf = context.getConfiguration();

            topNum = Integer.parseInt(conf.get("topNum"));
            Object obj = JSONValue.parse(conf.get("userListJsonText"));
            userArray = (JSONArray)obj;
            userNum = userArray.size();

            for (Integer i = 0; i < userNum; i++) {
                repToRecordMaps.add(new TreeMap<Pair<Long, Long>, Text>(topComparator));
            }
        }


		@Override
		public void reduce(LongWritable key, Iterable<Text> values,
				Context context) throws IOException, InterruptedException {
			for (Text value : values) {
			    // Parse the input string into a nice map
			    Map parsed = MRDPUtils.transformJSONToMap(value.toString());
			    if (parsed == null) {
			    	continue;
			    }

			    LinkedList idPair= (LinkedList)parsed.get("id_pair");
			    Long innerProduct = (Long)parsed.get("inner_product");

			    // Get will return null if the key is not there
			    if (idPair == null || innerProduct == null) {
			    	// skip this record
			    	continue;
			    }

                Iterator<JSONObject> iterator = userArray.iterator();
                Integer i = (int)(key.get() % userArray.size());
				repToRecordMaps.get(i).put(Pair.of(innerProduct, (Long)idPair.get(1)), new Text(value)); 
				if (repToRecordMaps.get(i).size() > topNum) {
					repToRecordMaps.get(i).remove(repToRecordMaps.get(i).firstKey());
				}
			}

		}

		@Override
		protected void cleanup(Context context) throws IOException,
				InterruptedException {
            for (TreeMap<Pair<Long, Long>, Text> repToRecordMap: repToRecordMaps)
            {
                for (Text t : repToRecordMap.values()) {
                    context.write(null, t);
                }
            }
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs();
		if (otherArgs.length != 5) {
			System.err.println("Usage: TopTenDriver <in> <out>");
			System.exit(2);
		}

        /* delete output path */
        // configuration should contain reference to your namenode
        FileSystem fs = FileSystem.get(new Configuration());
        // true stands for recursively deleting the folder you gave
        fs.delete(new Path(otherArgs[1]), true);

        /* initialize current input filename and offset */
        List<String> inputFileList = MRDPUtils.getAllFilePath(new Path(otherArgs[0]), fs);
        if (inputFileList.isEmpty())
            return;

        String curInputFileName = inputFileList.get(0);

        //Path pt=new Path("hdfs://localhost:9000" + otherArgs[2]);
        Path pt=new Path("hdfs://10.248.31.37:8020" + otherArgs[2]);
        BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(pt)));
        String line;
        line=br.readLine();
        br.close();
        if (line != null){
            Map parsed = MRDPUtils.transformJSONToMap(line.toString());
            if (parsed == null) {
                return; 
            }
			curInputFileName = (String)parsed.get("curfile");
        } else {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fs.create(pt, true)));
            JSONObject obj = new JSONObject();
            obj.put("curfile", curInputFileName);
            StringWriter out = new StringWriter();
            obj.writeJSONString(out);
            String jsonText = out.toString();
            bw.write(jsonText);
            bw.close();
        }

        ///* read user data */
        Integer curReadingFileIndex = inputFileList.indexOf(curInputFileName);
        List userList = new ArrayList();
        Integer batch = Integer.parseInt(otherArgs[3]);
        Integer i = curReadingFileIndex;
        for (; i < inputFileList.size() && (i - curReadingFileIndex) < batch; i++) {
            Path ptc=new Path(inputFileList.get(i));
            BufferedReader brr=new BufferedReader(new InputStreamReader(fs.open(ptc)));

            String linee;
            linee=brr.readLine();
            while (linee != null) {
                Map parsed = MRDPUtils.transformJSONToMap(linee.toString());
                if (parsed != null) {
                    userList.add(parsed);
                }
                linee=brr.readLine();
            }
        }
        //curInputFileName = inputFileList.get(i);

        if (userList.isEmpty()) {
            return;
        }

        StringWriter out = new StringWriter();
        JSONValue.writeJSONString(userList, out);
        String userListJsonText = out.toString(); 

        /* main process */
        conf.set("userListJsonText", userListJsonText);
        conf.set("topNum", otherArgs[4]);

		Job job = Job.getInstance(conf, "Top Ten Users by Reputation");
		job.setJarByClass(TopHundredDriver.class);
		job.setMapperClass(SOTopTenMapper.class);
		job.setReducerClass(SOTopTenReducer.class);
		job.setNumReduceTasks(1);
		//job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

        Log log = LogFactory.getLog(TopHundredDriver.class);
        log.info("Your message");

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
