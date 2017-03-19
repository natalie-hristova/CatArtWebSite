package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.xml.bind.ValidationException;

import model.Photo;
import model.Photo.Genre;
import model.User;

public class PhotoDAO {
	
	//TODO upload photo
	//TODO edit photo
	//TODO delete photo
	
	public static void getOnePhoto(long imgID){
		HashMap<Integer, String> photo = new HashMap<>();
		String s = "SELECT p.photo_id, p.photo_link FROM photos p WHERE photo_id = " + imgID + ";";
		//getImgs(s, photo);
	}

}
