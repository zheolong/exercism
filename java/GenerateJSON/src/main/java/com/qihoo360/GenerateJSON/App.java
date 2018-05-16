package com.qihoo360.GenerateJSON;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Thread;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    throws Exception
    {
        File file = new File("output.json");

        // if file does not exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);

        for (Integer i = 1; i <= 100; i++) {
            JSONObject obj = new JSONObject();
            obj.put("id", i);

            JSONArray list = new JSONArray();

            Random rand = new Random(System.currentTimeMillis());
            for (Integer j = 1; j <= 200; j++) {
                list.add(rand.nextInt(10));
            }
            obj.put("eigenvector", list);

            StringWriter out = new StringWriter();
            obj.writeJSONString(out);
            String jsonText = out.toString(); 

            System.out.println(jsonText);
            fw.write(jsonText);
            fw.write('\n');
            Thread.sleep(1);
        }

        fw.flush();
        fw.close();
        /*
        String s = "[{\"id\":1,\"eigenvector\":[1,2]}, {\"id\":1,\"eigenvector\":[1,2]}]";
        Object obj=JSONValue.parse(s);
        JSONArray array = (JSONArray)obj; 
            Iterator<String> iterator = array.iterator();
            while (iterator.hasNext()) {
                iterator.next().get("eigenvector");
            }
        */
    }

    public static Map transformJSONToList(String jsonText) {
        JSONParser parser=new JSONParser();

        ContainerFactory orderedKeyFactory = new ContainerFactory() {
            public List creatArrayContainer() {
              return new LinkedList();
            }

            public Map createObjectContainer() {
              return new LinkedHashMap();
            }

        };
        System.out.println("=======decode=======");

        try{
            Map json = (Map)parser.parse(jsonText, orderedKeyFactory);
            //Iterator iter = json.entrySet().iterator();

            //System.out.println("==iterate result==");

            //while(iter.hasNext()){
            //    Map.Entry entry = (Map.Entry)iter.next();
            //    System.out.println(entry.getKey() + "=>" + entry.getValue());
            //}

            //System.out.println("==toJSONString()==");
            //System.out.println(JSONValue.toJSONString(json));
            return json;
        } catch(ParseException pe){
            System.out.println(pe); 
            return null;
        }
    }
}
