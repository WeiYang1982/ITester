package com.dji.itester.http;

import com.dji.itester.bean.ResponseBean;
import com.dji.itester.utils.LogUtil;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

/**
 * 将从服务端返回的response结果解析为ResponseBean对象
 * @author Charlie.chen
 * @date 2016-10-31
 *
 */

public class ReponseUtil {

	private static LogUtil log = new LogUtil(ReponseUtil.class);
	private static ResponseBean responseBean=null;

	public static ResponseBean setResponseBean(CloseableHttpResponse httpResponse) {

		// 使用响应对象获取响应实体
		HttpEntity entity = httpResponse.getEntity();
		if (entity != null)
			try {
				// 将响应实体转为字符串
				String responseString = EntityUtils.toString(entity, "utf-8");
				String rs = responseString.replace("\r\n", "");
				
				responseBean = new ResponseBean();
				responseBean.setStatus(httpResponse.getStatusLine().getReasonPhrase());
				responseBean.setStatusCode(Integer.toString(httpResponse.getStatusLine().getStatusCode()));
				responseBean.setBody(rs);
				
				log.info("\n" + "***************************返回开始**********************************" + "\n"
						+ httpResponse.getStatusLine().getReasonPhrase() + "\n"
						+ Integer.toString(httpResponse.getStatusLine().getStatusCode()) + "\n" + "Context" + rs + "\n"
						+ "***************************返回结束**********************************");

				HeaderIterator iterator = httpResponse.headerIterator();
				while (iterator.hasNext()) {
					log.debug("\t" + iterator.next());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return responseBean;

	}

}
