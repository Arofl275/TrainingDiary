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

import ap.trainingdiary.image.TrainingImages;
import ap.trainingdiary.type.Training;
import ap.trainingdiary.type.TrainingPath;
import ap.trainingdiary.type.TrainingRow;

import ap.swing.JPanelBuilder;
import org.jdatepicker.JDatePanel;
public abstract class AbstractTrainingDialog extends AbstractOkCancelDialog{
	protected final JDatePanel datePanel;
	protected final JComboBox<String> comboBox;
	protected final JLabel imageBox;
	protected final JEditorPane textPane;
	protected final List<TrainingRow> trainingRowList;
	protected final TreeSet<TrainingPath> trainingPathSet;
	protected AbstractTrainingDialog(final Window parent, final String title){
		super(parent, title);
		okButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent actionEvent){
					TrainingPath path = new TrainingPath(
						getYear(),
						getMonth(),
						getDay(),
						getTrainingIndex()
					);
					Calendar calendar = Calendar.getInstance();
					calendar.set(getYear(), getMonth(), getDay(), 0, 0);
					String date = new SimpleDateFormat(Properties.getInstance().getValue(DateFormat.DATEFORMAT)).format(calendar.getTime());
					String traningType = Properties.getInstance().getValue(TrainingType.getTrainingType(getTrainingIndex()));
					String messageFormat = Properties.getInstance().getValue(Message.TRAININGEXISTS);
					if(trainingPathSet.contains(path)){
						int n = JOptionPaneBuilder.showYesNoDialog(
							dialog,
							String.format(messageFormat, traningType, date),
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
		datePanel = new JDatePanel();
		datePanel.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					datePanel.getModel().setSelected(true);
				}
			}
		);
		comboBox = new JComboBox<String>(
			new String[]{
				Properties.getInstance().getValue(TrainingType.BENCHPRESS),
				Properties.getInstance().getValue(TrainingType.DEADLIFT),
				Properties.getInstance().getValue(TrainingType.SQUATS)
			}
		);
		comboBox.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent actionEvent){
					imageBox.setIcon(TrainingImages.getInstance().getImageIcon(comboBox.getSelectedIndex()));
				}
			}
		);
		imageBox = new JLabel();
		imageBox.setIcon(TrainingImages.getInstance().getImageIcon(comboBox.getSelectedIndex()));
		textPane = new JEditorPane();
		trainingRowList = new ArrayList<TrainingRow>();
		trainingPathSet = new TreeSet<TrainingPath>();
	}
	protected JDialog createDialog() throws HeadlessException{
		datePanel.getModel().setSelected(true);

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
		
		JPanel comboImagePanel = JPanelBuilder.createBoxLayoutY();
		JPanel imageBoxPanel = JPanelBuilder.createFlowLayoutLeft();
		imageBoxPanel.add(imageBox);
		comboImagePanel.add(imageBoxPanel);
		comboImagePanel.add(comboBox);
		
		JPanel calenderPanel = JPanelBuilder.createFlowLayoutLeft();

		calenderPanel.add(datePanel);
		calenderPanel.add(comboImagePanel);
		
		JPanel textPanel = new JPanel();
		JScrollPane textScrollPane = new JScrollPane(textPane);
		textScrollPane.setPreferredSize(new Dimension(width, relHeight * 3));
		textPanel.add(textScrollPane);
		
		JPanel mainPanel = JPanelBuilder.createBoxLayoutY();
		mainPanel.add(calenderPanel);
		mainPanel.add(tablePanel);
		mainPanel.add(textPanel);
		mainPanel.add(buttonPanel);

		dialog.getContentPane().add(mainPanel);
		dialog.pack();
		dialog.setLocationRelativeTo(parent);
		dialog.setResizable(false);

		return dialog;
	}
	private int getYear(){
		return datePanel.getModel().getYear();
	}
	private void setYear(final int year){
		datePanel.getModel().setYear(year);
	}
	private int getMonth(){
		return datePanel.getModel().getMonth();
	}
	private void setMonth(final int month){
		datePanel.getModel().setMonth(month);
	}
	private int getDay(){
		return datePanel.getModel().getDay();
	}
	private void setDay(final int day){
		datePanel.getModel().setDay(day);
	}
	private int getTrainingIndex(){
		return comboBox.getSelectedIndex();
	}
	private void setTrainingIndex(final int index){
		comboBox.setSelectedIndex(index);
	}
	private void setDate(final int day, final int month, final int year){
		setDay(day);
		setMonth(month);
		setYear(year);
	}
	private List<TrainingRow> getTrainingData(){
		return new ArrayList<TrainingRow>(trainingRowList);
	}
	public void setTrainingData(final Collection<TrainingRow> collection){
		trainingRowList.clear();
		trainingRowList.addAll(collection);
	}
	private String getComment(){
		return textPane.getText();
	}
	public void setComment(String text){
		textPane.setText(text);
	}
	public Training getTraining(){
		return Training.create(getDay(), getMonth(), getYear(), getTrainingIndex(), getTrainingData(), getComment());
	}
	public void setTraining(final Training training){
		setDay(training.getDay());
		setMonth(training.getMonth());
		setYear(training.getYear());
		setTrainingIndex(training.getTrainingIndex());
		setTrainingData(training.getTrainingData());
		setComment(training.getComment());
	}
	public void setTrainingPathSet(final Collection<TrainingPath> collection){
		trainingPathSet.clear();
		trainingPathSet.addAll(collection);
	}
}
