package com.database.connections;
import com.datastructures.*;
import com.database.connections.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.ProjectManager;

public class ChatUtilities {
	public static HashMap<Integer,ArrayList> regMessagebox = new HashMap<Integer,ArrayList>();
	public static HashMap<Integer,String> authenticationList = new HashMap<Integer,String>();
	public static boolean authsloaded=false;
	public static ArrayList<User> userlist = new ArrayList<User>();	
}
