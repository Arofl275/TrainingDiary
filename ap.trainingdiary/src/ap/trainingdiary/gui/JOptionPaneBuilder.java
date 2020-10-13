package ap.trainingdiary.gui;

import java.awt.Component;
import javax.swing.JOptionPane;
import ap.trainingdiary.languages.Button;
import ap.trainingdiary.languages.Properties;

public class JOptionPaneBuilder{
	public static int showYesNoDialog(
		final Component parentComponent,
		final String message,
		final String title
	){
		Object[] options = {
			Properties.getInstance().getValue(Button.YES),
			Properties.getInstance().getValue(Button.NO)
		};
		return JOptionPane.showOptionDialog(
			parentComponent,
			message,
			title,
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options[0]
		);
	}
}

