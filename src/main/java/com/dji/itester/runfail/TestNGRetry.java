package com.dji.itester.runfail;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.dji.itester.utils.LogUtil;

/**
 * 用例失败自动重跑逻辑
 * @author Charlie.chen
 *
 */
public class TestNGRetry implements IRetryAnalyzer {
	public LogUtil log = new LogUtil(this.getClass());
	private int retryCount = 0;
	private int maxRetryCount=2;


	public boolean retry(ITestResult result) {
		if (retryCount <= maxRetryCount) {
			String message = "running retry for  '" + result.getName() + "' on class " + 
                                       this.getClass().getName() + " Retrying " + retryCount + " times";
			log.info(message);
			retryCount++;
			return true;
		}
		return false;
	}
}