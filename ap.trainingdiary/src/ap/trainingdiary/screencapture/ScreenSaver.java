package ap.trainingdiary.screencapture;

import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ScreenSaver implements Runnable{
	private int frameCounter;
	private final Delayer delayer;
	private final String format;
	private final File path;
	private final Rectangle rectangle;
	private final Robot robot;
	public ScreenSaver(
		final int frameCounter,
		final int fps,
		final int precision,
		final File path,
		final Rectangle rectangle,
		final Robot robot
	){
		this.frameCounter = frameCounter;
		this.delayer = new Delayer((int) (1000.0 / ((double) fps)));
		this.format = "image%0" + precision + "d.png";
		this.path = path;
		this.rectangle = rectangle;
		this.robot = robot;
	}
	private String getFileName(){
		return String.format(format, frameCounter);
	}
	private File getFile(){
		return new File(path, getFileName());
	}
	private void saveScreen(){
		try{
			BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
			ImageIO.write(bufferedImage, "png", getFile());
			delayer.delay();
			delayer.reset();
			frameCounter++;
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	public void run(){
		for(;;){
			saveScreen();
		}
	}
}
