package model;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;

import javax.xml.bind.ValidationException;

import model.Photo.Genre;
import model.Photo.InvalidInfoException;
import model.User.Gender;

public class Main {
	public static void main(String[] args) throws InvalidInfoException {

		Gallery.getInstance();
		// registration
		User gesho;
		User pesho;
		try {
			gesho = new User("gesho", "gogi", "gogi123@abv.bg", Gender.M);
			pesho = new User("pesho", "pepi", "pepi2010@abv.bg", Gender.F);
			Gallery.addProfile(pesho);
			Gallery.addProfile(gesho);
			Gallery.addProfile(gesho);
		} catch (ValidationException e2) {
			System.out.println("Something was not valid");
			e2.printStackTrace();
		}

		// comment
		
		System.out.println(Gallery.getProfileByUserName("gesho"));

		Gallery.showProfiles();
		Gallery.getProfileByUserName("gesho").AddFriend(Gallery.getProfileByUserName("pesho"));
		Photo a = new Photo("sun1", Gallery.getProfileByUserName("gesho"), Genre.PHOTO, "sun stuff", "Sun,yellow,photo");
		for (int i = 0; i < 100000000; i++) {
			// buy some time
		}
		Photo b = new Photo("sun2", Gallery.getProfileByUserName("gesho"), Genre.TRADITIONAL, "sun stuff", "Sun,yellow,photo");
		Gallery.getProfileByUserName("gesho").addPhoto(a);
		for (int i = 0; i < 100000000; i++) {
			// buy some time
		}
		Gallery.getProfileByUserName("gesho").addPhoto(new Photo("sun123", Gallery.getProfileByUserName("gesho"), Genre.SCETCH, "sun stuff", "Sun,yellow,photo"));
		Gallery.getProfileByUserName("gesho").addPhoto(b);
		for (int i = 0; i < 100000000; i++) {
			// buy some time
		}
		Gallery.getProfileByUserName("gesho").addPhoto(new Photo("Slunce", Gallery.getProfileByUserName("gesho"), Genre.SCETCH, "sun stuff", "sun,yelo,photo"));
		a.addComment(new Comment(Gallery.getProfileByUserName("gesho"), "Hello"));
		a.addComment(new Comment(Gallery.getProfileByUserName("gesho"), "Hellosss"));
		a.addComment(new Comment(Gallery.getProfileByUserName("gesho"), "Hello"));
		b.addComment(new Comment(Gallery.getProfileByUserName("gesho"), "Hellosss"));
		a.changeRaiting(4, Gallery.getProfileByUserName("gesho"));
		a.changeRaiting(5, Gallery.getProfileByUserName("gesho"));
		b.changeRaiting(1, Gallery.getProfileByUserName("gesho"));
		b.changeRaiting(1, Gallery.getProfileByUserName("gesho"));

		Gallery.showPhoto();

		TreeSet<Photo> alphabetic = new TreeSet<Photo>(Gallery.alphabeticProfileComparator);
		for (Entry<Photo.Genre, HashMap<String, TreeSet<Photo>>> e : Gallery.getGallery().entrySet()) {
			for (Entry<String, TreeSet<Photo>> e1 : e.getValue().entrySet()) {
				alphabetic.addAll(e1.getValue());
			}
		}
		// works
		System.out.println(alphabetic.toString());
		System.out.println();
		TreeSet<Photo> mostComments = new TreeSet<Photo>(Gallery.mostCommentsComparator);
		for (Entry<Photo.Genre, HashMap<String, TreeSet<Photo>>> e : Gallery.getGallery().entrySet()) {
			for (Entry<String, TreeSet<Photo>> e1 : e.getValue().entrySet()) {
				mostComments.addAll(e1.getValue());
			}
		}
		// works
		System.out.println(mostComments.toString());
		System.out.println();
		TreeSet<Photo> mostRating = new TreeSet<Photo>(Gallery.mostRatingStarsComparator);
		for (Entry<Photo.Genre, HashMap<String, TreeSet<Photo>>> e : Gallery.getGallery().entrySet()) {
			for (Entry<String, TreeSet<Photo>> e1 : e.getValue().entrySet()) {
				mostRating.addAll(e1.getValue());
			}
		}
		// works
		System.out.println(mostRating.toString());
		System.out.println();

		TreeSet<Photo> noComments = new TreeSet<Photo>(Gallery.noCommentsComparator);
		for (Entry<Photo.Genre, HashMap<String, TreeSet<Photo>>> e : Gallery.getGallery().entrySet()) {
			for (Entry<String, TreeSet<Photo>> e1 : e.getValue().entrySet()) {
				noComments.addAll(e1.getValue());
			}
		}
		// works
		System.out.println(noComments.toString());
		System.out.println();
		TreeSet<Photo> time = new TreeSet<Photo>(Gallery.timeOfUploadComparator);
		for (Entry<Photo.Genre, HashMap<String, TreeSet<Photo>>> e : Gallery.getGallery().entrySet()) {
			for (Entry<String, TreeSet<Photo>> e1 : e.getValue().entrySet()) {
				time.addAll(e1.getValue());
			}
		}
		// works
		System.out.println(time.toString());
	}
}