package ap.trainingdiary.type;

import java.io.File;

import ap.trainingdiary.languages.Properties;
import ap.trainingdiary.languages.TrainingType;

public class CommentNodeUserObject extends AbctractComparable{
	private final String nodeName;
	private final File file;
	public CommentNodeUserObject(final String nodeName, final File file){
		super(NodeType.COMMENTNODEUSEROBJECT);
		this.nodeName = nodeName;
		this.file = file;
	}
	public int compareTo(final AbctractComparable other){
		final int cmp = super.compareTo(other);
		if(cmp == 0){
			CommentNodeUserObject commentNodeUserObjectOther = (CommentNodeUserObject) other;
			return nodeName.compareTo(commentNodeUserObjectOther.nodeName);
		}
		else{
			return cmp;
		}
	}
	public String getCommentName(){
		return nodeName;
	}
	public String toString(){
		return nodeName;
	}
	public File getFile(){
		return file;
	}
}
