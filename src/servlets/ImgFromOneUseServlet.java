package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import DAO.GalleryDAO;
import model.Photo;
import model.User;
import model.User.Gender;
import model.User.Rights;

/**
 * Servlet implementation class ImgFromOneUseServlet
 */
@WebServlet("/ImgFromOneUseServlet")
public class ImgFromOneUseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HashMap<Long, Photo> list = new HashMap<>();
		User u = null;
		try {
			u = new User("abds", "assda", "absd@abv.bg", Gender.M, Rights.MEMBER);
		} catch (ValidationException e1) {
			System.out.println("whaaaat new user");
		}
		try {
			GalleryDAO.getMoreImgFromThisUser(u, list);
		} catch (SQLException e) {
			System.out.println("error in imgfromoneuser" + e.getMessage());
		} catch (ValidationException e) {
			System.out.println("ops can recreat imgs");
		}
		for(Photo p : list.values()){
			resp.getWriter().write("<img src=\""+ p.getPhotoLink() +"\">");
		}
	//    long userID = req.getAttribute("user_id");
	//    req.setAttribute("list", list);
	//    req.getRequestDispatcher("/JSP/BrowserPage.jsp").forward(req, resp);
	}
}
