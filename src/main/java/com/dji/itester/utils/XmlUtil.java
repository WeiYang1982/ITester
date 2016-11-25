package com.dji.itester.utils;




import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.*;

import com.dji.itester.http.HttpClientUtil;

public class XmlUtil {

	/**
	 * @param
	 * @param
	 * @author Charlie.chen
	 * @date 2016-10-31
	 */
	private static LogUtil log = new LogUtil(XmlUtil.class);

	public static Map<String, String> readXMLDocument(String xmlPath, String beanName) throws IOException {

		HashMap<String, String> locatorMap = new HashMap<String, String>();

		File file = new File(xmlPath);
		if (!file.exists()) {
			log.error("Can't find " + xmlPath);
		}

		SAXReader saxReader = new SAXReader();
		Document document;
		try {
			document = saxReader.read(file);

			for (Iterator iter = document.getRootElement().elementIterator(); iter.hasNext();) {
				Element e1 = (Element) iter.next();
				if (e1.attributeValue("beanName").equalsIgnoreCase(beanName)) {
					for (Iterator iter1 = e1.elementIterator(); iter1.hasNext();) {
						Element e2 = (Element) iter1.next();

						String elementName = e2.attributeValue("name").toString();
						String elementValue = e2.attributeValue("value").toString();
						locatorMap.put(elementName, elementValue);
					}
					return locatorMap;
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) throws IOException {
		String url = PropertiesUtil.getValue("url");

		String xmlPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\dji\\itester\\data\\paramData.xml";
		Map<String, String> paramsMap = XmlUtil.readXMLDocument(xmlPath, "signIn");

		//String response = HttpClientUtil.doPost(url, paramsMap);
		//System.out.println(response);
	}

}
