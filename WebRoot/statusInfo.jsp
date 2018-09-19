<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>状态监控页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
		
		/**
		 * 清除表格中出标题行外的所有元素的方法
		 */
		function clearTableNodes() {
			var headTitle = $("#statusTable tr:first");
			var table  = $("#statusTable");
			table.html("");
			table.append(headTitle);
			
			$("#errPanel").hide();
		}
		
		/**
		 * 把后台返回的数据已DOM节点形式展示的方法
		 */
		function showStatusResult(data) {
			
			//清除标题行外的dom元素
			clearTableNodes();
			
			var tr = document.createElement("tr");
			var td = document.createElement("td");
			//已后台返回的数据循环创建tr，拼接到table
			$.each(data, function(i,item){
 				var trCopy = $(tr).clone();				
				trCopy.append($(td).clone().html(item.alertId));
				trCopy.append($(td).clone().html(item.alertType));
				trCopy.append($(td).clone().html(item.alertDetail));
				trCopy.append($(td).clone().html(item.alertValue));
				trCopy.append($(td).clone().html(item.value));
				trCopy.append($(td).clone().html(item.alertStatus));
				trCopy.append($(td).clone().html(item.alertDate));
				trCopy.append($(td).clone().html(item.remark));
				trCopy.append($(td).clone().html(item.weixinStatus));
				trCopy.append($(td).clone().html(item.weixinDate));
				
				$("#statusTable").append(trCopy);
			});
			
		}
	
		
		/**
		 * 发送请求获得监控状态的方法
		 */
		function checkStatusInfo() {
			$.ajax({
					async : true,
					cache : false,
					timeout : 30000,
					type : "POST",
					url : "<%=basePath%>statusInfo",
					dataType : 'json',
					data : {
						
					},
					success : function(data) {
 						showStatusResult(data);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						var errPanel = $("#errPanel");
						if (textStatus == "timeout") {
							errPanel.html("超时") ;
						} else {
							errPanel.html("加载出错") ;
						}
						errPanel.show();
					},
					
				});
		}
		
		//onload执行的方法
		$(document).ready(function() {
			checkStatusInfo();
			setInterval(checkStatusInfo,10000);
		});
	</script>
  </head>
  
  <body>
  	<h1>状态监控</h1>
  	<hr />
  	<table id="statusTable" border="1" cellspacing="2" cellpadding="5">
  		<tr id="statusTableTitle" style="text-align: center;background-color: #AAAAAA;font-weight: bold;">
  			<td>告警	ID</td>
  			<td>告警类型</td>
  			<td>告警明细</td>
  			<td>监控数值</td>
  			<td>监控阈值</td>
  			<td>告警状态值(0：正常；1：异常；)</td>
  			<td>告警时间</td>
  			<td>备注</td>
  			<td>微信监控状态值</td>
  			<td>微信记录告警时间</td>
  		</tr>
  	</table>
  	
    <input type="button" value="刷新" onclick="checkStatusInfo()"/>
    <h4 id="errPanel" style="display:none" ></h4>
  </body>
</html>
