package ap.util.property;

import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.StringJoiner;

public class PropertyPath{
	private static final String emptyString = "";
	private static final String sNull = "null";
	private static final String sEmpty = "empty";
	private static final String errorMessageFormat = "The path item%s is %s.";
	private static final String positionFormat = "at position %d";
	private final List<String> pathList;
	private PropertyPath(final List<String> pathList){
		this.pathList = pathList;
	}
	private PropertyPath(){
		this(new LinkedList<String>());
	}
	public List<String> getPathList(){
		return new LinkedList<String>(pathList);
	}
	private void add(final String key){
		pathList.add(key);
	}
	private static NullPointerException getNullPointerException(){
		return new NullPointerException(String.format(errorMessageFormat, emptyString, sNull));
	}
	private static NullPointerException getNullPointerException(final int position){
		return new NullPointerException(String.format(errorMessageFormat, String.format(positionFormat, position), sNull));
	}
	private static IllegalArgumentException getIllegalArgumentException(){
		return new IllegalArgumentException(String.format(errorMessageFormat, emptyString, sEmpty));
	}
	private static IllegalArgumentException getIllegalArgumentException(final int position){
		return new IllegalArgumentException(String.format(errorMessageFormat, String.format(positionFormat, position), sEmpty));
	}
	private static void check(final String pathItem) throws NullPointerException, IllegalArgumentException{
		if(pathItem == null){
			throw getNullPointerException();
		}
		else if(pathItem.isEmpty()){
			throw getIllegalArgumentException();
		}
	}
	private static void check(final String pathItem, final int position) throws NullPointerException, IllegalArgumentException{
		if(pathItem == null){
			throw getNullPointerException(position);
		}
		else if(pathItem.isEmpty()){
			throw getIllegalArgumentException(position);
		}
	}
	public PropertyPath createNewPropertyPath(final String pathItem) throws NullPointerException, IllegalArgumentException{
		if(pathItem == null){
			new NullPointerException();
		}
		check(pathItem);
		PropertyPath propertyPath = new PropertyPath(getPathList());
		propertyPath.add(pathItem);
		return propertyPath;
	}
	public static PropertyPath create(final Collection<String> pathCollection) throws NullPointerException, IllegalArgumentException{
		if(pathCollection == null){
			new NullPointerException();
		}
		int position = 0;
		for(String pathItem: pathCollection){
			check(pathItem, position++);
		}
		position = 0;
		PropertyPath propertyPath = new PropertyPath();
		for(String pathItem: pathCollection){
			propertyPath.add(pathItem);
		}
		return propertyPath;
	}
	public static PropertyPath create(final String[] pathArray) throws NullPointerException, IllegalArgumentException{
		if(pathArray == null){
			new NullPointerException();
		}
		int position = 0;
		for(String pathItem: pathArray){
			check(pathItem, position++);
		}
		position = 0;
		PropertyPath propertyPath = new PropertyPath();
		for(String pathItem: pathArray){
			propertyPath.add(pathItem);
		}
		return propertyPath;
	}
	public static PropertyPath create(final String pathItem) throws NullPointerException, IllegalArgumentException{
		if(pathItem == null){
			new NullPointerException();
		}
		check(pathItem);
		PropertyPath propertyPath = new PropertyPath();
		propertyPath.add(pathItem);
		return propertyPath;
	}
	public String toString(){
		StringJoiner stringJoiner = new StringJoiner(".");
		for(String pathItem: pathList){
			stringJoiner.add(pathItem);
		}
		return stringJoiner.toString();
	}
}
