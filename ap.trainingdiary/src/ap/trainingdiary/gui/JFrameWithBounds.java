package ap.trainingdiary.gui;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class JFrameWithBounds extends JFrame{
	private final int width;
	private final int height;
	JFrameWithBounds(final int width, int height){
		super();
		this.width = width;
		this.height = height;
	}
	JFrameWithBounds(final GraphicsConfiguration gc, final int width, int height){
		super(gc);
		this.width = width;
		this.height = height;
	}
	JFrameWithBounds(final String title, final int width, int height){
		super(title);
		this.width = width;
		this.height = height;
	}
	JFrameWithBounds(final String title, final GraphicsConfiguration gc, final int width, int height){
		super(title, gc);
		this.width = width;
		this.height = height;
	}
	public void setBounds()throws HeadlessException{
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		Rectangle screenBounds = gc.getBounds();
		setBounds(
			screenBounds.x + ((screenBounds.width - screenBounds.x - width) >> 1),
			screenBounds.y + ((screenBounds.height - screenBounds.y - height) >> 1),
			width,
			height
		);
	}
}
