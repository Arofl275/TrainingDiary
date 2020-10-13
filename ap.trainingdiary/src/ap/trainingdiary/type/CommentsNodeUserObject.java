package ap.trainingdiary.type;

import ap.trainingdiary.languages.NodeName;
import ap.trainingdiary.languages.Properties;

public class CommentsNodeUserObject extends AbctractComparable{
	public CommentsNodeUserObject(){
		super(NodeType.COMMENTSNODEUSEROBJECT);
	}
	public String toString(){
		return Properties.getInstance().getValue(NodeName.COMMENTS);
	}
}
