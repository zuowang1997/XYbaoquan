package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.MD5;
import model.SqlManager;
import model.User;

public class UserLogin extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserLogin() {
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
		
		
		 HttpSession session = request.getSession();

	     String name = new String(request.getParameter("userlogin_username").getBytes("ISO8859-1"),"UTF-8");
	     String pwd = new String(request.getParameter("userlogin_password").getBytes("ISO8859-1"),"UTF-8");
	     
	     System.out.println("ID: "+ name +"  pwd: "+pwd);
	     
	     SqlManager sql = new SqlManager();
	      if(sql.UserLogin(name,pwd)==true){
	    	  session.setAttribute("UserLogin_userID", name); 
	    	 System.out.println("User Login Succ!");
    		 response.sendRedirect("authentication.jsp");
	      }

	       else{
	      	 System.out.println("User Login Failed!");
	   	     response.sendRedirect("UserLoginFail.jsp");
	     	 return;
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
