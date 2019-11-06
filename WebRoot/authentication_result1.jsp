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

    <title>数据保全认证页-认证结果</title>

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
         String userid = String.valueOf(request.getSession().getAttribute("userinfor_id"));
         String filename = String.valueOf(request.getSession().getAttribute("yanzheng_filename"));
         String securitytime = String.valueOf(request.getSession().getAttribute("yanzheng_securitytime"));
         String temp_usergender = String.valueOf(request.getSession().getAttribute("UserGender"));
         String usergender = null;
         String CertificateCode_data = String.valueOf(request.getSession().getAttribute("yanzheng_CerCode_data"));
         String CertificateCode_time = String.valueOf(request.getSession().getAttribute("yanzheng_CerCode_time"));
         String CertificateCode_device = String.valueOf(request.getSession().getAttribute("yanzheng_CerCode_device"));
         if(temp_usergender == "M")
         {
        	 usergender="先生";
         }
         else{ usergender="女士"; }
         
         //认证时间
         String yanzheng_time = String.valueOf(request.getSession().getAttribute("yanzheng_Time"));
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
                                                                                             保全认证-认证结果
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i> 信优电子数据保全系统
                            </li>
                            <li class="active">
                                <i class="fa fa-desktop"></i> 认证结果
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

<div class="jumbotron" style="width:1310px; height:1300px;">
       

      <div class="col-lg-6" style="width:1000px; height:800px;"> 
                <div >
       
       <div class="plant_main" style="position:absolute; height:60px; width:700px; top:40px; left:100px;">
       <h1><font color="red">恭喜您，认证成功！</font></h1>  
      </div>
      <br/><br/>
      
        <div style="position:absolute; height:400px; width:600px; top:120px; left:50px;">
            <h3>本次认证生成的文件数据指纹为：</h3><br/>
             <div  style="position:absolute; height:88px; width:500px; top:40px; left:50px;">
            <h3><span style="word-break:normal; width:auto; display:block; white-space:pre-wrap;word-wrap : break-word ;overflow:hidden;"><font color="#6495ED"><%=CertificateCode_data%></font></span></h3>
            </div>
            
            <div style="position:absolute; height:400px; width:600px; top:160px; left:0px;">
            <h3>本次认证生成的文件完成时间指纹为：</h3><br/>
             <div  style="position:absolute; height:88px; width:500px; top:40px; left:50px;">
            <h3><span style="word-break:normal; width:auto; display:block; white-space:pre-wrap;word-wrap : break-word ;overflow:hidden;"><font color="#6495ED"><%=CertificateCode_time%></font></span></h3>
            </div>
            
             <div style="position:absolute; height:400px; width:600px; top:180px; left:0px;">
            <h3>本次认证生成的文件来源指纹为：</h3><br/>
             <div  style="position:absolute; height:88px; width:500px; top:40px; left:50px;">
            <h3><span style="word-break:normal; width:auto; display:block; white-space:pre-wrap;word-wrap : break-word ;overflow:hidden;"><font color="#6495ED"><%=CertificateCode_device%></font></span></h3>
            </div> 
            
            
            <br/><br/><br/><br/><br/>
            <h3>该指纹与您在<span></span>进行保全时生成的文件指纹<font color="red">相符</font>！</h3><br/>
            <h3>现本系统可为您提供专业认证：</h3><br/><br/>
            
           <div class="plant_main" style="background:url(./image/yanzheng_secc.png); position:absolute; height:296px; width:1031px; top:320px; left:0px;">
            <br/><br/>
            <div style="position:relative; left:100px;">
            <h3><span><font color="#EE4000"><%=userid%></font></span><span><%=usergender%></span>，拥有文件<span><font color="#EE4000">《<%=filename%>》</font></span>的所有权。</h3>
            <h3>该权利自<span><font color="#EE4000"><%=securitytime%></font></span>在本平台上进行保全起便具有法律效益。</h3>
            <h3>该认证的解释权归数据保全平台所有。</h3><br/>
            <h4>认证时间：<span><%=yanzheng_time%></span></h4>
            </div>
           </div>
        </div>
      
      
      
      
      

    </div> </div>
    
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
