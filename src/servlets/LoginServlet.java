package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DBManager;
import DAO.UserDAO;
@WebServlet("/HTML/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//TODO try to validate login. 
		String user = req.getParameter("user");
		String pass = req.getParameter("password");
		String fileName;
		try {
			if(UserDAO.getInstance().validLogin(user, pass)){
				System.out.println("Lognahme se!");
				fileName = "home.html";
			}
			else{
				fileName = "loginFailed.html";
			}
		} catch (SQLException e) {
			System.out.println("Error loging in - " + e.getMessage());
			fileName = "loginFailed.html";
		}
		RequestDispatcher rd = req.getRequestDispatcher(fileName);
		rd.forward(req, resp);
		
	}
	


	@Override
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
