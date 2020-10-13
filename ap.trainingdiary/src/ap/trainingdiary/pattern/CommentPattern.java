package ap.trainingdiary.pattern;

import java.util.regex.Pattern;

public class CommentPattern{
	private static final Pattern commentPattern;
	static{
		commentPattern = Pattern.compile("^comment(\\d+)\\.json$");
	}
	public static Pattern getInstance(){
		return commentPattern;
	}
}
