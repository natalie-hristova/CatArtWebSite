package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import model.User;

public class UserDAO {
	private static UserDAO instance;
	private static final HashMap<String, User> allUsers = new HashMap<>();//username - > user

	private UserDAO(){
		
	}

	public static synchronized UserDAO getInstance(){
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	public synchronized boolean validLogin(String user, String pass) throws SQLException {
		if(getAllUsers().containsKey(user)){
			boolean isValid = getAllUsers().get(user).getPass().equals(pass);
			return isValid;
		}
		return false;
	}

	public boolean existsInDB(User user){
		if (allUsers.keySet().contains(user.getUsername())) {
			return true;
		}for (Iterator iterator = allUsers.values().iterator(); iterator.hasNext();) {
			User u = (User) iterator.next();
			if (u.getEmail().equals(user.getEmail())) {
				return true;
			}
		}
		return false;
	}

	public HashMap<String, User> getAllUsers() throws SQLException{
		if(allUsers.isEmpty()){
			String sql = "SELECT user_id, username, password, gender FROM users;";
			PreparedStatement st = Demo.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while(res.next()){
				User u = new User( res.getString("username"),res.getString("password"),parseGender(res.getString("gender")));
				u.setId(res.getInt("user_id"));
				allUsers.put(u.getUsername(), u);
			}
		}
		System.out.println(allUsers+ "===============================================");
		return allUsers;
	}

	public User.Gender parseGender(String gender){
		if (gender=="F") {
			return User.Gender.F;
		}else if (gender=="M") {
			return User.Gender.M;
		}
		return null;
	}
	
	
}
