<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录失败</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="JavaScript1.2" type="text/javascript">
		function delayURL(url) {
			var delay = document.getElementById("time").innerHTML;
			if(delay > 0) {
			   delay--;
			   document.getElementById("time").innerHTML = delay;
			} else {
			   window.location.href = url;
			    }
			    setTimeout("delayURL('" + url + "')", 1000);
		}
	</script>

    <style type="text/css">

body {    
 margin:0;    
 padding:0;   
 background-color:#BCD2EE; 
 text-align:center;
 }
 
 </style> 





  </head>
  
  <body>
    <p><h1>登录失败, 请确认您的用户名与密码正确。</h1></p>
     <p><h1>将在 <span id="time">5</span> 秒后返回登录页面。</h1></p><br>
  	  <script type="text/javascript">  
		delayURL("authentication_Login.jsp"); 
	  </script>
  </body>
</html>
