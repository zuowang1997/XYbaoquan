package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class SqlCourse {
	public Connection ConnectSql()
	{
		  Connection conn=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			try {
				conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1;databaseName=DataSecurity","sa","12080003");
				System.out.println("database connect Succ !");  
				return conn;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("database connect Failed !"+e.toString());
				e.printStackTrace();
				return null;
			}
		
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	 public String addSecurityNotes(Course SecurityNotes)
	{
		Connection conn=ConnectSql();
		Statement st; 
		String certificatecode="";
		try{
			
			String sql = "insert into DataSecurity..SecurityNotes(ID, SecurityTime, FileName , FileAbstract,Files,CertificateCode,FileFinishTime,SourceNumber,FileType,CertificateSource,CertificateHolder,CertificateCode_Time,CertificateCode_Device,Security_Type)"
					+ "VALUES('"+SecurityNotes.getID()+"','"+SecurityNotes.getSecurityTime()+"','"+SecurityNotes.getFileName()+"','"+SecurityNotes.getFileAbstract()+"','"+SecurityNotes.getFiles()+"','"+SecurityNotes.getCertificateCode()+"',"
							+ "'"+SecurityNotes.getFileFinishTime()+"','"+SecurityNotes.getSourceNumber()+"','"+SecurityNotes.getFileType()+"','"+SecurityNotes.getCertificateSource()+"','"+SecurityNotes.getCertificateHolder()+"','"+SecurityNotes.getCertificateCode_Time()+"','"+SecurityNotes.getCertificateCode_Device()+"','"+SecurityNotes.getSecurity_Type()+"')";
			 
	        st = (Statement) conn.createStatement();
	        st.executeUpdate(sql);
	        conn.close();
	        certificatecode = SecurityNotes.getCertificateCode();
	        return certificatecode;
       }
		catch(SQLException e)
		{
			System.out.println(e); 
			return "nonono£¡";	
		}
		
	}
	

	
	/*Used to list all course info in the course manage page*/
	
	 public List<Course> readAllSecurityNotes(){
		 List<Course> courselist = new ArrayList<Course>();
		 Connection conn=ConnectSql();
		 Statement st;
		 
		 
		 try{
			 String sql = "select * from DataSecurity..SecurityNotes";
			 st = (Statement) conn.createStatement();  
			 
	         ResultSet res = st.executeQuery(sql);   
	         while (res.next()) { 
	        	
	        	
	        	String ID = res.getString("ID");
	        	String SecurityTime = res.getString("SecurityTime");
	        	String FileAbstract = res.getString("FileAbstract");
	        	String Files = res.getString("Files");
	        	String CertificateCode = res.getString("CertificateCode");
	        	String FileName = res.getString("FileName");
	        	String FileFinishTime = res.getString("FileFinishTime");
	        	String SourceNumber = res.getString("SourceNumber");
	        	String FileType = res.getString("FileType");
	        	String CertificateSource = res.getString("CertificateSource");
	        	String CertificateHolder = res.getString("CertificateHolder");
	        	String CertificateCode_Time = res.getString("CertificateCode_Time");
	        	String CertificateCode_Device = res.getString("CertificateCode_Device");
	        	String Security_Type = res.getString("Security_Type");
	        	
	        	
	        	Course SecurityNotes =new Course();
	        	SecurityNotes.setID(ID);
	        	SecurityNotes.setSecurityTime(SecurityTime);
	        	SecurityNotes.setFileAbstract(FileAbstract);
	        	SecurityNotes.setFiles(Files);
	        	SecurityNotes.setCertificateCode(CertificateCode);
	        	SecurityNotes.setFileName(FileName);
	        	SecurityNotes.setFileFinishTime(FileFinishTime);
	        	SecurityNotes.setSourceNumber(SourceNumber);
	        	SecurityNotes.setFileType(FileType);
	        	SecurityNotes.setCertificateSource(CertificateSource);
	        	SecurityNotes.setCertificateHolder(CertificateHolder);
	        	SecurityNotes.setCertificateCode_Time(CertificateCode_Time);
	        	SecurityNotes.setCertificateCode_Device(CertificateCode_Device);
	        	SecurityNotes.setSecurity_Type(Security_Type);

                
                
	            courselist.add(SecurityNotes);
	            //System.out.println(id);     
	            //System.out.println("test"); 
	            }  
		 }
		 
		 /*Below is closing the database*/
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// close SQL connection
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 return courselist;
}
	 
	 
	 
	   public boolean deleteSecurityNotes(String SecurityNotes_CertificateCode){
		   Connection conn=ConnectSql();
		    Statement st;
		   
		   try{
				
				String sql = "delete from DataSecurity..SecurityNotes where CertificateCode='"+SecurityNotes_CertificateCode+"'";
				
		        st = (Statement) conn.createStatement();
		        st.executeUpdate(sql);
		        conn.close();
		        return true;
		       
		   }
		   
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	   }
		   
		
		   
		   
		   
		   public boolean deleteCertificateByCertificateCode(String SecurityNotes_CertificateCode){
			   Connection conn=ConnectSql();
			    Statement st;
			   
			   try{
					
					String sql = "update DataSecurity..SecurityNotes set Certificate=null where CertificateCode='"+SecurityNotes_CertificateCode+"'";
					
			        st = (Statement) conn.createStatement();
			        st.executeUpdate(sql);
			        conn.close();
			        return true;
			       
			   }
			   
			   catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
   
	   }
	   
		   
		   
		   
		   
		   
	   public Course getNotesByCertificateCode(String cercode){
		   Connection conn=ConnectSql();
		   Course course=new Course();
		   Statement st;
		   String CerCode = cercode;
		   try{	
				String sql = "select * from DataSecurity..SecurityNotes where CertificateCode='"+CerCode+"'";
		        st = (Statement) conn.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        while(rs.next())
	            {
		        	String ID=rs.getString("ID");
		        	String SecurityTime=rs.getString("SecurityTime");
		        	String FileAbstract=rs.getString("FileAbstract");
		        	String Files=rs.getString("Files");
		        	String CertificateCode=rs.getString("CertificateCode");
		        	String FileName=rs.getString("FileName");
		        	String FileFinishTime=rs.getString("FileFinishTime");
		        	String SourceNumber=rs.getString("SourceNumber");
		        	String FileType=rs.getString("FileType");
		        	String CertificateSource=rs.getString("CertificateSource");
		        	String CertificateHolder=rs.getString("CertificateHolder");
		        	String CertificateCode_Time=rs.getString("CertificateCode_Time");
		        	String CertificateCode_Device=rs.getString("CertificateCode_Device");
		        	String Security_Type=rs.getString("Security_Type");
		        	
		        	
		        	course.setID(ID);
		        	course.setSecurityTime(SecurityTime);
		        	course.setFileAbstract(FileAbstract);
		        	course.setFiles(Files);
		        	course.setCertificateCode(CertificateCode);
		        	course.setFileName(FileName);
		        	course.setFileFinishTime(FileFinishTime);
		        	course.setSourceNumber(SourceNumber);
		        	course.setFileType(FileType);
		        	course.setCertificateSource(CertificateSource);
		        	course.setCertificateHolder(CertificateHolder);
		        	course.setCertificateCode_Time(CertificateCode_Time);
		        	course.setCertificateCode_Device(CertificateCode_Device);
		        	course.setSecurity_Type(Security_Type);
	            
		 
		           
	            }
	            conn.close();     
	    		return course;
		       
		   }
		   
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		   		  
	   }
	   
	   
	   
	   public boolean updateCourse(Course course)
		{
			Connection conn=ConnectSql();
			Statement st; 
			try{
				
				String sql = "update DataSecurity..SecurityNotes set ID='"+course.getID()+"',SecurityTime='"+course.getSecurityTime()+"',FileName='"+course.getFileName()+"',FileAbstract='"+course.getFileAbstract()+"',Files='"+course.getFiles()+ "'"
						+ ",FileFinishTime='"+course.getFileFinishTime()+ "',SourceNumber='"+course.getSourceNumber()+ "',FileType='"+course.getFileType()+ "',CertificateSource='"+course.getCertificateSource()+ "',CertificateHolder='"+course.getCertificateHolder()+ "',CertificateCode_Time='"+course.getCertificateCode_Time()+ "',CertificateCode_Device='"+course.getCertificateCode_Device()+ "',Security_Type='"+course.getSecurity_Type()+ "' where CertificateCode='"+course.getCertificateCode()+"'";
				
		        st = (Statement) conn.createStatement();
		        st.executeUpdate(sql);
		        conn.close();
		        return true;
	        }
			catch(SQLException e)
			{
				System.out.println(e);
				return false;
			}
			
		}
	   
	   

	   
	   
	   public boolean checkCertificateCode(String ID, String SecurityTime){
		    Connection conn=ConnectSql();
			Statement st = null;  
			String res="";
			int resCount=0;
			
		    String sql = "select * from DataSecurity..SecurityNotes where ID='"+ID+"' and SecurityTime='"+SecurityTime+"'"; 
		    System.out.println(sql);
		    try {
				st = (Statement)conn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				rs.last();
				resCount = rs.getRow();
				System.out.println("CertificateCode number: "+resCount);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    

		    if(resCount==1) {  
		        return true;
		    }  
		    else{
		    	return false;
		    	
		    }
		   		  
	   }
	   
	   
	   
	   
	   public List<Course> getCoursesByID(String ID){
		   List<Course> courselist = new ArrayList<Course>();
		   Connection conn=ConnectSql();
		   Statement st;

		   try{	
				String sql = "select * from DataSecurity..SecurityNotes where ID='"+ID+"'";
		        st = (Statement) conn.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        while(rs.next())
	            {
		        	String id=rs.getString("ID");
		        	String SecurityTime=rs.getString("SecurityTime");
		        	String FileAbstract=rs.getString("FileAbstract");
		        	String Files=rs.getString("Files");
		        	String CertificateCode=rs.getString("CertificateCode");
		        	String FileName=rs.getString("FileName");
		        	String FileFinishTime=rs.getString("FileFinishTime");
		        	String SourceNumber=rs.getString("SourceNumber");
		        	String FileType=rs.getString("FileType");
		        	String CertificateSource=rs.getString("CertificateSource");
		        	String CertificateHolder=rs.getString("CertificateHolder");
		        	String CertificateCode_Time=rs.getString("CertificateCode_Time");
		        	String CertificateCode_Device=rs.getString("CertificateCode_Device");
		        	String Security_Type=rs.getString("Security_Type");
		        	
		        	
		        	Course course=new Course();
		        	course.setID(id);
		        	course.setSecurityTime(SecurityTime);
		        	course.setFileAbstract(FileAbstract);
		        	course.setFiles(Files);
		        	course.setCertificateCode(CertificateCode);
		        	course.setFileName(FileName);
		        	course.setFileFinishTime(FileFinishTime);
		        	course.setSourceNumber(SourceNumber);
		        	course.setFileType(FileType);
		        	course.setCertificateSource(CertificateSource);
		        	course.setCertificateHolder(CertificateHolder);
		        	course.setCertificateCode_Time(CertificateCode_Time);
		        	course.setCertificateCode_Device(CertificateCode_Device);
		        	course.setSecurity_Type(Security_Type);
		        	
		        	courselist.add(course);
	            
		       
	            }
   
		   }
		   
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// close SQL connection
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 return courselist;
		}
	   
	   
	   
	   
	   public List<Course> getCoursesByFileAbstract(String fileabstract){
		   List<Course> courselist = new ArrayList<Course>();
		   Connection conn=ConnectSql();
		   
		   Statement st;

		   try{	
				String sql = "select * from DataSecurity..SecurityNotes where FileAbstract='"+fileabstract+"'";
		        st = (Statement) conn.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        while(rs.next())
	            {
		        	String id=rs.getString("ID");
		        	String SecurityTime=rs.getString("SecurityTime");
		        	String FileAbstract=rs.getString("FileAbstract");
		        	String Files=rs.getString("Files");
		        	String CertificateCode=rs.getString("CertificateCode");
		        	String FileName=rs.getString("FileName");
		        	String FileFinishTime=rs.getString("FileFinishTime");
		        	String SourceNumber=rs.getString("SourceNumber");
		        	String FileType=rs.getString("FileType");
		        	String CertificateSource=rs.getString("CertificateSource");
		        	String CertificateHolder=rs.getString("CertificateHolder");
		        	String CertificateCode_Time=rs.getString("CertificateCode_Time");
		        	String CertificateCode_Device=rs.getString("CertificateCode_Device");
		        	String Security_Type=rs.getString("Security_Type");
		        	
		        	Course course=new Course();
		        	course.setID(id);
		        	course.setSecurityTime(SecurityTime);
		        	course.setFileAbstract(FileAbstract);
		        	course.setFiles(Files);
		        	course.setCertificateCode(CertificateCode);
		        	course.setFileName(FileName);
		        	course.setFileFinishTime(FileFinishTime);
		        	course.setSourceNumber(SourceNumber);
		        	course.setFileType(FileType);
		        	course.setCertificateSource(CertificateSource);
		        	course.setCertificateHolder(CertificateHolder);
		        	course.setCertificateCode_Time(CertificateCode_Time);
		        	course.setCertificateCode_Device(CertificateCode_Device);
		        	course.setSecurity_Type(Security_Type);
		        	
		        	courselist.add(course);
	            
		       
	            }
   
		   }
		   
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// close SQL connection
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 return courselist;
		}
	   
	   
	   
	   
	   public List<Course> getCoursesByCertificateCode(String certificatecode){
		   List<Course> courselist = new ArrayList<Course>();
		   Connection conn=ConnectSql();
		   
		   Statement st;

		   try{	
				String sql = "select * from DataSecurity..SecurityNotes where CertificateCode='"+certificatecode+"'";
		        st = (Statement) conn.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        while(rs.next())
	            {
		        	String id=rs.getString("ID");
		        	String SecurityTime=rs.getString("SecurityTime");
		        	String FileAbstract=rs.getString("FileAbstract");
		        	String Files=rs.getString("Files");
		        	String CertificateCode=rs.getString("CertificateCode");
		        	String FileName=rs.getString("FileName");
		        	String FileFinishTime=rs.getString("FileFinishTime");
		        	String SourceNumber=rs.getString("SourceNumber");
		        	String FileType=rs.getString("FileType");
		        	String CertificateSource=rs.getString("CertificateSource");
		        	String CertificateHolder=rs.getString("CertificateHolder");
		        	String CertificateCode_Time=rs.getString("CertificateCode_Time");
		        	String CertificateCode_Device=rs.getString("CertificateCode_Device");
		        	String Security_Type=rs.getString("Security_Type");
		        	
		        	Course course=new Course();
		        	course.setID(id);
		        	course.setSecurityTime(SecurityTime);
		        	course.setFileAbstract(FileAbstract);
		        	course.setFiles(Files);
		        	course.setCertificateCode(CertificateCode);
		        	course.setFileName(FileName);
		        	course.setFileFinishTime(FileFinishTime);
		        	course.setSourceNumber(SourceNumber);
		        	course.setFileType(FileType);
		        	course.setCertificateSource(CertificateSource);
		        	course.setCertificateHolder(CertificateHolder);
		        	course.setCertificateCode_Time(CertificateCode_Time);
		        	course.setCertificateCode_Device(CertificateCode_Device);
		        	course.setSecurity_Type(Security_Type);
		        	
		        	courselist.add(course);
	            
		       
	            }
   
		   }
		   
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// close SQL connection
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 return courselist;
		}
	   
	   
	   
	   
	   
	   public Course getCourseByCertificateCode(String certificatecode){
		   Course course = new Course();
		   Connection conn=ConnectSql();
		   
		   Statement st;

		   try{	
				String sql = "select * from DataSecurity..SecurityNotes where CertificateCode='"+certificatecode+"'";
		        st = (Statement) conn.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        while(rs.next())
	            {
		        	String id=rs.getString("ID");
		        	String SecurityTime=rs.getString("SecurityTime");
		        	String FileAbstract=rs.getString("FileAbstract");
		        	String Files=rs.getString("Files");
		        	String CertificateCode=rs.getString("CertificateCode");
		        	String FileName=rs.getString("FileName");
		        	String FileFinishTime=rs.getString("FileFinishTime");
		        	String SourceNumber=rs.getString("SourceNumber");
		        	String FileType=rs.getString("FileType");
		        	String CertificateSource=rs.getString("CertificateSource");
		        	String CertificateHolder=rs.getString("CertificateHolder");
		        	String CertificateCode_Time=rs.getString("CertificateCode_Time");
		        	String CertificateCode_Device=rs.getString("CertificateCode_Device");
		        	String Security_Type=rs.getString("Security_Type");
		        	
		        	course.setID(id);
		        	course.setSecurityTime(SecurityTime);
		        	course.setFileAbstract(FileAbstract);
		        	course.setFiles(Files);
		        	course.setCertificateCode(CertificateCode);
		        	course.setFileName(FileName);
		        	course.setFileFinishTime(FileFinishTime);
		        	course.setSourceNumber(SourceNumber);
		        	course.setFileType(FileType);
		        	course.setCertificateSource(CertificateSource);
		        	course.setCertificateHolder(CertificateHolder);
		        	course.setCertificateCode_Time(CertificateCode_Time);
		        	course.setCertificateCode_Device(CertificateCode_Device);
		        	course.setSecurity_Type(Security_Type);    
		       
	            }
   
		   }
		   
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// close SQL connection
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 return course;
		}
	   
	   
	   
	   
	   public String getCertificateCodebyKey(String id, String setime){
		   String CertificateCode = "noresult";
		   Connection conn=ConnectSql();
		   String userid = id;
		   String securitytime = setime;
		   Statement st;

		   try{	
				String sql = "select CertificateCode from DataSecurity..SecurityNotes where ID='"+userid+"' and SecurityTime='"+securitytime+"'";
		        st = (Statement) conn.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        while(rs.next())
	            {
		           CertificateCode = rs.getString("CertificateCode");
	            }
		        
		   
		   }
		   
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				
			    e.printStackTrace();
			}
			// close SQL connection
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 return CertificateCode;
		}
	   
	   
	   
	   
	   public String getCertificateCode_TimebyKey(String id, String setime){
		   String CertificateCode = "noresult";
		   Connection conn=ConnectSql();
		   String userid = id;
		   String securitytime = setime;
		   Statement st;

		   try{	
				String sql = "select CertificateCode_Time from DataSecurity..SecurityNotes where ID='"+userid+"' and SecurityTime='"+securitytime+"'";
		        st = (Statement) conn.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        while(rs.next())
	            {
		           CertificateCode = rs.getString("CertificateCode_Time");
	            }
		        
		   
		   }
		   
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				
			    e.printStackTrace();
			}
			// close SQL connection
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 return CertificateCode;
		}
	   
	   
	   
	   
	   public String getCertificateCode_DevicebyKey(String id, String setime){
		   String CertificateCode_Device = "noresult";
		   Connection conn=ConnectSql();
		   String userid = id;
		   String securitytime = setime;
		   Statement st;

		   try{	
				String sql = "select CertificateCode_Device from DataSecurity..SecurityNotes where ID='"+userid+"' and SecurityTime='"+securitytime+"'";
		        st = (Statement) conn.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        while(rs.next())
	            {
		        	CertificateCode_Device = rs.getString("CertificateCode_Device");
	            }
		        
		   
		   }
		   
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				
			    e.printStackTrace();
			}
			// close SQL connection
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 return CertificateCode_Device;
		}
	   
	   
	   
	   
	   
	   public String getSecurityTypebyKey(String id, String setime){
		   String SecurityType = "noresult";
		   Connection conn=ConnectSql();
		   String userid = id;
		   String securitytime = setime;
		   Statement st;

		   try{	
				String sql = "select Security_Type from DataSecurity..SecurityNotes where ID='"+userid+"' and SecurityTime='"+securitytime+"'";
		        st = (Statement) conn.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        while(rs.next())
	            {
		        	SecurityType = rs.getString("Security_Type");
	            }
		        
		   
		   }
		   
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				
			    e.printStackTrace();
			}
			// close SQL connection
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 return SecurityType;
		}
	   
	   
	   
	   
	   
	   public String getSecurityTypebyCerCode(String CerCode){
		   String SecurityType = "noresult";
		   Connection conn=ConnectSql();
		   String cercode = CerCode;
		   Statement st;

		   try{	
				String sql = "select Security_Type from DataSecurity..SecurityNotes where CertificateCode='"+cercode+"'";
		        st = (Statement) conn.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        while(rs.next())
	            {
		        	SecurityType = rs.getString("Security_Type");
	            }
		        
		   
		   }
		   
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				
			    e.printStackTrace();
			}
			// close SQL connection
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 return SecurityType;
		}
	   
	   
	   
	   
	   
	   public Timestamp getSeTimebyCerCode(String CerCode){
		   Timestamp SecurityTime = null;
		   String string_SecurityTime = "noresult";
		   Connection conn=ConnectSql();
		   String cercode = CerCode;
		   Statement st;

		   try{	
				String sql = "select SecurityTime from DataSecurity..SecurityNotes where CertificateCode='"+cercode+"'";
		        st = (Statement) conn.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        while(rs.next())
	            {
		        	string_SecurityTime = rs.getString("SecurityTime");
	            }
		        SecurityTime = Timestamp.valueOf(string_SecurityTime);
		        
		   
		   }
		   
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				
			    e.printStackTrace();
			}
			// close SQL connection
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 return SecurityTime;
		}
	   
	   
	   
	   
	   
	   
	   
	   
	   public int CountAllNotes(){
			 Connection conn=ConnectSql();
			 Statement st;

			 try{
				 String sql = "select FileAbstract from DataSecurity..SecurityNotes";
				 st = (Statement) conn.createStatement();  
				 
		         ResultSet countfile = st.executeQuery(sql);  
		         
		         int i = 0;
		         while(countfile.next())
		            {
			         i++;   
		            }
		         
		         conn.close();
		         return i;
		 }
			 catch (SQLException e) {  
		        	System.out.println(e);
		        	return -1;
		        }
			 
        }
	   
	   
	   
	   

}
