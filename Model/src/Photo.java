
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Photo implements Comparable<Photo> {
	public enum Genre {
		PHOTO, DIGITAL, TRADITIONAL, CRAFTS, COMICS, FANART, SCETCH;
	}

	private String name;
	private Profile profile;
	private double  rating;
	private ArrayList<Profile> pplThatHaveRated;
	private LocalDateTime dateofuploading;//dateOfUploading
	private Genre genre;
	private String about;
	private ArrayList<String> tags = new ArrayList<>();
	private ArrayList<Comment> comments = new ArrayList<>();
	private File photo;
	private int ratedPeople=0;
	Photo(String name, Profile profile, Genre genre, String about, String tag) throws InvalidInfoException {
		this.rating = 0;
		this.dateofuploading = LocalDateTime.now();
		this.profile = profile;
		this.changeInfo(about);
		this.changeName(name);
		this.changeGenre(genre);
		// photo = new File();
	}

	private void SeparateTags(String tag) {
		if (tag.length() == 0) {
			return;
		}
		if (tag.indexOf(',') < 0) {
			this.tags.add(tag.trim());
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

	public void changeName(String name) throws InvalidInfoException {
		if (name == null || name.isEmpty()) {
			throw new InvalidInfoException();
		} else {
			this.name = name;
		}
	}
	public void changeRaiting(int rate, Profile profil) {
		if (this.pplThatHaveRated.contains(profil)) {
			return;
		}
			rating*= ratedPeople + rate;
			ratedPeople ++;
			this.pplThatHaveRated.add(profil);
			rating/=ratedPeople; 
		
	}

	public void changeGenre(Genre genre) throws InvalidInfoException {
		if (genre == null && this.genre == null) {
			throw new InvalidInfoException();
		} else {
			this.genre = genre;
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
	private class InvalidInfoException extends Exception {
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
							return this.dateofuploading.compareTo(p.dateofuploading);
						}
					}
				}
			}
		}
		return a;
	}

	public static void alphabeticProfileComparator(Photo photo) {
		class Comparator1 implements Comparator<Photo> {
			@Override
			public int compare(Photo o1, Photo o2) {
				int a = o1.profile.getUserName().compareTo(o2.profile.getUserName());
				if (a == 0) {
					a = o1.getName().compareTo(o2.getName());
					if (a == 0) {
						return o1.dateofuploading.compareTo(o2.dateofuploading);
					}
				}
				return a;
			}
		}
	}

	public static void mostCommentsComparator(Photo photo) {
		class Comparator1 implements Comparator<Photo> {
			@Override
			public int compare(Photo o1, Photo o2) {
				int a = o1.comments.size() - o2.comments.size();
				if (a == 0) {
					o1.compareTo(o2);
				}
				return a;
			}
		}
	}
	public static void mostRatingStarsComparator(Photo photo) {
		class Comparator1 implements Comparator<Photo> {
			@Override
			public int compare(Photo o1, Photo o2) {
				int a = (int)(o1.rating - o2.rating);
				if (a == 0) {
					o1.compareTo(o2);
				}
				return a;
			}
		}
	}

	public static void noCommentsComparator(Photo photo) {
		class Comparator1 implements Comparator<Photo> {
			@Override
			public int compare(Photo o1, Photo o2) {
				if (o1.rating > o2.rating) {
					return -1;
				} else {
					if (o1.rating < o2.rating) {
						return 1;
					} else {
						return o1.compareTo(o2);
					}
				}
			}
		}
	}

	public static void timeOfUploadComparator(Photo photo) {
		class Comparator1 implements Comparator<Photo> {
			@Override
			
	
			public int compare(Photo o1, Photo o2) {
				int a = o1.dateofuploading.compareTo(o2.dateofuploading);
				if (a == 0) {
					o1.compareTo(o2);
				}
				return a;
			}
		}
	}

	public void addComment(Comment c) {
		if (c!= null) {
			comments.add(c);
		}
		
	}

	
}