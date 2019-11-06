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

    <title>数据保全认证页-问题反映</title>

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
                    <li>
                        <a href="./authentication_link.jsp"><i class="fa fa-fw fa-wrench"></i>更多链接</a>
                    </li>
                    <li>
                        <a href="./authentication_quali.jsp"><i class="fa fa-fw fa-wrench"></i>相关资质</a>
                    </li>
                    <li>
                        <a href="./authentication_User.jsp"><i class="fa fa-fw fa-wrench"></i>用户信息查询</a>
                    </li>
                    <li class="active">
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
                                                                                             保全认证-问题反映
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i> 信优电子数据保全系统
                            </li>
                            <li class="active">
                                <i class="fa fa-desktop"></i> 问题反映
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

<div class="jumbotron" style="width:1310px; height:800px;">
       

      <div class="col-lg-6" style="width:1000px; height:800px;"> 
       <h3>如您在进行认证的过程中出现问题，请确认您的以下几项操作：</h3>  
       <br/>
       <h4><font color="#EE7942">☻ 按照正确的流程对文件进行了保全</font></h4>  
       <h4><font color="#EE7942">☻ 认证文件类型（文字/图片）选择正确</font></h4> 
       <h4><font color="#EE7942">☻ 保全后，未对收到的电子证书进行任何人为修改</font></h4>
       <h4><font color="#EE7942">☻ 认证时，上传的文件名称与相关证书上一致</font></h4>
       <h4><font color="#EE7942">☻ 认证时使用的是与本次认证操作所对应的证书</font></h4>
     
      <br/><br/><br/>
      
       <div style="position:absolute; height:60px; width:700px; top:260px; left:50px;">
       <h3>如这几项操作均没有问题，请向管理员发送邮件说明问题：</h3>  
      <br/>
        <form  class="form-horizontal" action="mailto:390316312@qq.com" method="post" enctype="text/plain">

                                                                           用户ID：<br />
                 <input type="text" name="mail_userID" value=" " size="20">
                 <br />
                                                                           真实姓名：<br />
                 <input type="text" name="mail_userName" value=" " size="20">
            <br />
                                                                           可收到回复的邮箱：<br />
                 <input type="text" name="mail_usermail" value=" " size="20">
            <br />
                                                                           情况：<br />
                 <input type="text" name="mail_situation" value=" " style="height:51px;width:300px">
            <br />
                  <input type="submit" value="发送">
                  <input type="reset" value="重置">

        </form>  
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
