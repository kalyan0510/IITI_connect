package webService;

import java.sql.Connection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.axis.encoding.Base64;

import com.database.connections.Database;
import com.database.connections.Project;
import com.datastructures.User;
import com.google.gson.Gson;

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
	public Boolean changedp(int Reg_id,String	 x){
		System.out.println("change dp called");
		 Database database= new Database();
		    Connection connection;
			try {
				
				connection = database.Get_Connection();
				//return false;
				return  Project.changedp(connection,Reg_id,x );
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
	
	
	
		

}