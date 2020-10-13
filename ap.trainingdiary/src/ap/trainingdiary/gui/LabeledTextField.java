package ap.trainingdiary.gui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabeledTextField extends JPanel{
	private JLabel label;
	private JTextField textField;
	public LabeledTextField(){
		super(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel();
		textField = new JTextField();
		add(label);
		add(textField);
	}
	public JLabel getLabel(){
		return label;
	}
	public JTextField getTextField(){
		return textField;
	}
}
