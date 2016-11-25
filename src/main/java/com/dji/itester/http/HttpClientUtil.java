package com.dji.itester.http;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


/**
 * 封装一个工具类HttpClientUtil，发送http和https请求的方法(包括get，post，put，delete请求)
 * 大家以后在用到网络请求时，直接用这个工具类即可
 * 
 * @author Charlie.Chen
 * @date 2016-10-31
 *
 */

public class HttpClientUtil {

	private static Charset s = Consts.UTF_8;
	// 请求实体
	private static UrlEncodedFormEntity entitys = null;

	/**
	 * get请求
	 * @param url
	 * @param params
	 * @param httpclient
	 * @param cookieStore
	 * @return
	 */
	public static CloseableHttpResponse doGet(String url, Map<String, String> paramsMap, CloseableHttpClient httpclient, CookieStore cookieStore) {

		HttpGet httpget = null;
		entitys = getFormEntity(paramsMap);

		try {
			httpget = new HttpGet(url + '?' + EntityUtils.toString(entitys));
			if (cookieStore != null) {
				httpget.setHeader("Cookie", "JSESSIONID=" + cookieStore.getCookies().get(0).getValue().trim());
			}
			
			// 执行get请求，返回response服务器响应对象, 其中包含了状态信息和服务器返回的数据
			CloseableHttpResponse httpResponse = httpclient.execute(httpget);
			return httpResponse;

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	/**
	 * post请求
	 * @param url
	 * @param paramsMap
	 * @param httpclient
	 * @param cookieStore
	 * @return
	 */
	public static CloseableHttpResponse doPost(String url, Map<String, String> paramsMap, CloseableHttpClient httpclient,
			CookieStore cookieStore) {

		HttpPost httppost = new HttpPost(url);

		if (cookieStore != null) {
			httppost.setHeader("Cookie", "JSESSIONID=" + cookieStore.getCookies().get(0).getValue().trim());
		}

		// 请求实体
		entitys = getFormEntity(paramsMap);
		httppost.setEntity(entitys);
		// httppost.setHeader("Content-type","application/json,charset=utf-8");
		// httppost.setHeader("Accept", "application/json");

		// 执行post请求，返回response服务器响应对象, 其中包含了状态信息和服务器返回的数据
		try {
			CloseableHttpResponse httpResponse = httpclient.execute(httppost);
			return httpResponse;
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}

	/**
	 * put请求
	 * @param url
	 * @param params
	 * @param httpclient
	 * @param cookieStore
	 * @return
	 */
	public static CloseableHttpResponse doPut(String url, Map<String, String> paramsMap, CloseableHttpClient httpclient, CookieStore cookieStore) {

		HttpPut httpput = new HttpPut(url);
		if (cookieStore != null) {
			httpput.setHeader("Cookie", "JSESSIONID=" + cookieStore.getCookies().get(0).getValue().trim());
		}

		entitys = getFormEntity(paramsMap);
		httpput.setEntity(entitys);
		// 执行putt请求，返回response服务器响应对象, 其中包含了状态信息和服务器返回的数据
		try {
			CloseableHttpResponse httpResponse = httpclient.execute(httpput);
			return httpResponse;

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * delete请求
	 * @param url
	 * @param params
	 * @param httpclient
	 * @param cookieStore
	 * @return
	 */
	public static CloseableHttpResponse doDelete(String url, Map<String, String> paramsMap, CloseableHttpClient httpclient, CookieStore cookieStore) {

		HttpDelete httpdelete  = null;
		entitys = getFormEntity(paramsMap);

		try {
			httpdelete = new HttpDelete(url + '?' + EntityUtils.toString(entitys));
			if (cookieStore != null) {
				httpdelete.setHeader("Cookie", "JSESSIONID=" + cookieStore.getCookies().get(0).getValue().trim());
			}
			
			// 执行get请求，返回response服务器响应对象, 其中包含了状态信息和服务器返回的数据
			CloseableHttpResponse httpResponse = httpclient.execute(httpdelete);
			return httpResponse;

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	

	/**
	 * 封装一个获取请求实体的方法
	 * @param params
	 * @param ucode
	 * @return
	 */
	public static UrlEncodedFormEntity getFormEntity(Map<String, String> paramsMap, Charset... ucode) {
		Charset f = null;
		if (ucode.equals("")) {
			f = Consts.UTF_8;
		} else {
			f = s;
		}
		List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
		Set<String> keySet = paramsMap.keySet();
		for (String key : keySet) {
			paramsList.add(new BasicNameValuePair(key, paramsMap.get(key)));
		}
		UrlEncodedFormEntity entitys = new UrlEncodedFormEntity(paramsList, f);
		return entitys;
	}

}
