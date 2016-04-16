package com.datastructures;

public class User {
    private int reg_id;
    private String first_name;
    private String last_name;
    private String password;
    private String username;
    private String pic;
    private String Mail;
    private String status;
    public User(int id,String fn,String ln,String un,String p,String pi,String m,String s){
        reg_id=id;
        first_name=fn;
        last_name=ln;
        password=p;
        username=un;
        pic=pi;
        Mail=m;
        status=s;
        
    }
    public User() {
		// TODO Auto-generated constructor stub
	}
	public void setReg_id(int id){
        reg_id = id;
    }
    public void setFirst_name(String fn){
        first_name= fn;
    }
    public void setLast_name(String ln){
        last_name= ln;
    }
    public void setUsername(String un){
        username= un;
    }
    public void setPassword(String pw){
        password = pw;
    }

    public String getMail() {
        return Mail;
    }

    public String getPic() {
        return pic;
    }

    public String getStatus() {
        return status;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getReg_id() {
        return reg_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
