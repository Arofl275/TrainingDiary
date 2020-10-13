package ap.trainingdiary.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class TrainingImages{
	private static final String pathFormat = "%s/%s";
	private static final String packageName = "ap/trainingdiary/images";
	private static final String[] imageName = new String[]{
		"benchpress.png",
		"deadlift.png",
		"squats.png"
	};
	private static TrainingImages instance;
	static{
		instance = new TrainingImages();
	}
	public static TrainingImages getInstance(){
		return instance;
	}
	private final ImageIcon[] imageIcon;
	private final byte[][] byteArray;
	private TrainingImages(){
		imageIcon = new ImageIcon[imageName.length];
		for(int i = 0; i < imageName.length; i++){
			try{
				imageIcon[i] = new ImageIcon(getBufferedImage(i));
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		byteArray = new byte[imageName.length][];
		for(int i = 0; i < imageName.length; i++){
			try{
				byteArray[i] = getBytes(i);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	private static String getPath(final int index){
		return String.format(pathFormat, packageName, imageName[index]);
	}
	private static InputStream getInputStream(final int index){
		return TrainingImages.class.getClassLoader().getResourceAsStream(getPath(index));
	}
	private static BufferedImage getBufferedImage(final int index)throws IOException{
		return ImageIO.read(getInputStream(index));
	}
	public ImageIcon getImageIcon(final int index){
		return imageIcon[index];
	}
	private static byte[] getBytes(final int index)throws IOException{
		InputStream is = getInputStream(index);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] buffer = new byte[256];
		for(int length = is.read(buffer); length != -1; length = is.read(buffer)){
			os.write(buffer, 0, length);
		}
		return os.toByteArray();
	}
	public byte[] getImageBytes(final int index){
		return byteArray[index];
	}	
}
