package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Static;

@WebServlet("/uploadImage")
public class UploadImgServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("<h1>Hello, world!</h1>");
		File f = new File("D:/DB_IMG/1/" + Static.count++ + ".jpg");
		FileInputStream is = null;
		FileOutputStream os = null;
		try {
			f.createNewFile();
		} catch (IOException e) {
			System.out.println("creating file error");
		}
		try {
			is = new FileInputStream(req.getParameter("pic"));
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
		int number = 0;
		while(number != -1){
			try {
				number = is.read();
				os = new FileOutputStream(f);
				os.write(number);
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
			}
			catch (IOException e1) {
				System.out.println("io exeption" + e1.getMessage());
			}
		}
		is.close();
		os.close();
	}
}