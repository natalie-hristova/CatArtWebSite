package DAO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.ValidationException;

import model.User;
import model.User.Gender;
import model.User.Rights;

public class UserDAO {
	private static UserDAO instance;
	private static final HashMap<String, User> allUsers = new HashMap<>();

	private UserDAO() {

	}

	public static synchronized UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	public synchronized boolean validLogin(String user, String pass) throws SQLException {
		boolean isValid = false;
		if (getAllUsers().containsKey(user)) {
			for(User u : UserDAO.allUsers.values()){
				if(u.getPass().equals(pass)){
					isValid = true;
					break;
				}
			}
		}
		return isValid;
	}

	public boolean existsInDB(User user) {
		if (allUsers.keySet().contains(user.getUsername())) {
			return true;
		}
		for (Iterator iterator = allUsers.values().iterator(); iterator.hasNext();) {
			User u = (User) iterator.next();
			if (u.getEmail().equals(user.getEmail())) {
				return true;
			}
		}
		return false;
	}
	
	public User.Gender parseGender(String gender) {
		if (gender == "F") {
			return User.Gender.F;
		} else if (gender == "M") {
			return User.Gender.M;
		}
		return null;
	}

	private User.Rights getRights(String right) {

		switch (right) {
		case "MEMBER":
			return User.Rights.MEMBER;
		case "ADMIN":
			return User.Rights.ADMIN;
		case "MODERATOR":
			return User.Rights.MODERATOR;
		default:
			return null;
		}
	}
	
	public HashMap<String, User> getAllUsers() throws SQLException {
		if (allUsers.isEmpty()) {
			String sql = "SELECT user_id, nickname, password, gender FROM users";
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				User u = new User(res.getString("nickname"), res.getString("password"),
						parseGender(res.getString("gender")));
				u.setUserID(res.getLong("user_id"));
				allUsers.put(u.getUsername(), u);
			}
		}
		return allUsers;
	}

	
	

public  User getUser(long id) throws ValidationException, SQLException{
		User u = null;
		String sql = "SELECT user_id, nickname, password, email, real_name, birthday, signature, avatar, registration_date, gender, rights, country_id FROM users WHERE user_id = " + id + ";";
		Statement st = DBManager.getInstance().getConnection().createStatement();
		ResultSet res = null;
		try {
			res = st.executeQuery(sql);
		} catch (SQLException e) {
		 	System.out.println("cant get user");
		}
		 //Create user with right rights :)
		if(res.next()){
		 	Rights r = Rights.MEMBER;
		 	if(res.getString("rights") == "admin"){
		 		r = Rights.ADMIN;
		 	}
		 	if(res.getString("rights") == "moderator"){
		 		r = Rights.MODERATOR;
		 	}
		 //and right gender
		 	if (res.getString("gender") == "M"){
		 		u = new User(res.getString("nickname"), res.getString("password"), res.getString("email"), Gender.M, r);
		 	}else{
		 		u =  new User(res.getString("nickname"), res.getString("password"), res.getString("email"), Gender.F, r);
		 	}
		 	u.setUserID(res.getLong("user_id"));
		 }
		//set all other stuff
		return u;
	}
public User getUser(String username) throws ValidationException, SQLException{
	User u = null;
	String sql = "SELECT user_id, nickname, password, email, real_name, birthday, signature, avatar, registration_date, gender, rights, country_id FROM users WHERE nickname = '" + username + "' ;";
	Statement st = DBManager.getInstance().getConnection().createStatement();
	ResultSet res = null;
	try {
		res = st.executeQuery(sql);
	} catch (SQLException e) {
	 	System.out.println("cant get user");
	}
	 //Create user with right rights :)
	if(res.next()){
	 	Rights r = Rights.MEMBER;
	 	if(res.getString("rights") == "admin"){
	 		r = Rights.ADMIN;
	 	}
	 	if(res.getString("rights") == "moderator"){
	 		r = Rights.MODERATOR;
	 	}
	 //and right gender
	 	if (res.getString("gender") == "M"){
	 		u = new User(res.getString("nickname"), res.getString("password"), res.getString("email"), Gender.M, r);
	 	}else{
	 		u =  new User(res.getString("nickname"), res.getString("password"), res.getString("email"), Gender.F, r);
	 	}
	 	u.setUserID(res.getLong("user_id"));
	 }
	//set all other stuff
	return u;
}

