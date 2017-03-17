package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.GalleryDAO;

@WebServlet("/ShowImg")
public class ShowImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HashMap<Integer, String> list = new HashMap<>();
		try {
			GalleryDAO.getAllImgesAtRandom(list);
		} catch (SQLException e) {
			System.out.println("error in AllImagesAtRandom");
		}
	    req.setAttribute("list", list);
	    req.getRequestDispatcher("/JSP/BrowserPage.jsp").forward(req, resp);
	}
}