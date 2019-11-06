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
function fuxuankuang2()
{
  var aa = document.getElementsByName("security_type");
      if(aa[2].checked) {
      if(confirm("如需进行原保全，请下载客户端！\n要现在下载客户端吗？"))
    	    {
    	    location.href="Client_Download.jsp";
    	    }
       else
    	    {
    	     aa[2].checked = false;
    	    }
      }
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


<object id=locator classid=CLSID:76A64158-CB41-11D1-8B02-00600806D9B6 VIEWASTEXT></object>
<object id=foo classid=CLSID:75718C9A-F029-11d1-A1AC-00C04FB6C223></object>
   <script language="JScript">
       var service = locator.ConnectServer();
       var MACAddr ;
       var IPAddr ;
       var DomainAddr;
       var sDNSName;
       service.Security_.ImpersonationLevel=3;
       service.InstancesOfAsync(foo, 'Win32_NetworkAdapterConfiguration');
   </script>
   
   
   
   <script type="text/javascript">
   function BaoQuan(){  
         document.getElementById('testsecurity').action = "UploadFile";  
         document.getElementById("testsecurity").submit();  
     }  
      
     function ZhiFu(){  
    	 document.getElementById('testsecurity').action = "ZhiFutiaozhuan";  
         document.getElementById("testsecurity").submit();  
     } 
     </script>
   





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
                                <li class="active">
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
                                <i class="fa fa-desktop"></i> 数据保全
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

<div class="jumbotron" style="width:1310px; height:1000px;">
       

      <div class="col-lg-6" style="width:1000px; height:1000px;"> 



<%   
              //##########################################
               //保全表格，如果未支付
               String zhifu_result = null;
               zhifu_result = String.valueOf(request.getSession().getAttribute("ZhiFu_Result"));
               if(!(zhifu_result.equals("secsess"))){
         
        %>


<div class="wrap"> 
     <p><h3>请填写以下信息以完成保全：</h3></p>
     <br/>
     <p><h4><font color="red">提示：</font>若您还未付款，请填写好信息后点击“去支付”按钮，完成付款后方能进行保全。</h4></p>
     <p><h4>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp带*符号为必填项。如果您选择的保全类型中有“源保全”，请使用IE浏览器。</h4></p>
     <br/>
     <form name="testsecurity" id="testsecurity" action="" method="post" enctype="multipart/form-data">
     
     
  
  
  <div class="form-group">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4><font color="red">*</font>用户ID：</h4></u></label>
    <div class="col-sm-4">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="security_id" />
			</div>
    </div>
  </div>
  <br/><br/><br/>
  
  
  
    <div class="form-group" style="height:40px">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4><font color="red">*</font>选择保全类型：</h4></u></label>
      <div class="col-sm-4">
      		<div class="input-group" style="width:800px;">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				&nbsp&nbsp&nbsp<input type="checkbox"  name="security_type" value="data" checked ='checked' onclick="fuxuankuang1()" /><font size="4">&nbsp&nbsp数据保全（必选）</font><br/>
  				&nbsp&nbsp&nbsp<input type="checkbox"  name="security_type" value="dataanddevice" /><font size="4">&nbsp&nbsp源保全</font><br/>
  				&nbsp&nbsp&nbsp<input type="checkbox"  name="security_type" value="dataandtime" onclick="fuxuankuang2()"/><font size="4">&nbsp&nbsp原保全</font><br/>
			</div>
    </div>
   </div>
  <br/><br/><br/>
  
  
  
  
      <div class="form-group">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4><font color="red">*</font>待保全文件：</h4></u></label>
    <div class="col-sm-4">
      		<div class="input-group" style="width:500px;">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="file" class="form-control" enctype="multipart/form-data" name="upload_wordFile" maxlength="1000">
  			</div>
    </div>
  </div>
  <br/><br/><br/>
  
  
  
  
  <div class="form-group">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4>证书所有人：</h4></u></label>
    <div class="col-sm-4">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="certificate_holder"/>
			</div>
    </div>
  </div>
  <br/><br/>
  
  
  <div class="form-group">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4>所有人单位：</h4></u></label>
    <div class="col-sm-4">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="security_source"/>
			</div>
    </div>
  </div>
  <br/><br/>
  


  <div class="form-group">
    <!--<label class="col-sm-2 control-label" style="width:180px"><u><h4>来源设备号：</h4></u></label>-->
    <div class="col-sm-4">
      		<div class="input-group">
  				<input type="hidden" class="form-control" name="file_sourcenumber"  readonly="readonly" />
			</div>
    </div>
  </div>
  <br/><br/>
  
  <%   
             }
           else{  }
        %>
        
        
        
        
       <%   
           //##########################################
           //保全表格，如果已支付
           if(zhifu_result.equals("secsess")){
            	   
            //接受参数
            String userID = String.valueOf(request.getSession().getAttribute("security_id"));
		    String filename = null;
		    String securitysource = String.valueOf(request.getSession().getAttribute("security_source"));
		    String certificateholder = String.valueOf(request.getSession().getAttribute("certificate_holder"));
		    String securitytype = String.valueOf(request.getSession().getAttribute("security_type"));
		    String filepath = String.valueOf(request.getSession().getAttribute("security_filepath"));
		    
         
        %>
        <div class="wrap"> 
     <p><h3>请填写以下信息以完成保全：</h3></p>
     <br/>
     <p><h4><font color="red">提示：</font>您已完成付款，现在可以直接点击【开始保全】按钮保全您的数据。</h4></p>
     <br/>
     <form name="testsecurity" id="testsecurity" action="" method="post" enctype="multipart/form-data">
     
     
  
  
  <div class="form-group">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4><font color="red">*</font>用户ID：</h4></u></label>
    <div class="col-sm-4">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="security_id" value="<%=userID%>" readonly="readonly" />
			</div>
    </div>
  </div>
  <br/><br/><br/>
  
  
  
  
  <% if(securitytype.equals("data")){ %>
    <div class="form-group" style="height:40px">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4><font color="red">*</font>选择保全类型：</h4></u></label>
      <div class="col-sm-4">
      		<div class="input-group" style="width:800px;">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				&nbsp&nbsp&nbsp<input type="checkbox"  name="security_type" value="data" checked ='checked' onclick="fuxuankuang1()" disabled="disabled" /><font size="4">&nbsp&nbsp数据保全（必选）</font><br/>
  				&nbsp&nbsp&nbsp<input type="checkbox"  name="security_type" value="dataanddevice" disabled="disabled" /><font size="4">&nbsp&nbsp源保全</font><br/>
  				&nbsp&nbsp&nbsp<input type="checkbox"  name="security_type" value="dataandtime" onclick="fuxuankuang2()"/><font size="4">&nbsp&nbsp原保全</font><br/>
			</div>
    </div>
   </div>
  <br/><br/><br/>
  <% } 
   else { %>
    <div class="form-group" style="height:40px">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4><font color="red">*</font>选择保全类型：</h4></u></label>
      <div class="col-sm-4">
      		<div class="input-group" style="width:800px;">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				&nbsp&nbsp&nbsp<input type="checkbox"  name="security_type" value="data" checked ='checked' onclick="fuxuankuang1()" disabled="disabled" /><font size="4">&nbsp&nbsp数据保全（必选）</font><br/>
  				&nbsp&nbsp&nbsp<input type="checkbox"  name="security_type" value="dataanddevice" checked ='checked' disabled="disabled" /><font size="4">&nbsp&nbsp源保全</font><br/>
  				&nbsp&nbsp&nbsp<input type="checkbox"  name="security_type" value="dataandtime" onclick="fuxuankuang2()"/><font size="4">&nbsp&nbsp原保全</font><br/>
			</div>
    </div>
   </div>
  <br/><br/><br/>
  <% } %>
  
  
  
  
  
      <div class="form-group">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4><font color="red">*</font>待保全文件：</h4></u></label>
    <div class="col-sm-4">
      		<div class="input-group" style="width:500px;">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="upload_wordFile_fake" maxlength="1000" value="<%=filepath%>" readonly="readonly" >
  			</div>
    </div>
  </div>
  <br/><br/><br/>
  
  
  
  
  <div class="form-group">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4>证书所有人：</h4></u></label>
    <div class="col-sm-4">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="certificate_holder" value="<%=certificateholder%>" readonly="readonly" />
			</div>
    </div>
  </div>
  <br/><br/>
  
  
  <div class="form-group">
    <label class="col-sm-2 control-label" style="width:180px"><u><h4>所有人单位：</h4></u></label>
    <div class="col-sm-4">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="security_source" value="<%=securitysource%>" readonly="readonly"/>
			</div>
    </div>
  </div>
  <br/><br/>
  


  <div class="form-group">
    <!--<label class="col-sm-2 control-label" style="width:180px"><u><h4>来源设备号：</h4></u></label>-->
    <div class="col-sm-4">
      		<div class="input-group">
  				<input type="hidden" class="form-control" name="file_sourcenumber"  readonly="readonly" />
			</div>
    </div>
  </div>
  <br/><br/>
          <%   
               }
               else{  }
        %>
        
        
        
  


  
       <%   
              //##########################################
               //按钮，如果已支付
               zhifu_result = String.valueOf(request.getSession().getAttribute("ZhiFu_Result"));
               if(zhifu_result.equals("secsess")){
         
        %>
  
     <div class="form-group">
    <div class="col-sm-offset-5 col-sm-10" style="margin-left:200px">
    
      <input type="hidden" name="userid" value="<%%>"/>
       <button type="submit" onclick="BaoQuan()" class="btn btn-default btn-lg btn-primary">开始保全</button>
      <br/><br/><br/><br/><br/><br/>

    </div>
  </div>
  
  <%   
               }
               else{  } 
         
        %>
        
        
        
               <%   
             //##########################################
               //按钮，如果未支付
               if(!(zhifu_result.equals("secsess"))){
         
        %>
  
     <div class="form-group">
    <div class="col-sm-offset-5 col-sm-10" style="margin-left:200px">
    
      <input type="hidden" name="userid" value="<%%>"/>
       <button type="submit"  onclick="ZhiFu()" class="btn btn-default btn-lg btn-primary">去支付</button>
      <br/><br/><br/><br/><br/><br/>

    </div>
  </div>
  
  <%       }
       else{  }
         
        %>
   
        
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

