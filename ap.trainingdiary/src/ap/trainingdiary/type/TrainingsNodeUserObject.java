package ap.trainingdiary.type;

import ap.trainingdiary.languages.NodeName;
import ap.trainingdiary.languages.Properties;

public class TrainingsNodeUserObject extends AbctractComparable{
	public TrainingsNodeUserObject(){
		super(NodeType.TRAININGSNODEUSEROBJECT);
	}
	public String toString(){
		return Properties.getInstance().getValue(NodeName.TRAININGS);
	}
}
