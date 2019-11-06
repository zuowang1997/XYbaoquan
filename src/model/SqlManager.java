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



public class SqlManager {
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
	
	
	public String registor(User user)
	{
		Connection conn=ConnectSql();
		Statement st; 
		String userId = "";
		try{
			String sql = "insert into DataSecurity..UserInformation(UserID, UserAge, UserGender, UserOccupation,UserTelephone,UserEmail,RegistrationTime,UserLevel,UserPassword)  VALUES('"+user.getUserID()+"','"+user.getUserAge()+"','"+user.getUserGender()+"','"+user.getUserOccupation()+"','"+user.getUserTelephone()+"','"+user.getUserEmail()+"','"+user.getRegistrationTime()+"','"+user.getUserLevel()+"','"+user.getUserPassword()+"')";
			//System.out.println(sql); 
	        st = (Statement) conn.createStatement();
	        st.executeUpdate(sql);
	        conn.close();
	        
	        userId = findUserIdByEmail(user.getUserEmail());
	        System.out.println("The new userId: "+userId);
	        return userId;
        }
		catch(SQLException e)
		{
			System.out.println(e); 
			return "no user！";	
		}
		
	}
	

	/*function Find a user by his email and return only his ID  */
	public String findUserIdByEmail(String email)
	{
		Connection conn=ConnectSql();
		Statement st; 
		String sql="";
		String resId = "";
		String type ="";
		try {  
			sql = "select UserID from DataSecurity..UserInformation where UserEmail='"+email+"'";
            st = (Statement) conn.createStatement();  
            ResultSet rs = st.executeQuery(sql); 
            while(rs.next())
            {
    
            	resId = rs.getString("UserID");
	            
            }
            conn.close();   
    		return resId;
    		
              
        } catch (SQLException e) {  
        	System.out.println(e);
            return "no result found";
        }
	}
	
	

