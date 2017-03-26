package model;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.xml.bind.ValidationException;

import model.Photo.Genre;

public class User implements Comparable<User> {
	public enum Rights {
		ADMIN, MEMBER, MODERATOR;
	}

	public enum Gender {
		M, F
	}

	private long userId;
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
	private String country;
	private HashSet<Photo> favourites;
	private HashMap<String, User> friends;
	private ArrayList<Comment> comments;
	private HashMap<Photo.Genre, HashMap<String, ConcurrentSkipListSet<Photo>>> myGallery;
	private HashSet<User> blockedUsers;

	public User(String userName, String password, String email, String name, Calendar dateOfBirth, Gender gender,
			String signature, String country, Rights r) throws ValidationException {
		this(userName, password, email, gender, r);
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.signature = signature;
		this.country = country;
	}

	public User(String userName, String password, String email, Gender gender, Rights r) throws ValidationException {
		this.rights = Rights.MEMBER;
		this.gender = gender;
		if (isValidEmail(email)) {
			this.email = email;
		}
		this.joiningDate = LocalDateTime.now();
		this.isLogged = true;
		if (isValidUserName(userName)) {
			this.userName = userName;
		}
		if (isValidPassword(password)) {
			this.password = password;
		}
		this.friends = new HashMap<>();
		this.comments = new ArrayList<>();
		this.myGallery = new HashMap<>();
		this.blockedUsers = new HashSet<>();
	}

	public User(String username, String password, User.Gender gender) {
		this.userName = username;
		this.password = password;
		this.gender = gender;
	}

	private boolean isValidPassword(String password) throws ValidationException {
		if (password != null && password.length() > 3) {
			return true;
		} else {
			throw new ValidationException("Not valid password");
		}
	}

	private boolean isValidUserName(String userName) throws ValidationException {
		if (userName != null && userName.length() > 4) {
			return true;
		} else {
			throw new ValidationException("Not valid username");
		}
	}

	private boolean isValidEmail(String email) throws ValidationException {
		if (email != null && email.matches("[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}")) {
			return true;
		} else {
			throw new ValidationException("Not valid email");
		}
	}

	public void logIn(String userName, String password) {
		if (Gallery.getProfileByUserName(userName) != null && password.equals(this.password)) {
			isLogged = true;
		} else {
			System.out.println("Invalid username or password");
		}
	}

	public void LogOut() {
		this.isLogged = false;
	}

	public void AddFriend(User p) {

		if (isLogged && !blockedUsers.contains(p)) {
			if (!friends.containsValue(p) && p != null) {
				friends.put(p.getUsername(), p);
				p.AddFriend(this);
			}
		}
	}

	public void RemoveFriend(User p) {
		if (isLogged && friends.containsValue(p)) {
			friends.remove(p.getUsername(), p);
			p.RemoveFriend(this);
		}
	}

	public void blockProfile(User p) {
		if (isLogged && !blockedUsers.contains(p)) {
			blockedUsers.add(p);
			if (friends.containsKey(p)) {
				friends.remove(p);
			}
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
		this.signature = s;
	}

	public void changeAvatar(File f) {
		if (isLogged && f != null) {
			this.avatar = f;
		}
	}

	public void changeRights(Rights r, User user) {
		if (this.rights.equals(Rights.ADMIN)) {
			user.rights = r;
		}
	}

	public void addPhoto(Photo p) {
		// dobavqne i v kolekciqta na user-a
		if (!this.myGallery.containsKey(p.getGenre())) {
			this.myGallery.put(p.getGenre(), new HashMap<>());
		}
		for (String s : p.getTags()) {
			if (!this.myGallery.get(p.getGenre()).containsKey(s)) {
				this.myGallery.get(p.getGenre()).put(s, new ConcurrentSkipListSet<>());
			}
			this.myGallery.get(p.getGenre()).get(s).add(p);
		}
		Gallery.addPhoto(p);
	}

	public void removePhoto(Photo p) {
		// mahane ot kolekciqta
		for (Entry<Genre, HashMap<String, ConcurrentSkipListSet<Photo>>> e : this.myGallery.entrySet()) {
			for (Entry<String, ConcurrentSkipListSet<Photo>> e2 : e.getValue().entrySet()) {
				for (Iterator<Photo> it = e2.getValue().iterator(); it.hasNext();) {
					Photo a = it.next();
					if (a.equals(p)) {
						it.remove();
					}
				}
			}
		}
		Gallery.deletePhoto(p);
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
			p.changeRaiting(rate, this);
		}
	}

	public void addToFavorite(Photo p) {

		this.favourites.add(p);
	}

	@Override
	public int compareTo(User o) {
		int a = this.userName.compareTo(o.userName);
		if (a == 0) {
			return this.email.compareTo(o.email);
		}
		return a;
	}

	public String getUsername() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Rights getRights() {
		return rights;
	}

	@Override
	public String toString() {
		return this.userName + " - " + this.email;
	}

	public Map<Photo.Genre, HashMap<String, ConcurrentSkipListSet<Photo>>> getMyGallery() {
		return Collections.unmodifiableMap(myGallery);
	}

	public Date getJoiningDate() {
		LocalDateTime now = LocalDateTime.now();
		Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
		Date dateFromOld = Date.from(instant);
		Month m = joiningDate.getMonth();
		return dateFromOld;
	}

	public void setUserID(long l) {
		this.userId = l;
	}

	public String getPass() {
		return this.password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getSignature() {
		return signature;
	}

	public String getCountry() {
		return country;
	}

	public Date getBirthday() {
		return dateOfBirth.getTime();
	}

	public long getUserID() {
		return this.userId;
	}

	public void setBirthday(LocalDateTime bday) {
		this.dateOfBirth.set(bday.getYear(), bday.getMonthValue(), bday.getDayOfMonth());
				
	}

	public void setJoiningDate(LocalDateTime joining_date) {
		this.joiningDate = joining_date;
	}

	public void setName(String name) {
		this.name = name;
	}
}