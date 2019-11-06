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


public class CA_yanzheng extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CA_yanzheng() {
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
		
		String mi_Hashcode = String.valueOf(request.getSession().getAttribute("yanzheng_mi_after1Hash"));
		String mi_userid = String.valueOf(request.getSession().getAttribute("yanzheng_mi_userid"));
		String mi_filename = String.valueOf(request.getSession().getAttribute("yanzheng_mi_filename"));
		String mi_securitytime = String.valueOf(request.getSession().getAttribute("yanzheng_mi_setime"));
		String after2Hash = null;
		String mi_unixTimestamp = null;
		
		
		RSA rsa = new RSA();
		RSAPrivateKey priKey = (RSAPrivateKey)request.getSession().getAttribute("CA_prikey");
		String ming_Hashcode = null;
		String ming_userid = null;
		String ming_filename = null;
		String ming_securitytime = null;
		try {
			ming_Hashcode = rsa.decryptByPrivateKey(mi_Hashcode, priKey);
			ming_userid = rsa.decryptByPrivateKey(mi_userid, priKey);
			ming_filename = rsa.decryptByPrivateKey(mi_filename, priKey);
			ming_securitytime = rsa.decryptByPrivateKey(mi_securitytime, priKey);
			System.out.println("CA收到指纹："+ming_Hashcode+"用户ID："+ming_userid+"文件名："+ming_filename+"保全时间："+ming_securitytime);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//从数据库中找到时间戳
		SqlCA sqlca = new SqlCA();
		int len = ming_securitytime.length();
		if( len<23 ){
			int lenlen = 23-len;
			int i=0;
			for(i=0;i<lenlen;i++){
				ming_securitytime = ming_securitytime+"0";
			}
		}
		String Timestamp = sqlca.getTimeStampbyKey(ming_userid,ming_securitytime);
		
	
		
		CA ca = new CA();
		
		//检查哈希值是否是128位的
		if(ca.checkHashCode(ming_Hashcode)){ 
			
			//模拟CA加盖时间戳
			make_certificate newcertificate = new make_certificate();
			String beforeTimestamp = ming_Hashcode + Timestamp ;
			after2Hash = newcertificate.doHash(beforeTimestamp);
			
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
			mi_unixTimestamp = rsa2.encryptByPublicKey(Timestamp, pubKey2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		session.setAttribute("yanzheng_afterCA_Footprint", mi_afterCA_Footprint); 
		session.setAttribute("yanzheng_afterCA_timestamp", mi_unixTimestamp); 
		
		
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
