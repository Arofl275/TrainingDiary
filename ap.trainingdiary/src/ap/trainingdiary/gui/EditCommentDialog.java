package ap.trainingdiary.gui;

import java.awt.Window;

import ap.trainingdiary.languages.Properties;
import ap.trainingdiary.languages.Title;

public class EditCommentDialog extends AbstractCommentDialog{
	public EditCommentDialog(final Window parent){
		super(parent, Properties.getInstance().getValue(Title.EDITCOMMENT));
		labeledTextField.getTextField().setEnabled(false);
	}
}
