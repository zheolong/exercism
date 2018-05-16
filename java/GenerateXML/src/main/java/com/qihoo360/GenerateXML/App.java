package com.qihoo360.GenerateXML;

import java.io.File;
import java.io.FileWriter;
import com.megginson.sax.XMLWriter;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main (String args[])
    throws Exception
    {
        File file = new File("output.xml");

        // if file does not exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());

        XMLWriter writer = new XMLWriter(fw);

        writer.startDocument();
        //writer.startElement("","ingredients","",null);
        //writer.startElement("ingredients");
        writer.startElement("", "ingredients", "", new AttributesImpl());
        for (Integer i = 1; i <= 100; i++) {
            AttributesImpl attribs = new AttributesImpl();
            attribs.addAttribute("","name","","",i.toString());
            writer.startElement("", "ingredient","",attribs);
            writer.endElement("ingredient");
        }
        writer.endElement("ingredients");
        writer.endDocument();
    }
}
