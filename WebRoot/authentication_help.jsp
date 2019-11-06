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

    <title>数据保全认证页-帮助</title>

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
                    <li class="active">
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
                                                                                             保全认证-帮助
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i> 信优电子数据保全系统
                            </li>
                            <li class="active">
                                <i class="fa fa-desktop"></i> 帮助
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

<div class="jumbotron" style="width:1310px; height:1200px;">
       

      <div class="col-lg-6" style="width:1000px; height:650px;"> 

<p><center><h2>帮助</h2></center></p>
<hr style="height:5px;border:none;border-top:5px groove skyblue;" />

<table border="1px" cellspacing="0px" bordercolor="#87CEEB">   
<tr>            
  <th>关于本平台</th>            
  <td>在本平台您可以使用您以前做文件保全所得到的证书和相应的文件进行认证，如认证通过，您将获得与本系统<br/>
      合作的相关权威部门给出的您对您提交文件所有权的认证。我们给出的认证中会包括您进行保全的具体时间，拥<br/>
      有这个“xx用户在xx月xx日xx时xx分xx秒对xx文件拥有绝对的所有权”的认证，您就可以较轻松地处理一切对您的这<br/>
      份文件有侵权行为的事件。您需要在下方上传要进行认证的文件，上传好文件后，点击【认证】按钮就可以进行<br/>
      认证了，认证只需等待一会儿就可以了。 如果进行认证时，平台上显示的您的ID或文件名出现错误，请通过左<br/>
      边导航栏中【问题反映】来联系管理员解决问题。</td>         
</tr> 


<tr>            
  <th>保全认证</th>
  <td>左边导航栏中的“保全认证”栏对应的是本平台的手动保全认证。您可能由于无法扫描二维码、电子文件不在手<br/>
      机上等等一些原因无法使用二维码认证通道，所以本系统为您准备了手动认证通道。在“保全认证”栏中，您需<br/>
      要手动输入您的ID、文件名称、保全时间，并选择文件类型、上传文件。需要注意的是您输入的信息必须与您<br/>
      手中持有的保全数据时得到的证书上的信息一致，否则无法正确完成保全认证。<br/>
      手动输入保全时间时，格式为：yyyy-mm-dd hh:mm:ss.SSS。</td>        
</tr>         
<tr>            
  <th>保全认证-二维码通道</th>            
  <td>保全认证-二维码通道这个页面只能由您扫描二维码来进入，其他方式不能进入。<br/>
       在进行认证时，您的ID、文件名和保全时间都是在您做出认证申请时自动传递给系统后台的， 您不能进行修改，<br/>
       这样是为了提高认证的可靠性。 您需要在下方上传要进行认证的文件，上传好文件后，点击【认证】按钮就可以<br/>
       进行认证了，认证只需等待一会儿就可以了。 如果进行认证时，平台上显示的您的ID或文件名出现错误，请通<br/>
       过上方导航栏的【信息有误通道】来联系管理员解决问题。</td>         
</tr>         
<tr>            
  <th>问题反映</th>            
  <td>问题反映页面是您在进行保全认证操作中发现系统 给出的基本信息有误时需要进入的页面。在该页面中您<br/>
        可以向管理员发送邮件来说明您遇到的问题，我们的工作人员会尽快解决您的问题。</td>         
</tr>        
<tr>            
  <th>认证结果</th>  
  <td>认证结果页面只能在您进行保全认证后自动跳转到达，其他方式不能进入。这个页面给出您此次保全认证<br/>
       的结果。如果页面显示“认证成功”，您就可以得到本平台和第三方权威机构联合给出的证明，这个证明将<br/>
       会保障您的权益。</td>         
</tr>  
</table>

    
     </div>
      
      
      
      
      
       <div class="col-lg-6" style="width:1000px; height:400px;"> 

<p><center><h2>收费标准</h2></center></p>
<hr style="height:5px;border:none;border-top:5px groove skyblue;" />

<table border="1px" cellspacing="0px" bordercolor="#87CEEB">
<tr>
<th>商品名称</th>
<th>商品介绍</th>
<th>商品价格（元）</th>
</tr>
<tr>
<td>数据保全1</td>
<td>对数据进行保全2</td>
<td>10.00</td>
</tr>
<tr>
<td>数据保全2</td>
<td>对数据进行保全2</td>
<td>20.00</td>
</tr>
</table>

    
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
