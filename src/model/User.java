package model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class User {
	private String UserID;
	private int UserAge;
	private String UserGender;
	private String UserOccupation;
	private String UserTelephone;
	private String UserEmail;
	private Timestamp RegistrationTime;
	private String UserLevel;
	private String UserPassword;
	private String Type;
    
	
	
	
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userid) {
		UserID = userid;
	}
	public Integer getUserAge() {
		return UserAge;
	}
	public void setUserAge(Integer userage) {
		UserAge = userage;
	}
	public String getUserGender() {
		return UserGender;
	}
	public void setUserGender(String usergender) {
		UserGender = usergender;
	}
	public String getUserOccupation() {
		return UserOccupation;
	}
	public void setUserOccupation(String useroccupation) {
		UserOccupation = useroccupation;
	}
	public String getUserTelephone() {
		return UserTelephone;
	}
	public void setUserTelephone(String usertelephone) {
		UserTelephone = usertelephone;
	}
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String useremail) {
		UserEmail = useremail;
	}
	public Timestamp getRegistrationTime() {
		return RegistrationTime;
	}
	public void setRegistrationTime(Timestamp registrationtime) {
		RegistrationTime = registrationtime;
	}
	public String getUserPassword() {
		return UserPassword;
	}
	public void setUserPassword(String userpassword) {
		UserPassword = userpassword;
	}
	public String getUserLevel() {
		return UserLevel;
	}
	public void setUserLevel(String userlevel) {
		UserLevel = userlevel;
	}
	
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
	
	
	
	public User(String userid,int userage,String usergender,String useroccupation,
			String usertelephone,String useremail,Timestamp registrationtime,String userlevel, String userpassword) {
		super();
		UserID = userid;
		UserAge = userage;
		UserGender = usergender;
		UserOccupation = useroccupation;
		UserTelephone = usertelephone;
		UserEmail = useremail;
		RegistrationTime = registrationtime;
		UserLevel = userlevel;
		UserPassword = userpassword;
	}
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
