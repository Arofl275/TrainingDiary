package ap.trainingdiary.pattern;

import java.util.regex.Pattern;

public class TemplatePattern{
	private static final Pattern templatePattern;
	static{
		templatePattern = Pattern.compile("^template(\\d+)\\.json$");
	}
	public static Pattern getInstance(){
		return templatePattern;
	}
}
