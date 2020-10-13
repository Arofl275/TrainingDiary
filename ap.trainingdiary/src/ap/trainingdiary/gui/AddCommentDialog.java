package ap.trainingdiary.gui;

import java.awt.Window;

import ap.trainingdiary.languages.Properties;
import ap.trainingdiary.languages.Title;

public class AddCommentDialog extends AbstractCommentDialog{
	public AddCommentDialog(final Window parent){
		super(parent, Properties.getInstance().getValue(Title.ADDCOMMENT));
	}
}
