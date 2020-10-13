package ap.trainingdiary.filefilter;

import java.io.File;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.filefilter.IOFileFilter;

import ap.trainingdiary.pattern.TemplatePattern;

public class TemplatesIOFileFilter{
	private static IOFileFilter fileFilter;
	static{
		fileFilter = new IOFileFilter(){
			public boolean accept(File file){
				Matcher m = TemplatePattern.getInstance().matcher(file.getName());
				return m.matches(); 
			}
			public boolean accept(File dir, String name){
				Matcher m = TemplatePattern.getInstance().matcher(name);
				return m.matches(); 
			}
		};
	}
	public static IOFileFilter getFileFilter(){
		return fileFilter;
	}
}
