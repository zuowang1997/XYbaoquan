package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Post {
	private String UserID;
	private Timestamp GetFileTime;
	private String FileAbstract;
	private String SecurityTime;
	private Timestamp SendtoCATime;
	private Timestamp GetfromCATime;
	private Timestamp MakeCertificateTime;
	private Timestamp SendtoClientTime;

	
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userid) {
		UserID = userid;
	}
	public Timestamp getGetFileTime() {
		return GetFileTime;
	}
	public void setGetFileTime(Timestamp getfiletime) {
		GetFileTime = getfiletime;
	}
	public String getFileAbstract() {
		return FileAbstract;
	}
	public void setFileAbstract(String fileabstract) {
		FileAbstract = fileabstract;
	}
	public String getSecurityTime() {
		return SecurityTime;
	}
	public void setSecurityTime(String securitytime) {
		SecurityTime = securitytime;
	}
	public Timestamp getSendtoCATime() {
		return SendtoCATime;
	}
	public void setSendtoCATime(Timestamp sendtoCAtime) {
		SendtoCATime = sendtoCAtime;
	}
	public Timestamp getGetfromCATime() {
		return GetfromCATime;
	}
	public void setGetfromCATime(Timestamp getfromCAtime) {
		GetfromCATime = getfromCAtime;
	}
	public Timestamp getMakeCertificateTime() {
		return MakeCertificateTime;
	}
	public void setMakeCertificateTime(Timestamp makecertificatetime) {
		MakeCertificateTime = makecertificatetime;
	}
	public Timestamp getSendtoClientTime() {
		return SendtoClientTime;
	}
	public void setSendtoClientTime(Timestamp sendtoclienttime) {
		SendtoClientTime = sendtoclienttime;
	}
	/**
	 * @param value
	 * @param meta
	 */
	public Post(String userid,Timestamp getfiletime,String fileabstract,String securitytime,
			Timestamp sendtoCAtime,Timestamp getfromCAtime,Timestamp makecertificatetime,Timestamp sendtoclienttime) {
		super();
		UserID = userid;
		GetFileTime = getfiletime;
		FileAbstract = fileabstract;
		SecurityTime = securitytime;
		SendtoCATime = sendtoCAtime;
		GetfromCATime = getfromCAtime;
		MakeCertificateTime = makecertificatetime;
		SendtoClientTime = sendtoclienttime;
	}
	
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}



}