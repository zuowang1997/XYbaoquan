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


public class CAsimulation extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CAsimulation() {
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
		
		String mi_Hashcode = String.valueOf(request.getSession().getAttribute("mi_after1Hash"));
		String mi_userid = String.valueOf(request.getSession().getAttribute("mi_userid"));
		String mi_filename = String.valueOf(request.getSession().getAttribute("mi_filename"));
		String mi_securitytime = String.valueOf(request.getSession().getAttribute("mi_setime"));
		
		String after2Hash = null;
		String after2Hash_finishtime = null;
		String after2Hash_sourcenumber = null;
		
		
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
		
		
		
		CA ca = new CA();
		String afterTimeStamp = null;
		
		//检查哈希值是否是128位的
		if(ca.checkHashCode(ming_Hashcode)){ 
			afterTimeStamp = ca.AddTimeStamp(ming_Hashcode);
			
			
			//CA加盖时间戳
			make_certificate newcertificate = new make_certificate();
			after2Hash = newcertificate.doHash(afterTimeStamp);
			
			//入CA数据库
			CA_timestampnotes newCAnotes = new CA_timestampnotes();
			String unixtimestamp = afterTimeStamp.substring(afterTimeStamp.length()-10,afterTimeStamp.length());
			int unixtimestmap = Integer.parseInt(unixtimestamp);
			
			String st = ming_securitytime;
			int timelong = st.length();
			if(timelong == 21){
				st = st+"00"; 
			}
            if(timelong == 22){
            	st = st+"0"; 
			}

			newCAnotes.setUserID(ming_userid);
			newCAnotes.setFileName(ming_filename);
			newCAnotes.setSecurityTime(st);
			newCAnotes.setFileFingerprint(after2Hash);
			newCAnotes.setTimeStamps(unixtimestmap);
			newCAnotes.setFileFingerprint_Time(after2Hash_finishtime);
			newCAnotes.setFileFingerprint_Device(after2Hash_sourcenumber);
			newCAnotes.setTimestamp_Time(0);
			newCAnotes.setTimestamp_Device(0);
			SqlCA sql_CA=new SqlCA();
			String res_CA = sql_CA.addTimestampNotes(newCAnotes);
			if(res_CA != "" ){
		    	 System.out.println("CA成功添加一条记录到自己的数据库中!");
		    }
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
        String mi_afterCA_userid = null;
        String mi_afterCA_setime = null;
        String mi_afterCA_Footprint_finishtime = null;
        String mi_afterCA_Footprint_sourcenumber = null;

        try {
			mi_afterCA_Footprint = rsa2.encryptByPublicKey(after2Hash, pubKey2);
			mi_afterCA_userid = rsa2.encryptByPublicKey(ming_userid, pubKey2);
			mi_afterCA_setime = rsa2.encryptByPublicKey(ming_securitytime, pubKey2);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		session.setAttribute("afterCA_Footprint", mi_afterCA_Footprint); 
		session.setAttribute("afterCA_userid", mi_afterCA_userid); 
		session.setAttribute("afterCA_setime", mi_afterCA_setime); 
		
		
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
