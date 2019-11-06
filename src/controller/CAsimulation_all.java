package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import model.*;
import library.*;


public class CAsimulation_all extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CAsimulation_all() {
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
		
		String userid = String.valueOf(request.getSession().getAttribute("UserID"));
		String securitytime = String.valueOf(request.getSession().getAttribute("securitytime"));
		String mi_after1Hash = String.valueOf(request.getSession().getAttribute("mi_after1Hash_all"));
		
		String after2Hash = null;
		
		
		RSA rsa = new RSA();
		RSAPrivateKey priKey = (RSAPrivateKey)request.getSession().getAttribute("CA_prikey");
		String ming_Hashcode = null;
		try {
			ming_Hashcode = rsa.decryptByPrivateKey(mi_after1Hash, priKey);
			System.out.println("CA收到指纹："+ming_Hashcode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		CA ca = new CA();
		String afterTimeStamp = null;
		
		//检查哈希值是否是128位的
		if(ca.checkHashCode(ming_Hashcode)){ 
			afterTimeStamp = ca.AddTimeStamp(ming_Hashcode);
			
			
			//CA加盖时间戳
			make_certificate newcertificate = new make_certificate();
			after2Hash = newcertificate.doHash(afterTimeStamp);
			
			//不入CA数据库
			//SqlCA ca_time = new SqlCA();
			//String unixtimestamp = afterTimeStamp.substring(afterTimeStamp.length()-10,afterTimeStamp.length());
			//int unixtimestmap = Integer.parseInt(unixtimestamp);
		}
		else{	} 
		
		
		
		//再次调用CA_generatekey模块来生成公私钥
        CA_generatekey ca_generatekey = new CA_generatekey();
        ca_generatekey.doPost(request,response);
        //得到新的pubkey
        RSA rsa2 = new RSA();
        RSAPublicKey pubKey2 = (RSAPublicKey)request.getSession().getAttribute("CA_pubkey");
        //用pubkey加密
        String mi_afterCA_Footprint = null;

        try {
			mi_afterCA_Footprint = rsa2.encryptByPublicKey(after2Hash, pubKey2);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		session.setAttribute("afterCA_Footprint_all", mi_afterCA_Footprint); 
		
		
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
