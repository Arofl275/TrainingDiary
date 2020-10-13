package ap.trainingdiary.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.jdatepicker.DatePicker;
import org.jdatepicker.JDatePicker;

import ap.trainingdiary.languages.Properties;
import ap.trainingdiary.languages.Title;

public class EditTrainingDialog extends AbstractTrainingDialog{
	public EditTrainingDialog(final Window parent){
		super(parent, Properties.getInstance().getValue(Title.EDITTRAINING));
		datePanel.setEnabled(false);
		comboBox.setEnabled(false);
	}
}
