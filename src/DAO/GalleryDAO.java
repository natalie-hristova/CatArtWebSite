package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

import model.Photo.Genre;

public class GalleryDAO {
	
	public static HashMap<Integer, String> getAllImgesAtRandom(HashMap<Integer, String> allphotos) throws SQLException{

		Statement st = DBManager.getInstance().getConnection().createStatement();
		//random photos to start + initialization
		ResultSet rez = null;
		rez = st.executeQuery("Select count(photo_id) as num from photos");
		rez.next();
		int size = (int) rez.getLong(1);
		Random rn = new Random();
		int img = rn.nextInt(size) + 30; 
		int  img2 = rn.nextInt(size);
		ResultSet result = null;
		ResultSet result1 = null;
		//make 2 sets of results
		try {
			result = st.executeQuery(" SELECT photo_link, photo_id FROM photos WHERE photo_id > "+ img +" LIMIT 20;");
			result1 = st.executeQuery("SELECT photo_link, photo_id FROM photos WHERE photo_id > "+ img2 +" LIMIT 20;");
		} catch (SQLException e) {
			System.out.println("ops1 " + e.getMessage());
		}
		//shuffle them
		allphotos = GalleryDAO.shuffle(result, result1, allphotos);
		st.close();
		return allphotos;
	}	
	public static void getAllImgesGenre(HashMap<Integer, String> allphotos, Genre genre){
		String s = " SELECT photo_id, photo_link FROM photos WHERE genre = '"+ genre +"'";
		getImgs(s, allphotos);
	}
	public static void getImgAlphabeticByUser(LinkedHashMap<Integer, String> allphotos) {
		String s = "SELECT p.photo_id, p.photo_link, u.nickname FROM photos p JOIN users u ON u.user_id = p.user_id ORDER BY u.nickname";
		getImgs(s, allphotos);
	}
	public static void getImgWithNoComments(HashMap<Integer, String> allphotos) {
		String s = "SELECT p.photo_id, p.photo_link FROM photos p LEFT JOIN comments c "
				+ "ON p.user_id = c.user_id GROUP BY p.photo_id, p.photo_link HAVING COUNT(c.photo_id) "
				+ "< 1 ORDER BY COUNT(c.photo_id)";
		getImgs(s, allphotos);
	}
	public static void getImgMostComments(LinkedHashMap<Integer, String> allphotos) {
		String s = "SELECT p.photo_id, p.photo_link FROM photos p JOIN comments c " +
			 "ON p.user_id = c.user_id GROUP BY p.photo_id, p.photo_link ORDER BY count(c.photo_id) DESC";
		getImgs(s, allphotos);
		}
	public static void getImgMostRaiting(LinkedHashMap<Integer, String> allphotos) {
		String s = "SELECT p.photo_id, p.photo_link, raiting FROM photos p WHERE raiting is not null ORDER BY raiting DESC;";
		getImgs(s, allphotos);
	}
	public static void getImgLastUploaded(LinkedHashMap<Integer, String> allphotos) {
		String s = "SELECT p.photo_id, p.photo_link FROM photos p ORDER BY upload_date DESC LIMIT 40";
		getImgs(s, allphotos);
	}
	public static void getImgByTag(HashMap<Integer, String> allphotos, String tag) {
		String s = "SELECT p.photo_id, p.photo_link FROM photos p JOIN tag_photo tp "
				+ "ON p.photo_id = tp.photo_id  JOIN tags t ON tp.tag_id = t.tag_id WHERE t.type = \""+ tag +"\";";
		getImgs(s, allphotos);
	}
	private static LinkedHashMap<Integer, String> getImgs(String s, LinkedHashMap<Integer, String> allphotos) {
		//initialization
		Statement st = null;
		try {
			st = DBManager.getInstance().getConnection().createStatement();
		} catch (SQLException e2) {
			System.out.println("Error in getAllImgesbyGenre" + e2.getMessage());
		}
		ResultSet result = null;
		//get result
		try {
			result = st.executeQuery(s);
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
	private static HashMap<Integer, String> getImgs(String s, HashMap<Integer, String> allphotos) {
		//initialization
		Statement st = null;
		try {
			st = DBManager.getInstance().getConnection().createStatement();
		} catch (SQLException e2) {
			System.out.println("Error in getAllImgesbyGenre" + e2.getMessage());
		}
		ResultSet result = null;
		//get result
		try {
			result = st.executeQuery(s);
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
	
	//no servlet 
	public static void getOneImg(int imgID){
		HashMap<Integer, String> photo = new HashMap<>();
		String s = "SELECT p.photo_id, p.photo_link FROM photos p WHERE photo_id = " + imgID + ";";
		getImgs(s, photo);
	}
	public static void getImgFromOneUser(int userID){
		HashMap<Integer, String> photo = new HashMap<>();
		String s = "SELECT p.photo_id, p.photo_link FROM photos p WHERE user_id = " + userID + ";";
		getImgs(s, photo);
	}
	//TODO photos of my friends 
}
