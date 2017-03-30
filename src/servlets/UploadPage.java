package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/uploadPage")
public class UploadPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession();
		if(ses.getAttribute("logged")!= null){
			boolean logged = (Boolean) request.getSession().getAttribute("logged");
			if(logged){
				response.sendRedirect("JSP/UploadImg.jsp");
			}
			else{
				response.sendRedirect("JSP/FrontPage.jsp");
			}
		}
		else{
			response.sendRedirect("JSP/FrontPage.jsp");
		}
	}

}
