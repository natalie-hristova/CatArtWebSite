package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Random;

import model.Photo.Genre;

public class GalleryDAO {

	HashMap<Integer, String> allphotos = new HashMap<>();

	
	public static HashMap<Integer, String> getAllImgesAtRandom(HashMap<Integer, String> allphotos) throws SQLException{

		Statement st = Demo.getInstance().getConnection().createStatement();
		//random photos to start + initialization
		Random rn = new Random();
		int img = rn.nextInt(50) + 30; 
		int img2 = rn.nextInt(40);
		ResultSet result = null;
		ResultSet result1 = null;
		//make 2 sets of results
		try {
			result = st.executeQuery(" Select photo_link, photo_id from photos where photo_id > "+ img +" limit 20;");
			result1 = st.executeQuery("Select photo_link, photo_id from photos where photo_id > "+ img2 +" limit 20;");
		} catch (SQLException e) {
			System.out.println("ops1 " + e.getMessage());
		}
		//shuffle them
		allphotos = GalleryDAO.shuffle(result, result1, allphotos);
		st.close();
		return allphotos;
	}
	
	private static HashMap<Integer, String> shuffle(ResultSet a, ResultSet b, HashMap<Integer, String> allphotos){
		//shuffle
		for(int i = 0; i < 40; i++){
			try {
				while(a.next()){
					allphotos.put(a.getInt("photo_id"), a.getString("photo_link"));
					break;
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				while(b.next()){
					allphotos.put(b.getInt("photo_id"), b.getString("photo_link"));
					break;
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return allphotos;
	}
	
	static HashMap<Integer, String> getAllImgesGenre(HashMap<Integer, String> allphotos, Genre genre){
		//initialization
		Statement st = null;
		try {
			st = Demo.getInstance().getConnection().createStatement();
		} catch (SQLException e2) {
			System.out.println("Error in getAllImgesbyGenre");
		}
		ResultSet result = null;
		//get result
		try {
			result = st.executeQuery(" select photo_id, photo_link from photos where genre = '"+ genre +"'; limit 40;");
		} catch (SQLException e) {
			System.out.println("ops1 " + e.getMessage());
		}
		//put result in hashmap
		 try {
			 while(result.next()){
				 allphotos.put(result.getInt("photo_id"), result.getString("photo_link"));
			 }
		} catch (SQLException e1) {
			System.out.println("this column dosent exist!");
		}
		try {
			st.close();
		} catch (SQLException e) {
			System.out.println("close what?" + e.getMessage());
		}
		return allphotos;
	}
	
}
