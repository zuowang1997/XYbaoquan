package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CA_timestampnotes {
	private String UserID;
	private String FileName;
	private String SecurityTime;
	private String FileFingerprint;
	private int TimeStamps;
	private String FileFingerprint_Time;
	private String FileFingerprint_Device;
	private int Timestamp_Time;
	private int Timestamp_Device;
    
	
	
	
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userid) {
		UserID = userid;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String filename) {
		FileName = filename;
	}
	public String getSecurityTime() {
		return SecurityTime;
	}
	public void setSecurityTime(String securitytime) {
		SecurityTime = securitytime;
	}
	public String getFileFingerprint() {
		return FileFingerprint;
	}
	public void setFileFingerprint(String filefingerprint) {
		FileFingerprint = filefingerprint;
	}
	public int getTimeStamps() {
		return TimeStamps;
	}
	public void setTimeStamps(int timestamps) {
		TimeStamps = timestamps;
	}
	public String getFileFingerprint_Time() {
		return FileFingerprint_Time;
	}
	public void setFileFingerprint_Time(String filefingerprinttime) {
		FileFingerprint_Time = filefingerprinttime;
	}
	public String getFileFingerprint_Device() {
		return FileFingerprint_Device;
	}
	public void setFileFingerprint_Device(String filefingerprintdevice) {
		FileFingerprint_Device = filefingerprintdevice;
	}
	public int getTimestamp_Time() {
		return Timestamp_Time;
	}
	public void setTimestamp_Time(int timestamptime) {
		Timestamp_Time = timestamptime;
	}
	public int getTimestamp_Device() {
		return Timestamp_Device;
	}
	public void setTimestamp_Device(int timestampdevice) {
		Timestamp_Device = timestampdevice;
	}
	
	
	
	
	public CA_timestampnotes(String userid,String filename,String securitytime,String filefingerprint,int timestamps,String filefingerprinttime,String filefingerprintdevice,int timestamptime,int timestampdevice) {
		super();
		UserID = userid;
        FileName = filename;
        SecurityTime = securitytime;
        FileFingerprint = filefingerprint;
        TimeStamps = timestamps;
        FileFingerprint_Time = filefingerprinttime;
        FileFingerprint_Device = filefingerprintdevice;
        Timestamp_Time = timestamptime;
        Timestamp_Device = timestampdevice;
	}
	
	
	public CA_timestampnotes() {
		super();
		// TODO Auto-generated constructor stub
	}

}
