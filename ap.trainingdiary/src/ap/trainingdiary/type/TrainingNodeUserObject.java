package ap.trainingdiary.type;

import java.io.File;

import ap.trainingdiary.languages.Properties;
import ap.trainingdiary.languages.TrainingType;

public class TrainingNodeUserObject extends AbstractIndexNodeUserObject{
	private final File file;
	public TrainingNodeUserObject(final Integer index, final File file){
		super(NodeType.TRAININGNODEUSEROBJECT, index);
		this.file = file;
	}
	public String toString(){
		return Properties.getInstance().getValue(TrainingType.getTrainingType(index));
	}
	public File getFile(){
		return file;
	}
}
