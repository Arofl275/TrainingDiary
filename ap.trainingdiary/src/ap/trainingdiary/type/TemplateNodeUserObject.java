package ap.trainingdiary.type;

import java.io.File;

import ap.trainingdiary.languages.Properties;
import ap.trainingdiary.languages.TrainingType;

public class TemplateNodeUserObject extends AbctractComparable{
	private final String nodeName;
	private final File file;
	public TemplateNodeUserObject(final String nodeName, final File file){
		super(NodeType.TEMPLATENODEUSEROBJECT);
		this.nodeName = nodeName;
		this.file = file;
	}
	public int compareTo(final AbctractComparable other){
		final int cmp = super.compareTo(other);
		if(cmp == 0){
			TemplateNodeUserObject templateNodeUserObjectOther = (TemplateNodeUserObject) other;
			return nodeName.compareTo(templateNodeUserObjectOther.nodeName);
		}
		else{
			return cmp;
		}
	}
	public String getTemplateName(){
		return nodeName;
	}
	public String toString(){
		return nodeName;
	}
	public File getFile(){
		return file;
	}
}
