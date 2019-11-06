package model;

import java.sql.Timestamp;

public class Course {

	private String ID;
	private String SecurityTime;
	private String FileName;
	private String FileAbstract;
	private String Files;
	private String CertificateCode;
	private String FileFinishTime;
	private String SourceNumber;
	private String FileType;
	private String CertificateSource;
	private String CertificateHolder;
	private String CertificateCode_Time;
	private String CertificateCode_Device;
	private String Security_Type;
//
	
	public String getID() {
		return ID;
	}
	public void setID(String Id) {
		ID = Id;
	}
	public String getSecurityTime() {
		return SecurityTime;
	}
	public void setSecurityTime(String securitytime) {
		SecurityTime = securitytime;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String filename) {
		FileName = filename;
	}
	public String getFileAbstract() {
		return FileAbstract;
	}
	public void setFileAbstract(String fileabstract) {
		FileAbstract = fileabstract;
	}
	public String getFiles() {
		return Files;
	}
	public void setFiles(String files) {
		Files = files;
	}
	public String getCertificateCode() {
		return CertificateCode;
	}
	public void setCertificateCode(String certificatecode) {
		CertificateCode = certificatecode;
	}
	public String getFileFinishTime() {
		return FileFinishTime;
	}
	public void setFileFinishTime(String filefinishtime) {
		FileFinishTime = filefinishtime;
	}
	public String getSourceNumber() {
		return SourceNumber;
	}
	public void setSourceNumber(String sourcenumber) {
		SourceNumber = sourcenumber;
	}
	public String getFileType() {
		return FileType;
	}
	public void setFileType(String filetype) {
		FileType = filetype;
	}
	public String getCertificateSource() {
		return CertificateSource;
	}
	public void setCertificateSource(String certificatesource) {
		CertificateSource = certificatesource;
	}
	public String getCertificateHolder() {
		return CertificateHolder;
	}
	public void setCertificateHolder(String certificatehloder) {
		CertificateHolder = certificatehloder;
	}
	public String getCertificateCode_Time() {
		return CertificateCode_Time;
	}
	public void setCertificateCode_Time(String certificatecode_time) {
		CertificateCode_Time = certificatecode_time;
	}
	public String getCertificateCode_Device() {
		return CertificateCode_Device;
	}
	public void setCertificateCode_Device(String certificatecode_device) {
		CertificateCode_Device = certificatecode_device;
	}
	public String getSecurity_Type() {
		return Security_Type;
	}
	public void setSecurity_Type(String security_type) {
		Security_Type = security_type;
	}
	
	
	public Course(String id,String securitytime,String filename , String fileabstract,String files,String certificatecode,String filefinishtime,String sourcenumber,
			String filetype,String certificatesource,String certificatehloder,String certificatecode_time,String certificatecode_device,String security_type) {
		super();
		ID = id;
		SecurityTime = securitytime;
		FileName = filename;
		FileAbstract = fileabstract;
		Files = files;
		CertificateCode = certificatecode;
		FileFinishTime = filefinishtime;
		SourceNumber = sourcenumber;
		FileType = filetype;
		CertificateSource = certificatesource;
		CertificateHolder = certificatehloder;
		CertificateCode_Time = certificatecode_time;
		CertificateCode_Device = certificatecode_device;
		Security_Type = security_type;
		
	}
	
	
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

}