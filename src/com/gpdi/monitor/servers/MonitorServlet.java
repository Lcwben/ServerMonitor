package com.gpdi.monitor.servers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class MonitorServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request , HttpServletResponse response){
		this.doPost(request, response) ;
	}
	
	public void doPost(HttpServletRequest request , HttpServletResponse response){
		String type = null ;//1、只监测可达情况
		String url = null ;
		
		type = request.getParameter("type") ;
		url = request.getParameter("url") ;
		
		StringBuffer infoSb = new StringBuffer() ;
		
		if(type!=null && type.equals("1")){
			long begTime = System.currentTimeMillis() ;
			try {
				if(url!=null && this.alivedCheck(url)){
					infoSb.append("\"time\":").append( System.currentTimeMillis()-begTime) ;
				}else{
					infoSb.append("\"time\":").append("TimeOut") ;
				}
			} catch (Exception e) {
				e.printStackTrace() ;
				infoSb.append("\"erro\":").append(true) ;
			}
		}
		
		response.setContentType("text/Xml;charset=utf-8");
        PrintWriter out = null;
        try {
        	out = response.getWriter();
        	out.println("{"+infoSb+"}");
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        finally {
            out.close();
        }
		
	}
	
	private boolean alivedCheck(String url) throws Exception{
		
		boolean isAlived = false ;
		
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
					String status = httpResponse.getStatusLine().toString() ;
					//System.out.println("响应状态码:"+ httpResponse.getStatusLine());
					if(status.indexOf("200")>-1){
						isAlived = true ;
					}
					
					//System.out.println("-------------------------------------------------");
					//System.out.println("响应内容:" + EntityUtils.toString(entity));
					//System.out.println("-------------------------------------------------");                    
				}
			}
			finally{
				httpResponse.close();
			}
		} catch (Exception e) {
			e.printStackTrace() ;
			throw new Exception(e) ;
		}
		return isAlived ;
	}

}
