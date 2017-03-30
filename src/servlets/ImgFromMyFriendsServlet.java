package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import DAO.GalleryDAO;
import model.Photo;
import model.User;
import model.User.Gender;
import model.User.Rights;


@WebServlet("/ImgFromMyFriendsServlet")
public class ImgFromMyFriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HashMap<Long, Photo> list = new HashMap<>();
		//String tag = req.getAttribute("tag");
		User u = null;
		try {
			u = new User("abds", "assda", "absd@abv.bg", Gender.M, Rights.MEMBER);
		} catch (ValidationException e1) {
			System.out.println("whaaaat new user");
		}
		try {
			GalleryDAO.getImgFromMyFriends(u, list);
		} catch (ValidationException | SQLException e) {
			System.out.println("ops error in imgbytag");
		}
	    req.setAttribute("list", list);
	    req.setAttribute("size", list.size());
	    req.setAttribute("type", "HashMap");
		HttpSession ses = req.getSession();
		if(ses.getAttribute("logged")!= null){
			boolean logged = (Boolean) req.getSession().getAttribute("logged");
			if(logged){
				req.getRequestDispatcher("JSP/BrowserPageLogedRedirect.jsp").forward(req, resp);
			}
			else{
				req.getRequestDispatcher("JSP/BrowserPageRedirect.jsp").forward(req, resp);
			}
		}
		else{
			req.getRequestDispatcher("JSP/BrowserPageRedirect.jsp").forward(req, resp);
		}
	} 
}
