package com.gpdi.common.util;




//import com.gpdi.infores.sys.service.LogWebclientService;
//import com.gpdi.infores.util.SpringContextUtil;

/**
 * soap 发出消息处理类
 * @author Dong
 *
 */
public class SoapReqHandle extends SoapHandle {
	//private LogWebclientService logWebclientService 
	//	= (LogWebclientService)SpringContextUtil.getBean("logWebclientService");
	private String sid;//唯一标识
	private String endPointAddress;//WS目标地址
	
	public SoapReqHandle(String sid, String endPointAddress) {
		super();
		this.sid = sid;
		this.endPointAddress = endPointAddress;
	}

	@Override
	protected void handle(String soapmsg) {
	}
}
