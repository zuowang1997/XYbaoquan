package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
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


public class CA_generatekey extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CA_generatekey() {
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
		
		//RSA部分，生成公钥与私钥，将公钥传给保全系统
		RSA rsa = new RSA();
		HashMap<String, Object> map = null;
		try {
			map = rsa.getKeys();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//生成公钥和私钥
		RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
		RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
		String modulus = publicKey.getModulus().toString();
		//公钥指数
		String public_exponent = publicKey.getPublicExponent().toString();
		//私钥指数
		String private_exponent = privateKey.getPrivateExponent().toString();
		//使用模和指数生成公钥和私钥
		RSAPublicKey pubKey = rsa.getPublicKey(modulus, public_exponent);
		RSAPrivateKey priKey = rsa.getPrivateKey(modulus, private_exponent);
		session.setAttribute("CA_pubkey", pubKey); 
		session.setAttribute("CA_prikey", priKey); 

		
		
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
