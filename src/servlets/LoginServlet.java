package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import DAO.DBManager;
import DAO.UserDAO;
import model.User;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		//if valid login
		String fileName;
		User u = null;
		try {
			if(UserDAO.getInstance().validLogin(user, pass)){
				try {
					u = UserDAO.getInstance().getUser(user);
				} catch (ValidationException e) {
					System.out.println("ops");
				}
				fileName = "JSP/BrowserPageLoged.jsp";
				HttpSession session = req.getSession();
				session.setAttribute("username", user);
				session.setAttribute("user", u);
				session.setAttribute("logged", true);
			}
			else{
				fileName = "HTML/loginFailed.html";
			}
		} catch (SQLException e) {
			System.out.println("Error loging in - " + e.getMessage());
			fileName = "HTML/loginFailed.html";
		}		
		resp.sendRedirect(fileName);
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		resp.sendRedirect("HTML/index.html");
	}
}
