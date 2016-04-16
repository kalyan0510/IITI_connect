package model;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.database.connections.Database;
import com.database.connections.Project;
import com.datastructures.User;
public class ProjectManager {
	
	public static String generatepasswd(){
		String str="";
		for(int i=0;i<7;i++){
			str+=(int)(Math.random()*10);
		}
		return str;
	}
	public static void main(String[] args){
		Database database= new Database();
	    Connection connection;
		try {
			connection = database.Get_Connection();
			System.out.println(checkusername(connection,"kalyan"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public static String email(String mail,String pwd,String message,String username){
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		  // Get a Properties object
		     Properties props = System.getProperties();
		     props.setProperty("mail.smtp.host", "smtp.gmail.com");
		     props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		     props.setProperty("mail.smtp.socketFactory.fallback", "false");
		     props.setProperty("mail.smtp.port", "465");
		     props.setProperty("mail.smtp.socketFactory.port", "465");
		     props.put("mail.smtp.auth", "true");
		     props.put("mail.debug", "true");
		     props.put("mail.store.protocol", "pop3");
		     props.put("mail.transport.protocol", "smtp");//`
		     final String port = "iiticonnect@gmail.com";
		     final char[] portSequence = {102,117,99,107,105,110,103,112,111,116,97,116,111};
		     System.out.println("VOILA->"+new String(portSequence));
		     try{
		     Session session = Session.getDefaultInstance(props, 
		                          new Authenticator(){
		                             protected PasswordAuthentication getPasswordAuthentication() {
		                                return new PasswordAuthentication(port, new String(portSequence));
		                             }});

		   // -- Create a new message --
		     Message msg = new MimeMessage(session);

		  // -- Set the FROM and TO fields --
		     msg.setFrom(new InternetAddress("iiticonnect@gmail.com"));
		     msg.setRecipients(Message.RecipientType.TO, 
		                      InternetAddress.parse(mail+"@iiti.ac.in",false));
		     if(message.equals("confirmation")){
		    	 msg.setSubject("Confirmation");
			     msg.setText("Hi "+username+",\n\tThis is a conirmation mail for signing up at IITI connect android app\n"
			     		+ "	Your password is "+pwd+"\n\n"
			     				+ "If you did not try to signup for IITI Connect , please ignore this email");
		     }else if(message.equals("forgotpassword")){
		    	 msg.setSubject("Forgot password ");
			     msg.setText("Hi "+username+",\n\tThis is reply for using forgot password service at IITI connect android app\n"
			     		+ "	Your password is "+pwd+"\nIt is highly adviced to delete this mail or change the password after using this\n"
			     				+ "Have a nice day");
		     }
		     msg.setSentDate(new Date(0));
		     Transport.send(msg);
		     System.out.println("Message sent.");
		  }catch (MessagingException e){ System.out.println("Error caused by: " + e);}
			return "";
		    
	}

	public static boolean checkusername(Connection connection,String username){
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT username from User where username= "+"\'"+username+"\'");
			ResultSet rs = ps.executeQuery();
			rs.next();
			String un = rs.getString("username");
			System.out.println(""+un+"--"+username);
			if(un.equalsIgnoreCase(username))
				return false;
			else
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return true;
		}
	}
	public static boolean checkmail(Connection connection,String mail){
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT Mail from User where Mail= "+"\'"+mail+"\'");
			ResultSet rs = ps.executeQuery();
			rs.next();
			String un = rs.getString("Mail");
			if(un.equals(mail))
				return false;
			else
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return true;
		}
	}

	
}
