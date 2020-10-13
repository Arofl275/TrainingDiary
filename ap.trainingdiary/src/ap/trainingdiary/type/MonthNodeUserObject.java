package ap.trainingdiary.type;

import ap.trainingdiary.languages.Properties;
import ap.trainingdiary.languages.Month;

public class MonthNodeUserObject extends AbstractIndexNodeUserObject{
	public MonthNodeUserObject(final Integer index){
		super(NodeType.MONTHNODEUSEROBJECT, index);
	}
	public String toString(){
		return Properties.getInstance().getValue(Month.getMonth(index));
	}
}
