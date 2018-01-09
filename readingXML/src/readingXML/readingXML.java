package readingXML;

import java.io.IOException;
import java.util.ArrayList;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class readingXML {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
	ArrayList<String> altTagName=new ArrayList<String>();
	ArrayList<String> altTagValue=new ArrayList<String>();
		
	DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			
	
		DocumentBuilder builder=factory.newDocumentBuilder();
		
		Document doc=builder.parse("C:\\Users\\tangirala\\Desktop\\test.xml");//path of your xml
		
		NodeList list=doc.getElementsByTagName("*");
		
		for(int i=0;i<list.getLength();i++)
		{
			Element element=(Element)list.item(i);
			String tagName=element.getNodeName();
			//System.out.println(tagName);//This will fetch all the tag Names in your xml
			String tagValue="";
			//if(tagName.equalsIgnoreCase("catalog"))
			//{
				int j=i;
				
				while(j<list.getLength())
				{
					Element elements=(Element)list.item(j);
					altTagName.add(elements.getNodeName());
					
					ArrayList<String> AttributeValues=new ArrayList<String>();
				String attributeValues=fun_ExtractNodeAttribute(elements);
				AttributeValues.add(attributeValues);
					System.out.println(AttributeValues);   //Get Attributes and its values
					tagValue=fun_ExtractNodeValue(elements);//Get child Nodes values function
					System.out.println(tagValue);
					j++;
				}
			//}
		}

	}
	
	public static String fun_ExtractNodeAttribute(Element ele)
	{
	String attrilist="";
	String attributeValues="";
	NamedNodeMap attributes=ele.getAttributes();
	int numAttributes=attributes.getLength();
		try
		{
			if(numAttributes>0)
			{
				for(int i=0;i<numAttributes;i++)
				{
					Attr attr=(Attr)attributes.item(i);
					String attrName=attr.getNodeName();
					String attrValue=attr.getNodeValue();
					attrilist=attrName+"="+"\""+attrValue+"\"";
					attributeValues=attributeValues+attrilist;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return attributeValues;
	}

	
	
	public static String fun_ExtractNodeValue(Element ele)
	
	{
		String tagValue="";
		try
		{
			if(ele.hasChildNodes())
			{
				tagValue=ele.getFirstChild().getNodeValue();
				
				if(tagValue==null)
				{
					tagValue="";
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return tagValue;
	}
}
