package ap.trainingdiary.type;

import java.util.Collections;
import java.util.List;

public class Training{
	private final int day;
	private final int month;
	private final int year;
	private final int trainingIndex;
	private final List<TrainingRow> trainingRowList;
	private final String comment;
	
	private Training(
		final int day,
		final int month,
		final int year,
		final int trainingIndex,
		final List<TrainingRow> trainingRowList,
		final String comment
	){
		this.day = day;
		this.month = month;
		this.year = year;
		this.trainingIndex = trainingIndex;
		this.trainingRowList = trainingRowList;
		this.comment = comment;
	}
	
	public static Training create(
		final int day,
		final int month,
		final int year,
		final int trainingIndex,
		final List<TrainingRow> trainingRowList,
		final String comment
	){
		return new Training(day, month, year, trainingIndex, Collections.unmodifiableList(trainingRowList), comment);
	}
	public int getDay(){
		return day;
	}
	public int getMonth(){
		return month;
	}
	public int getYear(){
		return year;
	}
	public int getTrainingIndex(){
		return trainingIndex;
	}
	public List<TrainingRow> getTrainingData(){
		return trainingRowList;
	}
	public String getComment(){
		return comment;
	}
}
