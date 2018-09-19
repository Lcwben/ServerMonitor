<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>服务运行监控平台</title>
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
		var serverArray = new Array() ;
		/* serverArray[0] = "addr27_8099,标准地址服务27_8099,http://192.168.4.27:8099/addrMgr/monitor,0" ;
		serverArray[1] = "addr28_8099,标准地址服务28_8099,http://192.168.4.28:8099/addrMgr/monitor,0" ;
		serverArray[2] = "addr24_8099,标准地址服务24_8099,http://192.168.4.24:8099/addrMgr/monitor,0" ;
        serverArray[3] = "addr24_8088,标准地址服务24_8088,http://192.168.4.24:8088/addrMgr/monitor,0" ;
		serverArray[4] = "addr27_8082,收单接口服务27_8082,http://192.168.4.27:8082/addrMgr/monitor,0" ;
		serverArray[5] = "addr28_8082,收单接口服务28_8082,http://192.168.4.28:8082/addrMgr/monitor,0" ;
		serverArray[6] = "addr83_8082,收单接口服务83_8082,http://192.168.4.83:8082/addrMgr/monitor,0" ;
		serverArray[7] = "addr84_8082,收单接口服务84_8082,http://192.168.4.84:8082/addrMgr/monitor,0" ;
		serverArray[8] = "addr85_8082,收单接口服务85_8082,http://192.168.4.85:8082/addrMgr/monitor,0" ;
		serverArray[9] = "addr86_8082,收单接口服务86_8082,http://192.168.4.86:8082/addrMgr/monitor,0" ;
		serverArray[10] = "addr24_8082,接口服务24_8082,http://192.168.4.24:8082/addrMgr/monitor,0" ;
		serverArray[11] = "addr240_8082,接口服务240_8082,http://10.201.39.240:8082/addrMgr/monitor,0" ;
		serverArray[12] = "gis,底图服务,http://map4.super100.gmcc.net/arcgis/rest/services/basemaps/basegd84/MapServer/tile/11/618287/848325,1" ;
		serverArray[13] = "gis96,底图服务96,http://192.168.65.6:6080/arcgis/rest/services/basemaps/basegd84/MapServer/tile/11/618287/848325,1" ;
		serverArray[14] = "gis97,底图服务97,http://192.168.65.7:8399/arcgis/rest/services/basemaps/basegd84/MapServer/tile/11/618287/848325,1" ;
        serverArray[15] = "gis56,底图服务56,http://192.168.4.56:8399/arcgis/rest/services/basemaps/basegd84/MapServer/tile/11/618287/848325,1" ;
        serverArray[16] = "gis57,底图服务57,http://192.168.4.57:6080/arcgis/rest/services/basemaps/basegd84/MapServer/tile/11/618287/848325,1" ;
        serverArray[17] = "gis113,底图服务113,http://192.168.7.23:6080/arcgis/rest/services/basemaps/basegd84/MapServer/tile/11/618287/848325,1" ;
        serverArray[18] = "index25,地址索引25,http://192.168.4.25:8063/addrServer/,1" ;
        serverArray[19] = "index26,地址索引26,http://192.168.4.26:8063/addrServer/,1" ;
		serverArray[20] = "gis1,GIS,http://poms1.gmcc.net:8905/webgis/main.html?username=sa&sessionid=0AA4B4F365038FE6D985D92C6A9E92BF,1" ;
		serverArray[21] = "report,报表,http://poms1.gmcc.net:8084/cmrmReport/adminLogin.jsp,1" ;
		serverArray[22] = "resview5006,可视化1,http://poms1.gmcc.net:5006/ResView/main.html?username=sa&sessionid=D848A5F143B69F3C9DD4BCEB8FD37A36,1" ;
		serverArray[23] = "resview5007,可视化2,http://poms1.gmcc.net:5007/ResView/main.html?username=sa&sessionid=D848A5F143B69F3C9DD4BCEB8FD37A36,1" ;
		serverArray[24] = "webrm15,webrm15节点,http://192.168.4.15:8081/webrm,1" ;
		serverArray[25] = "webrm16,webrm16节点,http://192.168.4.16:8081/webrm,1" ;
		serverArray[26] = "webrm18,webrm18节点,http://192.168.4.18:8081/webrm,1" ;
		serverArray[27] = "webrm19,webrm19节点,http://192.168.4.19:8081/webrm,1" ; */
		//serverArray[3] = "test,测试,http://www.baidu.com,1" ;
		serverArray[0] = "addr_8080,标准地址服务_8080,http://127.0.0.1:8080/addrMgr/monitor,0" ;
		
		
	
		function checkServerInfo(url,key,type){
			
			if(type==1){
				$.ajax({
					async : true,
					cache : false,
					timeout : 30000,
					type : "POST",
					url : "<%=basePath%>monitor",
					data : {
						url:url,
						type:type
					},
					error : function(jqXHR, textStatus, errorThrown) {
						if (textStatus == "timeout") {
							$("#"+key+"_time").html("超时") ;
						} else {
							$("#"+key+"_remark").html("加载出错") ;
						}
					},
					success : function(msg) {
						var date = JSON.parse(msg) ;
						if(date.erro){
							$("#"+key+"_remark").html("加载出错") ;
						}else{
						  $("#"+key+"_time").html(date.time) ;
						}
						//$("#msgShow").html(msg);
					}
				});
			}else{
				var myDate = new Date();
				var startTimeM = myDate.getMinutes() ;//分
				var startTimeS = myDate.getSeconds() ;//秒
				var startTimeMS = myDate.getMilliseconds() ;//毫秒
				$.ajax({
					async : true,
					cache : false,
					timeout : 30000,
					type : "POST",
					url : url,
					data : {
					},
					error : function(jqXHR, textStatus, errorThrown) {
						if (textStatus == "timeout") {
							$("#"+key+"_time").html("超时") ;
						} else {
							$("#"+key+"_remark").html("加载出错") ;
						}
						var nMyDate = new Date();
						var nStartTimeM = nMyDate.getMinutes() ;//分
						var nStartTimeS = nMyDate.getSeconds() ;//秒
						var nStartTimeMS = nMyDate.getMilliseconds() ;//毫秒
						$("#"+key+"_time").html((nStartTimeM-startTimeM)*60*1000+ (nStartTimeS-startTimeS)*1000+ (nStartTimeMS-startTimeMS)) ;
					},
					success : function(msg) {
						var date = JSON.parse(msg) ;
						$("#"+key+"_maxMemory").html(date.maxMemory) ;
						$("#"+key+"_useMemory").html(date.userMemory) ;
						$("#"+key+"_maxDbPool").html(date.maxActive) ;
						$("#"+key+"_activeCount").html(date.activeCount) ;
						var nMyDate = new Date();
						var nStartTimeM = nMyDate.getMinutes() ;//分
						var nStartTimeS = nMyDate.getSeconds() ;//秒
						var nStartTimeMS = nMyDate.getMilliseconds() ;//毫秒
						$("#"+key+"_time").html( (nStartTimeM-startTimeM)*60*1000+ (nStartTimeS-startTimeS)*1000+ (nStartTimeMS-startTimeMS) ) ;
						//$("#msgShow").html(msg);
					}
				});
			}
		}
		
		function monitor(){
			clearDate() ;
			for(var i=0 ; i<serverArray.length ; i++){
				var _msg = serverArray[i].split(",")
				checkServerInfo(_msg[2],_msg[0],_msg[3]) ;
			}
		}
		
		function clearDate(){
			for(var i=0 ; i<serverArray.length ; i++){
				var _msg = serverArray[i].split(",")
				$("#"+_msg[0]+"_maxMemory").html("") ;
				$("#"+_msg[0]+"_useMemory").html("") ;
				$("#"+_msg[0]+"_maxDbPool").html("") ;
				$("#"+_msg[0]+"_activeCount").html("") ;
				$("#"+_msg[0]+"_time").html("") ;
				$("#"+_msg[0]+"_remark").html("") ;
			}
		}
		
		$(function(){
			for(var i=0 ; i<serverArray.length ; i++){
				var _msg = serverArray[i].split(",")
				var trHtml ="<tr>"+
							   "<td id='"+_msg[0]+"_serverName'>"+_msg[1]+"</td>"+
							   "<td id='"+_msg[0]+"_maxMemory'></td>"+
							   "<td id='"+_msg[0]+"_useMemory'></td>"+
							   "<td id='"+_msg[0]+"_maxDbPool'></td>"+
							   "<td id='"+_msg[0]+"_activeCount'></td>"+
							   "<td id='"+_msg[0]+"_time'></td>"+
							   "<td id='"+_msg[0]+"_remark'></td>"+
							 "</tr>" ;
				$('#msgTable').append(trHtml) ;
			}
			monitor() ;
		})
	</script>
  </head>
  
  <body>
  	<h1>WEB服务监控平台</h1>
  	<hr />
  	<table id="msgTable" border="1" cellspacing="2" cellpadding="5">
  		<tr id="msgTableTitle" style="text-align: center;background-color: #AAAAAA;font-weight: bold;">
  			<td>服务器名称</td>
  			<td>最大可用内存</td>
  			<td>已用内存</td>
  			<td>最大可用数据连接</td>
  			<td>活动数据连接</td>
  			<td>加载时间</td>
  			<td>操作/备注</td>
  		</tr>
  	</table>
    <input type="button" value="刷新" onclick="monitor()"/>
  </body>
</html>
