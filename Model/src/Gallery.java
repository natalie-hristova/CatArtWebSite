
import java.util.HashMap;


public class Gallery {
	private static Gallery instance;
	private static  HashMap<Photo.Genre, HashMap <String, Photo >> gallery;
			//Genre > tags > Photo
	private static HashMap<String, Profile> allUsers;
			//userName > profile

	private Gallery(){
		this.gallery = new HashMap<>();
		this.allUsers = new HashMap<>();
	}
	
	public static Gallery getInstance(){
		if(instance == null){
				instance = new Gallery();
			}
		return instance;
	}
	
	
	///TODO ne trqbva li da sme sig 4e go nqma da imaproverka dal ie v mapa
	public static void addProfile(Profile p){
		allUsers.put(p.getUserName(), p);
		//they are unique by user name so it ok
	}
	
	public static void deleteProfile(Profile p, Profile admin){
		if(allUsers.containsKey(p.getUserName())){
			if(admin.getRights().equals(Profile.Rights.ADMIN)){
				allUsers.remove(p);
			}
		}
	}
	
	public static void addPhoto(Photo p){
		if(!gallery.containsKey(p.getGenre())){
			gallery.put(p.getGenre(), new HashMap<>());
		}
		for(String s : p.getTags()){
			gallery.get(p.getGenre()).put(s, p);
		}
	}
	
	public static void deleteGenre(Photo.Genre g, Profile admin){
		if(admin.getRights().equals(Profile.Rights.ADMIN)){
			if(!gallery.containsKey(g)){
				gallery.remove(g);
			}
		}
	}
}