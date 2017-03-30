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
import model.Photo.Genre;


@WebServlet("/ShowImgGenre")
public class ImgGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String s = (String) req.getParameter("genre");
		System.out.println(s);
		HashMap<Long, Photo> list = new HashMap<>();
		Genre g = this.rightGenre(s);
		try {
			GalleryDAO.getAllImgesGenre(list, g);
		} catch (ValidationException | SQLException e) {
			System.out.println("ops error in imgbyGenre");
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


	private Genre rightGenre(String s) {
		if(s.equals("PHOTO")){
			return Genre.PHOTO;
		}
		if(s.equals("DIGITAL")){
			return Genre.DIGITAL;
		}
		if(s.equals("TRADITIONAL")){
			return Genre.TRADITIONAL;
		}
		if(s.equals("CRAFTS")){
			return Genre.CRAFTS;
		}
		if(s.equals("COMICS")){
			return Genre.COMICS;
		}
		if(s.equals("FANART")){
			return Genre.FANART;
		}
		return Genre.SCETCH;
	} 
}
