package com.dji.itester.http;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

import com.dji.itester.utils.LogUtil;

import java.io.IOException;


/**
 * 将服务端返回的SessionID存储在CookieStore中
 * @author Charlie.chen
 * @date 2016-10-31
 *
 */
public class CookieUtil {

    private static CookieStore cookieStore = null;
    private static LogUtil log = new LogUtil(CookieUtil.class);


    /*
     * 通过CookieStore储存cookie
     */
    public static CookieStore setCookieStore(HttpResponse httpResponse) {
    	
        log.info("setCookieStore");
        cookieStore = new BasicCookieStore();
        // JSESSIONID
        String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
        String JSESSIONID = setCookie.substring("JSESSIONID=".length(),
        setCookie.indexOf(";"));
        log.debug("JSESSIONID:" + JSESSIONID);
        
        // 新建一个Cookie
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", JSESSIONID);
        cookie.setVersion(0);
        cookie.setDomain("127.0.0.1");
        cookie.setPath("/CwlProClient");
       
        cookieStore.addCookie(cookie);
        return cookieStore;
    }

    
    public static void printResponse(HttpResponse httpResponse){
    	
        // 获取响应消息实体
        HttpEntity entity = httpResponse.getEntity();
        // 响应状态
        log.info("status:" + httpResponse.getStatusLine());
        log.info("headers:");
        HeaderIterator iterator = httpResponse.headerIterator();
        while (iterator.hasNext()) {
            log.info("\t" + iterator.next());
        }
        // 判断响应实体是否为空
        if (entity != null) {
            String responseString = null;
            try {
                responseString = EntityUtils.toString(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("response length:" + responseString.length());
            log.info("response content:" + responseString.replace("\r\n", ""));
        }
    }

}
