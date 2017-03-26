package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.bind.ValidationException;

import model.User;
import model.User.Gender;
import model.User.Rights;

public class UserDAO {
	private static UserDAO instance;
	private static final HashMap<String, User> allUsers = new HashMap<>();// username
																			// -
																			// >
																			// user

	private UserDAO() {

	}

	public static synchronized UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	public synchronized boolean validLogin(String user, String pass) throws SQLException {
		if (getAllUsers().containsKey(user)) {
			boolean isValid = getAllUsers().get(user).getPass().equals(pass);
			return isValid;
		}
		return false;
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

	public HashMap<String, User> getAllUsers() throws SQLException {
		if (allUsers.isEmpty()) {
			String sql = "SELECT user_id, username, password, gender FROM users;";
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				User u = new User(res.getString("username"), res.getString("password"),
						parseGender(res.getString("gender")));
				u.setUserID(res.getInt("user_id"));
				allUsers.put(u.getUsername(), u);
			}
		}
		System.out.println(allUsers + "===============================================");
		return allUsers;
	}

	public User.Gender parseGender(String gender) {
		if (gender == "F") {
			return User.Gender.F;
		} else if (gender == "M") {
			return User.Gender.M;
		}
		return null;
	}

	private static User.Rights getRights(String right) {

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

	public static User getUser(long id) throws ValidationException, SQLException {
		User u = null;
		String sql = "SELECT  username, password, email, gender, rights,user_id, name, birthday, signiture, avatar, joining_date,  country_id FROM users WHERE user_id = "
				+ id + ";";
		Statement st = DBManager.getInstance().getConnection().createStatement();
		ResultSet res = null;
		try {
			
			res = st.executeQuery(sql);
			String rights = res.getString("rights");
			String gender=res.getString("gender");
			User user = new User(res.getString("username"), res.getString("password"), res.getString("email"),getGender(gender), getRights(rights));
		} catch (SQLException e) {
			System.out.println("cant get user");
		}
		// Create user with right rights :)
		if (res.next()) {
			Rights r = Rights.MEMBER;
			;
			if (res.getString("rights") == "admin") {
				r = Rights.ADMIN;
			}
			if (res.getString("rights") == "moderator") {
				r = Rights.MODERATOR;
			}
			// and right gender
			if (res.getString("gender") == "M") {
				u = new User(res.getString("nickname"), res.getString("password"), res.getString("email"), Gender.M, r);
			} else {
				u = new User(res.getString("nickname"), res.getString("password"), res.getString("email"), Gender.F, r);
			}
			u.setUserID(res.getLong("user_id"));
		}
		// set all other stuff
		return u;
	}

	private static Gender getGender(String gender) {
		switch (gender) {
		case "M":
			return User.Gender.M;
		case "F":
			return User.Gender.F;
		default:
			return null;
		}
	}


	public static User getUser(String username){
		User user = null;
		String sql = "SELECT  user_id, password, email, gender, rights,user_id, name, birthday, signiture, avatar, joining_date,  country_id FROM users WHERE username = "
				+ username + ";";
		Statement st= null;
		try {
			st = DBManager.getInstance().getConnection().createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet res = null;
		try {
			
			res = st.executeQuery(sql);
			String rights = res.getString("rights");
			String gender=res.getString("gender");
			try {
				 user = new User(res.getString("username"), res.getString("password"), res.getString("email"),getGender(gender), getRights(rights));
				 user.setBirthday(res.getTimestamp("birthday").toLocalDateTime());
				 user.setJoiningDate((res.getTimestamp("joining_date")).toLocalDateTime());
				 user.setUserID(res.getLong("user_id"));
				 user.setName(res.getString("name"));
			} catch (ValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			System.out.println("cant get user");
		}
		return user;
	}
}
