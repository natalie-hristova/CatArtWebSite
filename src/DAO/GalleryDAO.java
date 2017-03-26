package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Random;

import javax.xml.bind.ValidationException;

import model.Photo;
import model.User;
import model.Photo.Genre;

public class GalleryDAO {
	
	public static HashMap<Long, Photo> getAllImgesAtRandom(HashMap<Long, Photo> allphotos) throws SQLException, ValidationException{

		Statement st = DBManager.getInstance().getConnection().createStatement();
		//random photos to start + initialization
		ResultSet rez = null;
		rez = st.executeQuery("Select count(photo_id) as num from photos");
		rez.next();
		int size = (int) rez.getLong(1);
		Random rn = new Random();
		int img = rn.nextInt(size/2) + 30; 
		int  img2 = rn.nextInt(size/2);
		System.out.println(img);
		System.out.println(img2);
		HashMap<Long, Photo> a = new HashMap<>();
		HashMap<Long, Photo> b = new HashMap<>();
		//make 2 sets of results
		String queryStart = "SELECT photo_id, name, user_id, genre, about, photo_link, raiting, upload_date FROM photos WHERE photo_id > ";
		String queryEnd = " LIMIT 20";
		try {
			ResultSet result = st.executeQuery(queryStart + img + queryEnd);
			makeaMap(result, a);
			ResultSet result1 = st.executeQuery(queryStart + img2 + queryEnd);
			makeaMap(result1, b);
		} catch (SQLException e) {
			System.out.println("ops1 " + e.getMessage());
		}
		//shuffle them
		allphotos = GalleryDAO.shuffle(a, b, allphotos);
		return allphotos;
	}	
	//works
	public static void getAllImgesGenre(HashMap<Long, Photo> allphotos, Genre genre) throws ValidationException, SQLException{
		String s = " SELECT photo_id, name, user_id, genre, about, photo_link, raiting, upload_date FROM photos WHERE genre = '"+ genre +"'";
		getImgs(s, allphotos);
	}
	//working
	public static void getImgAlphabetic(LinkedHashMap<Long, Photo> allphotos) throws ValidationException, SQLException {
		String s = "SELECT photo_id, name, user_id, genre, about, photo_link, raiting, upload_date FROM photos ORDER BY name";
		getImgs(s, allphotos);
	}
	//works
	public static void getImgWithNoComments(HashMap<Long, Photo> allphotos) throws ValidationException, SQLException {
		String s = "SELECT p.photo_id, p.name, p.user_id, p.genre, p.about, p.photo_link, p.raiting, p.upload_date FROM photos p LEFT JOIN comments c "
				+ "ON p.user_id = c.user_id GROUP BY p.photo_id, p.photo_link HAVING COUNT(c.photo_id) "
				+ "< 1 ORDER BY COUNT(c.photo_id)";
		getImgs(s, allphotos);
	}
	//works
	public static void getImgMostComments(LinkedHashMap<Long, Photo> allphotos) throws ValidationException, SQLException {
		String s = "SELECT p.photo_id, p.name, p.user_id, p.genre, p.about, p.photo_link, p.raiting, p.upload_date FROM photos p JOIN comments c " +
			 "ON p.user_id = c.user_id GROUP BY p.photo_id, p.photo_link ORDER BY count(c.photo_id) DESC";
		getImgs(s, allphotos);
		}
	//works
	public static void getImgMostRaiting(LinkedHashMap<Long, Photo> allphotos) throws ValidationException, SQLException {
		String s = "SELECT photo_id, name, user_id, genre, about, photo_link, raiting, upload_date FROM photos p WHERE raiting is not null ORDER BY raiting DESC;";
		getImgs(s, allphotos);
	}
	//working
	public static void getImgLastUploaded(LinkedHashMap<Long, Photo> allphotos) throws ValidationException, SQLException {
		String s = "SELECT photo_id, name, user_id, genre, about, photo_link, raiting, upload_date FROM photos p ORDER BY upload_date DESC LIMIT 40";
		getImgs(s, allphotos);
	}
	//working
	public static void getImgByTag(HashMap<Long, Photo> allphotos, String tag) throws ValidationException, SQLException {
		String s = "SELECT p.photo_id, p.name, p.user_id, p.genre, p.about, p.photo_link, p.raiting, p.upload_date FROM photos p JOIN tag_photo tp "
				+ "ON p.photo_id = tp.photo_id  JOIN tags t ON tp.tag_id = t.tag_id WHERE t.type = \""+ tag +"\";";
		getImgs(s, allphotos);
	}
	//works
	public static void getMoreImgFromThisUser(User user, HashMap<Long, Photo> photos) throws ValidationException, SQLException{
		String s = "SELECT p.photo_id, p.name, p.user_id, p.genre, p.about, p.photo_link, p.raiting, p.upload_date  FROM photos p WHERE user_id = " + user.getUserID() + ";";
		getImgs(s, photos);
	}
	//works this will do for allMyimg too
	public static void getImgFromMyFriends(User user, HashMap<Long, Photo> photos) throws ValidationException, SQLException{
		String s = ("SELECT p.photo_id, p.name, p.user_id, p.genre, p.about, p.photo_link, p.raiting, p.upload_date " +  
					"FROM photos p JOIN users u ON u.user_id = p.user_id JOIN friends f " + 
					"ON u.user_id = f.user_id WHERE f.friend_id = " + user.getUserID());
		getImgs(s, photos);
	}
	//works

