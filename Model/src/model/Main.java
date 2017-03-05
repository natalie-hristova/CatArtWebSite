package model;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;

import model.Photo.Genre;
import model.Photo.InvalidInfoException;
import model.Profile.Gender;

public class Main {
	public static void main(String[] args) throws InvalidInfoException {
	
		Gallery.getInstance();
		//registration
		Profile gesho = new Profile("gesho", "gogi", "gogi123@abv.bg", Gender.M);
		Profile pesho = new Profile("pesho", "pepi", "pepi2010@abv.bg", Gender.F);
		//comment
		Gallery.addProfile(pesho);
		Gallery.addProfile(gesho);
		System.out.println(pesho.getUserName());
		
		Gallery.showProfiles();
		gesho.AddFriend(pesho);
		Photo a = new Photo("sun1", gesho, Genre.PHOTO, "sun stuff", "Sun,yellow,photo");
		for(int i =0 ; i <100000000; i++){
			//buy some time
		}
		Photo b = new Photo("sun2", gesho, Genre.TRADITIONAL, "sun stuff", "Sun,yellow,photo");
		gesho.addPhoto(a);
		for(int i =0 ; i <100000000; i++){
			//buy some time
		}
		gesho.addPhoto(new Photo("sun123", gesho, Genre.SCETCH, "sun stuff", "Sun,yellow,photo"));
		gesho.addPhoto(b);
		for(int i =0 ; i <100000000; i++){
			//buy some time
		}
		gesho.addPhoto(new Photo("Slunce", gesho, Genre.SCETCH, "sun stuff", "sun,yelo,photo"));
		a.addComment(new Comment(gesho, "Hello"));
		a.addComment(new Comment(gesho, "Hellosss"));
		a.addComment(new Comment(gesho, "Hello"));
		b.addComment(new Comment(pesho, "Hellosss"));
		a.changeRaiting(4, gesho);
		a.changeRaiting(5, gesho);
		b.changeRaiting(1, gesho);
		b.changeRaiting(1, pesho);
		
		Gallery.showPhoto();
		
		TreeSet<Photo> alphabetic = new TreeSet<Photo>(Gallery.alphabeticProfileComparator);
		for(Entry <Photo.Genre, HashMap <String, TreeSet <Photo>>> e : Gallery.getGallery().entrySet()){
			for(Entry<String, TreeSet<Photo>> e1 : e.getValue().entrySet()){
				alphabetic.addAll(e1.getValue());
			}
		}
		//works
		System.out.println(alphabetic.toString());
		System.out.println();
		TreeSet<Photo> mostComments = new TreeSet<Photo>(Gallery.mostCommentsComparator);
		for(Entry <Photo.Genre, HashMap <String, TreeSet <Photo>>> e : Gallery.getGallery().entrySet()){
			for(Entry<String, TreeSet<Photo>> e1 : e.getValue().entrySet()){
				mostComments.addAll(e1.getValue());
			}
		}
		//works
		System.out.println(mostComments.toString());
		System.out.println();
		TreeSet<Photo> mostRating = new TreeSet<Photo>(Gallery.mostRatingStarsComparator);
		for(Entry <Photo.Genre, HashMap <String, TreeSet <Photo>>> e : Gallery.getGallery().entrySet()){
			for(Entry<String, TreeSet<Photo>> e1 : e.getValue().entrySet()){
				mostRating.addAll(e1.getValue());
			}
		}
		//works
		System.out.println(mostRating.toString());
		System.out.println();
		
		TreeSet<Photo> noComments = new TreeSet<Photo>(Gallery.noCommentsComparator);
		for(Entry <Photo.Genre, HashMap <String, TreeSet <Photo>>> e : Gallery.getGallery().entrySet()){
			for(Entry<String, TreeSet<Photo>> e1 : e.getValue().entrySet()){
				noComments.addAll(e1.getValue());
			}
		}
		//works
		System.out.println(noComments.toString());
		System.out.println();
		TreeSet<Photo> time = new TreeSet<Photo>(Gallery.timeOfUploadComparator);
		for(Entry <Photo.Genre, HashMap <String, TreeSet <Photo>>> e : Gallery.getGallery().entrySet()){
			for(Entry<String, TreeSet<Photo>> e1 : e.getValue().entrySet()){
				time.addAll(e1.getValue());
			}
		}
		//works
		System.out.println(time.toString());
	}
}