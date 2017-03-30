package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import DAO.GalleryDAO;
import model.Photo;

@WebServlet("/ImgWithMostRaiting")
public class ImgWithMostRaiting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LinkedHashMap<Long, Photo> list = new LinkedHashMap<>();
		try {
			GalleryDAO.getImgMostRaiting(list);
		} catch (ValidationException | SQLException e) {
			System.out.println("ops error in imgwithmostraiting");
		}
	    req.setAttribute("list", list);
	    req.setAttribute("size", list.size());
	    req.setAttribute("type", "LinkedHashMap");
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
