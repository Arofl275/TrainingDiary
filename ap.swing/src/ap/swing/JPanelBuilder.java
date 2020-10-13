package ap.swing;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class JPanelBuilder{
	public static JPanel createBoxLayoutY(){
		JPanel panel = new JPanel();
		panel.setLayout(
			new BoxLayout(panel, BoxLayout.Y_AXIS)
		);
		return panel;
	}
	public static JPanel createFlowLayoutLeft(){
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		return panel;
	}
	public static JPanel createFlowLayoutRight(){
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		return panel;
	}
	public static JPanel createGridLayout(final int rows, final int cols){
		JPanel panel = new JPanel(new GridLayout(rows, cols));
		return panel;
	}
}
