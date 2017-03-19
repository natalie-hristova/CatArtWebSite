package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import DAO.GalleryDAO;
import model.Photo;

@WebServlet("/ImgAlphabeticByUser")
public class SImgAlphabeticServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
         
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LinkedHashMap<Long, Photo> list = new LinkedHashMap<>();
		try {
			GalleryDAO.getImgAlphabetic(list);
		} catch (ValidationException | SQLException e) {
			System.out.println("ops error in alphabeticbyuser" + e.getMessage());
		}
		for(Photo p : list.values()){
			resp.getWriter().write("<img src=\""+ p.getPhotoLink() +"\">");
		}
	//    req.setAttribute("list", list);
	//    req.getRequestDispatcher("/JSP/BrowserPage.jsp").forward(req, resp);
	}
}

