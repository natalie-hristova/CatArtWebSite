package catArt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCConnection {
	
	private void makeConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error in connection");
			e.printStackTrace();
		} 
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDatabase", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
