package controller;

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
import model.Photo.Genre;
import model.User;
import model.User.Gender;
import model.User.Rights;


@WebServlet("/ImgMyFavoritesServlet")
public class ImgMyFavoritesServlet extends HttpServlet {
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
			GalleryDAO.getImgAllMyFav(u, list);
		} catch (ValidationException | SQLException e) {
			System.out.println("ops error in imgbytag");
		}
		for(Photo p : list.values()){
			resp.getWriter().write("<img src=\""+ p.getPhotoLink() +"\">");
		}
		//    req.setAttribute("list", list);
		//    req.getRequestDispatcher("/JSP/BrowserPage.jsp").forward(req, resp);
	}
}
