<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ page import="model.*" %>
<%@ page import="controller.*" %>
<%@ page import="library.*" %>
<%@ page import="java.sql.Timestamp" %>
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

    <title>数据保全认证页</title>

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
       
       String login_userid = String.valueOf(request.getSession().getAttribute("UserLogin_userID"));
       String userid = String.valueOf(request.getSession().getAttribute("YanZhengMiddle_userid"));
       String setime_str = String.valueOf(request.getSession().getAttribute("YanZhengMiddle_setime")); 
       Timestamp setime = Timestamp.valueOf(setime_str);
       String filetype = String.valueOf(request.getSession().getAttribute("YanZhengMiddle_FileType"));
       
       session.setAttribute("YanZhengMiddle_userid",userid);
       session.setAttribute("YanZhengMiddle_setime",setime);
       session.setAttribute("YanZhengMiddle_FileType",filetype);
       
       
       
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
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                </li>
                <li class="dropdown">
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <%=login_userid%> <b class="caret"></b></a>
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
                    <li class="active">
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
                                                                                             保全认证
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i> 电子数据保全系统
                            </li>
                            <li class="active">
                                <i class="fa fa-desktop"></i> 保全认证页面
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

<div class="jumbotron">
       
      <div class="col-lg-6" style="width:1500px;"> 
       <h3>第二步，请选择您需要的认证类型并上传文件：</h3>  
      </div>
      <br/><br/>
      
       <div  style="width:1600px; text-align:left">
     <form class="form-horizontal" action="YanZheng_notURL" method="post" name="submitform" enctype="multipart/form-data">
     
     <br/><br/>

  
  
            <div class="form-group">
    <label class="col-sm-2 control-label"><u>可供您选择的认证类型有：</u></label> <br/>
    <div class="col-sm-4">
      		<div class="input-group" style="width:700px">
  				<input type="radio" style="margin-left:60px; margin-top:8px;" name="check_AuthenticationType" value="only_data" />只认证数据
                <br/><br/>
                <input type="radio" style="margin-left:60px; margin-top:8px;" name="check_AuthenticationType" value="only_time" />只认证原文
                <br/><br/>
                <input type="radio" style="margin-left:60px; margin-top:8px;" name="check_AuthenticationType" value="only_device" />只认证源文
                <br/><br/>
                <input type="radio" style="margin-left:60px; margin-top:8px;" name="check_AuthenticationType" value="time_and_device" />认证原文与源文
                <br/><br/>
                <input type="radio" style="margin-left:60px; margin-top:8px;" name="check_AuthenticationType" value="authenticate_all" />全部认证
			</div>
    </div>
  </div>
  <br/><br/>
  
      <div class="form-group">
    <label class="col-sm-2 control-label"><u>上传文件：</u></label>
    <div class="col-sm-4">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="file" class="form-control" aria-describedby="sizing-addon2" name="check_File"  enctype="multipart/form-data" maxlength="100">
			</div>
    </div>
  </div>
  <br/>
  
  
 
   <div class="form-group">
    <div class="col-sm-offset-5 col-sm-10" style="margin-left:400px">
    
       <button type="submit" class="btn btn-default btn-lg btn-primary">认证</button>
      <br/><br/><br/><br/><br/><br/>

    </div>
  </div>
</form>
       
       
       
       
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
