import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Profile implements Comparable<Profile> {
	public enum Rights {
		ADMIN, MEMBER, MODERATOR;
	}

	public enum Gender {
		M, F
	}

	private String userName;
	private String password;
	private String email;
	private LocalDateTime joiningDate;
	private String name;
	private Calendar dateOfBirth;
	private Gender gender;
	private String signature;
	private File avatar;
	private boolean isLogged;
	private Rights rights;
	private HashSet<Photo> favourites;
	private HashMap<String, Profile> friends;
	private ArrayList<Comment> comments;
	private HashMap<Photo.Genre, HashMap<String, Photo>> myGallery;
	private HashSet<Profile> blockedUsers;
	
	
	Profile(String userName, String password,String email,Gender gender){
		this.rights = Rights.MEMBER;
		this.gender= gender;
		this.email= email;
		this.isLogged= true;
		this.joiningDate = LocalDateTime.now();
		this.userName = userName;
		this.password = password;
		this.friends = new HashMap<>();
		this.comments = new ArrayList<>();
		this.myGallery = new HashMap<>();
		this.blockedUsers = new HashSet<>();
	}

//	Profile(String userName, String password, String email, Rights rights, int year, int month, int day,
//			String signature, Gender gender) {
//		this.rights = Rights.MEMBER;
//		this.joiningDate = LocalDateTime.now();
//		this.userName = userName;
//		this.password = password;
//		this.changeDateOfBirth(year, month, day);
//		this.changeSignature(signature);
//
//		this.friends = new HashMap<>();
//		this.comments = new ArrayList<>();
//		this.myGallery = new HashMap<>();
//		this.blockedUsers = new HashSet<>();
//	}
	
	public Profile name(String name){
		this.name= name;
		return this;
	}
	
	public Profile signature(String s){
		this.signature= s;
		return this;
	}

	public Profile dateOfBirth(Calendar dateOfBirth){
		this.dateOfBirth=dateOfBirth;
		return this;
	}
	public void logIn(String userName, String password) {
		if (userName.equals(this.userName) && password.equals(this.password)) {
			isLogged = true;
		} else {
			System.out.println("Invalid user name or password");
		}
	}

	public void LogOut() {
		this.isLogged = false;
	}

	public void AddFriend(Profile p) {
		if (isLogged && !friends.containsValue(p)) {
			friends.put(p.getUserName(), p);
		}

	}

	public void RemoveFriend(Profile p) {
		if (isLogged && friends.containsValue(p)) {
			friends.remove(p.getUserName(), p);
		}
	}

	public void blockProfile(Profile p) {
		if (isLogged && !blockedUsers.contains(p)) {
			blockedUsers.add(p);
		}

	}

	public void changePassword(String oldPassword, String newPassword) {
		if (oldPassword.equals(this.password)) {
			this.password = newPassword;
		} else {
			System.out.println("Password not changed");
		}

	}

	public void changeName(String name) {
		if (name != null && name.length() > 0) {
			this.name = name;
		}

	}

	public void changeDateOfBirth(int year, int month, int day) {
		this.dateOfBirth.set(year, month, day);
	}

	public void changeSignature(String s) {
		this.signature= s;
	}

	public void changeAvatar(File f) {

	}

	public void changeRights(Rights r, Profile user) {
		if (this.rights.equals(Rights.ADMIN)) {
			user.setRights(r);
		}
	}

	protected void setRights(Rights r) {
		this.rights = r;

	}

	public void addPhoto(Photo p) {

	}

	public void removePhoto(Photo p) {
		this.myGallery.remove(p);
	}

	public void comment(Comment c, Photo p) {
		p.addComment(c);
	}

	public void editComment(Comment c, Photo photo) {
		if (c != null) {
			photo.addComment(c);
		}
	}

	public void deleteComment(Comment c) {
		if (this.comments.contains(c)) {
			comments.remove(c);
		}

	}

	public void ratePhoto(Photo p, int rate) {
		if (rate > 0 && rate < 6) {
			p.changeRaiting(rate,this);
		}
	}

	public void addToFavorite(Photo p) {

		this.favourites.add(p);
	}

	@Override
	public int compareTo(Profile o) {
		int a = this.userName.compareTo(o.userName);
		if (a == 0) {
			return this.email.compareTo(o.email);
		}
		return a;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Rights getRights() {
		return rights;
	}

	static class Admin extends Profile {
		Admin(Profile p) {
			super(p.userName, p.password, p.email, p.gender);
			this.setRights( Rights.ADMIN);
		}
	}

	static class Moderator extends Profile {

		Moderator(Profile p) {
			super(p.userName, p.password, p.email, p.gender);
			this.setRights( Rights.MODERATOR);
		}
	}

	static class Member extends Profile {

		Member(Profile p) {
			super(p.userName, p.password, p.email, p.gender);
			this.setRights(Rights.MEMBER);
		}
	}

}