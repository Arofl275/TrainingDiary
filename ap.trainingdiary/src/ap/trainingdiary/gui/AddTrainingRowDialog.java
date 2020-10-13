package ap.trainingdiary.gui;

import java.awt.Window;

import ap.trainingdiary.languages.Properties;
import ap.trainingdiary.languages.Title;

public class AddTrainingRowDialog extends AbstractTrainingRowDialog{
	public AddTrainingRowDialog(final Window parent){
		super(parent, Properties.getInstance().getValue(Title.ADDROW));
	}
}
