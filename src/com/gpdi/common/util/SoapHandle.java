package com.gpdi.common.util;



import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;



public abstract class SoapHandle extends BasicHandler {

	@Override
	public void invoke(MessageContext msgContext) throws AxisFault {
		//获取SOAP消息
		SOAPMessage msg = msgContext.getMessage();
		String soapMessage = null;
		try {
			soapMessage = SoapUtil.cover2Str(msg);
		} catch (SOAPException e) {
			//e.printStackTrace();
			throw new AxisFault(e.getMessage());
		}
		//System.out.println("SOAP消息...." + soapMessage);
		handle(soapMessage);
	}

	protected abstract void handle(String soapmsg);
}
