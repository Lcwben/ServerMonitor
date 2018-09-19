package com.gpdi.gx;

public class ClientInfo {
	
	private Integer recoverCountReset; //重新统计状态的间隔周期模板（用于重置周期）
	private Integer recoverCount; //发短信后，恢复到重新统计状态的间隔周期
	private Boolean hasSendMsg; //是否已发送短信标志
	
	public String endPoint;
	public String xmlInfo;
	public String phoneNums;
	
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getXmlInfo() {
		return xmlInfo;
	}
	public void setXmlInfo(String xmlInfo) {
		this.xmlInfo = xmlInfo;
	}
	public String getPhoneNums() {
		return phoneNums;
	}
	public void setPhoneNums(String phoneNums) {
		this.phoneNums = phoneNums;
	}
	
	public Integer getRecoverCountReset() {
		return recoverCountReset;
	}
	public void setRecoverCountReset(Integer recoverCountReset) {
		this.recoverCountReset = recoverCountReset;
	}
	public int getRecoverCount() {
		return recoverCount;
	}
	public void setRecoverCount(int recoverCount) {
		this.recoverCount = recoverCount;
	}
	public Boolean getHasSendMsg() {
		return hasSendMsg;
	}
	public void setHasSendMsg(Boolean hasSendMsg) {
		this.hasSendMsg = hasSendMsg;
	}
	
}
