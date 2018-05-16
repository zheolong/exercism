package com.qihoo360.TopHundredDriver;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileStatus;

import org.la4j.Vector;
import org.la4j.vector.DenseVector;
import org.la4j.vector.VectorFactory;



public class MRDPUtils {
	
	public static final String[] REDIS_INSTANCES = { "p0", "p1", "p2", "p3",
			"p4", "p6" };

	// This helper function parses the stackoverflow into a Map for us.
	public static Map<String, String> transformXmlToMap(String xml) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String[] tokens = xml.trim().substring(5, xml.trim().length() - 3)
					.split("\"");

			for (int i = 0; i < tokens.length - 1; i += 2) {
				String key = tokens[i].trim();
				String val = tokens[i + 1];

				map.put(key.substring(0, key.length() - 1), val);
			}
		} catch (StringIndexOutOfBoundsException e) {
			System.err.println(xml);
		}

		return map;
	}

    public static Map transformJSONToMap(String jsonText) {
        JSONParser parser=new JSONParser();

        ContainerFactory orderedKeyFactory = new ContainerFactory() {
            public List creatArrayContainer() {
              return new LinkedList();
            }

            public Map createObjectContainer() {
              return new LinkedHashMap();
            }

        };

        try{
            Map json = (Map)parser.parse(jsonText, orderedKeyFactory);
            return json;
        } catch(ParseException pe){
            System.out.println(pe); 
        }

        return null;
    }

    /**
     * @param filePath
     * @param fs
     * @return list of absolute file path present in given path
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static List<String> getAllFilePath(Path filePath, FileSystem fs) throws FileNotFoundException, IOException {
        List<String> fileList = new ArrayList<String>();
        FileStatus[] fileStatus = fs.listStatus(filePath);
        for (FileStatus fileStat : fileStatus) {
            if (fileStat.isDirectory()) {
                fileList.addAll(getAllFilePath(fileStat.getPath(), fs));
            } else {
                fileList.add(fileStat.getPath().toString());
            }
        }
        Collections.sort(fileList);
        return fileList;
    }

    public static ArrayList<Integer> convertJSONArrayToArray(JSONArray jsonArray)
    {
        ArrayList<Integer> list = new ArrayList<Integer>();     
        if (jsonArray != null) { 
           int len = jsonArray.size();
           for (int i=0;i<len;i++){ 
               list.add((Integer)jsonArray.get(i));
           } 
        } 
        return list;
    }

    //public static DenseVector convertJSONArrayToVector(JSONArray jsonArray)
    //{
    //    DenseFactory denseFactory = new DenseFactory();
    //    DenseVector v = denseFactory.createVector();

    //    if (jsonArray != null) { 
    //       int len = jsonArray.size();
    //       for (int i=0;i<len;i++){ 
    //           v.add((double)jsonArray.get(i));
    //       } 
    //    } 
    //    return v;
    //}

    public static Long getInnerProduct(JSONArray jsonArray1, LinkedList jsonArray2)
    {
         Long innerProduct = 0L;
         for (int i = 0; i < jsonArray1.size() && i < jsonArray2.size(); i++) {
             innerProduct += ((Long)jsonArray1.get(i)) * ((Long)jsonArray2.get(i));
             i++;
         }
         return innerProduct;
    }
}
