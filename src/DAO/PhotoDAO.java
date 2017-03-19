package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.bind.ValidationException;

import com.mysql.jdbc.Connection;

import model.Photo;
import model.Photo.Genre;
import model.User;

public class PhotoDAO {
	private String sql = "UPDATE photos SET ? ? WHERE user_id = ? ";
	
	
	//TODO upload photo
	public synchronized void uploadPhoto(Photo p, User u) throws SQLException{
		String sql = "INSERT INTO photos (name, upload_date, genre, user_id, photo_link) " +
					"VALUES (?, ?, ?, ?, ?)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, p.getName());
		st.setDate(2, p.getDateOfUploading());
		st.setString(3, p.getGenre().toString());
		st.setLong(4, u.getUserID());
		st.setString(5, p.getPhotoLink());
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		long id = res.getLong(1);
		p.setPhotoID(id);
		u.addPhoto(p);
	}
	
	
	//TODO edit photo
	public synchronized void editPhotoName(Photo p, User u, String s) throws SQLException{
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		try {
			p.changeName(s);
			st.setString(1, "name");
			st.setString(2, s);
			st.setLong(3, u.getUserID());
			st.execute();
		} catch (ValidationException e) {
			System.out.println("cant change photo name");
		}
	}
	
	public synchronized void editPhotoName(Photo p, User u, int i) throws SQLException{
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		p.changeRaiting(i, u);
		st.setString(1, "raiting");
		st.setInt(2, i);
		st.setLong(3, u.getUserID());
		st.execute();
	}
	
	public synchronized void editPhotoInfo(Photo p, User u, String a) throws SQLException{
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		p.changeInfo(a);
		st.setString(1, "about");
		st.setString(2, a);
		st.setLong(3, u.getUserID());
		st.execute();
	}
	
	public synchronized void editPhototags(Photo p, User u, String tags){
		PreparedStatement st = null;
		try {
			DBManager.getInstance().getConnection().setAutoCommit(false);
	
			HashMap<Long, String> tagMap = new HashMap<>();
			p.addTags(tags);
			for(String s: p.getTags()){
				String sql1 = "INSERT INTO tags (type) VALUES (?)";
				st = DBManager.getInstance().getConnection().prepareStatement(sql1);
				st.setString(1, s);
				tagMap.put(st.getGeneratedKeys().getLong(1), s);
			}
			String sql2 = "DELETE FROM tas_photos WHERE  ? ";
			st = DBManager.getInstance().getConnection().prepareStatement(sql2);
			st.setLong(1, p.getPhotoID());
			
			for(Entry <Long, String> e : tagMap.entrySet()){
				String sql3 = "UPDATE tags_photo SET tag_id = ? , photo_id = ? WHERE tag_id = (SELECT tag_id FROM tags where type = ?";
				st = DBManager.getInstance().getConnection().prepareStatement(sql3);
				st.setLong(1, e.getKey());
				st.setLong(2, p.getPhotoID());
				st.setString(3, e.getValue());
				st.execute();
			}		
		} catch (SQLException e1) {
			try {
				DBManager.getInstance().getConnection().rollback();
				System.out.println("cant change tags");
			} catch (SQLException e) {
				System.out.println("cant roll back");
			}finally{
				try {
					DBManager.getInstance().getConnection().setAutoCommit(true);
				} catch (SQLException e) {
					System.out.println("this wont show up... but hey it's an " + e.getMessage());
				}
			}
		}		
	}
	
	//TODO delete photo
	//TODO get one img

}
