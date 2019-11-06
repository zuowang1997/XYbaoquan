package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



public class SqlCA {
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
	
	
	
	 public String addTimestampNotes(CA_timestampnotes TimestampNotes)
	{
		Connection conn=ConnectSql();
		Statement st; 
		String id = null;
		try{
			
			String sql = "insert into CA_database..TimestampNotes(UserID, FileName, SecurityTime , FileFingerprint, Timestamp, FileFingerprint_Time, FileFingerprint_Device,Timestamp_Time,Timestamp_Device)"
					+ "VALUES('"+TimestampNotes.getUserID()+"','"+TimestampNotes.getFileName()+"','"+TimestampNotes.getSecurityTime()+"','"+TimestampNotes.getFileFingerprint()+"','"+TimestampNotes.getTimeStamps()+"','"+TimestampNotes.getFileFingerprint_Time()+"',"
							+ "'"+TimestampNotes.getFileFingerprint_Device()+"','"+TimestampNotes.getTimestamp_Time()+"','"+TimestampNotes.getTimestamp_Device()+"')";
			 
	        st = (Statement) conn.createStatement();
	        st.executeUpdate(sql);
	        conn.close();
	        id = TimestampNotes.getUserID();
	        return id;
      }
		catch(SQLException e)
		{
			System.out.println(e); 
			return "addTimestampNotes Fail£¡";	
		}
		
	}
	 
	 
	 
	 
	 public String getTimestampsbyFileFingerprint(String filefingerprint){
		   String Timestamps = null;
		   Connection conn=ConnectSql();
		   String FileFingerprint = filefingerprint;
		   Statement st;

		   try{	
				String sql = "select Timestamp from CA_database..TimestampNotes where FileFingerprint='"+FileFingerprint+"'";
		        st = (Statement) conn.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        while(rs.next())
	            {
		        	Timestamps = rs.getString("Timestamp");
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
		 return Timestamps;
		}
	 
	 
	 
	 public String getTimeStampbyKey(String userid, String setime){
		   String Timestamp = null;
		   Connection conn=ConnectSql();
		   String UserID = userid ;
		   String SecurityTime = setime ;
		   Statement st;

		   try{	
				String sql = "select Timestamp from CA_database..TimestampNotes where UserID='"+UserID+"' and SecurityTime = '"+SecurityTime+"'";
		        st = (Statement) conn.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        while(rs.next())
	            {
		        	Timestamp = rs.getString("Timestamp");
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
		 return Timestamp;
		}
	 
	 
	 
	 
	 public String getTimeStamp_TimebyKey(String userid, String setime){
		   String Timestamp_Time = null;
		   Connection conn=ConnectSql();
		   String UserID = userid ;
		   String SecurityTime = setime ;
		   Statement st;

		   try{	
				String sql = "select Timestamp_Time from CA_database..TimestampNotes where UserID='"+UserID+"' and SecurityTime = '"+SecurityTime+"'";
		        st = (Statement) conn.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        while(rs.next())
	            {
		        	Timestamp_Time = rs.getString("Timestamp_Time");
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
		 return Timestamp_Time;
		}
	 
	 
	 
	 public String getTimeStamp_DevicebyKey(String userid, String setime){
		   String Timestamp_Device = null;
		   Connection conn=ConnectSql();
		   String UserID = userid ;
		   String SecurityTime = setime ;
		   Statement st;

		   try{	
				String sql = "select Timestamp_Device from CA_database..TimestampNotes where UserID='"+UserID+"' and SecurityTime = '"+SecurityTime+"'";
		        st = (Statement) conn.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        while(rs.next())
	            {
		        	Timestamp_Device = rs.getString("Timestamp_Device");
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
		 return Timestamp_Device;
		}
	 
	 
	 
	 
	 public boolean addFileFingerprint_Time(String userid, String setime,String HashCode){
		   Connection conn=ConnectSql();
		   String UserID = userid ;
		   String SecurityTime = setime ;
		   String filefingerprint_time = HashCode;
		   Statement st;

		   try{	
				String sql = "update CA_database..TimestampNotes set FileFingerprint_Time='"+filefingerprint_time+"' where UserID='"+UserID+"' and SecurityTime = '"+SecurityTime+"'";
		        
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
	 
	 
	 
	 public boolean addFileFingerprint_Device(String userid, String setime,String HashCode){
		   Connection conn=ConnectSql();
		   String UserID = userid ;
		   String SecurityTime = setime ;
		   String filefingerprint_device = HashCode;
		   Statement st;

		   try{	
				String sql = "update CA_database..TimestampNotes set FileFingerprint_Device='"+filefingerprint_device+"' where UserID='"+UserID+"' and SecurityTime = '"+SecurityTime+"'";
		        
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
	 
	 
	 
	 
	 
	 public boolean addTimestamp_Time(String HashCode,int timestamp){
		   Connection conn=ConnectSql();
		   String hashcode = HashCode ;
		   int timestamp_time = timestamp;
		   Statement st;

		   try{	
				String sql = "update CA_database..TimestampNotes set Timestamp_Time='"+timestamp_time+"' where FileFingerprint_Time='"+hashcode+"'";
		        
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
	 
	 
	 
	 
	 
	 public boolean addTimestamp_Device(String HashCode,int timestamp){
		   Connection conn=ConnectSql();
		   String hashcode = HashCode ;
		   int timestamp_device = timestamp;
		   Statement st;

		   try{	
				String sql = "update CA_database..TimestampNotes set Timestamp_Device='"+timestamp_device+"' where FileFingerprint_Device='"+hashcode+"'";
		        
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
	 
	
	
	
	 
	
	
	
	
	
}