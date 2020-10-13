package ap.trainingdiary.gui;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;
import ap.trainingdiary.languages.Button;
import ap.trainingdiary.languages.DateFormat;
import ap.trainingdiary.languages.Message;
import ap.trainingdiary.languages.Properties;
import ap.trainingdiary.languages.Title;
import ap.trainingdiary.languages.TrainingType;

import ap.trainingdiary.type.Training;
import ap.trainingdiary.type.TrainingPath;
import ap.trainingdiary.type.TrainingRow;

import ap.swing.JPanelBuilder;
import org.jdatepicker.JDatePanel;

import ap.trainingdiary.type.Template;

public abstract class AbstractTemplateDialog extends AbstractOkCancelDialog{
	protected boolean editModus = false;
	protected LabeledTextField labeledTextField;
	protected final List<TrainingRow> trainingRowList;
	protected TreeSet<String> templateNameSet;
	protected AbstractTemplateDialog(final Window parent, final String title){
		super(parent, title);
		labeledTextField = LabeledTextFieldBuilder.nameOfTemplate();
		trainingRowList = new ArrayList<TrainingRow>();
		templateNameSet = new TreeSet<String>();
		okButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent actionEvent){
					String templateName = getTemplateName();
					if(templateNameSet.contains(templateName)){
						String messageFormat = Properties.getInstance().getValue(Message.TEMPLATEEXISTS);
						int n = JOptionPaneBuilder.showYesNoDialog(
							dialog,
							String.format(messageFormat, templateName),
							Properties.getInstance().getValue(Title.WARNING)
						);
						if(n == 1){
							return;
						}
					}
					returnValue = OK_OPTION;
					close();
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
	}
	protected JDialog createDialog() throws HeadlessException{
		final int width = 550;
		final int relHeight = 50;
		JDialog dialog = new JDialog(parent, title, Dialog.ModalityType.APPLICATION_MODAL);

		JPanel tablePanel = new JPanel();
		TrainingTable trainingTable = TrainingTable.create(dialog, trainingRowList);
		trainingTable.addTableModelListener(
			new TableModelListener(){
				public void tableChanged(TableModelEvent e){
					setTrainingData(trainingTable.getTrainingData());
				}
			}
		);
		JScrollPane tablePane = trainingTable.getScrollPane();
		tablePane.setPreferredSize(new Dimension(width, relHeight * 4));
		tablePanel.add(tablePane);
		
		JPanel mainPanel = JPanelBuilder.createBoxLayoutY();
		mainPanel.add(labeledTextField);
		mainPanel.add(tablePanel);
		mainPanel.add(buttonPanel);

		dialog.getContentPane().add(mainPanel);
		dialog.pack();
		dialog.setLocationRelativeTo(parent);
		dialog.setResizable(false);

		return dialog;
	}
	private String getTemplateName(){
		return labeledTextField.getTextField().getText();
	}
	public void setTemplateName(String templateName){
		labeledTextField.getTextField().setText(templateName);
	}
	private List<TrainingRow> getTrainingData(){
		return new ArrayList<TrainingRow>(trainingRowList);
	}
	private void setTrainingData(final Collection<TrainingRow> collection){
		trainingRowList.clear();
		trainingRowList.addAll(collection);
	}
	public Template getTemplate(){
		return Template.create(
			getTemplateName(),
			getTrainingData()
		);
	}
	public void setTemplate(Template template){
		setTemplateName(template.getName());
		setTrainingData(template.getTrainingData());
	}
	public void setTemplateNameSet(final Collection<String> collection){
		templateNameSet.clear();
		templateNameSet.addAll(collection);
	}
}
