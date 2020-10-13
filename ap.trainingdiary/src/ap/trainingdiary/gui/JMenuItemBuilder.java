package ap.trainingdiary.gui;

import javax.swing.JMenuItem;

import ap.trainingdiary.languages.Menu;
import ap.trainingdiary.languages.Properties;

public class JMenuItemBuilder{
	private static JMenuItem createMenuItem(final Menu menu){
		return new JMenuItem(Properties.getInstance().getValue(menu));
	}
	public static JMenuItem addComment(){
		return createMenuItem(Menu.ADDCOMMENT);
	}
	public static JMenuItem addRow(){
		return createMenuItem(Menu.ADDROW);
	}
	public static JMenuItem addTemplate(){
		return createMenuItem(Menu.ADDTEMPLATE);
	}
	public static JMenuItem addTraining(){
		return createMenuItem(Menu.ADDTRAINING);
	}
	public static JMenuItem deselect(){
		return createMenuItem(Menu.DESELECT);
	}
	public static JMenuItem editComment(){
		return createMenuItem(Menu.EDITCOMMENT);
	}
	public static JMenuItem editRow(){
		return createMenuItem(Menu.EDITROW);
	}
	public static JMenuItem editTemplate(){
		return createMenuItem(Menu.EDITTEMPLATE);
	}
	public static JMenuItem editTraining(){
		return createMenuItem(Menu.EDITTRAINING);
	}
	public static JMenuItem moveDOWN(){
		return createMenuItem(Menu.MOVEDOWN);
	}
	public static JMenuItem moveUP(){
		return createMenuItem(Menu.MOVEUP);
	}
	public static JMenuItem remove(){
		return createMenuItem(Menu.REMOVE);
	}
	public static JMenuItem removeAll(){
		return createMenuItem(Menu.REMOVEALL);
	}
	public static JMenuItem printToPDF(){
		return createMenuItem(Menu.PRINTTOPDF);
	}
}
