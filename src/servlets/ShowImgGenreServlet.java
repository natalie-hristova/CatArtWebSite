package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.GalleryDAO;
import model.Photo.Genre;


@WebServlet("/ShowImgGenre")
public class ShowImgGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HashMap<Integer, String> list = new HashMap<>();
		Genre g = Genre.DIGITAL;
		GalleryDAO.getAllImgesGenre(list, g);
		for(String s : list.values()){
			resp.getWriter().write("<img src=\""+ s +"\">");
		}
	//    req.setAttribute("list", list);
	//    req.getRequestDispatcher("/JSP/BrowserPage.jsp").forward(req, resp);
	}
}
