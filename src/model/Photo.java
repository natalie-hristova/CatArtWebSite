package model;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.xml.bind.ValidationException;

public class Photo implements Comparable<Photo> {
	public enum Genre {
		PHOTO, DIGITAL, TRADITIONAL, CRAFTS, COMICS, FANART, SCETCH;
	}

	private String name;
	private User profile;
	private double  rating;
	private ArrayList<User> ratedPpl = new ArrayList<>();
	private LocalDateTime dateOfUploading;
	private Genre genre;
	private String about;
	private ArrayList<String> tags = new ArrayList<>();
	private ArrayList<Comment> comments = new ArrayList<>();
	private File photo;
	
	Photo(String name, User profile, Genre genre, String about, String tag) throws ValidationException {
		this.rating = 0;
		this.dateOfUploading = LocalDateTime.now();
		this.profile = profile;
		this.changeInfo(about);
		this.changeName(name);
		this.changeGenre(genre);
		// photo = new File();
		this.SeparateTags(tag.toLowerCase());
	}

	private void SeparateTags(String tag) {
		if (tag.length() == 0) {
			return;
		}
		if (tag.indexOf(',') < 0) {
			this.tags.add(tag.trim());
			return;
		}
		this.tags.add(tag.substring(0, tag.indexOf(',')));
		String a = tag.substring(tag.indexOf(',') + 1).trim();
		SeparateTags(a);
	}
	public void addNewTags(String tag) {
		SeparateTags(tag);
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
		return this.profile;
	}
	public LocalDateTime getDateOfUploading() {
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