	/*function Login with users email and pwd  */
	public boolean LoginSql(String Name,String Pwd) 
	{  
		String wangsiyu="wangsiyu";
		String password="12080003";
	    if((Name.equals(wangsiyu)) & (Pwd.equals(password))) {  
	    	return true;
	    }  
	    else{
	    	return false;	    	
	    }
	}
	
	
	public boolean UserLogin(String Name,String Pwd) 
	{  
		String UserId = Name;
		String PassWord = Pwd;
		String password = null;
		Connection conn=ConnectSql();
		Statement st; 
		String sql="";
		try {  
			sql = "select UserPassword from DataSecurity..UserInformation where UserID='"+UserId+"'";
            st = (Statement) conn.createStatement();  
            ResultSet res = st.executeQuery(sql); 
            while(res.next())
            {
            	password = res.getString("UserPassword");
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
		
	    if(PassWord.equals(password)) {  
	    	return true;
	    }  
	    else{
	    	return false;	    	
	    }
	}
	
	
	
	
	
	
	/*Function for addcourse */
	 public String addSecurityNotes(Course SecurityNotes)
	{
		Connection conn=ConnectSql();
		Statement st; 
		String certificatecode="";
		try{
			
			String sql = "insert into DataSecurity..SecurityNotes(ID, SecurityTime, FileAbstract,Files,CertificateCode)"
					+ "VALUES('"+SecurityNotes.getID()+"','"+SecurityNotes.getSecurityTime()+"','"+SecurityNotes.getFileAbstract()+"','"+SecurityNotes.getFiles()+"','"+SecurityNotes.getCertificateCode()+"')";
			 
	        st = (Statement) conn.createStatement();
	        st.executeUpdate(sql);
	        conn.close();
	        certificatecode = SecurityNotes.getCertificateCode();
	        return certificatecode;
        }
		catch(SQLException e)
		{
			System.out.println(e); 
			return "nonono！";	
		}
		
	}
	 

	 
	 public List<User> readAllUsers(){
		 List<User> userlist = new ArrayList<User>();
		 Connection conn=ConnectSql();
		 Statement st;
		 
		 
		 try{
			 String sql = "select * from DataSecurity..UserInformation";
			 st = (Statement) conn.createStatement();  
			 
	         ResultSet res = st.executeQuery(sql);   
	         while (res.next()) { 
	        	
	        	String UserID=res.getString("UserID");
	        	int UserAge = res.getInt("UserAge");
	        	String UserGender=res.getString("UserGender");
	        	String UserOccupation=res.getString("UserOccupation");
	        	String UserTelephone=res.getString("UserTelephone");
	        	String UserEmail=res.getString("UserEmail");
	        	Timestamp RegistrationTime=res.getTimestamp("RegistrationTime");
	        	String UserLevel=res.getString("UserLevel");
	        	String UserPassword=res.getString("UserPassword");
	        	
	        	
	        	User user =new User();
                user.setUserID(UserID);
                user.setUserAge(UserAge);
                user.setUserGender(UserGender);
                user.setUserOccupation(UserOccupation);
                user.setUserTelephone(UserTelephone);
                user.setUserEmail(UserEmail);
                user.setRegistrationTime(RegistrationTime);
                user.setUserLevel(UserLevel);
                user.setUserPassword(UserPassword);
                
                
	            userlist.add(user);
	            System.out.println(UserID);     
	            System.out.println("test"); 
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
		 return userlist;
}
	 
	 
	 
	 
	   public boolean deleteUser(String userid){
		   String id = userid;
		   Connection conn=ConnectSql();
		    Statement st;
		   
		   try{
				
				String sql = "delete from DataSecurity..UserInformation where UserID='"+id+"'";
				
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
	   
	   public boolean updateUser(User user)
		{
			Connection conn=ConnectSql();
			Statement st; 
			try{
				
				String sql = "update DataSecurity..UserInformation set UserOccupation='"+user.getUserOccupation()+"',UserTelephone='"+user.getUserTelephone()+"',UserAge='"+user.getUserAge()+"',"
						+ "UserGender='"+user.getUserGender()+ "',RegistrationTime='"+user.getRegistrationTime()+ "',UserEmail='"+user.getUserEmail()+ "',UserLevel='"+user.getUserLevel()+ "',UserPassword='"+user.getUserPassword()+ "' where UserID='"+user.getUserID()+"'";
				
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
	   
	   
	   public User getUserById(String id)
		{
			User user=new User();
			Connection conn=ConnectSql();
			Statement st; 
			String sql="";
			try {  
				sql = "select * from DataSecurity..UserInformation where UserID='"+id+"'";
	            st = (Statement) conn.createStatement();  
	            ResultSet res = st.executeQuery(sql); 
	            while(res.next())
	            {
	            	String UserID=res.getString("UserID");
		        	int UserAge = res.getInt("UserAge");
		        	String UserGender=res.getString("UserGender");
		        	String UserOccupation=res.getString("UserOccupation");
		        	String UserTelephone=res.getString("UserTelephone");
		        	String UserEmail=res.getString("UserEmail");
		        	Timestamp RegistrationTime=res.getTimestamp("RegistrationTime");
		        	String UserLevel=res.getString("UserLevel");
		        	String UserPassword=res.getString("UserPassword");
		        	
	                user.setUserID(UserID);
	                user.setUserAge(UserAge);
	                user.setUserGender(UserGender);
	                user.setUserOccupation(UserOccupation);
	                user.setUserTelephone(UserTelephone);
	                user.setUserEmail(UserEmail);
	                user.setRegistrationTime(RegistrationTime);
	                user.setUserLevel(UserLevel);
	                user.setUserPassword(UserPassword);
		 
		           
	            }
	            conn.close();  
	    		return user;
	    		
	              
	        } catch (SQLException e) {  
	        	System.out.println(e);
	            return null;
	        }  
		}
	   
	   
	   
	   
	   public List<User> getUsersById(String id)
		{
		   List<User> userlist = new ArrayList<User>();
			String userid = id;
			Connection conn=ConnectSql();
			Statement st; 
			String sql="";
			try {  
				sql = "select * from DataSecurity..UserInformation where UserID='"+userid+"'";
	            st = (Statement) conn.createStatement();  
	            ResultSet res = st.executeQuery(sql); 
	            while(res.next())
	            {
		        	String UserID=res.getString("UserID");
		        	int UserAge = res.getInt("UserAge");
		        	String UserGender=res.getString("UserGender");
		        	String UserOccupation=res.getString("UserOccupation");
		        	String UserTelephone=res.getString("UserTelephone");
		        	String UserEmail=res.getString("UserEmail");
		        	Timestamp RegistrationTime=res.getTimestamp("RegistrationTime");
		        	String UserLevel=res.getString("UserLevel");
		        	String UserPassword=res.getString("UserPassword");
		        	
		        	User user =new User();
	                user.setUserID(UserID);
	                user.setUserAge(UserAge);
	                user.setUserGender(UserGender);
	                user.setUserOccupation(UserOccupation);
	                user.setUserTelephone(UserTelephone);
	                user.setUserEmail(UserEmail);
	                user.setRegistrationTime(RegistrationTime);
	                user.setUserLevel(UserLevel);
	                user.setUserPassword(UserPassword);
	                
	                userlist.add(user);
	            
		 
		           
	            }
	              
	        } 		 catch (SQLException e) {
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
		 return userlist;
		}
		
	   
		
		
	   public List<User> getUserByTelephone(String telephone)
		{
		   List<User> userlist = new ArrayList<User>();
			String usertelephone = telephone;
			
			Connection conn=ConnectSql();
			Statement st; 
			String sql="";
			try {  
				sql = "select * from DataSecurity..UserInformation where UserTelephone='"+usertelephone+"'";
	            st = (Statement) conn.createStatement();  
	            ResultSet res = st.executeQuery(sql); 
	            while(res.next())
	            {
		        	String UserID=res.getString("UserID");
		        	int UserAge = res.getInt("UserAge");
		        	String UserGender=res.getString("UserGender");
		        	String UserOccupation=res.getString("UserOccupation");
		        	String UserTelephone=res.getString("UserTelephone");
		        	String UserEmail=res.getString("UserEmail");
		        	Timestamp RegistrationTime=res.getTimestamp("RegistrationTime");
		        	String UserLevel=res.getString("UserLevel");
		        	String UserPassword=res.getString("UserPassword");
		        	
		        	User user=new User();
	                user.setUserID(UserID);
	                user.setUserAge(UserAge);
	                user.setUserGender(UserGender);
	                user.setUserOccupation(UserOccupation);
	                user.setUserTelephone(UserTelephone);
	                user.setUserEmail(UserEmail);
	                user.setRegistrationTime(RegistrationTime);
	                user.setUserLevel(UserLevel);
	                user.setUserPassword(UserPassword);
	            
	                userlist.add(user);
		           
	            }
             
	        } 		 catch (SQLException e) {
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
		 return userlist;
		}
		
		
	   
		
	   public List<User> getUserByEmail(String email)
		{
		   List<User> userlist = new ArrayList<User>();
			String useremail = email;
			
			Connection conn=ConnectSql();
			Statement st; 
			String sql="";
			try {  
				sql = "select * from DataSecurity..UserInformation where UserEmail='"+useremail+"'";
	            st = (Statement) conn.createStatement();  
	            ResultSet res = st.executeQuery(sql); 
	            while(res.next())
	            {
		        	String UserID=res.getString("UserID");
		        	int UserAge = res.getInt("UserAge");
		        	String UserGender=res.getString("UserGender");
		        	String UserOccupation=res.getString("UserOccupation");
		        	String UserTelephone=res.getString("UserTelephone");
		        	String UserEmail=res.getString("UserEmail");
		        	Timestamp RegistrationTime=res.getTimestamp("RegistrationTime");
		        	String UserLevel=res.getString("UserLevel");
		        	String UserPassword=res.getString("UserPassword");
		        	
		        	User user=new User();
	                user.setUserID(UserID);
	                user.setUserAge(UserAge);
	                user.setUserGender(UserGender);
	                user.setUserOccupation(UserOccupation);
	                user.setUserTelephone(UserTelephone);
	                user.setUserEmail(UserEmail);
	                user.setRegistrationTime(RegistrationTime);
	                user.setUserLevel(UserLevel);
	                user.setUserPassword(UserPassword);
	            
	                userlist.add(user);
		           
	            }
              
	        } 		 catch (SQLException e) {
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
		 return userlist;
		}
	   
	   
	   
	   public String getRegisterTimeByID(String id)
		{
		   String RegisterTime = null;
			String userID = id;
			
			Connection conn=ConnectSql();
			Statement st; 
			String sql="";
			try {  
				sql = "select RegistrationTime from DataSecurity..UserInformation where UserID='"+userID+"'";
	            st = (Statement) conn.createStatement();  
	            ResultSet res = st.executeQuery(sql); 
	            while(res.next())
	            {
	            	RegisterTime = res.getString("RegistrationTime");
		           
	            }
             
	        } 		 catch (SQLException e) {
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
		 return RegisterTime;
		}
	   
	   
	   
	   
	   
		
	   
	   
	   
	   public String getSecurityFilesById(String id)
		{
		    String filelist = "《";
			String userid = id;
			Connection conn=ConnectSql();
			Statement st; 
			String sql="";
			try {  
				sql = "select DISTINCT FileName from DataSecurity..SecurityNotes where ID='"+userid+"'";
	            st = (Statement) conn.createStatement();  
	            ResultSet res = st.executeQuery(sql); 
	            while(res.next())
	            {
		        	String FileName=res.getString("FileName");
		        	System.out.println(FileName);
		        	filelist = filelist + FileName;
		        	filelist = filelist + "》《";
	                
	            }
	            filelist = filelist.substring(0, filelist.length()-1);
	            System.out.println(filelist);
	              
	        } 		 catch (SQLException e) {
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
		 return filelist;
		}
	   
	   
	   
	   
	   
		

	   public int CountAllUsers(){
			 Connection conn=ConnectSql();
			 Statement st;

			 try{
				 String sql = "select UserID from DataSecurity..UserInformation";
				 st = (Statement) conn.createStatement();  
				 
		         ResultSet countid = st.executeQuery(sql);  
		         
		         int i = 0;
		         while(countid.next())
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

