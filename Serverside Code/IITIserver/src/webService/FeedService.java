package webService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.database.connections.Database;
import com.database.connections.Project;
import com.datastructures.Message;
import com.datastructures.Messagelist;
import com.datastructures.SOS;
import com.datastructures.User;
import com.datastructures.Userlist;
import com.datastructures.Warning;
import com.google.gson.Gson;
import com.database.connections.ChatUtilities;
import model.ProjectManager;

@Path("/WebService")
public class FeedService {
	@GET
	@Path("/GetFeeds")
	public String signup(final String username,String first_name,String last_name,final String mail){
		System.out.println(username+" trying to sign up");
		 Database database= new Database();
		    Connection connection;
			try {
				connection = database.Get_Connection();
				if(!ProjectManager.checkusername(connection, username))
					return "Username Already Exists";
				if(!ProjectManager.checkmail(connection, mail))
					return "Mail ID Already Exists";
				final String passwd=ProjectManager.generatepasswd();
				String x = Project.Adduser(connection, username, first_name, last_name,passwd , null,mail);
				new Thread(new Runnable(){
					public void run(){
						ProjectManager.email(mail, passwd,"confirmation",username);
					}
				}).start();
				return x;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return e.getMessage();
			}
		//	return "Successfully created. Check your mail for password";	
		    
	}
	@GET
	@Path("/GetFeeds")
	public String login(String username,String password){
		 Database database= new Database();
		    Connection connection;
			try {
				connection = database.Get_Connection();
				return  ""+Project.login(connection, username, password);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return e.getMessage();
			}	
		    
	}
	
	
	@GET
	@Path("/GetFeeds")
	public String forgotpassword(String entity,String value){
		 Database database= new Database();
		    Connection connection;
			try {
				System.out.println("forgot password by "+value);
				connection = database.Get_Connection();
				return  ""+Project.forgotoasswd(connection,entity, value);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return e.getMessage();
			}	
		    
	}
	
	@GET
	@Path("/GetFeeds")
	public String changepassword(int Reg_id,String oldpass,String newpass){
		 Database database= new Database();
		    Connection connection;
			try {
				connection = database.Get_Connection();
				return  ""+Project.changepasswd(connection,Reg_id, oldpass,newpass);
			} catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
	}
	

	@GET
	@Path("/GetFeeds")
	public Boolean changedp(int Reg_id,String	 x,String y){
		System.out.println("change dp called");
		 Database database= new Database();
		    Connection connection;
			try {
				
				connection = database.Get_Connection();
				//return false;
				return  Project.changedp(connection,Reg_id,x ,y);
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}
	}
	
