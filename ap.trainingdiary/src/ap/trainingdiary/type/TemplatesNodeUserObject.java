package ap.trainingdiary.type;

import ap.trainingdiary.languages.NodeName;
import ap.trainingdiary.languages.Properties;

public class TemplatesNodeUserObject extends AbctractComparable{
	public TemplatesNodeUserObject(){
		super(NodeType.TEMPLATESNODEUSEROBJECT);
	}
	public String toString(){
		return Properties.getInstance().getValue(NodeName.TEMPLATES);
	}
}
