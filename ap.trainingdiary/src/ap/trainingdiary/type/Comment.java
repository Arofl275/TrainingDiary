package ap.trainingdiary.type;

public class Comment{
	private final String commentName;
	private final String commentText;
	public Comment(
		final String commentName,
		final String commentText
	){
		this.commentName = commentName;
		this.commentText = commentText;
	}
	public String getName(){
		return commentName;
	}
	public String getText(){
		return commentText;
	}
}
