package ap.filefilter;

import java.io.File;

import org.apache.commons.io.filefilter.IOFileFilter;

public class NoRecursiveFilter{
	private static IOFileFilter fileFilter;
	static{
		fileFilter = new IOFileFilter(){
			public boolean accept(File file){
				return false;
			}
			public boolean accept(File dir, String name){
				return false;
			}
		};
	}
	public static IOFileFilter getFileFilter(){
		return fileFilter;
	}
}