private File fromBlobToFile(String column, ResultSet rs) {
	 		File file = new File(column);
	 		Blob blob;
	 		try {
				if (rs.getBlob(column) != null) {
	 				blob = rs.getBlob(column);
	 				InputStream in = blob.getBinaryStream();
	 				OutputStream out = new FileOutputStream(file);
	 				byte[] buff = new byte[4096]; // how much of the blob to
	 												// read/write at a time
	 				int len = 0;
	 
	 				while ((len = in.read(buff)) != -1) {
	 					out.write(buff, 0, len);
					}
	 			}
	 		} catch (IOException | SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	 
	 		return file;
	 	}
	 
	 	public void addFriend(User user, User friend) {
	 		Connection con = DBManager.getInstance().getConnection();
	 		PreparedStatement st;
	 		try {
	 			st = con.prepareStatement("INSERT INTO friends (user_id,friend_id) VALUES (?,?);");
	 			st.setLong(1, user.getUserID());
	 			st.setLong(2, friend.getUserID());
	 			st.executeUpdate();
	 		} catch (SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	 
	 	}
	 
	 	public List<String> getListOfFriends(String username) throws ValidationException, SQLException {
	 		List<String> friends = new ArrayList<>();
	 		User u = getInstance().getUser(username);
	 		long id = u.getUserID();
	 		String sql = "SELECT friend_id FROM friends WHERE user_id = " + id + ";";
			PreparedStatement st;
	 		ResultSet res;
	 		try {
	 			st = DBManager.getInstance().getConnection().prepareStatement(sql);
	 			res = st.executeQuery(sql);
	 			while (res.next()) {
	 				friends.add(getUser(res.getLong("friend_id")).getUsername());
	 			}
	 		} catch (ValidationException | SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	 
	 		return Collections.unmodifiableList(friends);
	 	}
	 
	 	public List<String> getListOfBlocked(String username){
	 		List<String> friends = new ArrayList<>();
	 		User u = null;
			try {
				u = getInstance().getUser(username);
			} catch (ValidationException e1) {
				System.out.println("ops");
			} catch (SQLException e1) {
				System.out.println("ops");
			}
	 		long id = u.getUserID();
	 		String sql = "SELECT blocked_user_id FROM blocked_users WHERE user_id = " + id + ";";
	 		PreparedStatement st;
	 		ResultSet res;
	 		try {
	 			st = DBManager.getInstance().getConnection().prepareStatement(sql);
	 			res = st.executeQuery(sql);
	 			while (res.next()) {
	 				friends.add(getUser(res.getLong("blocked_user_id")).getUsername());
	 			}
	 		} catch (ValidationException | SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	 
	 		return Collections.unmodifiableList(friends);
	 	}
	 
	 	public void blockUser(User user, User blocked) {
	 		Connection con = DBManager.getInstance().getConnection();
	 		PreparedStatement st;
	 		try {
	 			st = con.prepareStatement("INSERT INTO blocked_users (user_id,blocked_user_id) VALUES (?,?);");
	 			st.setLong(1, user.getUserID());
	 			st.setLong(2, blocked.getUserID());
	 			st.executeUpdate();
	 		} catch (SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	 	}
	 
	 	public void removeFromFriends(User user, User friend) {
	 		Connection con = DBManager.getInstance().getConnection();
	 		PreparedStatement st;
	 		try {
	 			st = con.prepareStatement("DELETE FROM friends WHERE user_id = " + user.getUserID() + " AND friend_id ="
	 					+ friend.getUserID() + ";");
	 			st.setLong(1, user.getUserID());
	 			st.setLong(2, friend.getUserID());
	 			st.executeUpdate();
	 		} catch (SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	 	}
	 
	 	public void removeFromBlocked(User user, User blocked) {
	 		Connection con = DBManager.getInstance().getConnection();
	 		PreparedStatement st;
	 		try {
	 			st = con.prepareStatement("DELETE FROM blocked_users WHERE user_id = " + user.getUserID()
	 					+ " AND blocked_user_id =" + blocked.getUserID() + ";");
	 			st.setLong(1, user.getUserID());
	 			st.setLong(2, blocked.getUserID());
	 			st.executeUpdate();
	 		} catch (SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	 	}
	 
	 
	 	public List<String> getAllNotBlockedOrFriends(String username){
	 		List<String> justUsers = new ArrayList<>();
	 		List<String> allUsers = getInstance().getListOfUsers();
	 		List<String> friends = null;
	 		List<String> blocked = null;
			try {
				friends = getInstance().getListOfFriends(username);
				blocked = getInstance().getListOfBlocked(username);
			} catch (ValidationException e) {
				System.out.println("ops");
			} catch (SQLException e) {
				System.out.println("ops");
			}
			for (int i = 0; i < allUsers.size(); i++) {
	 			String currentUser = allUsers.get(i);
	 			if (!(blocked.contains(currentUser) && friends.contains(currentUser))) {
	 				justUsers.add(currentUser);
	 			}
	 		}
	 		return Collections.unmodifiableList(justUsers);
	 	}
	 	public List<String> getListOfUsers() {
	 		List<String> users = new ArrayList();
	 		String sql = "SELECT user_id, username, password, gender FROM users;";
	 		PreparedStatement st;
	 		ResultSet res = null;
	 		try {
	 			st = DBManager.getInstance().getConnection().prepareStatement(sql);
	 			res = st.executeQuery();
	 		} catch (SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	 
	 		try {
	 			while (res.next()) {
	 				User u = new User(res.getString("username"), res.getString("password"),
	 						parseGender(res.getString("gender")));
	 				u.setUserID(res.getInt("user_id"));
	 				users.add(res.getString("username"));
	 			}
	 		} catch (SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	 		return users;
	 	}
}