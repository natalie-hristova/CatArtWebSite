package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import model.User;

/**
 * Servlet implementation class RemoveFriendServlet
 */
@WebServlet("/removeFriend")
public class RemoveFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username =(String)session.getAttribute("user");
		String frUsername = request.getParameter("remove");
		System.out.println("+++++++++++++++++++++" + frUsername);
		System.out.println(username + " removed " + request.getParameter("remove"));
		User user = UserDAO.getInstance().getUser(username);
		User fr =  UserDAO.getInstance().getUser(frUsername);
		UserDAO.getInstance().removeFromFriends(user,fr);
		System.out.println(username + " removed " + request.getParameter("remove"));
		response.sendRedirect("JSP/friends.jsp");
	}

}
