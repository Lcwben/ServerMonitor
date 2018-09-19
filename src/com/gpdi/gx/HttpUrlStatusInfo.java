package com.gpdi.gx;

import java.util.Set;

public class HttpUrlStatusInfo {

	public static Integer RECOVERCOUNTRESET; //重新统计状态的间隔周期模板（用于重置周期）
	public static Set<String> STATUSCODEWHITELIST; //http状态码白名单
	
	private String url; //需要监控的Http Url
	
	private Integer recoverCount; //发短信后，恢复到重新统计状态的间隔周期
	private Boolean hasSendMsg; //是否已发送短信标志
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getRecoverCount() {
		return recoverCount;
	}
	public void setRecoverCount(Integer recoverCount) {
		this.recoverCount = recoverCount;
	}
	public Boolean getHasSendMsg() {
		return hasSendMsg;
	}
	public void setHasSendMsg(Boolean hasSendMsg) {
		this.hasSendMsg = hasSendMsg;
	}
	
	
}
