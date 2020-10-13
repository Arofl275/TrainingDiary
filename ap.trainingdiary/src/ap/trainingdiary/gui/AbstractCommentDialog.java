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
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.math.BigDecimal;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jdatepicker.JDatePanel;

import ap.trainingdiary.languages.Button;
import ap.trainingdiary.languages.ColumnType;
import ap.trainingdiary.languages.ErrorType;
import ap.trainingdiary.languages.Message;
import ap.trainingdiary.languages.Properties;
import ap.trainingdiary.languages.Title;
import ap.trainingdiary.languages.TrainingType;
import ap.trainingdiary.type.Comment;
import ap.trainingdiary.util.CheckBigDecimal;

import ap.swing.JPanelBuilder;

public abstract class AbstractCommentDialog extends AbstractOkCancelDialog{
	protected LabeledTextField labeledTextField;
	protected JEditorPane textPane;
	protected TreeSet<String> commentNameSet;
	
	protected AbstractCommentDialog(final Window parent, final String title){
		super(parent, title);
		textPane = new JEditorPane();
		labeledTextField = LabeledTextFieldBuilder.nameOfComment();
		commentNameSet = new TreeSet<String>();
		okButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent actionEvent){
					String commentName = getCommentName();
					if(commentNameSet.contains(commentName)){
						String messageFormat = Properties.getInstance().getValue(Message.COMMENTEXISTS);
						int n = JOptionPaneBuilder.showYesNoDialog(
							dialog,
							String.format(messageFormat, commentName),
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
		JPanel textPanel = new JPanel();
		JScrollPane textScrollPane = new JScrollPane(textPane);
		textScrollPane.setPreferredSize(new Dimension(width, relHeight * 3));
		textPanel.add(textScrollPane);
		
		JPanel mainPanel = JPanelBuilder.createBoxLayoutY();
		mainPanel.add(labeledTextField);
		mainPanel.add(textPanel);
		mainPanel.add(buttonPanel);

		dialog.getContentPane().add(mainPanel);
		dialog.pack();
		dialog.setLocationRelativeTo(parent);
		return dialog;
	}
	private String getCommentName(){
		return labeledTextField.getTextField().getText();
	}
	private void setCommentName(String commentName){
		labeledTextField.getTextField().setText(commentName);
	}
	public Comment getComment(){
		return new Comment(
			getCommentName(),
			textPane.getText()
		);
	}
	public void setComment(Comment comment){
		setCommentName(comment.getName());
		textPane.setText(comment.getText());
	}
	public void setCommentNameSet(final Collection<String> collection){
		commentNameSet.clear();
		commentNameSet.addAll(collection);
	}
}
