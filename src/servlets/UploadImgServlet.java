package servlets;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Static;
import model.User;


@WebServlet("/UploadImgServlet")
@MultipartConfig
public class UploadImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String saveFile = "";
			String contentType = request.getContentType();
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
   			DataInputStream in = new DataInputStream(request.getInputStream());
    		int formDataLength = request.getContentLength();
   			byte dataBytes[] = new byte[formDataLength];
   			int byteRead = 0;
    		int totalBytesRead = 0;
    		while (totalBytesRead < formDataLength) {
          		byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
          		totalBytesRead += byteRead;
    		}
    		String file = new String(dataBytes);
    		saveFile = file.substring(file.indexOf("filename=\"") + 10);
    		saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
    		saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1, saveFile.indexOf("\""));
    		int lastIndex = contentType.lastIndexOf("=");
    		String boundary = contentType.substring(lastIndex + 1, contentType.length());
    		int pos;
    		pos = file.indexOf("filename=\"");
    		pos = file.indexOf("\n", pos) + 1;
    		pos = file.indexOf("\n", pos) + 1;
    		pos = file.indexOf("\n", pos) + 1;
    		int boundaryLocation = file.indexOf(boundary, pos) - 4;
    		int startPos = ((file.substring(0, pos)).getBytes()).length;
    		int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
            saveFile = "D:/eclips/CatArt/Cat/WebContent/DB_IMG/" + (Static.fileNum) + ".jpg";
    		File ff = new File(saveFile);
    		ff.createNewFile();
    		FileOutputStream fileOut = new FileOutputStream(ff);
    		fileOut.write(dataBytes, startPos, (endPos - startPos));
    		fileOut.flush();
    		fileOut.close(); 
    		String name = (String)request.getSession().getAttribute("imgName");
    		String about = (String)request.getSession().getAttribute("imgAbout");
    		String genre = (String)request.getSession().getAttribute("imgGenre");
    		User u = (User)request.getSession().getAttribute("user");
    		System.out.println(name + " " + about + " " + genre + " " +  u.getUserID());	
    		try {
				DAO.PhotoDAO.uploadPhoto(Static.fileNum++, name, genre, u.getUserID() , about);
			} catch (SQLException e) {
				System.out.println("ops cant upload file");
			}
    		response.sendRedirect("HTML/ImgUploadedSuccessfully.html");
		}	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String)request.getParameter("name");
		String about = (String)request.getParameter("about");
		String genre = (String)request.getParameter("genre");
		HttpSession session = request.getSession();
		session.setAttribute("imgName", name);
		session.setAttribute("imgAbout", about);
		session.setAttribute("imgGenre", genre);
		System.out.println(name + " " + about + " " + genre);
		request.getRequestDispatcher("JSP/UploadImgTwo.jsp").forward(request, response);
	}
}
