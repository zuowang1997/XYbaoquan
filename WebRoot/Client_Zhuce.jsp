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

    <title>信优数据保全客户端</title>

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
                <a class="navbar-brand">信优数据保全系统——网页客户端</a>
            </div>
                       
           
                       <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
               <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="./Client_Index.jsp"><i class="fa fa-fw fa-dashboard"></i>欢迎您</a>
                    </li>
                    <li class="active">
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
                                                                                             信优数据保全系统客户端
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i> 信优客户端
                            </li>
                            <li class="active">
                                <i class="fa fa-desktop"></i> 用户注册
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

<div class="jumbotron" style="width:1310px; height:1000px;">
       

      <div class="col-lg-6" style="width:1000px; height:1000px;"> 



<div class="wrap"> 


     <form class="form-horizontal" action="AddUser" method="POST">
  <div class="form-group">
  <br/>
  	<h3 class="form-signin-heading text-warning">填写用户信息：</h3>
  </div>
  <div class="form-group has-warning">  
  <label for="inputUserId3" class="col-sm-2 control-label text-primary"></label>
      <div class="col-sm-4">
      </div>
  </div>
 <div class="form-group has-warning">
    <label for="inputUserID3" class="col-sm-2 control-label text-primary"><u>用户ID</u></label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputUserID1" placeholder="请输入用户ID" name="UserID">
    </div>
    </div>
  <div class="form-group has-warning">
    <label for="inputUserAge3" class="col-sm-2 control-label text-primary"><u>用户年龄</u></label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputUserAge1" placeholder="请输入用户年龄(数字)" name="UserAge">
    </div>
  </div>
        <div class="form-group has-warning">
    <label for="inputUserGender3" class="col-sm-2 control-label text-primary"><u>用户性别</u></label>
    <div class="col-sm-4">  
  <input type="text" class="form-control" placeholder="M-男性 / F-女性" id="inputUserGender1" name="UserGender">
    </div>
  </div>
     <div class="form-group has-warning">
    <label for="inputUserOccupation3" class="col-sm-2 control-label text-primary"><u>用户职业</u></label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputUserOccupation1" placeholder="请输入用户职业" name="UserOccupation">
    </div>
  </div>
       <div class="form-group has-warning">
    <label for="inputUserTelephone3" class="col-sm-2 control-label text-primary"><u>用户电话</u></label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputUserTelephone1" placeholder="请输入11位手机" name="UserTelephone">
    </div>
  </div>
       <div class="form-group has-warning">
    <label for="inputUserEmail3" class="col-sm-2 control-label text-primary"><u>用户邮箱</u></label>
    <div class="col-sm-4">
      <input type="email" class="form-control" id="inputUserEmail1" placeholder="请用正确格式输入用户邮箱" name="UserEmail">
    </div>
  </div>
       <div class="form-group has-warning">
    <label for="inputRegistrationTime3" class="col-sm-2 control-label text-primary"><u>注册时间（系统自动生成）</u></label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputRegistrationTime1" placeholder="注册时间自动生成为当前时间，不允许自定义" name="RegistrationTime" readonly="readonly">
    </div>
  </div>
       <div class="form-group has-warning">
    <label for="inputUserLevel3" class="col-sm-2 control-label text-primary"><u>用户等级（初始为1）</u></label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputUserLevel1" placeholder="请输入用户等级" name="UserLevel" value="1" readonly="readonly">
    </div>
  </div>
         <div class="form-group has-warning">
    <label for="inputUserPassword3" class="col-sm-2 control-label text-primary"><u>用户密码</u></label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputUserPassword1" placeholder="请输入用户等级" name="UserPassword">
    </div>
  </div>
  
  <div class="form-group has-warning">
    <div class="col-sm-offset-2 col-sm-4">
      <div class="checkbox">
        <label>
          <input type="checkbox">确认添加（请在确认无误后勾选此项）
        </label>
      </div>
    </div>
  </div>
  <br/>
   <div class="form-group has-warning">
    <div class="col-sm-offset-5 col-sm-10" style="margin-left:170px">
      <button type="submit" class="btn btn-default btn-lg btn-warning">注册</button>
      <br/><br/><br/><br/><br/><br/>
    </div>
  </div>
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

