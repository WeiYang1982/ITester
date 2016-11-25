package com.dji.itester.utils;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonEncoding;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.dji.itester.bean.ParamBean;
import com.fasterxml.jackson.core.JsonParseException;


public class JacksonUtil {

	private static ObjectMapper objectMapper = null;
	private static JsonGenerator jsonGenerator = null;

	/**
	 * 将JavaBean/Map/List/Array等对象转换成json字符串
	 * 
	 * @author Charlie.chen
	 */
	public static void writeObject2JSON(Object obj) {

		objectMapper = new ObjectMapper();
		try {
			jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
			// writeObject可以转换java对象，eg:JavaBean/Map/List/Array等
			jsonGenerator.writeObject(obj);
			System.out.println();
			System.out.println(obj);
			
			// objectMapper.writeValue(System.out, bean);
			// //writeValue具有和writeObject相同的功能

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (jsonGenerator != null) {
					jsonGenerator.flush();
				}
				if (!jsonGenerator.isClosed()) {
					jsonGenerator.close();
				}
				jsonGenerator = null;
				objectMapper = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	/**
	 * 将json字符串转换成JavaBean对象
	 * Object 其实为JavaBean类
	 */
	public Object readJson2JavaBean(String jsonString, Object obj) {
	    
	    try {
	        obj = objectMapper.readValue(jsonString, Object.class);
	        return obj;
	    } catch (JsonParseException e) {
	        e.printStackTrace();
	    } catch (JsonMappingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
	
	
	/**
	 * 将JavaBean对象转换成xml文档
	 */
	/*
	public void writeJavaBean2Xml(Object obj){
		XmlMapper xml = new XmlMapper();
	    
	    try {

	        StringWriter sw = new StringWriter();
	        xml.writeValue(sw, obj);
	     
	        System.out.println(sw.toString());

	    } catch (JsonGenerationException e) {
	        e.printStackTrace();
	    } catch (JsonMappingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
	*/
	

	/**
	 * 将Map和List对象转换成xml文档
	 */
	/*
	public void writeMap2Xml(Object obj){
		XmlMapper xml = new XmlMapper();
	    
	    try {

	        StringWriter sw = new StringWriter();
	        System.out.println(xml.writeValueAsString(obj));

	    } catch (JsonGenerationException e) {
	        e.printStackTrace();
	    } catch (JsonMappingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
	*/
		
		
}
	
	
	
