<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ page import="model.*" %>
<%@ page import="controller.*" %>
<%@ page import="library.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">

<head>

    <base href="<%=basePath%>">

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <META content="MSHTML 6.00.2800.1106" name=GENERATOR>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    

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


<script language="JScript" event="OnCompleted(hResult,pErrorObject, pAsyncContext)" for=foo>
 document.forms[0].file_sourcenumber.value=unescape(MACAddr);
 document.forms[0].txtIPAddr.value=unescape(IPAddr);
 document.forms[0].txtDNSName.value=unescape(sDNSName);
 //document.formbar.submit();
  </script>
  
  <script language="JScript" event=OnObjectReady(objObject,objAsyncContext) for=foo>
   if(objObject.IPEnabled != null && objObject.IPEnabled != "undefined" && objObject.IPEnabled == true)
   {
    if(objObject.MACAddress != null && objObject.MACAddress != "undefined")
    MACAddr = objObject.MACAddress;
    if(objObject.IPEnabled && objObject.IPAddress(0) != null && objObject.IPAddress(0) != "undefined")
    IPAddr = objObject.IPAddress(0);
    if(objObject.DNSHostName != null && objObject.DNSHostName != "undefined")
    sDNSName = objObject.DNSHostName;
    }
  </script>
  
  
  

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




<script language=javascript>
function fuxuankuang1()
{
  var aa = document.getElementsByName("security_type");
  alert("该项为必选项！");
  aa[0].checked = true;
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
                    <li>
                        <a href="./Client_Zhuce.jsp"><i class="fa fa-fw fa-bar-chart-o"></i>用户注册</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-edit"></i>数据保全</a>
                         <ul class="nav nav-second-level">
                                <li>
                                    <a href="Client_Security.jsp">数据保全</a>
                                </li>
                                <li class="active">
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
                                <i class="fa fa-desktop"></i> 表单留存
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

<div class="jumbotron" style="width:1310px; height:1000px;">
       

      <div class="col-lg-6" style="width:1000px; height:1000px;"> 



<div class="wrap"> 
     <p><h3>请填写以下表单信息进行备案：</h3></p>
     <br/>
     <br/>
     <form name="tiandan" action="security_tiandan" method="post" enctype="multipart/form-data">
     
     
  
  
  <div class="form-group">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4><font color="red">*</font>货物名称：</h4></u></label>
    <div class="col-sm-4">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="tiandan_name" />
			</div>
    </div>
  </div>
  <br/><br/><br/>
  
    <div class="form-group">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4><font color="red">*</font>货物品牌：</h4></u></label>
    <div class="col-sm-4">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="tiandan_brand" />
			</div>
    </div>
  </div>
  <br/><br/><br/>
  
      <div class="form-group">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4><font color="red">*</font>货物生产时间：</h4></u></label>
    <div class="col-sm-4">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="tiandan_protime" />
			</div>
    </div>
  </div>
  <br/><br/><br/>
  
  
  
        <div class="form-group">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4><font color="red">*</font>货物型号1：</h4></u></label>
    <div class="col-sm-4" style="width:220px">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="tiandan_version1" style="width:130px"/>
			</div>
    </div>
    
     <label class="col-sm-2 control-label" style="width:100px"><u><h4><font color="red">*</font>数量：</h4></u></label>
    <div class="col-sm-4" style="width:180px">
      		<div class="input-group">
  				<input type="text" class="form-control" name="tiandan_number1" style="width:110px"/>
			</div>
    </div>
    
     <label class="col-sm-2 control-label" style="width:100px"><u><h4><font color="red">*</font>单位：</h4></u></label>
    <div class="col-sm-4" style="width:190px">
      		<div class="input-group">
  				<select class="form-control" style="width:90px" name="tiandan_unit1">
  				   <option value="0">个</option>
  				   <option value="1">片</option>
  				   <option value="2">斤</option>
  				   <option value="3">米</option>
  				</select>
			</div>
    </div>
  </div>
       <br/><br/><br/>
       
       
       
        <div class="form-group">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4><font color="red">*</font>货物型号2：</h4></u></label>
    <div class="col-sm-4" style="width:220px">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="tiandan_version2" style="width:130px"/>
			</div>
    </div>
    
     <label class="col-sm-2 control-label" style="width:100px"><u><h4><font color="red">*</font>数量：</h4></u></label>
    <div class="col-sm-4" style="width:180px">
      		<div class="input-group">
  				<input type="text" class="form-control" name="tiandan_number2" style="width:110px"/>
			</div>
    </div>
    
     <label class="col-sm-2 control-label" style="width:100px"><u><h4><font color="red">*</font>单位：</h4></u></label>
    <div class="col-sm-4" style="width:190px">
      		<div class="input-group">
  				<select class="form-control" style="width:90px" name="tiandan_unit2">
  				   <option value="0">个</option>
  				   <option value="1">片</option>
  				   <option value="2">斤</option>
  				   <option value="3">米</option>
  				</select>
			</div>
    </div>
  </div>
       <br/><br/><br/>
       
       
       
        <div class="form-group">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4><font color="red">*</font>货物型号3：</h4></u></label>
    <div class="col-sm-4" style="width:220px">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="tiandan_version3" style="width:130px"/>
			</div>
    </div>
    
     <label class="col-sm-2 control-label" style="width:100px"><u><h4><font color="red">*</font>数量：</h4></u></label>
    <div class="col-sm-4" style="width:180px">
      		<div class="input-group">
  				<input type="text" class="form-control" name="tiandan_number3" style="width:110px"/>
			</div>
    </div>
    
     <label class="col-sm-2 control-label" style="width:100px"><u><h4><font color="red">*</font>单位：</h4></u></label>
    <div class="col-sm-4" style="width:190px">
      		<div class="input-group">
  				<select class="form-control" style="width:90px" name="tiandan_unit3">
  				   <option value="0">个</option>
  				   <option value="1">片</option>
  				   <option value="2">斤</option>
  				   <option value="3">米</option>
  				</select>
			</div>
    </div>
  </div>
       <br/><br/><br/><br/><br/><br/>
       
       
       
  
  

  


  
     <div class="form-group">
    <div class="col-sm-offset-5 col-sm-10" style="margin-left:200px">
    
      <input type="hidden" name="userid" value="<%%>"/>
       <button type="submit" class="btn btn-default btn-lg btn-primary">确认留存</button>
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

