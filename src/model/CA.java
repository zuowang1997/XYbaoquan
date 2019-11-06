package model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CA{
	
	public boolean checkHashCode(String Hashcode){
		int len = Hashcode.length();
		if(len == 128){
			return true;
		}
		else{
			System.out.println("传入的Hash码长度不是128位哦   ∑(っ °Д °;)っ");
			return false;
		}
	}
	
	
	
	
	//将yyyy-MM-dd HH:mm:ss.SSS型的现实时间变为时间戳（大概9、10位的数字）
    public String dateToTimestamp(String user_time) {   
    	String re_time = null;     
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");   
    	Date d;     
    	try {           
    		d = sdf.parse(user_time);        
    		long l = d.getTime();         
    		String str = String.valueOf(l);          
    		re_time = str.substring(0, 10);       
        } 
    	catch (ParseException e) {  
          e.printStackTrace();       
          }       
    	return re_time;  
  } 
	
	public String AddTimeStamp(String Hashcode){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Timestamp TimeStampTime = new Timestamp(System.currentTimeMillis());
		String unixTimestamp = dateToTimestamp(String.valueOf(TimeStampTime));
		String afterTimeStamp = Hashcode+unixTimestamp;
		
		return afterTimeStamp;
		
	}
	

	
	
	
}