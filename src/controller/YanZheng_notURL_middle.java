package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

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


public class YanZheng_notURL_middle extends HttpServlet {

	
	
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
	public YanZheng_notURL_middle() {
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
		    
		    //File Certificate = new File(request.getParameter("check_Certificate"));
		    //String FileType = new String(request.getParameter("check_FileType"));
		      String FileType = null;
		      String FileNameWithFormat = null;
		
		    //得到参数
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
                                    FileType = FileNameWithFormat.substring(FileNameWithFormat.length()-32,FileNameWithFormat.length()-29);
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
        
        
		
		    //读证书
            File Certificate = new File("C:\\Tomcat\\webapps\\XYbaoquan\\upload\\"+FileNameWithFormat);
		    //处理证书名字
		    String CertificateName = Certificate.getName(); 
		    System.out.println("证书名："+CertificateName);
		    String SeTime = null;
		    
		    FilesHandling handle = new FilesHandling();
		    String DataBaseKey = null;
		    try {
				DataBaseKey = handle.GetTextFromPdf(Certificate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.println("从pdf中抽取的信息："+DataBaseKey);
		    
		       int i = DataBaseKey.indexOf("+");
		       String userID = DataBaseKey.substring(0,i);
		       String sss_date=DataBaseKey.substring(i+1,i+11);
		       String sss_time_1 = DataBaseKey.substring(i+11,i+13);
		       String sss_time_2 = DataBaseKey.substring(i+13,i+15);
		       String sss_time_3 = DataBaseKey.substring(i+15,i+17);
		       String sss_time_4 = DataBaseKey.substring(i+17,i+20);
		       String sss_finish = sss_date+" "+sss_time_1+":"+sss_time_2+":"+sss_time_3+"."+sss_time_4;
		       SeTime = sss_finish;

		       System.out.println("处理好后的信息，ID："+userID+"时间："+sss_finish);
		       
		    
		    //得到保全时选择的保全类型
		    SqlCourse course = new SqlCourse();
		    String setime = sss_finish;
		    
		    String SecurityType = course.getSecurityTypebyKey(userID,setime);
		    
		    
		    
		    //传递数据
		    HttpSession session = request.getSession();
		    session.setAttribute("YanZhengMiddle_userid", userID);
		    session.setAttribute("YanZhengMiddle_setime", setime);
		    session.setAttribute("YanZhengMiddle_FileType", FileType); 
		    
		    
		    //删除证书
		    make_certificate newcertificate = new make_certificate();
		    newcertificate.deleteFile("C:\\Tomcat\\webapps\\XYbaoquan\\upload\\"+FileNameWithFormat);
		     
		    
		    
		    //转到不同jsp页面
		    if(SecurityType.equals("data")){
		    	response.sendRedirect("authentication_notQR_data.jsp");
		    }
		    else if(SecurityType.equals("dataandtime")){
		    	response.sendRedirect("authentication_notQR_data.jsp");
		    }
		    else if(SecurityType.equals("dataanddevice")){
		    	response.sendRedirect("authentication_notQR_datadevice.jsp");
		    }
		    else if(SecurityType.equals("all")){
		    	response.sendRedirect("authentication_notQR_datadevice.jsp");
		    }
		    else { 
		    	response.sendRedirect("authentication_middlefail.jsp");
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
