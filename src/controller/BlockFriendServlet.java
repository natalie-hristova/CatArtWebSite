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
 * Servlet implementation class BlockedUsers
 */
@WebServlet("/blockFriend")
public class BlockFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username =(String)session.getAttribute("user");
		String frUsername = request.getParameter("blocked");
		System.out.println(username + " added " + request.getParameter("blocked"));
		User user = UserDAO.getInstance().getUser(username);
		User fr =  UserDAO.getInstance().getUser(frUsername);
		UserDAO.getInstance().removeFromFriends(user,fr);
		UserDAO.getInstance().blockUser(user,fr);
		response.sendRedirect("JSP/blockedUsers.jsp");
	}

}
