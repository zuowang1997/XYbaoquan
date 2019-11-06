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

public class SqlPost {
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
	
	
	 public List<Post> readAllTimeNotes(){
		 List<Post> postlist = new ArrayList<Post>();
		 Connection conn=ConnectSql();
		 Statement st;
		 
		 
		 try{
			 String sql = "select * from DataSecurity..TimeNotes";
			 st = (Statement) conn.createStatement();  
			 
	         ResultSet res = st.executeQuery(sql);   
	         while (res.next()) { 
	        	
	        	
	        	String UserID = res.getString("UserID");
	        	Timestamp GetFileTime = res.getTimestamp("GetFileTime");
	        	String FileAbstract = res.getString("FileAbstract");
	        	String SecurityTime = res.getString("SecurityTime");
	        	Timestamp SendtoCATime = res.getTimestamp("SendtoCATime");
	        	Timestamp GetfromCATime = res.getTimestamp("GetfromCATime");
	        	Timestamp MakeCertificateTime = res.getTimestamp("MakeCertificateTime");
	        	Timestamp SendtoClientTime = res.getTimestamp("SendtoClientTime");

	        	
	        	
	        	Post TimeNotes =new Post();
	        	TimeNotes.setUserID(UserID);
				TimeNotes.setGetFileTime(GetFileTime);
				TimeNotes.setFileAbstract(FileAbstract);
				TimeNotes.setSecurityTime(SecurityTime);
				TimeNotes.setSendtoCATime(SendtoCATime);
				TimeNotes.setGetfromCATime(GetfromCATime);
				TimeNotes.setMakeCertificateTime(MakeCertificateTime);
				TimeNotes.setSendtoClientTime(SendtoClientTime);


                
                
	            postlist.add(TimeNotes);
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
		 return postlist;
}
	 
	 
	   public boolean deleteTimeNotes(String userid,Timestamp getfiletime){
		   Connection conn=ConnectSql();
		    Statement st;
		   
		   try{
				
				String sql = "delete from DataSecurity..TimeNotes where UserID='"+userid+"' and GetFileTime='"+getfiletime+"'";
				
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
	   
	   
	   
		 public boolean addTimeNotes(Post TimeNotes)
			{
				Connection conn=ConnectSql();
				Statement st; 
				try{
					
					String sql = "insert into DataSecurity..TimeNotes(UserID,GetFileTime,FileAbstract,SecurityTime,SendtoCATime,GetfromCATime,MakeCertificateTime,SendtoClientTime)"
							+ "VALUES('"+TimeNotes.getUserID()+"','"+TimeNotes.getGetFileTime()+"','"+TimeNotes.getFileAbstract()+"','"+TimeNotes.getSecurityTime()+"','"+TimeNotes.getSendtoCATime()+"','"+TimeNotes.getGetfromCATime()+"','"+TimeNotes.getMakeCertificateTime()+"','"+TimeNotes.getSendtoClientTime()+"')";
					 
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
		 
		 
		   public String getUserIDByFileAbstract(String fileabstract){
			   Connection conn=ConnectSql();
			   Statement st;
			   String FileAbstract = "0";
			   try{	
					String sql = "select UserID from DataSecurity..TimeNotes where FileAbstract='"+fileabstract+"'";
			        st = (Statement) conn.createStatement();
			        ResultSet rs = st.executeQuery(sql);
			        while(rs.next())
		            {
			        	FileAbstract = rs.getString("FileAbstract");
		            }
		            conn.close();     
		    		return FileAbstract;
			       
			   }
			   
			   catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "no result";
				}
			   		  
		   }
		   
		   
		   
		   
		   public String getsendtoCAtimeBykey(String userid,String setime){
			   Connection conn=ConnectSql();
			   Statement st;
			   String UserID = userid;
			   String securitytime = setime;
			   String sendtoCAtime = "noresult";
			   try{	
					String sql = "select SendtoCATime from DataSecurity..TimeNotes where UserID='"+UserID+"' and SecurityTime='"+securitytime+"'";
			        st = (Statement) conn.createStatement();
			        ResultSet rs = st.executeQuery(sql);
			        while(rs.next())
		            {
			        	sendtoCAtime = rs.getString("SendtoCATime");
		            }
		            conn.close();     
		    		return sendtoCAtime;
			       
			   }
			   
			   catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "noresult";
				}
			   		  
		   }
		   
		   
		   
		   
		   public String getgetfromCAtimeBykey(String userid,String setime){
			   Connection conn=ConnectSql();
			   Statement st;
			   String UserID = userid;
			   String securitytime = setime;
			   String getfromCAtime = "noresult";
			   try{	
					String sql = "select GetfromCATime from DataSecurity..TimeNotes where UserID='"+UserID+"' and SecurityTime='"+securitytime+"'";
			        st = (Statement) conn.createStatement();
			        ResultSet rs = st.executeQuery(sql);
			        while(rs.next())
		            {
			        	getfromCAtime = rs.getString("GetfromCATime");
		            }
		            conn.close();     
		    		return getfromCAtime;
			       
			   }
			   
			   catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "noresult";
				}
			   		  
		   }
		   
		   
		   
		   
		   
		   
		   public List<Post> getPostByID(String ID){
				 List<Post> postlist = new ArrayList<Post>();
				 Connection conn=ConnectSql();
				 Statement st;
				 
				 
				 try{
					 String sql = "select * from DataSecurity..TimeNotes where UserID='"+ID+"'";
					 st = (Statement) conn.createStatement();  
					 
			         ResultSet res = st.executeQuery(sql);   
			         while (res.next()) { 
			        	
			        	
			        	String UserID = res.getString("UserID");
			        	Timestamp GetFileTime = res.getTimestamp("GetFileTime");
			        	String FileAbstract = res.getString("FileAbstract");
			        	String SecurityTime = res.getString("SecurityTime");
			        	Timestamp SendtoCATime = res.getTimestamp("SendtoCATime");
			        	Timestamp GetfromCATime = res.getTimestamp("GetfromCATime");
			        	Timestamp MakeCertificateTime = res.getTimestamp("MakeCertificateTime");
			        	Timestamp SendtoClientTime = res.getTimestamp("SendtoClientTime");

			        	
			        	
			        	Post TimeNotes =new Post();
			        	TimeNotes.setUserID(UserID);
						TimeNotes.setGetFileTime(GetFileTime);
						TimeNotes.setFileAbstract(FileAbstract);
						TimeNotes.setSecurityTime(SecurityTime);
						TimeNotes.setSendtoCATime(SendtoCATime);
						TimeNotes.setGetfromCATime(GetfromCATime);
						TimeNotes.setMakeCertificateTime(MakeCertificateTime);
						TimeNotes.setSendtoClientTime(SendtoClientTime);


		                
		                
			            postlist.add(TimeNotes);
			            //System.out.println(id);     
			            //System.out.println("test"); 
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
				 return postlist;
		}
				 
		   
		   
		   
}