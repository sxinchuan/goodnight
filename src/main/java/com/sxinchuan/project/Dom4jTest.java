package com.sxinchuan.project;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jTest {
	
	@SuppressWarnings("all")
	public static void main(String[] args) throws Exception {
		String hotQuestionFilter = "test,答案流程样式测试,是";
		String[] hotQuestionFilterArr = hotQuestionFilter.split(",");
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(new File("D:/hot-question.xml"));
		Element root = doc.getRootElement();
		List list = root.elements("Question");
		System.out.println(list.size());
        Iterator<Attribute> iter = list.iterator();
		
		while (iter.hasNext()) {
			Element elm = (Element) iter.next();
			for (int i = 0; i < hotQuestionFilterArr.length; i++) {
	            if (hotQuestionFilterArr[i].equals(elm.getText())) {
	                //修改属性值
	            	boolean remove = root.remove(elm);
	            	System.out.println(remove);
	            }
	        }
        }
		List list2 = root.elements("Question");
		System.out.println(list2.size());
		OutputFormat format = OutputFormat.createPrettyPrint();
	    XMLWriter writer = new XMLWriter(new FileWriter("D:/output.xml"), format);
	    writer.write(doc);
	    writer.close();
	}
}
