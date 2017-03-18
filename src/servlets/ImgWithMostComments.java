package servlets;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.GalleryDAO;


@WebServlet("/ImgWithMostComments")
public class ImgWithMostComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LinkedHashMap<Integer, String> list = new LinkedHashMap<>();
		GalleryDAO.getImgMostComments(list);
		for(String s : list.values()){
			resp.getWriter().write("<img src=\""+ s +"\">");
		}
		//    req.setAttribute("list", list);
		//    req.getRequestDispatcher("/JSP/BrowserPage.jsp").forward(req, resp);
	}
}
