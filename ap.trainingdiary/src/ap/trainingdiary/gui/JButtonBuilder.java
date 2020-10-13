package ap.trainingdiary.gui;

import javax.swing.JButton;

import ap.trainingdiary.languages.Button;
import ap.trainingdiary.languages.Properties;

public class JButtonBuilder{
	private static JButton create(final Button button){
		return new JButton(Properties.getInstance().getValue(button));
	}
	public static JButton ok(){
		return create(Button.OK);
	}
	public static JButton cancel(){
		return create(Button.CANCEL);
	}
}