	@GET
	@Path("/GetFeeds")
	public String getdp(int Reg_id){
		System.out.println("get dp called");
		 Database database= new Database();
		    Connection connection;
			try {
				
				connection = database.Get_Connection();
				//return false;
				return  Project.getdp(connection,Reg_id );
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}
	@GET
	@Path("/GetFeeds")
	public String geticon(int Reg_id){
		System.out.println("get icon called");
		 Database database= new Database();
		    Connection connection;
			try {
				
				connection = database.Get_Connection();
				//return false;
				return  Project.geticonimg(connection,Reg_id );
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}
	@GET
	@Path("/GetFeeds")
	public String getuser(int Reg_id){
		System.out.println("get User called");
		 Database database= new Database();
		    Connection connection;
			try {
				
				connection = database.Get_Connection();
				//return false;
				User u =   Project.getuserobj(connection,Reg_id );
				String json = new Gson().toJson(u);
				return json;
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			return "";
	}
	
	@GET
	@Path("/GetFeeds")
	public String setWarning(int Reg_id,String message,String type){
		
		
		
		Database database= new Database();
	    Connection connection;
		try {
			
			connection = database.Get_Connection();
			System.out.println("Warning set");
			Warning.setWarning(Project.getusername(connection, Reg_id),message, type); 
			return "Successfuly set";
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "";
	}
	
	@GET
	@Path("/GetFeeds")
	public String getWarning(){
		return Warning.getWarning();
	}
	
	@GET
	@Path("/GetFeeds")
	public String isWarnignGenerated(){
		return Warning.isWarning()?"yes":"no";
	}
	
	@GET
	@Path("/GetFeeds")
	public String changeStatus(int reg_id,String status){
		
		System.out.println("get User called");
		 Database database= new Database();
		    Connection connection;
			try {
				
				connection = database.Get_Connection();
				return Project.changeStatus(connection,reg_id,status);
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
	}
	
	@GET
	@Path("/GetFeeds")
	public void clearWarning(){
		
		Warning.removeWarning();
	}
	
	
	
	
	
	
	@GET
	@Path("/GetFeeds")
	public String setSOS(int Reg_id,String message,String type){
		
		
		
		Database database= new Database();
	    Connection connection;
		try {
			
			connection = database.Get_Connection();
			System.out.println("Warning set");
			SOS.setSOS(Project.getusername(connection, Reg_id),message, type); 
			return "Successfuly set";
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "";
	}
	
	@GET
	@Path("/GetFeeds")
	public String getSOS(){
		return SOS.getSOS();
	}
	
	@GET
	@Path("/GetFeeds")
	public String isSOSGenerated(){
		return SOS.isSOS()?"yes":"no";
	}
	
	
	@GET
	@Path("/GetFeeds")
	public void clearSOS(){
		
		SOS.removeSOS();
	}
	@GET
	@Path("/GetFeeds")
	public boolean wasMacSet(String mac){
		Database database= new Database();
	    Connection connection;
		try {
			
			connection = database.Get_Connection();
			return Project.wasMacset(connection,mac);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return false;
	}
	@GET
	@Path("/GetFeeds")
	public boolean setAddress(String address,String mac,int reg_id){
		Database database= new Database();
	    Connection connection;
		try {
			
			connection = database.Get_Connection();
			return Project.setAddress(connection, reg_id, mac, address);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		System.out.println("Error in set Address");
		return true;
	}
	@GET
	@Path("/GetFeeds")
	public String getAddress(String mac){
		Database database= new Database();
	    Connection connection;
		try {
			
			connection = database.Get_Connection();
			return Project.getAddress(connection, mac);
	
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		System.out.println("Error in get Address");
		return "fail";
	}
	@GET
	@Path("/GetFeeds")
	public static String timenow() {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
       // System.out.println( sdf.format(cal.getTime()) );
	    return sdf.format(cal.getTime());
	}
	/*@GET
	@Path("/GetFeeds")
	public int loadAuth(){
		System.out.println("Loading Authenticattion List");
		 Database database= new Database();
		    Connection connection=null;
			try {
				connection = database.Get_Connection();
				ArrayList<User> ulist = com.database.connections.Project.GetUsers_det(connection);
				if(ulist==null){
					return 0;
				}
				
				connection.close();
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			try {
				if(connection!=null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
			
	}*/
	@GET
	@Path("/GetFeeds")
	public int loadusers(){
		System.out.println("Loading Authenticattion List");
		 Database database= new Database();
		    Connection connection=null;
			try {
				connection = database.Get_Connection();
				ArrayList<User> ulist = com.database.connections.Project.GetUsers_det(connection);
				if(ulist==null){
					return 0;
				}
				ChatUtilities.authenticationList.clear();
				for(User u:ulist){
					ChatUtilities.authenticationList.put(u.getReg_id(), u.getPassword());
					u.setPassword("");
					//System.out.println(u.getReg_id()+"  "+ u.getPassword());
				}
				ChatUtilities.userlist.clear();
				for(User u:ulist){
					ChatUtilities.userlist.add(u);
					System.out.println(u.getReg_id()+"  "+ u.getFirst_name()+' '+u.getLast_name());
				}
				
				
				connection.close();
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			try {
				if(connection!=null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
			
	}
	@GET
	@Path("/GetFeeds")
	public String getusersforsearch(){
		if(!ChatUtilities.authsloaded){
			loadusers();
			ChatUtilities.authsloaded=true;
		}	
		Userlist ul = new Userlist();
		ul.list = ChatUtilities.userlist;
		String json = new Gson().toJson(ul);
		//ChatUtilities.regMessagebox.get(reg).clear();
		return json;
	}

	@GET
	@Path("/GetFeeds")
	public int putmessage(int regfrom,String name,String au,int regto,String str,String img){
		try{
			if(ChatUtilities.authsloaded==false){
				loadusers();
			}
			ArrayList<Message> ar = (ArrayList<Message>)ChatUtilities.regMessagebox.get(regto);
			Message mess;
			if(ar==null){
				ar= new ArrayList<Message>();
				ChatUtilities.regMessagebox.put(regto,ar);
			}
			mess = new Message(regfrom,name, str, timenow(), img, 1);
			ChatUtilities.regMessagebox.get(regto).add(mess);
			return 1;
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	@GET
	@Path("/GetFeeds")
	public String getMessage(final int reg,String authentic){
		System.out.print("");
		if(!ChatUtilities.authsloaded){
			loadusers();
			ChatUtilities.authsloaded=true;
		}
		if(!ChatUtilities.authenticationList.get(reg).equals(authentic)){
			return "Invalid key";
		}
		
		if(ChatUtilities.regMessagebox.get(reg)==null){
			return new Gson().toJson(new Messagelist(new ArrayList()));
			
		}
		Messagelist ml = new Messagelist(ChatUtilities.regMessagebox.get(reg));
		String json = new Gson().toJson(ml);
		ChatUtilities.regMessagebox.get(reg).clear();
		return json;
	}
	

}