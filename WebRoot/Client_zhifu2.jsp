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

    <title>数据保全认证页-在线支付</title>

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" type="text/css" href="./NewCssandJs/bootstrap.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="./NewCssandJs/sb-admin.css">

    <!-- Morris Charts CSS -->
    <link rel="stylesheet" type="text/css" href="./NewCssandJs/morris.css">

    <!-- Custom Fonts -->
    <link href="./NewCssandJs/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    

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

<script>

        var even = document.getElementById("licode");   
        var showqrs = document.getElementById("showqrs");
         even.onmouseover = function(){
            showqrs.style.display = "block"; 
         }
         even.onmouseleave = function(){
            showqrs.style.display = "none";
         }
         
         var out_trade_no = document.getElementById("out_trade_no");

         //设定时间格式化函数
         Date.prototype.format = function (format) {
               var args = {
                   "M+": this.getMonth() + 1,
                   "d+": this.getDate(),
                   "h+": this.getHours(),
                   "m+": this.getMinutes(),
                   "s+": this.getSeconds(),
               };
               if (/(y+)/.test(format))
                   format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
               for (var i in args) {
                   var n = args[i];
                   if (new RegExp("(" + i + ")").test(format))
                       format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
               }
               return format;
           };
           
         out_trade_no.value = 'test'+ new Date().format("yyyyMMddhhmmss");
 
</script>


<script language=javascript>
function shuaxin()
{
	window.location.reload();
}
</script>





</head>

<body>

    <div id="wrapper">
    
    
    <%   
               String userid = String.valueOf(request.getSession().getAttribute("UserLogin_userID"));
    
               String OrderNumber = String.valueOf(request.getSession().getAttribute("ZhiFu_OrderNumber"));
               String GoodName = String.valueOf(request.getSession().getAttribute("ZhiFu_GoodName"));
               String GoodDdescription = String.valueOf(request.getSession().getAttribute("ZhiFu_GoodDdescription"));
               
               OrderNumber = "这里是订单号";
               GoodName = "这里是商品名称";
               GoodDdescription = "这里是商品描述";
               
               //接收等级和钱数
               String SecurityLevel = String.valueOf(request.getSession().getAttribute("ZhiFu_level"));
               String NeedtoPay = String.valueOf(request.getSession().getAttribute("ZhiFu_money"));
               //钱数补零
               NeedtoPay = NeedtoPay + "0";
               
               String zhifu_result = null;
               zhifu_result = String.valueOf(request.getSession().getAttribute("ZhiFu_Result"));
               if(zhifu_result.equals("secsess")){
            	   response.sendRedirect("Client_Security.jsp");
               }
               else {   }
               
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
                                <li class="active">
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
                                                                                             保全认证-在线支付
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i> 信优电子数据保全系统
                            </li>
                            <li class="active">
                                <i class="fa fa-desktop"></i> 在线支付
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

<div class="jumbotron" style="width:1310px; height:800px;">
       


        <div class="form-group">
        <form action="alipayapi.jsp" class="alipayform" method="POST" target="_blank">
           
            <div class="element" style="margin-top:0px;">
                <div><p><h3>支付宝交易快速通道 </h3></p></div>
            </div>
            
            
            <div class="element" style="margin-top:30px;">
                <div><p><h5><font color="#919191">*本系统目前仅提供支付宝付款功能。请确认信息，并完成支付 </font></h5></p></div>
                <div><p><h5><font color="#919191">*您可以在【帮助页面】处查询到本平台各种服务的详细收费标准</font></h5></p></div>
            </div>
            <br/><br/><br/>
            
            
              <div class="form-group">
           <label class="col-sm-2 control-label" style="width:150px"><u><h4>订单号:</h4></u></label>
            <div class="col-sm-4">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="WIDout_trade_no" id="out_trade_no" value="<%=OrderNumber%>" />
			</div>
          </div>
           </div>
             <br/><br/><br/>
            
            
            
             <div class="form-group">
           <label class="col-sm-2 control-label" style="width:150px"><u><h4>商品名称:</h4></u></label>
            <div class="col-sm-4">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="WIDsubject" value="<%=GoodName%>" readonly="true" />
			</div>
          </div>
           </div>
             <br/><br/><br/>
             
             
             
             <div class="form-group">
            <label class="col-sm-2 control-label" style="width:150px"><u><h4>商品等级:</h4></u></label>
            <div class="col-sm-4">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="WIDsubject_level" value="<%=SecurityLevel%>" readonly="true" />
			</div>
          </div>
           </div>
             <br/><br/><br/>
             
             
             
             <div class="form-group">
           <label class="col-sm-2 control-label" style="width:150px"><u><h4>付款金额:</h4></u></label>
            <div class="col-sm-4">
      		<div class="input-group">
  			<span class="input-group-addon" id="sizing-addon2">@</span>
  				<input type="text" class="form-control" name="WIDtotal_fee" value="<%=NeedtoPay%>" />
			</div>
          </div>
           </div>
             <br/><br/><br/>
             
             
             
             <div class="form-group">
           <label class="col-sm-2 control-label" style="width:150px"><u><h4>商品描述:</h4></u></label>
            <div class="col-sm-4">
      		<div class="input-group">
  				<textarea class="form-control" rows="5" cols="55" name="WIDbody" value="<%=GoodDdescription%>" readonly="true"></textarea>
			</div>
          </div>
           </div>
             <br/><br/><br/>
            

       
       
       <br/><br/><br/>
         <div class="form-group">
         <div class="col-sm-offset-5 col-sm-10" style="margin-left:120px">
    
        <input type="submit" class="btn btn-default btn-lg btn-primary" value ="确认支付" data-toggle="modal" data-target="#myModal" />
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
    
    
    
    
    
    
    <!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					提示
				</h4>
			</div>
			<div class="modal-body">
				您是否已经付款成功？若付款成功，您可点击【确定】按钮继续完成保全
			</div>
			<div class="modal-footer">
			    <button type="button" class="btn btn-primary" onclick="shuaxin()">
					确定
				</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->



</body>


</html>
