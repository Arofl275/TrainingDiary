package ap.trainingdiary.pattern;

import java.util.regex.Pattern;

public class TrainingPattern{
	private static final Pattern trainingPattern;
	static{
		trainingPattern = Pattern.compile("^training(\\d+)\\.json$");
	}
	public static Pattern getInstance(){
		return trainingPattern;
	}
}
