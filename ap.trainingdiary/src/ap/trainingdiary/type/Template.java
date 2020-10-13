package ap.trainingdiary.type;

import java.util.Collections;
import java.util.List;
public class Template{
	private final String templateName;
	private final List<TrainingRow> trainingRowList;
	private Template(
		final String templateName,
		final List<TrainingRow> trainingRowList
	){
		this.templateName = templateName;
		this.trainingRowList = trainingRowList;
	}
	public static Template create(
		final String templateName,
		final List<TrainingRow> trainingRowList
	){
		return new Template(templateName, trainingRowList);
	}
	public String getName(){
		return templateName;
	}
	public List<TrainingRow> getTrainingData(){
		return trainingRowList;
	}
}
