package servlets;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Static;
import model.Photo.Genre;


@WebServlet("/UploadImgServlet")
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
    		System.out.println(saveFile);
    		ff.createNewFile();
    		System.out.println(ff.exists());
    		FileOutputStream fileOut = new FileOutputStream(ff);
    		fileOut.write(dataBytes, startPos, (endPos - startPos));
    		fileOut.flush();	
    		try {
				DAO.PhotoDAO.uploadPhoto(Static.fileNum++, (String)request.getAttribute("name"), (Genre)request.getAttribute("genre"), (long)request.getSession().getAttribute("user_id"), (String)request.getAttribute("about"));
			} catch (SQLException e) {
				System.out.println("ops cant insert to DB" + e.getMessage());
			}
    		System.out.println("Uploaded in DB");
    		fileOut.close(); 	
		}
	}
}
