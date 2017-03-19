package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.bind.ValidationException;

import model.User;

public class Demo {

	private static Demo instance;
	private static final String DB_IP = "127.0.0.1";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "natadb";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "Nata1723";
	private Connection con = null;

	private Demo() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found!");
		}
		try {
			String connectionString = String.format("jdbc:mysql://%s:%s/%s",  DB_IP, DB_PORT, DB_NAME);
			con = DriverManager.getConnection(connectionString, DB_USER,
					DB_PASS);
		} catch (SQLException e) {
			System.out.println("Error conneting to db"+e.getMessage());
		}
	}

	public static synchronized Demo getInstance() {

		if (instance == null) {
			instance = new Demo();
		}
		return instance;
	}

	public Connection getConnection(){
		return con;
	}
	private void x() {
		User naty = null;
		try {
			naty = new User("natalie", "12345", "naty@abv.bg", User.Gender.F);
		} catch (ValidationException e1) {
			System.out.println("this user already exists");
			e1.printStackTrace();
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found!");
		}

		try {
			PreparedStatement st = con.prepareStatement(
					"INSERT INTO users(username,password,email,gender,joining_date,rights) " + "VALUES(?,?,?,?,?,?)");
			st.setString(1, "natalie");
			st.setString(2, "12345");
			st.setString(3, "naty@abv.bg");
			st.setString(4, "F");
			st.setDate(5, new java.sql.Date(naty.getJoiningDate().getTime()));
			st.setString(6, "MEMBER");
			st.executeUpdate();
			ResultSet result = st.executeQuery("SELECT * FROM users;");
			System.out.println("We have a result");
			while (result.next()) {
				String name = result.getString("username");
				String pass = result.getString("password");
				String email = result.getString("email");
				String gender = result.getString("gender");

				System.out.println(name + " " + pass + " " + email + " " + gender);
			}
		} catch (SQLException e) {
			System.out.println("Statement error!" + e.getMessage());
		}

	}


}
