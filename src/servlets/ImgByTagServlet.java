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

@WebServlet("/ImgsByTagServlet")
public class ImgByTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HashMap<Long, Photo> list = new HashMap<>();
		//String tag = req.getAttribute("tag");
		String tag = "druid";
		try {
			GalleryDAO.getImgByTag(list, tag);
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
