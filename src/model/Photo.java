package model;
  
 import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.Collections;
 import javax.xml.bind.ValidationException;
 
 public class Photo implements Comparable<Photo> {
 	public enum Genre {
 		PHOTO, DIGITAL, TRADITIONAL, CRAFTS, COMICS, FANART, SCETCH;
 	}
  	
  	private long photoID;
  	private String name;
  	private User user;
  	private double  rating;
  	private ArrayList<User> ratedPpl = new ArrayList<>();
 	private Date dateOfUploading;
  	private Genre genre;
  	private String about;
  	private ArrayList<String> tags = new ArrayList<>();
 	private ArrayList<Comment> comments = new ArrayList<>();
 	private String photoLink;
  	
  	public Photo(String name, User profile, Genre genre, String about, String photoLink) throws ValidationException {
  		this.rating = 0;
 		this.dateOfUploading = Date.valueOf(LocalDate.now());
 		this.user = profile;
  		this.changeInfo(about);
  		this.changeName(name);
  		this.changeGenre(genre);
  		this.photoLink = photoLink;
  		// photo = new File();
  	}
  
 	public void addTags(String tag) {
  		if (tag.length() == 0) {
  			return;
  		}
 		if (tag.indexOf(',') < 0) {
 			this.tags.add(tag.trim());
 			return;
 		}
 		this.tags.add(tag.substring(0, tag.indexOf(',')));
 		String a = tag.substring(tag.indexOf(',') + 1).trim();
 		addTags(a);
 	}
 	
 	public void changeInfo(String about) {
 		this.about = about;
 
 	}
 	public void changeName(String name) throws ValidationException {
 		if (name == null || name.isEmpty()) {
 			throw new ValidationException("Not valid genre");
 		} else {
 			this.name = name;
 		}
 	}
 	public void changeRaiting(int rate, User profil) {
 		if (this.ratedPpl.contains(profil)) {
 			return;
 		}
 		this.ratedPpl.add(profil);
 		this.rating = rating + this.rating*(ratedPpl.size()-1) / this.ratedPpl.size();
 	}
 	public void changeGenre(Genre genre) throws ValidationException {
 		if (genre == null && this.genre == null) {
 			throw new ValidationException("Not valid genre");
 		} else {
 			this.genre = genre;
 		}
 	}
 	public void addComment(Comment c) {
 		if (c!= null) {
 			comments.add(c);
 		}		
 	}
 	
 	public String getName() {
 		return name;
 	}
 	public Genre getGenre() {
 		return genre;
 	}
 	public Collection<String> getTags() {
 		return Collections.unmodifiableList(this.tags);
  	}
  
  	public User getProfile() {
 		return this.user;
  	}
 	public Date getDateOfUploading() {
  		return dateOfUploading;
  	}
  	public int getComments(){
 		return this.comments.size();
 	}
 	public double getRating(){
 		return this.rating;
 	}
 	public String getInfo() {
 		return about;
 	}
 	
 	public void setPhotoID(long photoID) {
 		this.photoID = photoID;
 	}
 
  	public String getPhotoLink() {
  		return photoLink;
  	}
 	
 
 	public long getPhotoID() {
 		return photoID;
 	}
  
  	@Override
  	public int compareTo(Photo p) {
 		int a = this.genre.compareTo(p.genre);
 		if (a == 0) {
 			for (String s : p.tags) {
 				for (String s1 : this.tags) {
 					a = s.compareTo(s1);
 					if (a == 0) {
 						a = this.name.compareTo(p.name);
 						if (a == 0) {
 							return this.dateOfUploading.compareTo(p.dateOfUploading);
 						}
 					}
 					else{
 						return a;
 					}
 				}
 			}
 		}
 		return a;
 	}
 
 	@Override
 	public String toString() {
  		return "Photo [name=" + name + ", rating=" + rating + ", comments="
  				+ comments.size() + "]";
  	}
  }