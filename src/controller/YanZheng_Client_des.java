package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.*;
import library.*;


public class YanZheng_Client_des extends HttpServlet {

	
	//一些文件参数
		private static final long serialVersionUID = 1L;
	    
	    // 上传文件存储目录
	    private static final String UPLOAD_DIRECTORY = "upload";
	 
	    // 上传配置
	    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
	    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	
	
	/**
	 * Constructor of the object.
	 */
	public YanZheng_Client_des() {
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

		    //File UserFile = new File(request.getParameter("check_File"));
		    //String AuthenticationType = new String(request.getParameter("check_AuthenticationType"));
		    String userID = null;
		    String SeTime_str = null;
		    String FileDeviceNumber = null;
		    String FileFinishTime = null;
		    String CerNumber = null;
		    String AuthenticationType = null;
		    String WORDorPIC = null;
		    String file_encryped_string = null;
		    
		    String FileNameWithFormat = null;
		    
		    
		  //接收表单参数
		    boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
	   	    response.setContentType("text/html;charset=UTF-8");  
	        if (isMultipart) {  
	       	 HttpSession session = request.getSession();
	            // Create a factory for disk-based file items  
	            DiskFileItemFactory factory = new DiskFileItemFactory();  
	            
	            // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
	            factory.setSizeThreshold(MEMORY_THRESHOLD);
	            // 设置临时存储目录
	            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	     
	            ServletFileUpload upload1 = new ServletFileUpload(factory);
	             
	            // 设置最大文件上传值
	            upload1.setFileSizeMax(MAX_FILE_SIZE);
	             
	            // 设置最大请求值 (包含文件和表单数据)
	            upload1.setSizeMax(MAX_REQUEST_SIZE);
	            
	            // Configure a repository (to ensure a secure temp location is used)  
	            ServletContext servletContext = this.getServletConfig()  
	                    .getServletContext();  
	            File repository = (File) servletContext  
	                    .getAttribute("javax.servlet.context.tempdir");  
	            factory.setRepository(repository);  
	            // Create a new file upload handler  
	            ServletFileUpload upload = new ServletFileUpload(factory);  
	            // Parse the request  
	            try {  
	                @SuppressWarnings("unchecked")
					List<FileItem> items = upload.parseRequest(request);  
	  
	                Iterator<FileItem> iterator = items.iterator();  
	  
	                String dir = request.getSession().getServletContext()  
	                        .getRealPath("/upload");  
	                File dirFile = new File(dir);  
	             // 如果目录不存在则创建
	                if (!dirFile.exists()) {
	               	 dirFile.mkdir();
	                }  
	                while (iterator.hasNext()) {  
	                    FileItem fileItem = iterator.next(); 
	                    if (fileItem.isFormField()) {// 如果是文本类型参数  
	  
	                   	 String name = fileItem.getFieldName();  
	                        String value = fileItem.getString("UTF-8");  
	                        System.out.println(name + "  " + value);  
	                        session.setAttribute(name, value); 
	                        
	                    } 
	                    else {  
	                        // 如果是文件类型参数  
	                   	 if (items != null && items.size() > 0) {
	                            // 迭代表单数据
	                            for (FileItem item : items) {
	                                // 处理不在表单中的字段
	                                if (!item.isFormField()) {
	                                    String fileName = new File(item.getName()).getName();
	                                    String filePath = dir + File.separator + fileName;
	                                    File storeFile = new File(filePath);
	                                    // 在控制台输出文件的上传路径
	                                    System.out.println(filePath);
	                                    // 保存文件到硬盘
	                                    item.write(storeFile);
	                                    FileNameWithFormat = fileName;
	                                }
	                            }
	                        } 
	                    }  
	                }  
	            } catch (FileUploadException e) {  
	  
	                e.printStackTrace();  
	            } catch (Exception e) {  
	  
	                e.printStackTrace();  
	            }  
	  
	        } else {  
	            
	        } 
		    
		    
		    
		    //接受参数
	        FileDeviceNumber = String.valueOf(request.getSession().getAttribute("fromclient_devicenumber"));
	        FileFinishTime = String.valueOf(request.getSession().getAttribute("fromclient_finishtime"));
		    CerNumber = String.valueOf(request.getSession().getAttribute("fromclient_CerNumber"));
		    AuthenticationType = String.valueOf(request.getSession().getAttribute("fromclient_AuthenticationType"));
		    FileNameWithFormat = String.valueOf(request.getSession().getAttribute("fromclient_filename"));
		    WORDorPIC = FileNameWithFormat.substring(FileNameWithFormat.length()-4,FileNameWithFormat.length());
		    //得到文件名
		    String FileName = FileNameWithFormat.substring(0,FileNameWithFormat.length()-4);
		    //去掉文件名前面的des
		    FileName = FileName.substring(3);
		    System.out.println("文件名："+FileName);

		    
		    
		    
		    
		  //用des解密数据
		    String file_decryped_string = null;
			try {
				DES des = new DES();
				String key = "12345678";
				byte[] before_decrypt = null;
				before_decrypt = des.readJpeg("C:\\Tomcat\\webapps\\XYbaoquan\\upload\\"+FileNameWithFormat);
				byte[] decrypimg = des.decrypt(before_decrypt,key);
				newcertificate.deleteFile("C:\\Tomcat\\webapps\\XYbaoquan\\upload\\"+FileNameWithFormat);
				des.writeJpeg("C:\\Tomcat\\webapps\\XYbaoquan\\upload\\"+FileNameWithFormat,decrypimg);
				byte[] readfile = des.toByteArray("C:\\Tomcat\\webapps\\XYbaoquan\\upload\\"+FileNameWithFormat);
				file_decryped_string = new String(readfile);
			
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    
		    
		    
		    
	        //处理cernumber
	        int i = CerNumber.indexOf("+");
		       userID = CerNumber.substring(0,i);
		       String sss_date=CerNumber.substring(i+1,i+11);
		       String sss_time_1 = CerNumber.substring(i+11,i+13);
		       String sss_time_2 = CerNumber.substring(i+13,i+15);
		       String sss_time_3 = CerNumber.substring(i+15,i+17);
		       String sss_time_4 = CerNumber.substring(i+17,i+20);
		       String sss_finish = sss_date+" "+sss_time_1+":"+sss_time_2+":"+sss_time_3+"."+sss_time_4;
		       SeTime_str = sss_finish;

		       System.out.println("处理好后的信息，ID："+userID+"时间："+sss_finish);
	        
	        
		    
		    

		   
		   
		    String SeTime = SeTime_str;
		   

		    
		    boolean b = newcertificate.checkid(userID);
		    HttpSession session = request.getSession();
		    
		    //��yanzheng_seccess.jsp��yanzheng_fail.jsp������Ϣ
		    session.setAttribute("userinfor_id",userID);
		    session.setAttribute("yanzheng_filename",FileName);
	        session.setAttribute("yanzheng_securitytime",SeTime);	    
		    

		    String after1Hash = null;
		    String after2Hash = null;
		    String ming_afterTimeStamp = null;
		    String ming_unixtimestamp = null;
		    
		    int unixSendtoCATime = 0;
		    int unixGetfromCATime = 0;
		    int fail = 0; 
		    String userfileString = null;
		    
		    
		    
		    //判断要保全的内容是文字还是图片
            if( WORDorPIC.equals(".txt") )
            {
	           System.out.println("文件是文字类型的");
               userfileString = file_decryped_string;
            }
            else if( WORDorPIC.equals(".pdf") )
		    {
            	System.out.println("文件是pdf类型的");
            	userfileString = file_decryped_string;
		    }
            else if( (WORDorPIC.equals(".jpg")) | (WORDorPIC.equals("png")) )
            {
	           System.out.println("文件是图片类型的");
	           userfileString = file_decryped_string;
	          
            }
            else{
	           System.out.println("没有啥文件类型。。。");
	           userfileString = file_decryped_string;
            }
		    
		    
		    
		    
            if(b){
            	
            	
            	
            	//��ʼ�ֲ�ͬ����֤����
            	//����ͻ�ѡ��ֻ��֤���ݡ�
            	
            	String DataCertificateCode = null;
            	
            	//if(AuthenticationType.equals("only_data"))
            	if(true){
            	
		     SqlManager sql=new SqlManager();
			 User UserInfor=new User();
			 UserInfor=sql.getUserById(userID);
			 String user_gender = UserInfor.getUserGender();

	 
			 session.setAttribute("UserInfor", UserInfor);
			 session.setAttribute("UserGender", user_gender);
			 
			 //��ʼ��ԭ���ܹ���
			 //��ȡ�û�ע��ʱ��
			 String RegisterTime = sql.getRegisterTimeByID(userID);
			 //������Ϣ
		     String UserInformation = userID + RegisterTime ;
		     String TimeInformation = SeTime;
		     String FileInformation = FileName + userfileString;
				//调整SecurityTime的格式
				int timelong = TimeInformation.length();
				if(timelong == 21){
					TimeInformation = TimeInformation+"00";
				}
             if(timelong == 22){
             	TimeInformation = TimeInformation+"0";
				}
		     //������Ϣ�ķֱ����
			 String UserInformation_afterHash = newcertificate.doHash(UserInformation);
			 String TimeInformation_afterHash = newcertificate.doHash(TimeInformation);
			 String FileInformation_afterHash = newcertificate.doHash(FileInformation);
			 System.out.println(UserInformation+TimeInformation+FileInformation);
			 //������Ϣ�����ټ���
			 String before1Hash = UserInformation_afterHash + TimeInformation_afterHash + FileInformation_afterHash;
			 after1Hash = newcertificate.doHash(before1Hash);
		     session.setAttribute("yanzheng_after1Hash", after1Hash);
			 
		     
		      //����CA_generatekeyģ�������ɹ�˽Կ
	          CA_generatekey ca_generatekey = new CA_generatekey();
	          ca_generatekey.doPost(request,response);
	          //��after2Hash���û�ID���ļ����ƺͱ�ȫʱ��securitytimeһ��ʹ��RSA���ܣ�����Կ�����Ĵ���CA
              RSA rsa = new RSA();
              RSAPublicKey pubKey = (RSAPublicKey)request.getSession().getAttribute("CA_pubkey");
      		  String ming_after1Hash = after1Hash;
      		  String ming_userid = userID;
      		  String ming_filename = FileName;
      		  String ming_setime = SeTime;
      		  //�����CAǰ����
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
			
      		////����CA_yanzheng���ֵ�doPost����
	     	CA_yanzheng ca = new CA_yanzheng();
	     	ca.doPost(request,response);
	     	
	     	//��CA�����ռӸ���ʱ��������ݣ�����
	     	String mi_afterTimeStamp = String.valueOf(request.getSession().getAttribute("yanzheng_afterCA_Footprint"));
	     	String mi_unixtimestamp = String.valueOf(request.getSession().getAttribute("yanzheng_afterCA_timestamp"));
	     	RSA rsa2 = new RSA();
			RSAPrivateKey priKey2 = (RSAPrivateKey)request.getSession().getAttribute("CA_prikey");
			try {
				ming_afterTimeStamp = rsa2.decryptByPrivateKey(mi_afterTimeStamp, priKey2);
				ming_unixtimestamp = rsa2.decryptByPrivateKey(mi_unixtimestamp, priKey2);
				System.out.println("��CA���Ӹ���ʱ����󴫻ص����Ľ��ܺ�"+ming_afterTimeStamp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      		
      		

            
            
            SqlCourse sql2 = new SqlCourse();
            String CertificateCode = sql2.getCertificateCodebyKey(userID,SeTime).trim();
            if(CertificateCode.equals("noresult")){
            	fail = 1;
            	session.setAttribute("fail_reason", "未在CA数据库中找到您的保全信息");
            }
            else{
            System.out.println("��ȷ���ļ�ָ�ƣ�"+CertificateCode); 
            session.setAttribute("yanzheng_CerCode_data", ming_afterTimeStamp);
            session.setAttribute("yanzheng_CerCode_time", "未选择认证");
            session.setAttribute("yanzheng_CerCode_device", "未选择认证");
            
            //��TimeNotes��ȡSendtoCATime��GetfromCATime
            SqlPost newTimeNotes = new SqlPost();
            String SendtoCATime = newTimeNotes.getsendtoCAtimeBykey(userID,SeTime);
            String GetfromCATime = newTimeNotes.getgetfromCAtimeBykey(userID,SeTime);
            if((SendtoCATime.equals("noresult"))|(GetfromCATime.equals("noresult"))){
            	fail = 1;
            	session.setAttribute("fail_reason", "未在本平台数据库中找到您的保全信息");
            }
            else{
			CA newca = new CA();
			String newSendtoCATime = newca.dateToTimestamp(SendtoCATime);
			String newGetfromCATime = newca.dateToTimestamp(GetfromCATime);
			unixSendtoCATime = Integer.parseInt(newSendtoCATime);
			unixGetfromCATime = Integer.parseInt(newGetfromCATime);
            }
            
            
            //��֤����ָ��
            if(ming_afterTimeStamp.equals(CertificateCode)){
		       //�ļ���ָ����֤�ɹ����ٵ�CA�����ݿ���ȥ����ʱ����Բ���
            	SqlCA newCANotes = new SqlCA();
            	int unixtimestmap = Integer.parseInt(ming_unixtimestamp);
                //Ҫ����   sendtoCAtime �� unixtimestmap �� getfromCAtime
            	if((unixSendtoCATime<=unixtimestmap)&(unixtimestmap<=unixGetfromCATime)){
                	//ʱ���Ҳ��
                	Timestamp yanzhengTime = new Timestamp(System.currentTimeMillis()); 
                	String yanzheng_time = String.valueOf(yanzhengTime);
                	session.setAttribute("yanzheng_Time", yanzheng_time);
            	}
            	else{  
            		fail = 1;
            		session.setAttribute("fail_reason", "时间戳不在合理范围内，怀疑系统遭到攻击，认证过程作废");
            	}
            }
            else{ 
            	fail = 1;
    		    session.setAttribute("fail_reason", "数据指纹与正确指纹不符");
    		    }
            }
            
            
            
            DataCertificateCode = ming_afterTimeStamp;
             //只认证数据的if结束了
            }
           else {   }
            
             
            	
            	//����ͻ�ѡ��ֻ��֤�ļ����ʱ�䡱
                if(AuthenticationType.equals("only_time")){
                	
                	String ming_OnlyTime_userid = userID;
            		String ming_OnlyTime_setime = SeTime;
            		
            		
            		SqlManager sql=new SqlManager();
       			 User UserInfor=new User();
       			 UserInfor=sql.getUserById(userID);
       			 String user_gender = UserInfor.getUserGender();

       	 
       			 session.setAttribute("UserInfor", UserInfor);
       			 session.setAttribute("UserGender", user_gender);
                	
            		//取得文件最后修改时间
                	//String FileFinishTime = "2016-07-30";
            		//FilesHandling filehandle = new FilesHandling();
            		//String FileFinishTime = filehandle.GetFileFinishTime(UserFile);
       			     
            		
                	String OnlyTime_before1Hash = DataCertificateCode + FileFinishTime;
                	String OnlyTime_after1Hash = newcertificate.doHash(OnlyTime_before1Hash);
                	System.out.println("看一下数据指纹："+DataCertificateCode);
                	
                	//CA����
                	 //����CA_generatekeyģ�������ɹ�˽Կ
      	          CA_generatekey ca_generatekey = new CA_generatekey();
      	          ca_generatekey.doPost(request,response);
      	          //��after2Hash���û�ID���ļ����ƺͱ�ȫʱ��securitytimeһ��ʹ��RSA���ܣ�����Կ�����Ĵ���CA
                    RSA rsa = new RSA();
                    RSAPublicKey pubKey = (RSAPublicKey)request.getSession().getAttribute("CA_pubkey");
            		  String ming_OnlyTime_after1Hash = OnlyTime_after1Hash;
            		  //�����CAǰ����
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
      			
            		////����CA_yanzheng���ֵ�doPost����
      	     	CA_yanzheng_OnlyTime ca_time = new CA_yanzheng_OnlyTime();
      	     	ca_time.doPost(request,response);
      	     	
      	     	//��CA�����ռӸ���ʱ��������ݣ�����
      	     	String mi_afterTimeStamp_time = String.valueOf(request.getSession().getAttribute("yanzheng_time_afterCA_Footprint"));
      	     	String mi_unixtimestamp_time = String.valueOf(request.getSession().getAttribute("yanzheng_time_afterCA_timestamp"));
      	     	RSA rsa2 = new RSA();
      			RSAPrivateKey priKey2 = (RSAPrivateKey)request.getSession().getAttribute("CA_prikey");
      			
      			String ming_afterTimeStamp_time = null;
      			String ming_unixtimestamp_time = null;
      			try {
      				ming_afterTimeStamp_time = rsa2.decryptByPrivateKey(mi_afterTimeStamp_time, priKey2);
      				ming_unixtimestamp_time = rsa2.decryptByPrivateKey(mi_unixtimestamp_time, priKey2);
      				System.out.println("认证得到的原文指纹："+ming_afterTimeStamp_time);
      			} catch (Exception e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}

      			
                  SqlCourse sql2 = new SqlCourse();
                  String CertificateCode_time = sql2.getCertificateCode_TimebyKey(userID,SeTime).trim();
                  if(CertificateCode_time.equals("noresult")){
                  	fail = 1;
                  	session.setAttribute("fail_reason", "未在CA数据库中找到您的保全信息");
                  }
                  else{
                  System.out.println("正确的原文指纹："+CertificateCode_time); 
                  session.setAttribute("yanzheng_CerCode_time", ming_afterTimeStamp_time);
                  session.setAttribute("yanzheng_CerCode_data", "未选择认证");
                  session.setAttribute("yanzheng_CerCode_device", "未选择认证");
                  
                  
                  //��֤����ָ��
                  if(ming_afterTimeStamp_time.equals(CertificateCode_time)){
                      	Timestamp yanzhengTime = new Timestamp(System.currentTimeMillis()); 
                      	String yanzheng_time = String.valueOf(yanzhengTime);
                      	session.setAttribute("yanzheng_Time", yanzheng_time);
                  }
                  else{ 
                  	fail = 1;
          		    session.setAttribute("fail_reason", "原文指纹不符，认证失败");
          		    }
                  }
                	
                //认证时间的if结束了
                }
                else{   }
                
            	
            	
            	
            	
            	//����ͻ�ѡ��ֻ��֤�ļ���Դ��
                if(AuthenticationType.equals("only_device")){
                	
                	String ming_OnlyDevice_userid = userID;
            		String ming_OnlyDevice_setime = SeTime;
                	
                	//获取设备号
            		//String FileDeviceNumber = "486";
            		//UUID uuid=UUID.randomUUID();
            		//String FileDeviceNumber = uuid.toString();
            	
            		System.out.println("唯一设备号："+FileDeviceNumber);
                	
            		String OnlyDevice_before1Hash = DataCertificateCode + FileDeviceNumber;
            		String OnlyDevice_after1Hash = newcertificate.doHash(OnlyDevice_before1Hash);
                	
                	//CA����
                	 //����CA_generatekeyģ�������ɹ�˽Կ
      	          CA_generatekey ca_generatekey = new CA_generatekey();
      	          ca_generatekey.doPost(request,response);
      	          //��after2Hash���û�ID���ļ����ƺͱ�ȫʱ��securitytimeһ��ʹ��RSA���ܣ�����Կ�����Ĵ���CA
                    RSA rsa = new RSA();
                    RSAPublicKey pubKey = (RSAPublicKey)request.getSession().getAttribute("CA_pubkey");
            		  String ming_OnlyDevice_after1Hash = OnlyDevice_after1Hash;
            		  //�����CAǰ����
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
      			
            		////����CA_yanzheng���ֵ�doPost����
      	     	CA_yanzheng_OnlyDevice ca_device = new CA_yanzheng_OnlyDevice();
      	     	ca_device.doPost(request,response);
      	     	
      	     	//��CA�����ռӸ���ʱ��������ݣ�����
      	     	String mi_afterTimeStamp_device = String.valueOf(request.getSession().getAttribute("yanzheng_device_afterCA_Footprint"));
      	     	String mi_unixtimestamp_device = String.valueOf(request.getSession().getAttribute("yanzheng_device_afterCA_timestamp"));
      	     	RSA rsa2 = new RSA();
      			RSAPrivateKey priKey2 = (RSAPrivateKey)request.getSession().getAttribute("CA_prikey");
      			
      			String ming_afterTimeStamp_device = null;
      			String ming_unixtimestamp_device = null;
      			try {
      				ming_afterTimeStamp_device = rsa2.decryptByPrivateKey(mi_afterTimeStamp_device, priKey2);
      				ming_unixtimestamp_device = rsa2.decryptByPrivateKey(mi_unixtimestamp_device, priKey2);
      				System.out.println("��CA���Ӹ���ʱ����󴫻ص����Ľ��ܺ�"+ming_afterTimeStamp_device);
      			} catch (Exception e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}

      			
                  SqlCourse sql2 = new SqlCourse();
                  String CertificateCode_device = sql2.getCertificateCode_DevicebyKey(userID,SeTime).trim();
                  if(CertificateCode_device.equals("noresult")){
                  	fail = 1;
                  	session.setAttribute("fail_reason", "未在CA数据库中找到您的保全信息");
                  }
                  else{
                  System.out.println("��ȷ���ļ�ָ�ƣ�"+CertificateCode_device); 
                  session.setAttribute("yanzheng_CerCode_device", ming_afterTimeStamp_device);
                  session.setAttribute("yanzheng_CerCode_data", "未选择认证");
                  session.setAttribute("yanzheng_CerCode_time", "未选择认证");
                  
                  
                  
                  //��֤����ָ��
                  if(ming_afterTimeStamp_device.equals(CertificateCode_device)){
                      	Timestamp yanzhengTime = new Timestamp(System.currentTimeMillis()); 
                      	String yanzheng_time = String.valueOf(yanzhengTime);
                      	session.setAttribute("yanzheng_Time", yanzheng_time);
                  }
                  else{ 
                  	fail = 1;
          		    session.setAttribute("fail_reason", "源文指纹不符，认证失败");
          		    }
                  }
                	
                	
                	//认证设备号的if结束了
                }
                else{   }
            	
            	
            	
            	
            	//����ͻ�ѡ����֤�ļ����ʱ�����Դ��
               if(AuthenticationType.equals("time_and_device")){
                	String ming_TimeDevice_userid = userID;
            		String ming_TimeDevice_setime = SeTime;
            		
            		
            		SqlManager sql=new SqlManager();
       			 User UserInfor=new User();
       			 UserInfor=sql.getUserById(userID);
       			 String user_gender = UserInfor.getUserGender();

       	 
       			 session.setAttribute("UserInfor", UserInfor);
       			 session.setAttribute("UserGender", user_gender);
       			 
                	
                	//最后修改时间
            		//String FileFinishTime = "2016-07-30";
            		//FilesHandling filehandle = new FilesHandling();
            		//String FileFinishTime = filehandle.GetFileFinishTime(UserFile);
       			      
            		
            		//唯一设备号
                	//String FileDeviceNumber = "486";
                	//UUID uuid=UUID.randomUUID();
            		//String FileDeviceNumber = uuid.toString();
            		
            		System.out.println("唯一设备号："+FileDeviceNumber);
                	
                	
            		String HaveTime_before1Hash = DataCertificateCode + FileFinishTime;
            		String HaveDevice_before1Hash = DataCertificateCode + FileDeviceNumber;
                	String TimeDevice_after1Hash_time = newcertificate.doHash(HaveTime_before1Hash);
                	String TimeDevice_after1Hash_device = newcertificate.doHash(HaveDevice_before1Hash);
                	
                	//CA����
                	 //����CA_generatekeyģ�������ɹ�˽Կ
      	          CA_generatekey ca_generatekey = new CA_generatekey();
      	          ca_generatekey.doPost(request,response);
      	          //��after2Hash���û�ID���ļ����ƺͱ�ȫʱ��securitytimeһ��ʹ��RSA���ܣ�����Կ�����Ĵ���CA
                    RSA rsa = new RSA();
                    RSAPublicKey pubKey = (RSAPublicKey)request.getSession().getAttribute("CA_pubkey");
            		  String ming_TimeDevice_after1Hash_time = TimeDevice_after1Hash_time;
            		  String ming_TimeDevice_after1Hash_device = TimeDevice_after1Hash_device;
            		  //�����CAǰ����
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
      			
            		////����CA_yanzheng���ֵ�doPost����
      	     	CA_yanzheng_TimeDevice ca_timedevice = new CA_yanzheng_TimeDevice();
      	     	ca_timedevice.doPost(request,response);
      	     	
      	     	//��CA�����ռӸ���ʱ��������ݣ�����
      	     	String mi_afterTimeStamp_time = String.valueOf(request.getSession().getAttribute("yanzheng_timedevice_afterCA_Footprint_time"));
      	     	String mi_afterTimeStamp_device = String.valueOf(request.getSession().getAttribute("yanzheng_timedevice_afterCA_Footprint_device"));
      	     
      	     	RSA rsa2 = new RSA();
      			RSAPrivateKey priKey2 = (RSAPrivateKey)request.getSession().getAttribute("CA_prikey");
      			
      			String ming_afterTimeStamp_time = null;
      			String ming_afterTimeStamp_device = null;
      			try {
      				ming_afterTimeStamp_time = rsa2.decryptByPrivateKey(mi_afterTimeStamp_time, priKey2);
      				ming_afterTimeStamp_device = rsa2.decryptByPrivateKey(mi_afterTimeStamp_device, priKey2);
      				System.out.println("��CA���Ӹ���ʱ����󴫻ص����Ľ��ܺ�"+ming_afterTimeStamp_time);
      			} catch (Exception e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}

      			
                  SqlCourse sql2 = new SqlCourse();
                  String CertificateCode_time = sql2.getCertificateCode_TimebyKey(userID,SeTime).trim();
                  String CertificateCode_device = sql2.getCertificateCode_DevicebyKey(userID,SeTime).trim();
                  if(CertificateCode_time.equals("noresult")){
                  	fail = 1;
                  	session.setAttribute("fail_reason", "未在CA数据库中找到您的保全信息");
                  }
                  else{
                	  
                  System.out.println("��ȷ���ļ�ָ�ƣ�"+CertificateCode_time); 
                  session.setAttribute("yanzheng_CerCode_time", ming_afterTimeStamp_time);
                  session.setAttribute("yanzheng_CerCode_device", ming_afterTimeStamp_device);
                  session.setAttribute("yanzheng_CerCode_data", "未选择认证֤");
                  
                  
                  //��֤����ָ��
                  if(ming_afterTimeStamp_time.equals(CertificateCode_time)&&ming_afterTimeStamp_device.equals(CertificateCode_device)){

                	    Timestamp yanzhengTime = new Timestamp(System.currentTimeMillis()); 
                      	String yanzheng_time = String.valueOf(yanzhengTime);
                      	session.setAttribute("yanzheng_Time", yanzheng_time);
                  }
                  else if(ming_afterTimeStamp_time.equals(CertificateCode_time)&&(ming_afterTimeStamp_device.equals(CertificateCode_device)==false)){ 
                  	fail = 1;
          		    session.setAttribute("fail_reason", "原文认证成功，但源文认证失败");
          		    }
                  else if((ming_afterTimeStamp_time.equals(CertificateCode_time)==false)&&ming_afterTimeStamp_device.equals(CertificateCode_device)){
                	fail = 1;
            		session.setAttribute("fail_reason", "源文认证成功，但原文认证失败");
                  }
                  else{
                	fail = 1;
              		session.setAttribute("fail_reason", "源文认证与原文认证均失败");
                  }
                  
       
                  }
  	
                //认证时间和设备号的if结束了
                }
               else{   }
            	
            	
            	
            	
            	
            	//����ͻ�ѡ��ȫ����֤��
                if(AuthenticationType.equals("authenticate_all")){
	
                	String ming_All_userid = userID;
            		String ming_All_setime = SeTime;
                	
                	//��ȡ�ļ����ʱ��
            		//String FileFinishTime = "2016-07-30";
            		//FilesHandling filehandle = new FilesHandling();
            		//String FileFinishTime = filehandle.GetFileFinishTime(UserFile);
            		
            		
            		//��ȡ�豸Ψһ��ʶ��
                	//String FileDeviceNumber = "486";
                	//UUID uuid=UUID.randomUUID();
            		//String FileDeviceNumber = uuid.toString();
            		
            		System.out.println("唯一设备号："+FileDeviceNumber);
            		
            		//��ȡ�ļ�����
                	 SqlManager sql=new SqlManager();
        			 User UserInfor=new User();
        			 UserInfor=sql.getUserById(userID);
        			 String user_gender = UserInfor.getUserGender();

        	 
        			 session.setAttribute("UserInfor", UserInfor);
        			 session.setAttribute("UserGender", user_gender);
        			 
        			 //��ʼ��ԭ���ܹ���
        			 //��ȡ�û�ע��ʱ��
        			 String RegisterTime = sql.getRegisterTimeByID(userID);
        			 //������Ϣ
        		     String UserInformation = userID + RegisterTime ;
        		     String TimeInformation = SeTime;
        		     String FileInformation = FileName + userfileString;
        		     //������Ϣ�ķֱ����
        			 String UserInformation_afterHash = newcertificate.doHash(UserInformation);
        			 String TimeInformation_afterHash = newcertificate.doHash(TimeInformation);
        			 String FileInformation_afterHash = newcertificate.doHash(FileInformation);
        			 //������Ϣ����
        			 String before1Hash = UserInformation_afterHash + TimeInformation_afterHash + FileInformation_afterHash;
                	
        		     
                	//��һ�μ���
        			String HaveTime_before1Hash = DataCertificateCode + FileFinishTime;
             		String HaveDevice_before1Hash = DataCertificateCode + FileDeviceNumber;
                	String All_after1Hash_time = newcertificate.doHash(HaveTime_before1Hash);
                	String All_after1Hash_device = newcertificate.doHash(HaveDevice_before1Hash);
                	String All_after1Hash_data = newcertificate.doHash(before1Hash);
                	
                	//CA����
                	 //����CA_generatekeyģ�������ɹ�˽Կ
      	          CA_generatekey ca_generatekey = new CA_generatekey();
      	          ca_generatekey.doPost(request,response);
      	          //��after2Hash���û�ID���ļ����ƺͱ�ȫʱ��securitytimeһ��ʹ��RSA���ܣ�����Կ�����Ĵ���CA
                    RSA rsa = new RSA();
                    RSAPublicKey pubKey = (RSAPublicKey)request.getSession().getAttribute("CA_pubkey");
            		  String ming_All_after1Hash_time = All_after1Hash_time;
            		  String ming_All_after1Hash_device = All_after1Hash_device;
            		  String ming_All_after1Hash_data = All_after1Hash_data;
            		  //�����CAǰ����
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
      			
            		////����CA_yanzheng���ֵ�doPost����
      	     	CA_yanzheng_All ca_all = new CA_yanzheng_All();
      	     	ca_all.doPost(request,response);
      	     	
      	     	//��CA�����ռӸ���ʱ��������ݣ�����
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
      				System.out.println("��CA���Ӹ���ʱ����󴫻ص����Ľ��ܺ�"+ming_afterTimeStamp_time);
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
                  	session.setAttribute("fail_reason", "未在本平台数据库中找到您的认证信息");
                  }
                  else{
                	  
                  System.out.println("��ȷ���ļ�ָ�ƣ�"+CertificateCode_data); 
                  session.setAttribute("yanzheng_CerCode_data", ming_afterTimeStamp_data);
                  session.setAttribute("yanzheng_CerCode_time", ming_afterTimeStamp_time);
                  session.setAttribute("yanzheng_CerCode_device", ming_afterTimeStamp_device);
                  
                  
                //��TimeNotes��ȡSendtoCATime��GetfromCATime
                  SqlPost newTimeNotes = new SqlPost();
                  String SendtoCATime = newTimeNotes.getsendtoCAtimeBykey(userID,SeTime);
                  String GetfromCATime = newTimeNotes.getgetfromCAtimeBykey(userID,SeTime);
                  if((SendtoCATime.equals("noresult"))|(GetfromCATime.equals("noresult"))){
                  	fail = 1;
                  	session.setAttribute("fail_reason", "未在CA数据库中找到您的保全信息");
                  }
                  else{
      			CA newca = new CA();
      			String newSendtoCATime = newca.dateToTimestamp(SendtoCATime);
      			String newGetfromCATime = newca.dateToTimestamp(GetfromCATime);
      			unixSendtoCATime = Integer.parseInt(newSendtoCATime);
      			unixGetfromCATime = Integer.parseInt(newGetfromCATime);
                  }
                  
                  
                  //��֤����ָ��
                  if(ming_afterTimeStamp_data.equals(CertificateCode_data)){
      		       //�ļ���ָ����֤�ɹ����ٵ�CA�����ݿ���ȥ����ʱ����Բ���
                  	SqlCA newCANotes = new SqlCA();
                  	int unixtimestmap = Integer.parseInt(ming_afterTimeStamp_unixtimestamp);
                      //Ҫ����   sendtoCAtime �� unixtimestmap �� getfromCAtime
                  	if((unixSendtoCATime<=unixtimestmap)&(unixtimestmap<=unixGetfromCATime)){
                      	//ʱ���Ҳ��
                  		
                  		
                  		
                  		//��ʼ���ʱ������Դ��ָ�Ʊȶ�
                  		 if(ming_afterTimeStamp_time.equals(CertificateCode_time)&&ming_afterTimeStamp_device.equals(CertificateCode_device)){

                     	    Timestamp yanzhengTime = new Timestamp(System.currentTimeMillis()); 
                           	String yanzheng_time = String.valueOf(yanzhengTime);
                           	session.setAttribute("yanzheng_Time", yanzheng_time);
                       }
                       else if(ming_afterTimeStamp_time.equals(CertificateCode_time)&&(ming_afterTimeStamp_device.equals(CertificateCode_device)==false)){ 
                       	fail = 1;
               		    session.setAttribute("fail_reason", "数据认证与原文认证成功，但源文认证失败");
               		    }
                       else if((ming_afterTimeStamp_time.equals(CertificateCode_time)==false)&&ming_afterTimeStamp_device.equals(CertificateCode_device)){
                     	fail = 1;
                 		session.setAttribute("fail_reason", "数据认证与源文认证成功，但原文认证失败");
                       }
                       else{
                     	fail = 1;
                   		session.setAttribute("fail_reason", "数据认证成功，原文认证与源文认证均失败");
                       }
                        //���ʱ������Դָ�ƱȶԽ���

                  		 
                  	}
                  	else{  
                  		fail = 1;
                  		session.setAttribute("fail_reason", "时间戳不在合理范围内，怀疑系统遭到攻击，认证过程作废");
                  	}
                  }
                  else{ 
                  	fail = 1;
          		    session.setAttribute("fail_reason", "数据指纹与正确指纹不符，认证失败");
          		    }
                  }
                	
                	
                //认证全部的if结束了
                }
                else{   }
            	
  
            	
            	
            	
            	
            }
            
            
       	
            else{
            	//��֤ʧ��
            	fail = 1;
            	session.setAttribute("fail_reason", "您的ID没有在本系统注册！");
            }
            
            
            
            
            //删除文件
            newcertificate.deleteFile("C:\\Tomcat\\webapps\\XYbaoquan\\upload\\"+FileNameWithFormat);
            
            if(fail==0){
            	response.setCharacterEncoding("UTF_8");//设置Response的编码方式为UTF-8  
            	 response.setHeader("Content-type","text/html;charset=UTF-8");
            	 PrintWriter writer = response.getWriter();  
            	 writer.write("恭喜您，认证成功！"); 

            }
            else{
            	response.setCharacterEncoding("UTF_8");//设置Response的编码方式为UTF-8  
           	    response.setHeader("Content-type","text/html;charset=UTF-8");
           	    PrintWriter writer = response.getWriter();  
            	String failreason = String.valueOf(request.getSession().getAttribute("fail_reason"));
            	writer.write(failreason);
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
