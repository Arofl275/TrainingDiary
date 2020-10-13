package ap.swing;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class JMenuItemBuilder{
	public static JMenuItem addAction(
		final JComponent component,
		final JMenuItem menuItem,
		final KeyStroke keyStroke,
		final Action action,
		final String actionName
	){
		component.getInputMap().put(keyStroke, actionName);
		component.getActionMap().put(actionName, action);
		menuItem.setAccelerator(keyStroke);
		menuItem.addActionListener(action);
		return menuItem;
	}
}
