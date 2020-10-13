package ap.trainingdiary;
import javax.swing.SwingUtilities;
import java.awt.HeadlessException;
import ap.trainingdiary.gui.MainFrame;
import java.util.Locale;
import java.awt.Color;
import java.awt.Rectangle;
import org.jdatepicker.ComponentColorDefaults;

import ap.trainingdiary.screencapture.ScreenSaver;

import java.io.File;
import java.io.IOException;
import java.awt.AWTException;
import java.awt.Robot;
public class Main{
	private static Thread t = null;
	public static void main(String[] args){
		/*
		try{
			String current = new java.io.File( "." ).getCanonicalPath();
			System.out.println("Current dir:"+current);
			String currentDir = System.getProperty("user.dir");
			System.out.println("Current dir using System:" +currentDir);
		}
		catch(java.io.IOException e){
			e.printStackTrace();
		}
		*/
		// Locale.setDefault(new Locale("ru", "RU"));
		// Locale.setDefault(new Locale("de", "DE"));
		Locale.setDefault(new Locale("us", "US"));
		ComponentColorDefaults.getInstance().setColor(ComponentColorDefaults.Key.BG_MONTH_SELECTOR, Color.GRAY);
		ComponentColorDefaults.getInstance().setColor(ComponentColorDefaults.Key.FG_MONTH_SELECTOR, Color.BLACK);
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					/*
					try{
						Robot r = new Robot();
						t = new Thread(new ScreenSaver(0, 60, 5, new File("."), new Rectangle(0, 0, 1920, 1080), r));
						t.start();
					}
					catch(AWTException awte){
						awte.printStackTrace();
						System.exit(1);
					}
					*/
					try{
						MainFrame.show(800, 800);
					}
					catch(HeadlessException hle){
						hle.printStackTrace();
					}
					catch(Exception hle){
						hle.printStackTrace();
					}
				}
			}
		);
	}
}
