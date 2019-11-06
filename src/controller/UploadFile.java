package controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 

/**
 * Servlet implementation class UploadServlet
 */

@WebServlet("/UploadServlet")
public class UploadFile extends HttpServlet {
    private static final long serialVersionUID = 1L;
     
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";
 
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
 
    /**
     * 上传数据及保存文件
     */
    protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
 
        	
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
                         session.setAttribute(name , value);
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
                                     session.setAttribute("FileNameWithFormat" , fileName);

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
             this.doGet(request, response);  
         }  
         
         
         //调用数据保全过程
         securitytest security = new securitytest();
         security.doPost(request , response);
    	

        
        
        
    }
    
    
   
    
    
    
}