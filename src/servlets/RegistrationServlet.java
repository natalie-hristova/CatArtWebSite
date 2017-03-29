package servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import DAO.DBManager;
import DAO.UserDAO;
import model.User;
import model.User.Gender;
import model.User.Rights;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = 5;

		String fileName= "register.html";
		try {
			 result = addUser(req);
		} catch (SQLException e) {
			System.out.println("SQL  exception occured");
			e.printStackTrace();
		}
		if (result == 0) {
			fileName = "home.html";
		}
		if (result==1) {
			fileName= "loginFailed.html";
		}
		if (result==10) {
			fileName= "register.html";
			// opravi gre6kata tam s parolata
		}
		if (result ==100) {
			fileName= "register.html";
			// ima go v db
		}if (result ==11) {
			fileName= "register.html";
			// ne si validen gram
		}
		RequestDispatcher rd = req.getRequestDispatcher(fileName);
		rd.forward(req, resp);
	}

	private boolean isValidPasword(String password, String password2) {
		return (password.equals(password2));
	}

	
	//tova trqbva da e v userDAO
	public synchronized int addUser(HttpServletRequest req) throws SQLException {
		// TODO insert into db
		int errorCode = 0;
		String username = req.getParameter("username");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2");
		String gender = req.getParameter("gender");
		String interests = req.getParameter("interest");
		String country = req.getParameter("country");
		String day = req.getParameter("day");
		String month = req.getParameter("month");
		String year = req.getParameter("year");
		String bday = String.format("%s-%s-%s", year, month, day);
		User user = null;
		Calendar date = Calendar.getInstance();
		System.out.println(Integer.parseInt(year)+ "   "+  Integer.parseInt(month)+"    "+ Integer.parseInt(day));
		date.clear(); 
		date.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		
		System.out.println(date);
		try {
			user = new User(username, password2, email, name, date, UserDAO.getInstance().parseGender(gender),
					interests, country, Rights.MEMBER);
		} catch (ValidationException e) {
			errorCode +=1;// za nevaliden user
			System.out.println("Invalid user");
			e.printStackTrace();
		}

		if (isValidPasword(password, password2) ) {
			if( !UserDAO.getInstance().existsInDB(user) && user!=null){

			String sql = "INSERT INTO users (name, username, password,email,signiture,country_id,birthday,gender,rights,joining_date) values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			st.setString(1, user.getName());
			st.setString(2, user.getUsername());
			st.setString(3, user.getPass());// TODO hash pass
			st.setString(4, user.getEmail());
			st.setString(5, user.getSignature());
			st.setInt(6, Integer.parseInt(country));
			st.setString(7, bday);
			st.setString(8, gender);
			st.setString(9, "MEMBER");
			st.setDate(10, new java.sql.Date(user.getJoiningDate().getTime()));
			st.executeLargeUpdate();
			ResultSet res = st.getGeneratedKeys();
			res.next();
			long id = res.getLong(1);
			user.setUserID(id);
			UserDAO.getInstance().getAllUsers().put(user.getUsername(), user);
			}else{
				errorCode +=100;//ako go ima v db
			}
		}else{
			errorCode +=10;//  nevalidna parola
		}
		return errorCode;
	}


	public void destroy() {
		try {
			DBManager.getInstance().getConnection().close();
		} catch (SQLException e) {
			System.out.println("Log in servlet not closed!");
			e.printStackTrace();
		}
		super.destroy();
	}
}
