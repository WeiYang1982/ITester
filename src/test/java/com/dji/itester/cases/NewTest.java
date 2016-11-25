package com.dji.itester.cases;

import org.testng.annotations.Test;

import com.dji.itester.bean.ResponseBean;
import com.dji.itester.http.HttpClientUtil;
import com.dji.itester.http.ReponseUtil;
import com.dji.itester.utils.PropertiesUtil;
import com.dji.itester.utils.XmlUtil;

import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class NewTest {

	static CookieStore cookieStore=null;

	static CloseableHttpClient httpclient=null;

	@Test
	public void signIN() {

		try {
			String url = PropertiesUtil.getValue("url","config.properties");
			
			//String xmlPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\dji\\itester\\data\\paramData.xml";
			String xmlPath=NewTest.class.getClassLoader().getResource("paramData.xml").getPath();

			Map<String, String> paramsMap = XmlUtil.readXMLDocument(xmlPath, "signIn");

			httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
			CloseableHttpResponse httpResponse = HttpClientUtil.doPost(url, paramsMap, httpclient, cookieStore);

			ResponseBean responseBean = ReponseUtil.setResponseBean(httpResponse);

			// add Assert
			Assert.assertEquals("OK", responseBean.getStatus());
			Assert.assertEquals("200", responseBean.getStatusCode());
			Assert.assertEquals("dsgfdfgdfsdfdgfdg", responseBean.getBody());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {
	}

	@AfterSuite
	public void closeClient() {
		try {
			// 关闭流并释放资源
			httpclient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
