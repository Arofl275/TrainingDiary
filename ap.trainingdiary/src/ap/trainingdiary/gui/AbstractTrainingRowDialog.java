package ap.trainingdiary.gui;

import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import java.util.List;
import java.math.BigDecimal;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jdatepicker.JDatePanel;

import ap.trainingdiary.languages.Button;
import ap.trainingdiary.languages.ColumnType;
import ap.trainingdiary.languages.ErrorType;
import ap.trainingdiary.languages.Properties;
import ap.trainingdiary.languages.TrainingType;

import ap.trainingdiary.util.CheckBigDecimal;

import ap.swing.JPanelBuilder;

public abstract class AbstractTrainingRowDialog extends AbstractOkCancelDialog{
	protected BigDecimal weight;
	protected Integer repeats;
	protected AbstractTrainingRowDialog(final Window parent, final String title){
		super(parent, title);
	}
	protected JDialog createDialog() throws HeadlessException{
		JDialog dialog = new JDialog(parent, title, Dialog.ModalityType.APPLICATION_MODAL);
		final int columns = 20;
		LabeledTextField labeledTextField1 = LabeledTextFieldBuilder.weight(columns);
		if(weight != null){
			labeledTextField1.getTextField().setText(weight.toPlainString());
		}
		LabeledTextField labeledTextField2 = LabeledTextFieldBuilder.repeats(columns);
		if(repeats != null){
			labeledTextField2.getTextField().setText(repeats.toString());
		}
		okButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent actionEvent){
					BigDecimal oldWeight = weight;
					Integer oldRepeats = repeats;
					try{
						try{
							weight = new BigDecimal(labeledTextField1.getTextField().getText());
							if(CheckBigDecimal.wrongFormat(weight)){
								throw new NumberFormatException();
							}
						}
						catch(NumberFormatException e){
							JOptionPane.showMessageDialog(
								dialog,
								Properties.getInstance().getValue(ErrorType.WEIGHTFORMAT),
								Properties.getInstance().getValue(ColumnType.WEIGHTKG),
								JOptionPane.ERROR_MESSAGE
							);
							throw e;
						}
						try{
							repeats = Integer.valueOf(labeledTextField2.getTextField().getText());
							if(repeats.intValue() < 1){
								throw new NumberFormatException();
							}
						}
						catch(NumberFormatException e){
							JOptionPane.showMessageDialog(
								dialog,
								Properties.getInstance().getValue(ErrorType.REPEATSFORMAT),
								Properties.getInstance().getValue(ColumnType.REPEATS),
								JOptionPane.ERROR_MESSAGE
							);
							throw e;
						}
						returnValue = OK_OPTION;
						close();
					}
					catch(Exception e){
						weight = oldWeight;
						repeats = oldRepeats;
					}
				}
			}
		);
		cancelButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent actionEvent){
					returnValue = CANCEL_OPTION;
					close();
				}
			}
		);

		JPanel mainPanel = JPanelBuilder.createBoxLayoutY();
		mainPanel.add(labeledTextField1);
		mainPanel.add(labeledTextField2);
		mainPanel.add(buttonPanel);

		dialog.getContentPane().add(mainPanel);
		dialog.pack();
		dialog.setLocationRelativeTo(parent);
		return dialog;
	}
	public BigDecimal getWeight(){
		return weight;
	}
	public void setWeight(final BigDecimal weight){
		this.weight = weight;
	}
	public Integer getRepeats(){
		return repeats;
	}
	public void setRepeats(final Integer repeats){
		this.repeats = repeats;
	}
}
