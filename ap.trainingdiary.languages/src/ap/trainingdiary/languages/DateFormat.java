package ap.trainingdiary.languages;

import java.util.Collection;

import ap.util.property.PropertyPath;

public enum DateFormat{
	DATEFORMAT(BasePropertyPaths.DATEFORMAT.createNewPropertyPath(DateFormatKeys.DATEFORMAT), DateFormatKeys.DATEFORMAT);
	
	private final PropertyPath propertyPath;
	private final String key;

	private DateFormat(final PropertyPath propertyPath, final String key) {
		this.propertyPath = propertyPath;
		this.key = key;
	}
	public PropertyPath getPropertyPath(){
		return propertyPath;
	}
	public String getKey(){
		return key;
	}
	public static DateFormat getDateFormat(final int index)throws IndexOutOfBoundsException{
		DateFormat[] dateFormat = values();
		if(index < 0 || index >= dateFormat.length){
			throw new IndexOutOfBoundsException();
		}
		return dateFormat[index];
	}
}
