package com.gpdi.monitor.servers;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String type = null ;//1、只监测可达情况
		String url = null ;
		url = "http://www.baidu.com" ;
		
		CloseableHttpClient httpClient = HttpClients.createDefault() ;

		/* 
		 * setConnectTimeout：设置连接超时时间，单位毫秒。
		 * setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
		 * setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
		 */
		RequestConfig rc = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(30000).build() ;
		
		HttpGet get = new HttpGet(url) ;
		get.setConfig(rc) ;
		
		CloseableHttpResponse httpResponse = null;
		try {
			//发送get请求
			httpResponse = httpClient.execute(get);
			try{
				//response实体
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity){
					System.out.println("响应状态码:"+ httpResponse.getStatusLine());
					System.out.println("-------------------------------------------------");
					System.out.println("响应内容:" + EntityUtils.toString(entity));
					System.out.println("-------------------------------------------------");                    
				}
			}
			finally{
				httpResponse.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
		}

	}

}
