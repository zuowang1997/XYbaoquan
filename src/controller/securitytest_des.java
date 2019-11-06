package controller;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import model.*;
import controller.CAsimulation;
import library.*;


public class securitytest_des extends HttpServlet {

	
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
	public securitytest_des() {
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
		    HttpSession session = request.getSession();
		    
		    
		    String userID = null;
		    String filename = null;
		    String securitysource = null;
		    String certificateholder = null;
		    String securitytype = null;
		    String FileNameWithFormat = null;
		    String filetype = null;
		    
		    
		    
		  //接收表单参数
		    boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
	   	    response.setContentType("text/html;charset=UTF-8");  
	        if (isMultipart) {  
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
		    
		    
		    
		    //得到参数   
	        userID = String.valueOf(request.getSession().getAttribute("security_id"));
		    securitysource = String.valueOf(request.getSession().getAttribute("security_source"));
		    certificateholder = String.valueOf(request.getSession().getAttribute("certificate_holder"));
		    securitytype = String.valueOf(request.getSession().getAttribute("security_type"));
		    FileNameWithFormat = String.valueOf(request.getSession().getAttribute("FileNameWithFormat"));
		    filetype = FileNameWithFormat.substring(FileNameWithFormat.length()-4,FileNameWithFormat.length());
		    filename = FileNameWithFormat.substring(0,FileNameWithFormat.length()-4);
		    //去掉文件名前面的des
		    filename = filename.substring(3);
		    System.out.println("文件名："+filename);
		    
		    
		    

			
			//用DES解密数据
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


			
		    
		    
		    String finishtime = null;
		    String sourcenumber = null;
		    if(securitytype.equals("data")){  }
		    else if(securitytype.equals("dataandtime")){
		    	finishtime = String.valueOf(request.getSession().getAttribute("file_finishtime"));
		    }
		    else if(securitytype.equals("dataanddevice")){
		    	sourcenumber = String.valueOf(request.getSession().getAttribute("file_sourcenumber"));
		    }
		    else if(securitytype.equals("all")){
		    	finishtime = String.valueOf(request.getSession().getAttribute("file_finishtime"));
		    	sourcenumber = String.valueOf(request.getSession().getAttribute("file_sourcenumber"));
		    }
		    else {  }
		    
		    String securityString = null;
		    SqlManager sqlmanager = new SqlManager();
		    String RegisterTime = sqlmanager.getRegisterTimeByID(userID);
		    boolean b = newcertificate.checkid(userID);
		    

		    
		    //判断要保全的内容是文字还是图片
		    String WORDorPIC = filetype;
		    if( WORDorPIC.equals(".txt") )
		    {
		    	System.out.println("要保全的数据是文字形式的！");
		    	securityString = file_decryped_string;
		    }
		    else if( WORDorPIC.equals(".pdf") )
		    {
		    	System.out.println("要保全的数据是pdf形式的！");
		    	securityString = file_decryped_string;
		    }
		    else if( (WORDorPIC.equals(".jpg")) | (WORDorPIC.equals(".png")) )
		    {
		    	System.out.println("要保全的数据是图片形式的！");
		    	securityString = file_decryped_string;
		    }
		    else{
		    	System.out.println("不知道是什么类型了。。。。");
		    	securityString = file_decryped_string;
		    }
		    
		    
		    

		    
		    
		    
		    Timestamp SecurityTime2 = null;
		    String after1Hash = null;
		    Timestamp SendtoCATime5 = null;
		    Timestamp GetfromCATime6 = null;
		    Timestamp fate1 = new Timestamp(System.currentTimeMillis()); 
		    String ming_afterTimeStamp = null;
		    String ming_afterCA_userid = null;
		    String ming_afterCA_setime = null;
		    String ming_afterCA_finishtime = null;
		    String ming_afterCA_sourcenumber = null;
		    String ming_afterCA_all = null;
		    
		    String UserInformation = null;
	    	String SecurityTime = null;
	    	String FileInformation = null;
   
		    
		    
		    
		    
		    //模拟保全操作
		    if (b == true){
		    	
		    	//整合三个信息
		    	UserInformation = userID + RegisterTime ;
		    	SecurityTime = null;
		    	FileInformation = filename + securityString;
		    	
		    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
		    	//取时间——SecurityTime
				SecurityTime2 = new Timestamp(System.currentTimeMillis()); 
				SecurityTime = String.valueOf(SecurityTime2);
				//调整SecurityTime的格式
				int timelong = SecurityTime.length();
				if(timelong == 21){
					SecurityTime = SecurityTime+"00"; 
				}
                if(timelong == 22){
                	SecurityTime = SecurityTime+"0"; 
				}
				
				//三个信息的分别加密
				String UserInformation_afterHash = newcertificate.doHash(UserInformation);
				String TimeInformation_afterHash = newcertificate.doHash(SecurityTime);
				String FileInformation_afterHash = newcertificate.doHash(FileInformation);
				System.out.println(UserInformation+SecurityTime+FileInformation);
				
				//三个信息整合再加密
				String before1Hash = UserInformation_afterHash + TimeInformation_afterHash + FileInformation_afterHash;
				after1Hash = newcertificate.doHash(before1Hash);
		    	session.setAttribute("after1Hash", after1Hash);
		    	
		   		     	
		           
		          //调用CA_generatekey模块来生成公私钥
		          CA_generatekey ca_generatekey = new CA_generatekey();
		          ca_generatekey.doPost(request,response);
		     	  //将after2Hash、用户ID、文件名称和保全时间securitytime一起使用RSA加密，将公钥和密文传给CA
                  RSA rsa = new RSA();
                  RSAPublicKey pubKey = (RSAPublicKey)request.getSession().getAttribute("CA_pubkey");
          		  String ming_after1Hash = after1Hash;
          		  String ming_userid = userID;
          		  String ming_filename = filename;
          		  String ming_setime = SecurityTime;
          				  
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
          		session.setAttribute("CA_pubkey", pubKey);
          		session.setAttribute("mi_after1Hash", mi_after1Hash);	
          		session.setAttribute("mi_userid", mi_userid);	
          		session.setAttribute("mi_filename", mi_filename);	
          		session.setAttribute("mi_setime", mi_setime);	
	           
		           
          		
		     	//调用CA部分的doPost代码
		     	CAsimulation ca = new CAsimulation();		     	
		     	//取时间——SendtoCATime
		     	SendtoCATime5 = new Timestamp(System.currentTimeMillis()); 
		     	ca.doPost(request,response);

		     	
		     	//从CA处接收加盖了时间戳的数据，解密
		     	String mi_afterTimeStamp = String.valueOf(request.getSession().getAttribute("afterCA_Footprint"));
		     	String mi_afterCA_userid = String.valueOf(request.getSession().getAttribute("afterCA_userid"));
		     	String mi_afterCA_setime = String.valueOf(request.getSession().getAttribute("afterCA_setime"));
		     	RSA rsa2 = new RSA();
				RSAPrivateKey priKey2 = (RSAPrivateKey)request.getSession().getAttribute("CA_prikey");
				try {
					ming_afterTimeStamp = rsa2.decryptByPrivateKey(mi_afterTimeStamp, priKey2);
					ming_afterCA_userid = rsa2.decryptByPrivateKey(mi_afterCA_userid, priKey2);
					ming_afterCA_setime = rsa2.decryptByPrivateKey(mi_afterCA_setime, priKey2);
					System.out.println("从CA处加盖了时间戳后传回的密文解密后："+ming_afterTimeStamp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	
				
		     	//取文件指纹
		    	session.setAttribute("securityresulet", ming_afterTimeStamp);
		    	//取时间——GetfromCATime
		    	GetfromCATime6 = new Timestamp(System.currentTimeMillis()); 
		    }

		    else
		    {
		    	session.setAttribute("securityresulet", "用户还没有注册");
		    }
		    
		    
		    
		    
		    session.setAttribute("UserID", userID);
		    session.setAttribute("securitytime", SecurityTime2);
		    session.setAttribute("FileAbstract", after1Hash);
		    session.setAttribute("FileName", filename);
		    session.setAttribute("Files", securityString);
		    
		    
		    
		    
		    
		    //如果选择了保全修改时间
		    if((securitytype.equals("dataandtime")) | (securitytype.equals("all"))){
		    	String finishtime_dataandtime =  ming_afterTimeStamp + finishtime;
		    	String finishtime_after1Hash = newcertificate.doHash(finishtime_dataandtime);
		    	session.setAttribute("after1Hash_time", finishtime_after1Hash);
		    	
		    	 //调用CA_generatekey模块来生成公私钥
		          CA_generatekey ca_generatekey_time = new CA_generatekey();
		          ca_generatekey_time.doPost(request,response);
		     	  //使用RSA加密，将公钥和密文传给CA
                RSA rsa_time = new RSA();
                RSAPublicKey pubKey = (RSAPublicKey)request.getSession().getAttribute("CA_pubkey");
        		  String ming_finishtime = finishtime_after1Hash;
        				  
        		  //传输给CA前加密
        		  String mi_finishtime = null;
				try {
					mi_finishtime = rsa_time.encryptByPublicKey(ming_finishtime, pubKey);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		session.setAttribute("CA_pubkey", pubKey);
        		session.setAttribute("mi_after1Hash_time", mi_finishtime);		
	           

		     	//调用CA部分的doPost代码
		     	CAsimulation_time ca = new CAsimulation_time();		     	
		     	ca.doPost(request,response);

		     	
		     	//从CA处接收加盖了时间戳的数据，解密
		     	String mi_afterTimeStamp_time = String.valueOf(request.getSession().getAttribute("afterCA_Footprint_time"));
		     	RSA rsa2_time = new RSA();
				RSAPrivateKey priKey2 = (RSAPrivateKey)request.getSession().getAttribute("CA_prikey");
				try {
					ming_afterCA_finishtime = rsa2_time.decryptByPrivateKey(mi_afterTimeStamp_time, priKey2);
					System.out.println("从CA处加盖了时间戳后传回的密文解密后："+ming_afterCA_finishtime);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    }
		    else{   }
		    
		    
		    
		    
		    
		    
		  //如果选择了保全来源
		    if((securitytype.equals("dataanddevice")) | (securitytype.equals("all"))){
		    	String sourcenumber_dataanddevice =  ming_afterTimeStamp + sourcenumber;
		    	String sourcenumber_after1Hash = newcertificate.doHash(sourcenumber_dataanddevice);
		    	session.setAttribute("after1Hash_device", sourcenumber_after1Hash);
		    	
		    	 //调用CA_generatekey模块来生成公私钥
		          CA_generatekey ca_generatekey_device = new CA_generatekey();
		          ca_generatekey_device.doPost(request,response);
		     	  //使用RSA加密，将公钥和密文传给CA
                RSA rsa_device = new RSA();
                RSAPublicKey pubKey = (RSAPublicKey)request.getSession().getAttribute("CA_pubkey");
        		  String ming_sourcenumber = sourcenumber_after1Hash;
        				  
        		  //传输给CA前加密
        		  String mi_sourcenumber = null;
				try {
					mi_sourcenumber = rsa_device.encryptByPublicKey(ming_sourcenumber, pubKey);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		session.setAttribute("CA_pubkey", pubKey);
        		session.setAttribute("mi_after1Hash_device", mi_sourcenumber);		
	           

		     	//调用CA部分的doPost代码
		     	CAsimulation_device ca = new CAsimulation_device();		     	
		     	ca.doPost(request,response);

		     	
		     	//从CA处接收加盖了时间戳的数据，解密
		     	String mi_afterTimeStamp_deivce = String.valueOf(request.getSession().getAttribute("afterCA_Footprint_device"));
		     	RSA rsa2_device = new RSA();
				RSAPrivateKey priKey2 = (RSAPrivateKey)request.getSession().getAttribute("CA_prikey");
				try {
					ming_afterCA_sourcenumber = rsa2_device.decryptByPrivateKey(mi_afterTimeStamp_deivce, priKey2);
					System.out.println("从CA处加盖了时间戳后传回的密文解密后："+ming_afterCA_sourcenumber);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    }
		    else{   }
		    
		    
		    
		    
		    
			  //如果选择了全部保全
		    if(securitytype.equals("all")){
		    	String sedata_all =  ming_afterTimeStamp + finishtime + sourcenumber;
		    	String sedata_after1Hash = newcertificate.doHash(sedata_all);
		    	session.setAttribute("after1Hash_all", sedata_after1Hash);
		    	
		    	 //调用CA_generatekey模块来生成公私钥
		          CA_generatekey ca_generatekey_all = new CA_generatekey();
		          ca_generatekey_all.doPost(request,response);
		     	  //使用RSA加密，将公钥和密文传给CA
                RSA rsa_all = new RSA();
                RSAPublicKey pubKey = (RSAPublicKey)request.getSession().getAttribute("CA_pubkey");
        		  String ming_sedata = sedata_after1Hash;
        				  
        		  //传输给CA前加密
        		  String mi_sedata = null;
				try {
					mi_sedata = rsa_all.encryptByPublicKey(ming_sedata, pubKey);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		session.setAttribute("CA_pubkey", pubKey);
        		session.setAttribute("mi_after1Hash_all", mi_sedata);		
	           

		     	//调用CA部分的doPost代码
		     	CAsimulation_all ca = new CAsimulation_all();		     	
		     	ca.doPost(request,response);

		     	
		     	//从CA处接收加盖了时间戳的数据，解密
		     	String mi_afterTimeStamp_all = String.valueOf(request.getSession().getAttribute("afterCA_Footprint_all"));
		     	RSA rsa2_all = new RSA();
				RSAPrivateKey priKey2 = (RSAPrivateKey)request.getSession().getAttribute("CA_prikey");
				try {
					ming_afterCA_all = rsa2_all.decryptByPrivateKey(mi_afterTimeStamp_all, priKey2);
					System.out.println("从CA处加盖了时间戳后传回的密文解密后："+ming_afterCA_all);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    }
		    else{   }
		    
		    
		    
		    
		    
		    
		    
		    
		    //添加这项纪录到【保全记录表】
		    Course newSecurityNotes = new Course();
		    newSecurityNotes.setID(ming_afterCA_userid);
		    newSecurityNotes.setSecurityTime(SecurityTime);
		    newSecurityNotes.setFileName(filename);
		    newSecurityNotes.setFileAbstract(after1Hash); 
		       //只截保全内容的前10位存
		       //String filename_qian = securityString.substring(0,10);
		    newSecurityNotes.setFiles(filename);
		    newSecurityNotes.setCertificateCode(ming_afterTimeStamp);
		    newSecurityNotes.setFileFinishTime(finishtime);
		    newSecurityNotes.setSourceNumber(sourcenumber);
		    newSecurityNotes.setFileType(filetype);
		    newSecurityNotes.setCertificateSource(securitysource);
		    newSecurityNotes.setCertificateHolder(certificateholder);
		    newSecurityNotes.setCertificateCode_Time(ming_afterCA_finishtime);
		    newSecurityNotes.setCertificateCode_Device(ming_afterCA_sourcenumber);
		    newSecurityNotes.setSecurity_Type(securitytype);
		    SqlCourse sql_Course=new SqlCourse();
            String res_Course = sql_Course.addSecurityNotes(newSecurityNotes);
		    if(res_Course != "" ){
		    	 System.out.println("成功添加记录到【保全记录表】!");
		    }
		    
		    
		    
		    
		    //整理写到文件中的时间
		       String FileSeTime = null;
		       FileSeTime = SecurityTime;
		       String sss_date=FileSeTime.substring(0,10);
		       String sss_time_1 = FileSeTime.substring(11,13);
		       String sss_time_2 = FileSeTime.substring(14,16);
		       String sss_time_3 = FileSeTime.substring(17,19);
		       String sss_time_4 = null;
		       if(FileSeTime.length() == 21){
		    	   sss_time_4 = FileSeTime.substring(20,21)+"00";
            }
		       else if(FileSeTime.length() == 22){
		    	   sss_time_4 = FileSeTime.substring(20,22)+"0";
		       }
		       else {
		    	   sss_time_4 = FileSeTime.substring(20,23);
		       }
		       String sss_finish = sss_date+sss_time_1+sss_time_2+sss_time_3+sss_time_4;
		       FileSeTime = sss_finish;
		       System.out.println("保存为文件名的时间："+FileSeTime);
		     //整理写到文件名上的时间
		       String SeTime_cername = null;
		       SeTime_cername = "_"+sss_date+"_"+sss_time_1+"_"+sss_time_2+"_"+sss_time_3;
		    
		    
		    

		    
            //制作证书图片
		    
		    Timestamp MakeCertificateTime7 = null;
		    //如果filename不长
		    if(filename.length() <= 13){
		    	
		    	if(securitytype.equals("data")){
		    		String pressTime = String.valueOf(SecurityTime2);
		    		String ErWeiMa_Data = ming_afterTimeStamp;
		    		
		    		//String aaa = request.getSession().getServletContext().getRealPath("/WebRoot/MakeCertificate");
		    		//从模板复制出来一个jpg
				    newcertificate.CopyImage("C:\\XinYouData\\muban\\muban_one.jpg","C:\\XinYouData\\MakeCertificate\\yeah.jpg");
				    //打上文字和二维码
				    newcertificate.pressText(filename,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,668);
				    newcertificate.pressText(filetype,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,630);
				    newcertificate.pressText(certificateholder,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,357,588);
				    newcertificate.pressText(securitysource,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,357,548);
				    newcertificate.pressText(pressTime,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,22,382,508);
				    //数据指纹二维码
				    newcertificate.ErWeiMa(ErWeiMa_Data);
				    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",243,480);
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
				    //以二进制流写入数据库
				    newcertificate.writeFileToDb(userID,SecurityTime,"C:/XinYouData/MakeCertificate/yeah.jpg");
				  //改名字
        		    FilesHandling filehandle = new FilesHandling();
        		    filehandle.renameFile("C:/XinYouData/MakeCertificate/yeah.jpg","C:/XinYouData/MakeCertificate/"+userID+"+"+FileSeTime+".jpg");

		            //取时间——MakeCertificateTime
				    MakeCertificateTime7 = new Timestamp(System.currentTimeMillis());
				    
				    //变成pdf形式
				    FilesHandling handle = new FilesHandling();
						try {
							handle.jpgToPdf("C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf","C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".jpg");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						handle.createPDF("C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf",userID+"+"+FileSeTime);
				    String[] files = { "C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf", "C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf"};  
			        String savepath = "C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".pdf";  
			        handle.mergePdfFiles(files, savepath);
			        
			      //发送证书？
        		    // 以byte流的方式打开文件
        		    FileInputStream ZhengShu = new FileInputStream("C:/XinYouData/MakeCertificate/"+userID+"+"+FileSeTime+".pdf"); 
        		    //得到文件大小 
        		    int i = ZhengShu.available(); 
        		    byte data[]=new byte[i]; 
        		    //读数据 
        		    ZhengShu.read(data); 
        		    String cerName = FileNameWithFormat+SeTime_cername+".cert.pdf";
        		    response.addHeader("Content-Type", "application/pdf");
        		    response.addHeader("Content-Disposition","attachment;filename="+cerName);
        		    //得到向客户端输出二进制数据的对象
        		    OutputStream toClient = response.getOutputStream(); 
        		    //输出数据 
        		    toClient.write(data); 

        		    toClient.flush(); 
        		    toClient.close(); 
        		    ZhengShu.close();
			        
			        
			      //删除yeah.jpg文件及pdf文件
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".jpg");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".pdf");
		    		
				    
		    	}
		    	else if(securitytype.equals("dataandtime")){
		    		String pressTime = String.valueOf(SecurityTime2);
		    		String ErWeiMa_Data = ming_afterTimeStamp;
		    		String ErWeiMa_Time = ming_afterCA_finishtime;
		    		
				    //从模板复制出来一个jpg
				    newcertificate.CopyImage("C:\\XinYouData\\muban\\muban_dataandtime.jpg","C:\\XinYouData\\MakeCertificate\\yeah.jpg");
				    //打上文字和二维码
				    newcertificate.pressText(filename,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,668);
				    newcertificate.pressText(filetype,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,630);
				    newcertificate.pressText(certificateholder,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,357,588);
				    newcertificate.pressText(securitysource,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,357,548);
				    newcertificate.pressText(pressTime,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,22,382,508);
				    //数据指纹二维码
				    newcertificate.ErWeiMa(ErWeiMa_Data);
				    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",135,467);
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
				    //时间指纹二维码
				    newcertificate.ErWeiMa(ErWeiMa_Time);
				    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",350,467);
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
				    //以二进制流写入数据库
				    newcertificate.writeFileToDb(userID,SecurityTime,"C:/XinYouData/MakeCertificate/yeah.jpg");
				  //改名字
        		    FilesHandling filehandle = new FilesHandling();
        		    filehandle.renameFile("C:/XinYouData/MakeCertificate/yeah.jpg","C:/XinYouData/MakeCertificate/"+userID+"+"+FileSeTime+".jpg");

		            //取时间——MakeCertificateTime
				    MakeCertificateTime7 = new Timestamp(System.currentTimeMillis()); 
				    
				  //变成pdf形式
				    FilesHandling handle = new FilesHandling();
						try {
							handle.jpgToPdf("C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf","C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".jpg");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						handle.createPDF("C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf",userID+"+"+FileSeTime);
				    String[] files = { "C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf", "C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf"};  
			        String savepath = "C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".pdf";  
			        handle.mergePdfFiles(files, savepath);
			        
			      //发送证书？
        		    // 以byte流的方式打开文件
        		    FileInputStream ZhengShu = new FileInputStream("C:/XinYouData/MakeCertificate/"+userID+"+"+FileSeTime+".pdf"); 
        		    //得到文件大小 
        		    int i = ZhengShu.available(); 
        		    byte data[]=new byte[i]; 
        		    //读数据 
        		    ZhengShu.read(data); 
        		    String cerName = FileNameWithFormat+SeTime_cername+".cert.pdf";
        		    response.addHeader("Content-Type", "application/pdf");
        		    response.addHeader("Content-Disposition","attachment;filename="+cerName);
        		    //得到向客户端输出二进制数据的对象
        		    OutputStream toClient = response.getOutputStream(); 
        		    //输出数据 
        		    toClient.write(data); 

        		    toClient.flush(); 
        		    toClient.close(); 
        		    ZhengShu.close();
			        
			      //删除yeah.jpg文件及pdf文件
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".jpg");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".pdf");
		    		
		    	}
                else if(securitytype.equals("dataanddevice")){
		    		String pressTime = String.valueOf(SecurityTime2);
		    		String ErWeiMa_Data = ming_afterTimeStamp;
		    		String ErWeiMa_Device = ming_afterCA_sourcenumber;
		    		
		    		//从模板复制出来一个jpg
				    newcertificate.CopyImage("C:\\XinYouData\\muban\\muban_dataanddevice.jpg","C:\\XinYouData\\MakeCertificate\\yeah.jpg");
				    //打上文字和二维码
				    newcertificate.pressText(filename,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,668);
				    newcertificate.pressText(filetype,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,630);
				    newcertificate.pressText(certificateholder,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,357,588);
				    newcertificate.pressText(securitysource,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,357,548);
				    newcertificate.pressText(pressTime,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,22,382,508);
				    //数据指纹二维码
				    newcertificate.ErWeiMa(ErWeiMa_Data);
				    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",135,467);
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
				    //时间指纹二维码
				    newcertificate.ErWeiMa(ErWeiMa_Device);
				    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",350,467);
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
				    //以二进制流写入数据库
				    newcertificate.writeFileToDb(userID,SecurityTime,"C:/XinYouData/MakeCertificate/yeah.jpg");
				  //改名字
        		    FilesHandling filehandle = new FilesHandling();
        		    filehandle.renameFile("C:/XinYouData/MakeCertificate/yeah.jpg","C:/XinYouData/MakeCertificate/"+userID+"+"+FileSeTime+".jpg");
        		  
		            //取时间——MakeCertificateTime
				    MakeCertificateTime7 = new Timestamp(System.currentTimeMillis()); 
				    
				  //变成pdf形式
				    FilesHandling handle = new FilesHandling();
						try {
							handle.jpgToPdf("C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf","C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".jpg");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						handle.createPDF("C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf",userID+"+"+FileSeTime);
				    String[] files = { "C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf", "C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf"};  
			        String savepath = "C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".pdf";  
			        handle.mergePdfFiles(files, savepath);
			        
			      //发送证书？
        		    // 以byte流的方式打开文件
        		    FileInputStream ZhengShu = new FileInputStream("C:/XinYouData/MakeCertificate/"+userID+"+"+FileSeTime+".pdf"); 
        		    //得到文件大小 
        		    int i = ZhengShu.available(); 
        		    byte data[]=new byte[i]; 
        		    //读数据 
        		    ZhengShu.read(data); 
        		    String cerName = FileNameWithFormat+SeTime_cername+".cert.pdf";
        		    response.addHeader("Content-Type", "application/pdf");
        		    response.addHeader("Content-Disposition","attachment;filename="+cerName);
        		    //得到向客户端输出二进制数据的对象
        		    OutputStream toClient = response.getOutputStream(); 
        		    //输出数据 
        		    toClient.write(data); 

        		    toClient.flush(); 
        		    toClient.close(); 
        		    ZhengShu.close();
			        
			      //删除yeah.jpg文件及pdf文件
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".jpg");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".pdf");
		    		
		    	}
                else if(securitytype.equals("all")){
		    		
                	String pressText = ming_afterCA_userid;
         		    String pressTime = String.valueOf(SecurityTime2);
         		    String ErWeiMa_Data = ming_afterTimeStamp;
         		    String ErWeiMa_Time = ming_afterCA_finishtime;
         		    String ErWeiMa_Device = ming_afterCA_sourcenumber;
         		    String ErWeiMa_All = ming_afterCA_all;
         		    //拼接认证时的网址
         		    //String renzheng_url = "http://localhost:8080/db_project/testYANZHENG.jap?idid="+userID+"&filename="+filename+"&setime="+pressTime+"&filetype="+filetype+"&securitysource="+securitysource+"&certificateholder="+certificateholder;
         		    //从模板复制出来一个jpg
         		    newcertificate.CopyImage("C:\\XinYouData\\muban\\muban_four.jpg","C:\\XinYouData\\MakeCertificate\\yeah.jpg");
         		    //打上文字和二维码
         		    newcertificate.pressText(filename,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,668);
         		    newcertificate.pressText(filetype,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,630);
         		    newcertificate.pressText(certificateholder,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,357,588);
         		    newcertificate.pressText(securitysource,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,357,548);
         		    newcertificate.pressText(pressTime,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,22,382,508);
         		    //数据指纹二维码
         		    newcertificate.ErWeiMa(ErWeiMa_Data);
         		    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",134,415);
         		    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
         		    //时间指纹二维码
         		    newcertificate.ErWeiMa(ErWeiMa_Time);
         		    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",351,415);
         		    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
         		    //来源指纹二维码
         		    newcertificate.ErWeiMa(ErWeiMa_Device);
         		    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",134,576);
         		    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
         		    //综合指纹二维码
         		    newcertificate.ErWeiMa(ErWeiMa_All);
         		    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",351,576);
         		    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
         		    //以二进制流写入数据库
         		    newcertificate.writeFileToDb(userID,SecurityTime,"C:/XinYouData/MakeCertificate/yeah.jpg");
         		 //改名字
        		    FilesHandling filehandle = new FilesHandling();
        		    filehandle.renameFile("C:/XinYouData/MakeCertificate/yeah.jpg","C:/XinYouData/MakeCertificate/"+userID+"+"+FileSeTime+".jpg");
        		  
                     //取时间——MakeCertificateTime
         		    MakeCertificateTime7 = new Timestamp(System.currentTimeMillis()); 
         		    
         		 //变成pdf形式
				    FilesHandling handle = new FilesHandling();
						try {
							handle.jpgToPdf("C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf","C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".jpg");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						handle.createPDF("C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf",userID+"+"+FileSeTime);
				    String[] files = { "C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf", "C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf"};  
			        String savepath = "C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".pdf";  
			        handle.mergePdfFiles(files, savepath);
			        
			      //发送证书？
        		    // 以byte流的方式打开文件
        		    FileInputStream ZhengShu = new FileInputStream("C:/XinYouData/MakeCertificate/"+userID+"+"+FileSeTime+".pdf"); 
        		    //得到文件大小 
        		    int i = ZhengShu.available(); 
        		    byte data[]=new byte[i]; 
        		    //读数据 
        		    ZhengShu.read(data); 
        		    String cerName = FileNameWithFormat+SeTime_cername+".cert.pdf";
        		    response.addHeader("Content-Type", "application/pdf");
        		    response.addHeader("Content-Disposition","attachment;filename="+cerName);
        		    //得到向客户端输出二进制数据的对象
        		    OutputStream toClient = response.getOutputStream(); 
        		    //输出数据 
        		    toClient.write(data); 

        		    toClient.flush(); 
        		    toClient.close(); 
        		    ZhengShu.close();
			        
			      //删除yeah.jpg文件及pdf文件
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".jpg");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".pdf");
		    
		    }
            else{   }
		    	
		    	
		    }
 
		    //如果filename很长
		    else{
		    	
		    	if(securitytype.equals("data")){
		    		
		    		String pressTime = String.valueOf(SecurityTime2);
		    		String ErWeiMa_Data = ming_afterTimeStamp;
		    		String filename1 = filename.substring(0,13);
        		    String filename2 = filename.substring(13,filename.length());
		    		
		    		 //从模板复制出来一个jpg
				    newcertificate.CopyImage("C:\\XinYouData\\muban\\muban_one_longname.jpg","C:\\XinYouData\\MakeCertificate\\yeah.jpg");
				    //打上文字和二维码
        		    newcertificate.pressText(filename1,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,668);
        		    newcertificate.pressText(filename2,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,638);
        		    newcertificate.pressText(filetype,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,593);
        		    newcertificate.pressText(certificateholder,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,357,552);
        		    newcertificate.pressText(securitysource,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,357,512);
        		    newcertificate.pressText(pressTime,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,22,382,472);
				    //数据指纹二维码
				    newcertificate.ErWeiMa(ErWeiMa_Data);
				    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",243,480);
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
				    //以二进制流写入数据库
				    newcertificate.writeFileToDb(userID,SecurityTime,"C:/XinYouData/MakeCertificate/yeah.jpg");
				  //改名字
        		    FilesHandling filehandle = new FilesHandling();
        		    filehandle.renameFile("C:/XinYouData/MakeCertificate/yeah.jpg","C:/XinYouData/MakeCertificate/"+userID+"+"+FileSeTime+".jpg");
        		  
		            //取时间——MakeCertificateTime
				    MakeCertificateTime7 = new Timestamp(System.currentTimeMillis());
				    
				  //变成pdf形式
				    FilesHandling handle = new FilesHandling();
						try {
							handle.jpgToPdf("C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf","C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".jpg");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						handle.createPDF("C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf",userID+"+"+FileSeTime);
				    String[] files = { "C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf", "C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf"};  
			        String savepath = "C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".pdf";  
			        handle.mergePdfFiles(files, savepath);
			        
			      //发送证书？
        		    // 以byte流的方式打开文件
        		    FileInputStream ZhengShu = new FileInputStream("C:/XinYouData/MakeCertificate/"+userID+"+"+FileSeTime+".pdf"); 
        		    //得到文件大小 
        		    int i = ZhengShu.available(); 
        		    byte data[]=new byte[i]; 
        		    //读数据 
        		    ZhengShu.read(data); 
        		    String cerName = FileNameWithFormat+SeTime_cername+".cert.pdf";
        		    response.addHeader("Content-Type", "application/pdf");
        		    response.addHeader("Content-Disposition","attachment;filename="+cerName);
        		    //得到向客户端输出二进制数据的对象
        		    OutputStream toClient = response.getOutputStream(); 
        		    //输出数据 
        		    toClient.write(data); 

        		    toClient.flush(); 
        		    toClient.close(); 
        		    ZhengShu.close();
			        
			      //删除yeah.jpg文件及pdf文件
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".jpg");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".pdf");
		    		
		    	}
		    	else if(securitytype.equals("dataandtime")){
		    		
		    		String pressTime = String.valueOf(SecurityTime2);
		    		String ErWeiMa_Data = ming_afterTimeStamp;
		    		String ErWeiMa_Time = ming_afterCA_finishtime;
		    		String filename1 = filename.substring(0,13);
        		    String filename2 = filename.substring(13,filename.length());
		    		
				    //从模板复制出来一个jpg
				    newcertificate.CopyImage("C:\\XinYouData\\muban\\muban_dataandtime_longname.jpg","C:\\XinYouData\\MakeCertificate\\yeah.jpg");
				    //打上文字和二维码
				    newcertificate.pressText(filename1,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,668);
        		    newcertificate.pressText(filename2,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,638);
        		    newcertificate.pressText(filetype,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,593);
        		    newcertificate.pressText(certificateholder,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,357,552);
        		    newcertificate.pressText(securitysource,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,357,512);
        		    newcertificate.pressText(pressTime,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,22,382,472);
				    //数据指纹二维码
				    newcertificate.ErWeiMa(ErWeiMa_Data);
				    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",134,500);
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
				    //时间指纹二维码
				    newcertificate.ErWeiMa(ErWeiMa_Time);
				    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",349,500);
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
				    //以二进制流写入数据库
				    newcertificate.writeFileToDb(userID,SecurityTime,"C:/XinYouData/MakeCertificate/yeah.jpg");
				  //改名字
        		    FilesHandling filehandle = new FilesHandling();
        		    filehandle.renameFile("C:/XinYouData/MakeCertificate/yeah.jpg","C:/XinYouData/MakeCertificate/"+userID+"+"+FileSeTime+".jpg");
        		  
		            //取时间——MakeCertificateTime
				    MakeCertificateTime7 = new Timestamp(System.currentTimeMillis()); 
				    
				  //变成pdf形式
				    FilesHandling handle = new FilesHandling();
						try {
							handle.jpgToPdf("C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf","C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".jpg");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						handle.createPDF("C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf",userID+"+"+FileSeTime);
				    String[] files = { "C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf", "C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf"};  
			        String savepath = "C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".pdf";  
			        handle.mergePdfFiles(files, savepath);
			        
			      //发送证书？
        		    // 以byte流的方式打开文件
        		    FileInputStream ZhengShu = new FileInputStream("C:/XinYouData/MakeCertificate/"+userID+"+"+FileSeTime+".pdf"); 
        		    //得到文件大小 
        		    int i = ZhengShu.available(); 
        		    byte data[]=new byte[i]; 
        		    //读数据 
        		    ZhengShu.read(data); 
        		    String cerName = FileNameWithFormat+SeTime_cername+".cert.pdf";
        		    response.addHeader("Content-Type", "application/pdf");
        		    response.addHeader("Content-Disposition","attachment;filename="+cerName);
        		    //得到向客户端输出二进制数据的对象
        		    OutputStream toClient = response.getOutputStream(); 
        		    //输出数据 
        		    toClient.write(data); 

        		    toClient.flush(); 
        		    toClient.close(); 
        		    ZhengShu.close();
			        
			      //删除yeah.jpg文件及pdf文件
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".jpg");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".pdf");
		    		
		    	}
                else if(securitytype.equals("dataanddevice")){
		    		
                	String pressTime = String.valueOf(SecurityTime2);
		    		String ErWeiMa_Data = ming_afterTimeStamp;
		    		String ErWeiMa_Device = ming_afterCA_sourcenumber;
		    		String filename1 = filename.substring(0,13);
        		    String filename2 = filename.substring(13,filename.length());
		    		
				    //从模板复制出来一个jpg
				    newcertificate.CopyImage("C:\\XinYouData\\muban\\muban_dataanddevice_longname.jpg","C:\\XinYouData\\MakeCertificate\\yeah.jpg");
				    //打上文字和二维码
				    newcertificate.pressText(filename1,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,668);
        		    newcertificate.pressText(filename2,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,638);
        		    newcertificate.pressText(filetype,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,593);
        		    newcertificate.pressText(certificateholder,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,357,552);
        		    newcertificate.pressText(securitysource,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,357,512);
        		    newcertificate.pressText(pressTime,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,22,382,472);
				    //数据指纹二维码
				    newcertificate.ErWeiMa(ErWeiMa_Data);
				    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",134,500);
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
				    //时间指纹二维码
				    newcertificate.ErWeiMa(ErWeiMa_Device);
				    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",349,500);
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
				    //以二进制流写入数据库
				    newcertificate.writeFileToDb(userID,SecurityTime,"C:/XinYouData/MakeCertificate/yeah.jpg");
				  //改名字
        		    FilesHandling filehandle = new FilesHandling();
        		    filehandle.renameFile("C:/XinYouData/MakeCertificate/yeah.jpg","C:/XinYouData/MakeCertificate/"+userID+"+"+FileSeTime+".jpg");
        		  
		            //取时间——MakeCertificateTime
				    MakeCertificateTime7 = new Timestamp(System.currentTimeMillis());
				    
				  //变成pdf形式
				    FilesHandling handle = new FilesHandling();
						try {
							handle.jpgToPdf("C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf","C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".jpg");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						handle.createPDF("C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf",userID+"+"+FileSeTime);
				    String[] files = { "C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf", "C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf"};  
			        String savepath = "C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".pdf";  
			        handle.mergePdfFiles(files, savepath);
			        
			      //发送证书？
        		    // 以byte流的方式打开文件
        		    FileInputStream ZhengShu = new FileInputStream("C:/XinYouData/MakeCertificate/"+userID+"+"+FileSeTime+".pdf"); 
        		    //得到文件大小 
        		    int i = ZhengShu.available(); 
        		    byte data[]=new byte[i]; 
        		    //读数据 
        		    ZhengShu.read(data); 
        		    String cerName = FileNameWithFormat+SeTime_cername+".cert.pdf";
        		    response.addHeader("Content-Type", "application/pdf");
        		    response.addHeader("Content-Disposition","attachment;filename="+cerName);
        		    //得到向客户端输出二进制数据的对象
        		    OutputStream toClient = response.getOutputStream(); 
        		    //输出数据 
        		    toClient.write(data); 

        		    toClient.flush(); 
        		    toClient.close(); 
        		    ZhengShu.close();
			        
			      //删除yeah.jpg文件及pdf文件
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".jpg");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".pdf");
                	
		    	}
                else if(securitytype.equals("all")){
          
        		    String pressText = ming_afterCA_userid;
        		    String pressTime = String.valueOf(SecurityTime2);
        		    String ErWeiMa_Data = ming_afterTimeStamp;
        		    String ErWeiMa_Time = ming_afterCA_finishtime;
        		    String ErWeiMa_Device = ming_afterCA_sourcenumber;
        		    String ErWeiMa_All = ming_afterCA_all;
        		    String filename1 = filename.substring(0,13);
        		    String filename2 = filename.substring(13,filename.length());
        		    //拼接认证时的网址
        		    //String renzheng_url = "http://localhost:8080/db_project/testYANZHENG.jap?idid="+userID+"&filename="+filename+"&setime="+pressTime+"&filetype="+filetype+"&securitysource="+securitysource+"&certificateholder="+certificateholder;
        		    //从模板复制出来一个jpg
        		    newcertificate.CopyImage("C:\\XinYouData\\muban\\muban_four_longname.jpg","C:\\XinYouData\\MakeCertificate\\yeah.jpg");
        		    //打上文字和二维码
        		    newcertificate.pressText(filename1,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,668);
        		    newcertificate.pressText(filename2,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,638);
        		    newcertificate.pressText(filetype,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,380,593);
        		    newcertificate.pressText(certificateholder,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,357,552);
        		    newcertificate.pressText(securitysource,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,24,357,512);
        		    newcertificate.pressText(pressTime,"C:/XinYouData/MakeCertificate/yeah.jpg","宋体",Font.BOLD,0x000000,22,382,472);
        		    //数据指纹二维码
        		    newcertificate.ErWeiMa(ErWeiMa_Data);
        		    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",134,435);
        		    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
        		    //时间指纹二维码
        		    newcertificate.ErWeiMa(ErWeiMa_Time);
        		    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",351,435);
        		    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
        		    //来源指纹二维码
        		    newcertificate.ErWeiMa(ErWeiMa_Device);
        		    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",134,596);
        		    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
        		    //综合指纹二维码
        		    newcertificate.ErWeiMa(ErWeiMa_All);
        		    newcertificate.pressImage("C:/XinYouData/MakeCertificate/new.jpg","C:/XinYouData/MakeCertificate/yeah.jpg",351,596);
        		    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\new.jpg");
        		    //以二进制流写入数据库
        		    newcertificate.writeFileToDb(userID,SecurityTime,"C:/XinYouData/MakeCertificate/yeah.jpg");
        		    //改名字
        		    FilesHandling filehandle = new FilesHandling();
        		    filehandle.renameFile("C:/XinYouData/MakeCertificate/yeah.jpg","C:/XinYouData/MakeCertificate/"+userID+"+"+FileSeTime+".jpg");
        		  
                    //取时间——MakeCertificateTime
        		    MakeCertificateTime7 = new Timestamp(System.currentTimeMillis()); 
		    	
        		  //变成pdf形式
				    FilesHandling handle = new FilesHandling();
						try {
							handle.jpgToPdf("C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf","C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".jpg");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						handle.createPDF("C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf",userID+"+"+FileSeTime);
				    String[] files = { "C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf", "C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf"};  
			        String savepath = "C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".pdf";  
			        handle.mergePdfFiles(files, savepath);
			        
			      //发送证书？
        		    // 以byte流的方式打开文件
        		    FileInputStream ZhengShu = new FileInputStream("C:/XinYouData/MakeCertificate/"+userID+"+"+FileSeTime+".pdf"); 
        		    //得到文件大小 
        		    int i = ZhengShu.available(); 
        		    byte data[]=new byte[i]; 
        		    //读数据 
        		    ZhengShu.read(data); 
        		    String cerName = FileNameWithFormat+SeTime_cername+".cert.pdf";
        		    response.addHeader("Content-Type", "application/pdf");
        		    response.addHeader("Content-Disposition","attachment;filename="+cerName);
        		    //得到向客户端输出二进制数据的对象
        		    OutputStream toClient = response.getOutputStream(); 
        		    //输出数据 
        		    toClient.write(data); 

        		    toClient.flush(); 
        		    toClient.close(); 
        		    ZhengShu.close();
			        
			      //删除yeah.jpg文件及pdf文件
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".jpg");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\zhengshu1.pdf");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\zhengshu2.pdf");
				    newcertificate.deleteFile("C:\\XinYouData\\MakeCertificate\\"+userID+"+"+FileSeTime+".pdf");
                }
                
                else{  }
  	
		    }
		    
		    
		    
		    
            
		    Timestamp fate2 = new Timestamp(System.currentTimeMillis()); 
		    //添加这项纪录到【时间记录表】
		    Post newTimeNotes = new Post();
		    newTimeNotes.setUserID(userID);
		    newTimeNotes.setGetFileTime(fate1);
		    newTimeNotes.setFileAbstract(after1Hash);
		    newTimeNotes.setSecurityTime(SecurityTime);
		    newTimeNotes.setSendtoCATime(SendtoCATime5);
		    newTimeNotes.setGetfromCATime(GetfromCATime6);
		    newTimeNotes.setMakeCertificateTime(MakeCertificateTime7);
		    newTimeNotes.setSendtoClientTime(fate2);
		    SqlPost sql_Post=new SqlPost();
		    boolean res_Post = sql_Post.addTimeNotes(newTimeNotes);
		    if(res_Post == true ){
		    	 System.out.println("成功添加记录到【时间记录表】!");
		    }
		    else{ }
		    
		    
		    
		    
		    
		  
		    //返回
		    //response.sendRedirect("SecuritySeccess.jsp");
		    //向客户端返回一下DES解出来的明文
		    response.setCharacterEncoding("UTF_8");//设置Response的编码方式为UTF-8  
       	    response.setHeader("Content-type","text/html;charset=UTF-8");
       	    PrintWriter writer = response.getWriter();  
       	    writer.write(file_decryped_string); 
		    
		    
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