	private static LinkedHashMap<Long, Photo> getImgs(String s, LinkedHashMap<Long, Photo> allphotos) throws ValidationException {
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
		try {
			while(result.next()){
				Long l = result.getLong("photo_id");
				Photo p = null;
				try {
					User u = UserDAO.getUser(result.getInt("user_id"));
					p = new Photo(result.getString("name"), u, Genre.valueOf(result.getString("genre")), result.getString("about"), result.getString("photo_link"));
					p.setPhotoID(result.getLong("photo_id"));
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				allphotos.put(l, p);
			}
		} catch (SQLException e1) {
			System.out.println("error");
		}	
		return allphotos;
	}
	private static HashMap<Long, Photo> getImgs(String s, HashMap<Long, Photo> allphotos) throws ValidationException, SQLException {
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
		try {
			while(result.next()){
				Long l = result.getLong("photo_id");
				Photo p = null;
				try {
					User u = UserDAO.getUser(result.getInt("user_id"));
					p = new Photo(result.getString("name"), u, Genre.valueOf(result.getString("genre")), result.getString("about"), result.getString("photo_link"));
					p.setPhotoID(result.getLong("photo_id"));
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				allphotos.put(l, p);
			}
		} catch (SQLException e1) {
			System.out.println("error");
		}	
		return allphotos;
	}
	private static HashMap<Long, Photo> shuffle(HashMap<Long, Photo> a, HashMap<Long, Photo> b, HashMap<Long, Photo> allphotos) throws ValidationException{
		//shuffle
		for(Entry<Long, Photo> e : a.entrySet()){
			allphotos.put(e.getKey(), e.getValue());
			for(Entry<Long, Photo> e1 : b.entrySet()){
				allphotos.put(e1.getKey(), e1.getValue());
				break;
			}
		}
		return allphotos;
	}
	private static HashMap<Long, Photo> makeaMap(ResultSet a, HashMap<Long, Photo> map) throws ValidationException{
			try {
				while(a.next()){
					User u = UserDAO.getUser(a.getInt("user_id"));
					Photo p = new Photo(a.getString("name"), u, Genre.valueOf(a.getString("genre")), a.getString("about"), a.getString("photo_link"));
					p.setPhotoID(a.getLong("photo_id"));
					map.put(a.getLong("photo_id"), p);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return map;
	}
	
	
	public static void getImgAllMyFav(User user, HashMap<Long, Photo> photos) throws ValidationException, SQLException{
		String s = ("SELECT p.photo_id, p.name, p.user_id as creator, p.genre, p.about, p.photo_link, p.raiting, p.upload_date "
					+ "FROM photos p JOIN gallery_photo gp ON p.photo_id = gp.photo_id " + 
									"JOIN galleries g ON g.gallery_id = gp.galery_id " + 
									"JOIN users u ON g.user_id = u.user_id " + 
									"WHERE g.name = 'favorites' AND u.user_id = " +  user.getUserID());
		getImgs(s, photos);
	}
	//TODO still not working need userDAO to work
}
