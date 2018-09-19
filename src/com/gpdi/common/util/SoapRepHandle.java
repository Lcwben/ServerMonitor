package com.gpdi.common.util;




//import com.gpdi.infores.sys.service.LogWebclientService;
//import com.gpdi.infores.util.SpringContextUtil;

/**
 * soap 接收返回消息处理类
 * @author Dong
 *
 */
public class SoapRepHandle extends SoapHandle {
	//private LogWebclientService logWebclientService 
	//	= (LogWebclientService)SpringContextUtil.getBean("logWebclientService");
	private String sid;//唯一标识
	
	public SoapRepHandle(String sid) {
		super();
		this.sid = sid;
	}

	@Override
	protected void handle(String soapmsg) {
		
		System.out.println("SoapRepHandle.handle  soapmsg:"+soapmsg);
	}
	
}
