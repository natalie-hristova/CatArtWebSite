package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import DAO.Static;
import model.Photo;

@WebServlet("/uploadImage")
public class UploadImgServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		boolean isMultipath = ServletFileUpload.isMultipartContent(req);
		if(isMultipath){
			ServletFileUpload fileupload = new ServletFileUpload();
			try {
				FileItemIterator fii = fileupload.getItemIterator(req);
				while (fii.hasNext()){
					FileItemStream item = fii.next();
					if(item.isFormField()){
						//do field processing
						String fieldName = item.getFieldName();
						InputStream is = item.openStream();
						byte[] b = new byte[is.available()];
						is.read(b);
						String value = new String(b);
						resp.getWriter().println(fieldName + ": " + value + "</br>");
					}
					//do file upload processing
					String path = getServletContext().getRealPath("/");
					//call method to upload ile
					if(Photo.processFile(path, item)){
						resp.getWriter().write("Upload Sucsecfull");
					}else{
						resp.getWriter().write("Umage not uploaded");
					}
				}
			} catch (FileUploadException e) {
				System.out.println("file upload fail");
			}
		}
		//else do nothing
	}
}