package ap.trainingdiary.gui;

import java.awt.Dialog;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import ap.swing.JPanelBuilder;

public abstract class AbstractOkCancelDialog{
	public static final int ERROR_OPTION = -1;
	public static final int CANCEL_OPTION = 0;
	public static final int OK_OPTION = 1;
	protected int returnValue;
	protected final Window parent;
	protected final String title;
	protected JButton okButton;
	protected JButton cancelButton;
	protected JPanel buttonPanel;
	protected JDialog dialog;
	protected AbstractOkCancelDialog(final Window parent, final String title){
		this.parent = parent;
		this.title = title;
		returnValue = ERROR_OPTION;
		init();
	}
	private void init(){
		okButton = JButtonBuilder.ok();
		cancelButton = JButtonBuilder.cancel();
		buttonPanel = JPanelBuilder.createFlowLayoutRight();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
	}
	protected abstract JDialog createDialog() throws HeadlessException;
	public int show() throws HeadlessException{
		returnValue = ERROR_OPTION;
		dialog = createDialog();
		dialog.setVisible(true);
		dispose();
		init();
		return returnValue;
	}
	protected void dispose(){
		dialog.getContentPane().removeAll();
		dialog.dispose();
		dialog = null;
	}
	protected void close(){
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(
			new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING)
		);
	}
}
