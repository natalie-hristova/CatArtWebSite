package DAO;

import java.io.File;
import java.io.FileNotFoundException;
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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.ValidationException;

import com.mysql.fabric.xmlrpc.base.Array;

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

	public User getUser(long id) throws ValidationException, SQLException {
		User user = null;
		String sql = "SELECT   password, email, gender, rights,username, name, birthday, signiture, avatar, joining_date,  country_id FROM users WHERE user_id = "
				+ id + ";";
		PreparedStatement st = null;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet res = null;
		try {
			res = st.executeQuery(sql);
			if (res.next()) {
				String rights = res.getString("rights");
				String gender = res.getString("gender");
				user = new User(res.getString("username"), res.getString("password"), res.getString("email"),
						getGender(gender), getRights(rights));
				if (res.getTimestamp("birthday") != null) {
					user.setBirthday(res.getTimestamp("birthday").toLocalDateTime());
				}
				user.setJoiningDate((res.getTimestamp("joining_date")).toLocalDateTime());
				user.setUserID(id);
				user.setName(res.getString("name"));
				user.setAvatar(fromBlobToFile("avatar", res));
				user.setSigniture(res.getString("signiture"));
			}

		} catch (SQLException | ValidationException e) {
			System.out.println("cant get user");
			e.printStackTrace();
		}
		return user;
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

	public User getUser(String username) {
		User user = null;
		String sql = "SELECT  user_id, password, email, gender, rights, name, birthday, signiture, avatar, joining_date,  country_id FROM users WHERE username = '"
				+ username + "';";
		PreparedStatement st = null;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet res = null;
		try {
			res = st.executeQuery(sql);
			if (res.next()) {
				String rights = res.getString("rights");
				String gender = res.getString("gender");
				user = new User(username, res.getString("password"), res.getString("email"), getGender(gender),
						getRights(rights));
				if (res.getTimestamp("birthday") != null) {
					user.setBirthday(res.getTimestamp("birthday").toLocalDateTime());
				}
				user.setJoiningDate((res.getTimestamp("joining_date")).toLocalDateTime());
				user.setUserID(res.getLong("user_id"));
				user.setName(res.getString("name"));
				user.setAvatar(fromBlobToFile("avatar", res));
				user.setSigniture(res.getString("signiture"));
			}

		} catch (SQLException | ValidationException e) {
			System.out.println("cant get user");
			e.printStackTrace();
		}
		return user;
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

	public List<String> getListOfFriends(String username) {
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

	public List<String> getListOfBlocked(String username) {
		List<String> friends = new ArrayList<>();
		User u = getInstance().getUser(username);
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
		List<String> friends = getInstance().getListOfFriends(username);
		List<String> blocked = getInstance().getListOfBlocked(username);
		for (int i = 0; i < allUsers.size(); i++) {
			String currentUser = allUsers.get(i);
			if (!(blocked.contains(currentUser) && friends.contains(currentUser))) {
				justUsers.add(currentUser);
			}
		}
		return Collections.unmodifiableList(justUsers);
	}
}
