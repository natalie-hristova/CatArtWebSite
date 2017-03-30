package model;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.xml.bind.ValidationException;

import model.User.Gender;
import model.User.Rights;

public class Main {
	public static void main(String[] args) throws ValidationException {

		Gallery.getInstance("Catsss...<3");
		// registration
		User gesho;
		User pesho;
		try {
			gesho = new User("gesho", "gogi", "gogi123@abv.bg", Gender.M, Rights.MEMBER);
			pesho = new User("pesho", "pepi", "pepi2010@abv.bg", Gender.F, Rights.MEMBER);
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
		Gallery.getProfileByUserName("gesho").addFriend(Gallery.getProfileByUserName("pesho"));
		Gallery.showPhoto();

		TreeSet<Photo> alphabetic = new TreeSet<Photo>(Gallery.alphabeticProfileComparator);
		for (Entry<Photo.Genre, HashMap<String, ConcurrentSkipListSet<Photo>>> e : Gallery.getGallery().entrySet()) {
			for (Entry<String, ConcurrentSkipListSet<Photo>> e1 : e.getValue().entrySet()) {
				alphabetic.addAll(e1.getValue());
			}
		}
		// works
		System.out.println(alphabetic.toString());
		System.out.println();
		TreeSet<Photo> mostComments = new TreeSet<Photo>(Gallery.mostCommentsComparator);
		for (Entry<Photo.Genre, HashMap<String, ConcurrentSkipListSet<Photo>>> e : Gallery.getGallery().entrySet()) {
			for (Entry<String, ConcurrentSkipListSet<Photo>> e1 : e.getValue().entrySet()) {
				mostComments.addAll(e1.getValue());
			}
		}
		// works
		System.out.println(mostComments.toString());
		System.out.println();
		TreeSet<Photo> mostRating = new TreeSet<Photo>(Gallery.mostRatingStarsComparator);
		for (Entry<Photo.Genre, HashMap<String, ConcurrentSkipListSet<Photo>>> e : Gallery.getGallery().entrySet()) {
			for (Entry<String, ConcurrentSkipListSet<Photo>> e1 : e.getValue().entrySet()) {
				mostRating.addAll(e1.getValue());
			}
		}
		// works
		System.out.println(mostRating.toString());
		System.out.println();

		TreeSet<Photo> noComments = new TreeSet<Photo>(Gallery.noCommentsComparator);
		for (Entry<Photo.Genre, HashMap<String, ConcurrentSkipListSet<Photo>>> e : Gallery.getGallery().entrySet()) {
			for (Entry<String, ConcurrentSkipListSet<Photo>> e1 : e.getValue().entrySet()) {
				noComments.addAll(e1.getValue());
			}
		}
		// works
		System.out.println(noComments.toString());
		System.out.println();
		TreeSet<Photo> time = new TreeSet<Photo>(Gallery.timeOfUploadComparator);
		for (Entry<Photo.Genre, HashMap<String, ConcurrentSkipListSet<Photo>>> e : Gallery.getGallery().entrySet()) {
			for (Entry<String, ConcurrentSkipListSet<Photo>> e1 : e.getValue().entrySet()) {
				time.addAll(e1.getValue());
			}
		}
		// works
		System.out.println(time.toString());
	}
}