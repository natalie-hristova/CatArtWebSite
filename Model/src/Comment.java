import java.time.LocalDateTime;
import java.util.ArrayList;

public class Comment{

	private Profile profile;// koi go postva ?
	private String content;
	private LocalDateTime timeOfPosting;
	private ArrayList<Comment> comments = new ArrayList<>();
		
	Comment(Profile profile, String content) {
		if(!content.isEmpty() || content != null){
			this.profile = profile;
			this.content = content;
			this.timeOfPosting = LocalDateTime.now();
		}
	}
}
