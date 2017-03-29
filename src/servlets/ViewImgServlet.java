package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import DAO.PhotoDAO;
import model.Photo;

@WebServlet("/ViewImg")
public class ViewImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String i = request.getParameter("imgNum");
		System.out.println(i +"!");
		Photo p = null;
		try {
			p = PhotoDAO.createPhoto(i);
		} catch (ValidationException e) {
			System.out.println("ops cat get this img");
		}
		request.setAttribute("name", p.getName());
		request.setAttribute("date", p.getDateOfUploading().toString());
		request.setAttribute("genre", p.getGenre().toString());
		request.setAttribute("info", p.getInfo());
		request.setAttribute("link", p.getPhotoLink());
		request.setAttribute("username", p.getProfile().getName());
		int tagnum = 1;
		String tags = "";
		for(String tag: p.getTags()){
			tags += (String)request.getAttribute("tag"+i); 
			if(tag.length()<tagnum){
				tags += ",";
			}
		}
		request.setAttribute("tags", tags);
		HttpSession ses = request.getSession();
		if(ses.getAttribute("logged")!= null){
			boolean logged = (Boolean) request.getSession().getAttribute("logged");
			if(logged){
				request.getRequestDispatcher("JSP/ImgPageLoged.jsp").forward(request, response);
			}
			else{
				response.sendRedirect("HTML/index.html");
			}
		}
		else{
			response.sendRedirect("HTML/index.html");
		}
	} 
}

