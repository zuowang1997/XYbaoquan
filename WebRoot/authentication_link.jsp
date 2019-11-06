<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ page import="model.*" %>
<%@ page import="controller.*" %>
<%@ page import="library.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">

<head>

    <base href="<%=basePath%>">

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>数据保全认证页-更多链接</title>

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" type="text/css" href="./NewCssandJs/bootstrap.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="./NewCssandJs/sb-admin.css">

    <!-- Morris Charts CSS -->
    <link rel="stylesheet" type="text/css" href="./NewCssandJs/morris.css">

    <!-- Custom Fonts -->
    <link href="./NewCssandJs/font-awesome.min.css" rel="stylesheet" type="text/css">

<!-- IE 9 --> 
     <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>  
     <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script> 


 <script language="javascript" type="text/javascript">
function opendevice()
{
window.open("advice.html","","width=550,height=450") 
}
</script>


    <script language="javascript" type="text/javascript">
function queren()
{
var se=confirm("您确定要退出吗？");
if (se==true)
  {   window.location.href='authentication_Login.jsp';
      window.event.returnValue = false;  }
else
  {    }
}
</script>



</head>

<body>

    <div id="wrapper">
    
    
    <%   
               String userid = String.valueOf(request.getSession().getAttribute("UserLogin_userID"));
        %>


              <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand">电子数据保全系统</a>
            </div>
            
            
            <!-- Top Menu Items -->
 <%  if("null".equals(userid) || "".equals(userid)) {  
                %>
                <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                    <a href="./authentication_Login.jsp" class="dropdown-toggle" > 登录  &nbsp&nbsp&nbsp&nbsp</a>  
                </li>
            </ul>
            <% }  %>
                
               <% if("null".equals(userid) == false)  {%>
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                </li>
                <li class="dropdown">
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <%=userid%> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="./authentication_User.jsp"> 用户信息</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="javascript: ;" onclick="queren()"> 退出登录</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <% } %>
            
           
                       <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
               <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="./Client_Index.jsp"><i class="fa fa-fw fa-dashboard"></i>欢迎您</a>
                    </li>
                    <li>
                        <a href="./Client_Zhuce.jsp"><i class="fa fa-fw fa-bar-chart-o"></i>用户注册</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-edit"></i>数据保全</a>
                         <ul class="nav nav-second-level">
                                <li>
                                    <a href="Client_Security.jsp">数据保全</a>
                                </li>
                                <li>
                                    <a href="Client_tiandan.jsp">表单留存</a>
                                </li>
                                <li>
                                    <a href="Client_zhifu2.jsp">在线支付</a>
                                </li>
                            </ul>
                    </li>
                    <li>
                        <a href="./authentication_notQR.jsp"><i class="fa fa-fw fa-desktop"></i>保全认证</a>
                    </li>
                    <li>
                        <a href="./authentication_help.jsp"><i class="fa fa-fw fa-edit"></i>帮助页面</a>
                    </li>
                    <li class="active">
                        <a href="./authentication_link.jsp"><i class="fa fa-fw fa-wrench"></i>更多链接</a>
                    </li>
                    <li>
                        <a href="./authentication_quali.jsp"><i class="fa fa-fw fa-wrench"></i>相关资质</a>
                    </li>
                    <li>
                        <a href="./authentication_User.jsp"><i class="fa fa-fw fa-wrench"></i>用户信息查询</a>
                    </li>
                    <li>
                        <a href="./authentication_email.jsp"><i class="fa fa-fw fa-edit"></i>问题反映</a>
                    </li>
                    <li>
                        <a href="./Client_Download.jsp"><i class="fa fa-fw fa-desktop"></i>客户端下载</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                                                                                             保全认证-更多链接
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i> 信优电子数据保全系统
                            </li>
                            <li class="active">
                                <i class="fa fa-desktop"></i> 更多链接
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

<div class="jumbotron" style="width:1310px; height:800px;">
       

      <div class="col-lg-6" style="width:1000px; height:800px;"> 
                           <div class="row">
                    <div class="col-sm-4">
                        <div class="panel panel-red">
                            <div class="panel-heading">
                                <h3 class="panel-title"><a href="http://www.ntsc.ac.cn/">中国科学院国家授时中心</a></h3>
                            </div>
                            <div class="panel-body">
                                                                                                        中国最权威的授时机构。
                            </div>
                        </div>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">权威第三方CA</h3>
                            </div>
                            <div class="panel-body">
                                                                                                       第三方机构CA为本平台提供权威性。
                            </div>
                        </div>
                    </div>
                    <!-- /.col-sm-4 -->
                    <div class="col-sm-4">
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <h3 class="panel-title"><a href="./login.jsp">数据保全系统管理员页</a></h3>
                            </div>
                            <div class="panel-body">
                                                                                                        后台管理员管理系统时使用的前端页面。
                            </div>
                        </div>
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title"><a href="http://www.mydatetime.info/">当前时间核对</a></h3>
                            </div>
                            <div class="panel-body">
                                                                                                       为您提供较精确的当前时间。
                            </div>
                        </div>
                    </div>
                    <!-- /.col-sm-4 -->
                    <div class="col-sm-4">
                        <div class="panel panel-warning">
                            <div class="panel-heading">
                                <h3 class="panel-title"><a href="javascript: ;" onclick="opendevice()">给系统提建议</a></h3>
                            </div>
                            <div class="panel-body">
                                                                                                        向管理员发邮件提供建议
                            </div>
                        </div>
                        <div class="panel panel-danger">
                            <div class="panel-heading">
                                <h3 class="panel-title">更多链接...</h3>
                            </div>
                            <div class="panel-body">
                                                                                                           更多链接...
                            </div>
                        </div>
                    </div>
                    <!-- /.col-sm-4 -->
                </div>
 
    
     </div>
      
      
      
      
      

  </div>




                </div>
              </div> 

    <div style="position:relative; bottom:0px; text-align:center;"><a href="http://www.miitbeian.gov.cn/">平台备案———京ICP备16042651号</a></div>

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="./NewCssandJs/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="./NewCssandJs/bootstrap.min.js"></script>


</body>

</html>
