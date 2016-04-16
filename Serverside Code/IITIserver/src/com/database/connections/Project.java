package com.database.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.datastructures.User;

import model.ProjectManager;


public class Project { 
	
	public static ArrayList<User> GetUsers_det(Connection connection) 
	{
		ArrayList<User> users = new ArrayList<User>();
		
			//String uname = request.getParameter("uname");
			PreparedStatement ps;
			try {
				ps = connection.prepareStatement("SELECT username,reg_id,password,first_name,last_name,Mail FROM User");
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					User user = new User();
					user.setUsername(rs.getString("username"));
					user.setReg_id(rs.getInt("reg_id"));
					user.setPassword(rs.getString("password"));
					user.setFirst_name(rs.getString("first_name"));
					user.setLast_name(rs.getString("last_name"));
					user.setMail(rs.getString("Mail"));
					users.add(user);
				}
				return users;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			//ps.setString(1,uname);
			
		
		
	}
	public static  String Adduser(Connection connection,String username,String first_name,String last_name,String password, String pic,String mail){
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT MAX(reg_id) as max FROM User");
			ResultSet rs = ps.executeQuery();
			rs.next();
			int x= rs.getInt("max")+3;
			ps=null;
			ps = connection.prepareStatement("Insert into User values (?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, x);
			ps.setString(2,first_name );
			ps.setString(3,last_name );
			ps.setString(4,password );
			ps.setString(5,username );
			ps.setString(6, mail);
			ps.setString(7, "");
			ps.setString(8, null);
			ps.setString(9, null);
			 ps.execute();
			
			
			
			return "Success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "in proj \n"+e.getMessage();
		}
		
	}
	public static  String login(Connection connection,String username,String password){
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT reg_id,password  FROM `User` where BINARY `username`= "+"\""+username+"\"");
			ResultSet rs = ps.executeQuery();
			rs.next();
			String pa = rs.getString("password");
			if(pa.equals(password))
				return rs.getString("reg_id");
			else
				return "Incorrect password";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Invalid login credentials";
		}
		
	}
	public static String forgotoasswd(Connection connection,String um,String X){
		PreparedStatement ps;
		try {
			
			ps = connection.prepareStatement("SELECT *  FROM User where "+um+"= "+"\""+X+"\"");
			ResultSet rs = ps.executeQuery();
			rs.next();
			String mail = rs.getString("Mail");
			String pass = rs.getString("password");
			if(mail!=null){
				ProjectManager.email(mail, pass,"forgotpassword",X);
				return "Success";
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Invalid/Wrong credentials";
		}
		return "Failed";
	}
	public static String changepasswd(Connection connection,int reg_id,String pwd,String np){
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT * from User where reg_id="+reg_id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String cp = rs.getString("password");
			if(!cp.trim().equals(pwd.trim())){
				return "please check your password";
			}
			ps = connection.prepareStatement("UPDATE User SET password="+"\""+np+"\""+"where reg_id="+reg_id);
			 if(!ps.execute())
				 return "password successfuly changed";
			 else
				 return "error in changing to new password";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "Invalid/Wrong registration ID";
		}	
	}
	public static Boolean changedp(Connection connection, int reg_id, String array, String icon_array) {
		PreparedStatement ps;
		try {
			System.out.println("reg_id->"+reg_id);
			ps = connection.prepareStatement("UPDATE User SET pic = ? ,icon = ? where reg_id="+reg_id);
			ps.setString(1, array);
			ps.setString(2, icon_array);
			Boolean rs = ps.execute();
			System.out.println("rs->"+rs);
			return rs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}	
	}
	public static String getdp(Connection connection, int reg_id) {
		PreparedStatement ps;
		try {
			System.out.println("reg_id->"+reg_id);
			ps = connection.prepareStatement("Select pic from User where reg_id="+reg_id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getString("pic");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	public static String geticonimg(Connection connection, int reg_id) {
		PreparedStatement ps;
		try {
			System.out.println("reg_id->"+reg_id);
			ps = connection.prepareStatement("Select icon from User where reg_id="+reg_id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getString("icon");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	public static User getuserobj(Connection connection, int reg_id) {
		PreparedStatement ps;
		try {
			System.out.println("reg_id->"+reg_id);
			ps = connection.prepareStatement("Select * from User where reg_id="+reg_id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return new User(reg_id, rs.getString("first_name"),rs.getString("last_name"), rs.getString("username")
					, rs.getString("password"),rs.getString("icon"),rs.getString("Mail"), rs.getString("status"));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}	
		return new User(reg_id,"Anonymous","","xD","",null,"","ooo");
	}
	
	public static String getusername(Connection connection, int reg_id) {
		PreparedStatement ps;
		try {
			System.out.println("reg_id->"+reg_id);
			ps = connection.prepareStatement("Select * from User where reg_id="+reg_id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return   rs.getString("first_name")+' '+rs.getString("last_name");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}	
		return "unregistered user";
	}
	public static String changeStatus(Connection connection,int reg_id, String status) {
		PreparedStatement ps;
		try {
			System.out.println("reg_id->"+reg_id);
			ps = connection.prepareStatement("UPDATE User SET status = ? where reg_id="+reg_id);
			ps.setString(1, status);
			Boolean rs = ps.execute();
			connection.close();
			return "Success";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}	
	}
	public static boolean wasMacset(Connection connection, String mac) {
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("select * from MnA where mac=\""+mac+"\"");
			ResultSet rs = ps.executeQuery();
			rs.next();
			String address = rs.getString("address");
			
			if(address.equals(""))
				return false;
			else
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}
	public static String getAddress(Connection connection, String mac) {
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("select * from MnA where mac=\""+mac+"\"");
			ResultSet rs = ps.executeQuery();
			rs.next();
			String address = rs.getString("address");
			
			return ""+address;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failure";
		}	
	}
	public static boolean setAddress(Connection connection, int reg_id, String mac,String address) {
		PreparedStatement ps;
		try {	
			
			ps = connection.prepareStatement("Delete from MnA where mac=\""+mac+"\"");
			
			if(!ps.execute())
			ps = connection.prepareStatement("Insert into MnA values ( "+"\""+mac+"\""+","+"\""+address+"\""+",\""+Project.getusername(connection, reg_id)+"\")");
			return  ps.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}	
	}
	
}
