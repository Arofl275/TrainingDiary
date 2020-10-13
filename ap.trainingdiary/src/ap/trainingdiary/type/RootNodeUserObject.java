package ap.trainingdiary.type;

import ap.trainingdiary.languages.Properties;
import ap.trainingdiary.languages.Title;

public class RootNodeUserObject extends AbctractComparable{
	public RootNodeUserObject(){
		super(NodeType.ROOTNODEUSEROBJECT);
	}
	public String toString(){
		return Properties.getInstance().getValue(Title.TRAININGDIARY);
	}
}
