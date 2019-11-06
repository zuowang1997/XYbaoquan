package controller;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;
import controller.CAsimulation;
import library.*;


public class YanZheng extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public YanZheng() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    
		    make_certificate newcertificate = new make_certificate();
		    String userID =  new String(request.getParameter("check_UserID").getBytes("ISO8859-1"),"UTF-8");
		    String FileName =  new String(request.getParameter("check_FileName").getBytes("ISO8859-1"),"UTF-8");
		    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		       String sss=new String(request.getParameter("check_SecyrityTime"));
		        //补全保全时间
			    int timelong1 = sss.length();
				if(timelong1 == 21){
					sss = sss+"00"; 
				}
	            if(timelong1 == 22){
	            	sss = sss+"0"; 
				}
		       String SeTime = sss;
		    File UserFile = new File(request.getParameter("check_File"));
		    boolean b = newcertificate.checkid(userID);
		    HttpSession session = request.getSession();
		    String userfileString = null;
		    
		    //读取客户选择的认证种类
		       String AuthenticationType = new String(request.getParameter("check_AuthenticationType"));
		    		    
		    
		    //查看要认证的文件是文字形式的还是图片形式的
		    String WORDorPIC = new String(request.getParameter("check_FileType"));
		    if( WORDorPIC.equals("word") )
		    {
		    	System.out.println("要认证的数据是文字形式的！");
			    //将文字形式的认证文件转化为字符串
			    yanzheng userfile = new yanzheng();
			    userfileString = userfile.FiletoString(UserFile);
		    }
		    else if( WORDorPIC.equals("picture") )
		    {
		    	System.out.println("要认证的数据是图片形式的！");
		    	make_certificate DealWithPicture = new make_certificate();
		    	File picturefile = UserFile;
		    	String picturefile_string = DealWithPicture.PictureToString(picturefile);
		    	userfileString = picturefile_string;
		    	//System.out.println(picturefile_string);
		    }
		    else{
		    	System.out.println("没有选择要认证的文件是文字形式还是图片形式！");
		    	session.setAttribute("fail_reason", "您没有选择要认证的文件是文字形式还是图片形式");
		    }
		    
		    
		    
		    String after1Hash = null;
		    String after2Hash = null;
		    String ming_afterTimeStamp = null;
		    String ming_unixtimestamp = null;
		    
		    int unixSendtoCATime = 0;
		    int unixGetfromCATime = 0;
		    int fail = 0;
		    
		    
		    
		    
            if(b){
            	//开始分不同的认证种类
            	//如果客户选择“只认证数据”
            	if(AuthenticationType.equals("only_data")){
            	
		     SqlManager sql=new SqlManager();
			 User UserInfor=new User();
			 UserInfor=sql.getUserById(userID);
			 String user_gender = UserInfor.getUserGender();

	 
			 session.setAttribute("UserInfor", UserInfor);
			 session.setAttribute("UserGender", user_gender);
			 
			 //开始还原加密过程
			 //获取用户注册时间
			 String RegisterTime = sql.getRegisterTimeByID(userID);
			 //整合信息
		     String UserInformation = userID + RegisterTime ;
		     String TimeInformation = SeTime;
		     String FileInformation = FileName + userfileString;
		     //三个信息的分别加密
			 String UserInformation_afterHash = newcertificate.doHash(UserInformation);
			 String TimeInformation_afterHash = newcertificate.doHash(TimeInformation);
			 String FileInformation_afterHash = newcertificate.doHash(FileInformation);
			 //三个信息整合再加密
			 String before1Hash = UserInformation_afterHash + TimeInformation_afterHash + FileInformation_afterHash;
			 after1Hash = newcertificate.doHash(before1Hash);
		     session.setAttribute("yanzheng_after1Hash", after1Hash);
			 
		     
		      //调用CA_generatekey模块来生成公私钥
	          CA_generatekey ca_generatekey = new CA_generatekey();
	          ca_generatekey.doPost(request,response);
	          //将after2Hash、用户ID、文件名称和保全时间securitytime一起使用RSA加密，将公钥和密文传给CA
              RSA rsa = new RSA();
              RSAPublicKey pubKey = (RSAPublicKey)request.getSession().getAttribute("CA_pubkey");
      		  String ming_after1Hash = after1Hash;
      		  String ming_userid = userID;
      		  String ming_filename = FileName;
      		  String ming_setime = SeTime;
      		  //传输给CA前加密
      		  String mi_after1Hash = null;
      		  String mi_userid = null;
      		  String mi_filename = null;
      		  String mi_setime = null;
			try {
				mi_after1Hash = rsa.encryptByPublicKey(ming_after1Hash, pubKey);
				mi_userid = rsa.encryptByPublicKey(ming_userid, pubKey);
          	    mi_filename = rsa.encryptByPublicKey(ming_filename, pubKey);
          		mi_setime = rsa.encryptByPublicKey(ming_setime, pubKey);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      		//session.setAttribute("yanzheng_CA_pubkey", pubKey);
      		session.setAttribute("yanzheng_mi_after1Hash", mi_after1Hash);	
      		session.setAttribute("yanzheng_mi_userid", mi_userid);	
      		session.setAttribute("yanzheng_mi_filename", mi_filename);	
      		session.setAttribute("yanzheng_mi_setime", mi_setime);
			
      		////调用CA_yanzheng部分的doPost代码
	     	CA_yanzheng ca = new CA_yanzheng();
	     	ca.doPost(request,response);
	     	
	     	//从CA处接收加盖了时间戳的数据，解密
	     	String mi_afterTimeStamp = String.valueOf(request.getSession().getAttribute("yanzheng_afterCA_Footprint"));
	     	String mi_unixtimestamp = String.valueOf(request.getSession().getAttribute("yanzheng_afterCA_timestamp"));
	     	RSA rsa2 = new RSA();
			RSAPrivateKey priKey2 = (RSAPrivateKey)request.getSession().getAttribute("CA_prikey");
			try {
				ming_afterTimeStamp = rsa2.decryptByPrivateKey(mi_afterTimeStamp, priKey2);
				ming_unixtimestamp = rsa2.decryptByPrivateKey(mi_unixtimestamp, priKey2);
				System.out.println("从CA处加盖了时间戳后传回的密文解密后："+ming_afterTimeStamp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      		
      		

            
            
            SqlCourse sql2 = new SqlCourse();
            String CertificateCode = sql2.getCertificateCodebyKey(userID,SeTime).trim();
            if(CertificateCode.equals("noresult")){
            	fail = 1;
            	session.setAttribute("fail_reason", "您的保全信息并未在权威部门的数据库中被找到");
            }
            else{
            System.out.println("正确的文件指纹："+CertificateCode); 
            session.setAttribute("yanzheng_CerCode_data", ming_afterTimeStamp);
            session.setAttribute("yanzheng_CerCode_time", "未选择认证");
            session.setAttribute("yanzheng_CerCode_device", "未选择认证");
            
            //从TimeNotes里取SendtoCATime和GetfromCATime
            SqlPost newTimeNotes = new SqlPost();
            String SendtoCATime = newTimeNotes.getsendtoCAtimeBykey(userID,SeTime);
            String GetfromCATime = newTimeNotes.getgetfromCAtimeBykey(userID,SeTime);
            if((SendtoCATime.equals("noresult"))|(GetfromCATime.equals("noresult"))){
            	fail = 1;
            	session.setAttribute("fail_reason", "您的保全信息并未在本平台的数据库中被找到");
            }
            else{
			CA newca = new CA();
			String newSendtoCATime = newca.dateToTimestamp(SendtoCATime);
			String newGetfromCATime = newca.dateToTimestamp(GetfromCATime);
			unixSendtoCATime = Integer.parseInt(newSendtoCATime);
			unixGetfromCATime = Integer.parseInt(newGetfromCATime);
            }
            
            
            //验证数据指纹
            if(ming_afterTimeStamp.equals(CertificateCode)){
		       //文件的指纹验证成功，再到CA的数据库里去看看时间戳对不对
            	SqlCA newCANotes = new SqlCA();
            	int unixtimestmap = Integer.parseInt(ming_unixtimestamp);
                //要符合   sendtoCAtime ≤ unixtimestmap ≤ getfromCAtime
            	if((unixSendtoCATime<=unixtimestmap)&(unixtimestmap<=unixGetfromCATime)){
                	//时间戳也对
                	Timestamp yanzhengTime = new Timestamp(System.currentTimeMillis()); 
                	String yanzheng_time = String.valueOf(yanzhengTime);
                	session.setAttribute("yanzheng_Time", yanzheng_time);
            	}
            	else{  
            		fail = 1;
            		session.setAttribute("fail_reason", "时间戳不在合理范围内，怀疑系统遭到篡改，保全过程作废");
            	}
            }
            else{ 
            	fail = 1;
    		    session.setAttribute("fail_reason", "上传文件的数据指纹与保全时生成的指纹不相符");
    		    }
            }
            
            
            
            
             //对应第一个认证种类的if结束括号
            }
            
             
            	
            	//如果客户选择“只认证文件完成时间”
                else if(AuthenticationType.equals("only_time")){
                	
                	String ming_OnlyTime_userid = userID;
            		String ming_OnlyTime_setime = SeTime;
            		
            		
            		SqlManager sql=new SqlManager();
       			 User UserInfor=new User();
       			 UserInfor=sql.getUserById(userID);
       			 String user_gender = UserInfor.getUserGender();

       	 
       			 session.setAttribute("UserInfor", UserInfor);
       			 session.setAttribute("UserGender", user_gender);
                	
            		//获取文件完成时间
                	//String FileFinishTime = "2016-07-20";
            		FilesHandling filehandle = new FilesHandling();
            		String FileFinishTime = filehandle.GetFileFinishTime(UserFile);
            		
                	String OnlyTime_after1Hash = newcertificate.doHash(FileFinishTime);
                	
                	//CA部分
                	 //调用CA_generatekey模块来生成公私钥
      	          CA_generatekey ca_generatekey = new CA_generatekey();
      	          ca_generatekey.doPost(request,response);
      	          //将after2Hash、用户ID、文件名称和保全时间securitytime一起使用RSA加密，将公钥和密文传给CA
                    RSA rsa = new RSA();
                    RSAPublicKey pubKey = (RSAPublicKey)request.getSession().getAttribute("CA_pubkey");
            		  String ming_OnlyTime_after1Hash = OnlyTime_after1Hash;
            		  //传输给CA前加密
            		  String mi_OnlyTime_after1Hash = null;
            		  String mi_OnlyTime_userid = null;
            		  String mi_OnlyTime_setime = null;
      			try {
      				mi_OnlyTime_after1Hash = rsa.encryptByPublicKey(ming_OnlyTime_after1Hash, pubKey);
      				mi_OnlyTime_userid = rsa.encryptByPublicKey(ming_OnlyTime_userid, pubKey);
      				mi_OnlyTime_setime = rsa.encryptByPublicKey(ming_OnlyTime_setime, pubKey);
      			} catch (Exception e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}
            		//session.setAttribute("yanzheng_CA_pubkey", pubKey);
            		session.setAttribute("yanzheng_mi_OnlyTime_after1Hash", mi_OnlyTime_after1Hash);
            		session.setAttribute("yanzheng_mi_OnlyTime_userid", mi_OnlyTime_userid);
            		session.setAttribute("yanzheng_mi_OnlyTime_setime", mi_OnlyTime_setime);
      			
            		////调用CA_yanzheng部分的doPost代码
      	     	CA_yanzheng_OnlyTime ca_time = new CA_yanzheng_OnlyTime();
      	     	ca_time.doPost(request,response);
      	     	
      	     	//从CA处接收加盖了时间戳的数据，解密
      	     	String mi_afterTimeStamp_time = String.valueOf(request.getSession().getAttribute("yanzheng_time_afterCA_Footprint"));
      	     	String mi_unixtimestamp_time = String.valueOf(request.getSession().getAttribute("yanzheng_time_afterCA_timestamp"));
      	     	RSA rsa2 = new RSA();
      			RSAPrivateKey priKey2 = (RSAPrivateKey)request.getSession().getAttribute("CA_prikey");
      			
      			String ming_afterTimeStamp_time = null;
      			String ming_unixtimestamp_time = null;
      			try {
      				ming_afterTimeStamp_time = rsa2.decryptByPrivateKey(mi_afterTimeStamp_time, priKey2);
      				ming_unixtimestamp_time = rsa2.decryptByPrivateKey(mi_unixtimestamp_time, priKey2);
      				System.out.println("从CA处加盖了时间戳后传回的密文解密后："+ming_afterTimeStamp_time);
      			} catch (Exception e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}

      			
                  SqlCourse sql2 = new SqlCourse();
                  String CertificateCode_time = sql2.getCertificateCode_TimebyKey(userID,SeTime).trim();
                  if(CertificateCode_time.equals("noresult")){
                  	fail = 1;
                  	session.setAttribute("fail_reason", "您的保全信息并未在权威部门的数据库中被找到");
                  }
                  else{
                  System.out.println("正确的文件指纹："+CertificateCode_time); 
                  session.setAttribute("yanzheng_CerCode_time", ming_afterTimeStamp_time);
                  session.setAttribute("yanzheng_CerCode_data", "未选择认证");
                  session.setAttribute("yanzheng_CerCode_device", "未选择认证");
                  
                  
                  //验证数据指纹
                  if(ming_afterTimeStamp_time.equals(CertificateCode_time)){
                      	Timestamp yanzhengTime = new Timestamp(System.currentTimeMillis()); 
                      	String yanzheng_time = String.valueOf(yanzhengTime);
                      	session.setAttribute("yanzheng_Time", yanzheng_time);
                  }
                  else{ 
                  	fail = 1;
          		    session.setAttribute("fail_reason", "上传文件的完成时间指纹与保全时生成的指纹不相符");
          		    }
                  }
                	
                //这是“只认证文件完成时间”的if语句的括号结尾
                }
                
            	
            	
            	
            	
            	//如果客户选择“只认证文件来源”
                else if(AuthenticationType.equals("only_device")){
                	
                	String ming_OnlyDevice_userid = userID;
            		String ming_OnlyDevice_setime = SeTime;
                	
                	//获取设备唯一标识号
            		//String FileDeviceNumber = "486";
            		UUID uuid=UUID.randomUUID();
            		String FileDeviceNumber = uuid.toString();
            		System.out.println("设备唯一标识号："+FileDeviceNumber);
                	
            		String OnlyDevice_after1Hash = newcertificate.doHash(FileDeviceNumber);
                	
                	//CA部分
                	 //调用CA_generatekey模块来生成公私钥
      	          CA_generatekey ca_generatekey = new CA_generatekey();
      	          ca_generatekey.doPost(request,response);
      	          //将after2Hash、用户ID、文件名称和保全时间securitytime一起使用RSA加密，将公钥和密文传给CA
                    RSA rsa = new RSA();
                    RSAPublicKey pubKey = (RSAPublicKey)request.getSession().getAttribute("CA_pubkey");
            		  String ming_OnlyDevice_after1Hash = OnlyDevice_after1Hash;
            		  //传输给CA前加密
            		  String mi_OnlyDevice_after1Hash = null;
            		  String mi_OnlyDevice_userid = null;
            		  String mi_OnlyDevice_setime = null;
      			try {
      				mi_OnlyDevice_after1Hash = rsa.encryptByPublicKey(ming_OnlyDevice_after1Hash, pubKey);
      				mi_OnlyDevice_userid = rsa.encryptByPublicKey(ming_OnlyDevice_userid, pubKey);
      				mi_OnlyDevice_setime = rsa.encryptByPublicKey(ming_OnlyDevice_setime, pubKey);
      			} catch (Exception e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}
            		//session.setAttribute("yanzheng_CA_pubkey", pubKey);
            		session.setAttribute("yanzheng_mi_OnlyDevice_after1Hash", mi_OnlyDevice_after1Hash);
            		session.setAttribute("yanzheng_mi_OnlyDevice_userid", mi_OnlyDevice_userid);
            		session.setAttribute("yanzheng_mi_OnlyDevice_setime", mi_OnlyDevice_setime);
      			
            		////调用CA_yanzheng部分的doPost代码
      	     	CA_yanzheng_OnlyDevice ca_device = new CA_yanzheng_OnlyDevice();
      	     	ca_device.doPost(request,response);
      	     	
      	     	//从CA处接收加盖了时间戳的数据，解密
      	     	String mi_afterTimeStamp_device = String.valueOf(request.getSession().getAttribute("yanzheng_device_afterCA_Footprint"));
      	     	String mi_unixtimestamp_device = String.valueOf(request.getSession().getAttribute("yanzheng_device_afterCA_timestamp"));
      	     	RSA rsa2 = new RSA();
      			RSAPrivateKey priKey2 = (RSAPrivateKey)request.getSession().getAttribute("CA_prikey");
      			
      			String ming_afterTimeStamp_device = null;
      			String ming_unixtimestamp_device = null;
      			try {
      				ming_afterTimeStamp_device = rsa2.decryptByPrivateKey(mi_afterTimeStamp_device, priKey2);
      				ming_unixtimestamp_device = rsa2.decryptByPrivateKey(mi_unixtimestamp_device, priKey2);
      				System.out.println("从CA处加盖了时间戳后传回的密文解密后："+ming_afterTimeStamp_device);
      			} catch (Exception e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}

      			
                  SqlCourse sql2 = new SqlCourse();
                  String CertificateCode_device = sql2.getCertificateCode_DevicebyKey(userID,SeTime).trim();
                  if(CertificateCode_device.equals("noresult")){
                  	fail = 1;
                  	session.setAttribute("fail_reason", "您的保全信息并未在权威部门的数据库中被找到");
                  }
                  else{
                  System.out.println("正确的文件指纹："+CertificateCode_device); 
                  session.setAttribute("yanzheng_CerCode_device", ming_afterTimeStamp_device);
                  session.setAttribute("yanzheng_CerCode_data", "未选择认证");
                  session.setAttribute("yanzheng_CerCode_time", "未选择认证");
                  
                  
                  
                  //验证数据指纹
                  if(ming_afterTimeStamp_device.equals(CertificateCode_device)){
                      	Timestamp yanzhengTime = new Timestamp(System.currentTimeMillis()); 
                      	String yanzheng_time = String.valueOf(yanzhengTime);
                      	session.setAttribute("yanzheng_Time", yanzheng_time);
                  }
                  else{ 
                  	fail = 1;
          		    session.setAttribute("fail_reason", "上传文件的来源指纹与保全时生成的指纹不相符");
          		    }
                  }
                	
                	
                	//这是“只认证文件来源”的if语句的括号结尾
                }
            	
            	
            	
            	
            	//如果客户选择“认证文件完成时间和来源”
                else if(AuthenticationType.equals("time_and_device")){
                	String ming_TimeDevice_userid = userID;
            		String ming_TimeDevice_setime = SeTime;
            		
            		
            		SqlManager sql=new SqlManager();
       			 User UserInfor=new User();
       			 UserInfor=sql.getUserById(userID);
       			 String user_gender = UserInfor.getUserGender();

       	 
       			 session.setAttribute("UserInfor", UserInfor);
       			 session.setAttribute("UserGender", user_gender);
       			 
                	
                	//获取文件完成时间
            		//String FileFinishTime = "2016-07-20";
            		FilesHandling filehandle = new FilesHandling();
            		String FileFinishTime = filehandle.GetFileFinishTime(UserFile);
            		
            		//获取设备唯一标识号
                	//String FileDeviceNumber = "486";
                	UUID uuid=UUID.randomUUID();
            		String FileDeviceNumber = uuid.toString();
            		System.out.println("设备唯一标识号："+FileDeviceNumber);
                	
                	
                	String TimeDevice_after1Hash_time = newcertificate.doHash(FileFinishTime);
                	String TimeDevice_after1Hash_device = newcertificate.doHash(FileDeviceNumber);
                	
                	//CA部分
                	 //调用CA_generatekey模块来生成公私钥
      	          CA_generatekey ca_generatekey = new CA_generatekey();
      	          ca_generatekey.doPost(request,response);
      	          //将after2Hash、用户ID、文件名称和保全时间securitytime一起使用RSA加密，将公钥和密文传给CA
                    RSA rsa = new RSA();
                    RSAPublicKey pubKey = (RSAPublicKey)request.getSession().getAttribute("CA_pubkey");
            		  String ming_TimeDevice_after1Hash_time = TimeDevice_after1Hash_time;
            		  String ming_TimeDevice_after1Hash_device = TimeDevice_after1Hash_device;
            		  //传输给CA前加密
            		  String mi_TimeDevice_after1Hash_time = null;
            		  String mi_TimeDevice_after1Hash_device = null;
            		  String mi_TimeDevice_userid = null;
            		  String mi_TimeDevice_setime = null;
      			try {
      				mi_TimeDevice_after1Hash_time = rsa.encryptByPublicKey(ming_TimeDevice_after1Hash_time, pubKey);
      				mi_TimeDevice_after1Hash_device = rsa.encryptByPublicKey(ming_TimeDevice_after1Hash_device, pubKey);
      				mi_TimeDevice_userid = rsa.encryptByPublicKey(ming_TimeDevice_userid, pubKey);
      				mi_TimeDevice_setime = rsa.encryptByPublicKey(ming_TimeDevice_setime, pubKey);
      			} catch (Exception e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}
            		//session.setAttribute("yanzheng_CA_pubkey", pubKey);
            		session.setAttribute("yanzheng_mi_TimeDevice_after1Hash_time", mi_TimeDevice_after1Hash_time);
            		session.setAttribute("yanzheng_mi_TimeDevice_after1Hash_device", mi_TimeDevice_after1Hash_device);
            		session.setAttribute("yanzheng_mi_TimeDevice_userid", mi_TimeDevice_userid);
            		session.setAttribute("yanzheng_mi_TimeDevice_setime", mi_TimeDevice_setime);
      			
            		////调用CA_yanzheng部分的doPost代码
      	     	CA_yanzheng_TimeDevice ca_timedevice = new CA_yanzheng_TimeDevice();
      	     	ca_timedevice.doPost(request,response);
      	     	
      	     	//从CA处接收加盖了时间戳的数据，解密
      	     	String mi_afterTimeStamp_time = String.valueOf(request.getSession().getAttribute("yanzheng_timedevice_afterCA_Footprint_time"));
      	     	String mi_afterTimeStamp_device = String.valueOf(request.getSession().getAttribute("yanzheng_timedevice_afterCA_Footprint_device"));
      	     
      	     	RSA rsa2 = new RSA();
      			RSAPrivateKey priKey2 = (RSAPrivateKey)request.getSession().getAttribute("CA_prikey");
      			
      			String ming_afterTimeStamp_time = null;
      			String ming_afterTimeStamp_device = null;
      			try {
      				ming_afterTimeStamp_time = rsa2.decryptByPrivateKey(mi_afterTimeStamp_time, priKey2);
      				ming_afterTimeStamp_device = rsa2.decryptByPrivateKey(mi_afterTimeStamp_device, priKey2);
      				System.out.println("从CA处加盖了时间戳后传回的密文解密后："+ming_afterTimeStamp_time);
      			} catch (Exception e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}

      			
                  SqlCourse sql2 = new SqlCourse();
                  String CertificateCode_time = sql2.getCertificateCode_TimebyKey(userID,SeTime).trim();
                  String CertificateCode_device = sql2.getCertificateCode_DevicebyKey(userID,SeTime).trim();
                  if(CertificateCode_time.equals("noresult")){
                  	fail = 1;
                  	session.setAttribute("fail_reason", "您的保全信息并未在权威部门的数据库中被找到");
                  }
                  else{
                	  
                  System.out.println("正确的文件指纹："+CertificateCode_time); 
                  session.setAttribute("yanzheng_CerCode_time", ming_afterTimeStamp_time);
                  session.setAttribute("yanzheng_CerCode_device", ming_afterTimeStamp_device);
                  session.setAttribute("yanzheng_CerCode_data", "未选择认证");
                  
                  
                  //验证数据指纹
                  if(ming_afterTimeStamp_time.equals(CertificateCode_time)&&ming_afterTimeStamp_device.equals(CertificateCode_device)){

                	    Timestamp yanzhengTime = new Timestamp(System.currentTimeMillis()); 
                      	String yanzheng_time = String.valueOf(yanzhengTime);
                      	session.setAttribute("yanzheng_Time", yanzheng_time);
                  }
                  else if(ming_afterTimeStamp_time.equals(CertificateCode_time)&&(ming_afterTimeStamp_device.equals(CertificateCode_device)==false)){ 
                  	fail = 1;
          		    session.setAttribute("fail_reason", "上传文件的完成时间通过认证，但文件来源未能通过认证");
          		    }
                  else if((ming_afterTimeStamp_time.equals(CertificateCode_time)==false)&&ming_afterTimeStamp_device.equals(CertificateCode_device)){
                	fail = 1;
            		session.setAttribute("fail_reason", "上传文件的来源通过认证，但文件完成时间未能通过认证");
                  }
                  else{
                	fail = 1;
              		session.setAttribute("fail_reason", "上传文件的完成时间指纹、来源指纹均与保全时生成的指纹不相符");
                  }
                  
       
                  }
  	
                //这是“认证文件完成时间和来源”的if语句括号结尾
                }
            	
            	
            	
            	
            	
            	//如果客户选择“全部认证”
                else if(AuthenticationType.equals("authenticate_all")){
	
                	String ming_All_userid = userID;
            		String ming_All_setime = SeTime;
                	
                	//获取文件完成时间
            		//String FileFinishTime = "2016-07-20";
            		FilesHandling filehandle = new FilesHandling();
            		String FileFinishTime = filehandle.GetFileFinishTime(UserFile);
            		
            		//获取设备唯一标识号
                	//String FileDeviceNumber = "486";
                	UUID uuid=UUID.randomUUID();
            		String FileDeviceNumber = uuid.toString();
            		System.out.println("设备唯一标识号："+FileDeviceNumber);
            		
            		//获取文件数据
                	 SqlManager sql=new SqlManager();
        			 User UserInfor=new User();
        			 UserInfor=sql.getUserById(userID);
        			 String user_gender = UserInfor.getUserGender();

        	 
        			 session.setAttribute("UserInfor", UserInfor);
        			 session.setAttribute("UserGender", user_gender);
        			 
        			 //开始还原加密过程
        			 //获取用户注册时间
        			 String RegisterTime = sql.getRegisterTimeByID(userID);
        			 //整合信息
        		     String UserInformation = userID + RegisterTime ;
        		     String TimeInformation = SeTime;
        		     String FileInformation = FileName + userfileString;
        		     //三个信息的分别加密
        			 String UserInformation_afterHash = newcertificate.doHash(UserInformation);
        			 String TimeInformation_afterHash = newcertificate.doHash(TimeInformation);
        			 String FileInformation_afterHash = newcertificate.doHash(FileInformation);
        			 //三个信息整合
        			 String before1Hash = UserInformation_afterHash + TimeInformation_afterHash + FileInformation_afterHash;
                	
        		     
                	//第一次加密
                	String All_after1Hash_time = newcertificate.doHash(FileFinishTime);
                	String All_after1Hash_device = newcertificate.doHash(FileDeviceNumber);
                	String All_after1Hash_data = newcertificate.doHash(before1Hash);
                	
                	//CA部分
                	 //调用CA_generatekey模块来生成公私钥
      	          CA_generatekey ca_generatekey = new CA_generatekey();
      	          ca_generatekey.doPost(request,response);
      	          //将after2Hash、用户ID、文件名称和保全时间securitytime一起使用RSA加密，将公钥和密文传给CA
                    RSA rsa = new RSA();
                    RSAPublicKey pubKey = (RSAPublicKey)request.getSession().getAttribute("CA_pubkey");
            		  String ming_All_after1Hash_time = All_after1Hash_time;
            		  String ming_All_after1Hash_device = All_after1Hash_device;
            		  String ming_All_after1Hash_data = All_after1Hash_data;
            		  //传输给CA前加密
            		  String mi_All_after1Hash_time = null;
            		  String mi_All_after1Hash_device = null;
            		  String mi_All_after1Hash_data = null;
            		  String mi_All_userid = null;
            		  String mi_All_setime = null;
      			try {
      				mi_All_after1Hash_time = rsa.encryptByPublicKey(ming_All_after1Hash_time, pubKey);
      				mi_All_after1Hash_device = rsa.encryptByPublicKey(ming_All_after1Hash_device, pubKey);
      				mi_All_after1Hash_data = rsa.encryptByPublicKey(ming_All_after1Hash_data, pubKey);
      				mi_All_userid = rsa.encryptByPublicKey(ming_All_userid, pubKey);
      				mi_All_setime = rsa.encryptByPublicKey(ming_All_setime, pubKey);
      			} catch (Exception e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}
            		//session.setAttribute("yanzheng_CA_pubkey", pubKey);
            		session.setAttribute("yanzheng_mi_All_after1Hash_time", mi_All_after1Hash_time);
            		session.setAttribute("yanzheng_mi_All_after1Hash_device", mi_All_after1Hash_device);
            		session.setAttribute("yanzheng_mi_All_after1Hash_data", mi_All_after1Hash_data);
            		session.setAttribute("yanzheng_mi_All_userid", mi_All_userid);
            		session.setAttribute("yanzheng_mi_All_setime", mi_All_setime);
      			
            		////调用CA_yanzheng部分的doPost代码
      	     	CA_yanzheng_All ca_all = new CA_yanzheng_All();
      	     	ca_all.doPost(request,response);
      	     	
      	     	//从CA处接收加盖了时间戳的数据，解密
      	     	String mi_afterTimeStamp_time = String.valueOf(request.getSession().getAttribute("yanzheng_all_afterCA_Footprint_time"));
      	     	String mi_afterTimeStamp_device = String.valueOf(request.getSession().getAttribute("yanzheng_all_afterCA_Footprint_device"));
      	     	String mi_afterTimeStamp_data = String.valueOf(request.getSession().getAttribute("yanzheng_all_afterCA_Footprint_data"));
      	     	String mi_unixtimestamp = String.valueOf(request.getSession().getAttribute("yanzheng_all_afterCA_timestamp"));
      	     	
      	     	RSA rsa2 = new RSA();
      			RSAPrivateKey priKey2 = (RSAPrivateKey)request.getSession().getAttribute("CA_prikey");
      			
      			String ming_afterTimeStamp_time = null;
      			String ming_afterTimeStamp_device = null;
      			String ming_afterTimeStamp_data = null;
      			String ming_afterTimeStamp_unixtimestamp = null;
      			try {
      				ming_afterTimeStamp_time = rsa2.decryptByPrivateKey(mi_afterTimeStamp_time, priKey2);
      				ming_afterTimeStamp_device = rsa2.decryptByPrivateKey(mi_afterTimeStamp_device, priKey2);
      				ming_afterTimeStamp_data = rsa2.decryptByPrivateKey(mi_afterTimeStamp_data, priKey2);
      				ming_afterTimeStamp_unixtimestamp = rsa2.decryptByPrivateKey(mi_unixtimestamp, priKey2);
      				System.out.println("从CA处加盖了时间戳后传回的密文解密后："+ming_afterTimeStamp_time);
      			} catch (Exception e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}

      			
                  SqlCourse sql2 = new SqlCourse();
                  String CertificateCode_data = sql2.getCertificateCodebyKey(userID,SeTime).trim();
                  String CertificateCode_time = sql2.getCertificateCode_TimebyKey(userID,SeTime).trim();
                  String CertificateCode_device = sql2.getCertificateCode_DevicebyKey(userID,SeTime).trim();
                  if((CertificateCode_data.equals("noresult"))|(CertificateCode_time.equals("noresult"))|(CertificateCode_device.equals("noresult"))){
                  	fail = 1;
                  	session.setAttribute("fail_reason", "您的保全信息并未在权威部门的数据库中被找到");
                  }
                  else{
                	  
                  System.out.println("正确的文件指纹："+CertificateCode_data); 
                  session.setAttribute("yanzheng_CerCode_data", ming_afterTimeStamp_data);
                  session.setAttribute("yanzheng_CerCode_time", ming_afterTimeStamp_time);
                  session.setAttribute("yanzheng_CerCode_device", ming_afterTimeStamp_device);
                  
                  
                //从TimeNotes里取SendtoCATime和GetfromCATime
                  SqlPost newTimeNotes = new SqlPost();
                  String SendtoCATime = newTimeNotes.getsendtoCAtimeBykey(userID,SeTime);
                  String GetfromCATime = newTimeNotes.getgetfromCAtimeBykey(userID,SeTime);
                  if((SendtoCATime.equals("noresult"))|(GetfromCATime.equals("noresult"))){
                  	fail = 1;
                  	session.setAttribute("fail_reason", "您的保全信息并未在本平台的数据库中被找到");
                  }
                  else{
      			CA newca = new CA();
      			String newSendtoCATime = newca.dateToTimestamp(SendtoCATime);
      			String newGetfromCATime = newca.dateToTimestamp(GetfromCATime);
      			unixSendtoCATime = Integer.parseInt(newSendtoCATime);
      			unixGetfromCATime = Integer.parseInt(newGetfromCATime);
                  }
                  
                  
                  //验证数据指纹
                  if(ming_afterTimeStamp_data.equals(CertificateCode_data)){
      		       //文件的指纹验证成功，再到CA的数据库里去看看时间戳对不对
                  	SqlCA newCANotes = new SqlCA();
                  	int unixtimestmap = Integer.parseInt(ming_afterTimeStamp_unixtimestamp);
                      //要符合   sendtoCAtime ≤ unixtimestmap ≤ getfromCAtime
                  	if((unixSendtoCATime<=unixtimestmap)&(unixtimestmap<=unixGetfromCATime)){
                      	//时间戳也对
                  		
                  		
                  		
                  		//开始完成时间与来源的指纹比对
                  		 if(ming_afterTimeStamp_time.equals(CertificateCode_time)&&ming_afterTimeStamp_device.equals(CertificateCode_device)){

                     	    Timestamp yanzhengTime = new Timestamp(System.currentTimeMillis()); 
                           	String yanzheng_time = String.valueOf(yanzhengTime);
                           	session.setAttribute("yanzheng_Time", yanzheng_time);
                       }
                       else if(ming_afterTimeStamp_time.equals(CertificateCode_time)&&(ming_afterTimeStamp_device.equals(CertificateCode_device)==false)){ 
                       	fail = 1;
               		    session.setAttribute("fail_reason", "上传文件的数据与完成时间通过认证，但文件来源未能通过认证");
               		    }
                       else if((ming_afterTimeStamp_time.equals(CertificateCode_time)==false)&&ming_afterTimeStamp_device.equals(CertificateCode_device)){
                     	fail = 1;
                 		session.setAttribute("fail_reason", "上传文件的数据与来源通过认证，但文件完成时间未能通过认证");
                       }
                       else{
                     	fail = 1;
                   		session.setAttribute("fail_reason", "上传文件的数据通过认证，但完成时间、来源均未通过认证");
                       }
                        //完成时间与来源指纹比对结束

                  		 
                  	}
                  	else{  
                  		fail = 1;
                  		session.setAttribute("fail_reason", "时间戳不在合理范围内，怀疑系统遭到篡改，保全过程作废");
                  	}
                  }
                  else{ 
                  	fail = 1;
          		    session.setAttribute("fail_reason", "上传文件的数据指纹与保全时生成的指纹不相符");
          		    }
                  }
                	
                	
                //这是“全部认证”的if语句的括号结尾
                }
            	
            	
            	
            	//客户没有选择认证种类
                else{ 
                	fail = 1;
        		    session.setAttribute("fail_reason", "您没有选择认证种类");
                }	


            
            //if(b)的括号结尾
            }
       
            else{
            	//验证失败
            	fail = 1;
            	session.setAttribute("fail_reason", "您的ID未在本系统注册过");
            }
            
            
            
            if(fail==0){
            response.sendRedirect("authentication_result1.jsp");
            }
            else{
            response.sendRedirect("authentication_result2.jsp");
            }

	
	
	}

	

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
