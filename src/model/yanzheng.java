package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Hashtable; 
import java.io.*;
import java.nio.channels.FileChannel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


//有啥用？？？

public class yanzheng{
	
	public static String FiletoString(File file) throws IOException {
		  
		  BufferedReader bf = new BufferedReader(new FileReader(file));
		  
		  String content = "";
		  StringBuilder sb = new StringBuilder();
		  
		  while(content != null){
		   content = bf.readLine();
		   
		   if(content == null){
		    break;
		   }
		   
		   sb.append(content.trim());
		  }
		  
		bf.close();
		  return sb.toString();
		 }
	
	
	
	
	
	
}