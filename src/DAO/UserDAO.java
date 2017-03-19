package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.bind.ValidationException;

import model.Photo.Genre;
import model.User;
import model.User.Gender;
import model.User.Rights;

public class UserDAO {

	
	
	public static User getUser(long id) throws ValidationException, SQLException{
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
			Rights r = Rights.MEMBER;;
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
}
