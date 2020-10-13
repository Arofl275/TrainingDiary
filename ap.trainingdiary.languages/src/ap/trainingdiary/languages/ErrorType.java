package ap.trainingdiary.languages;

import java.util.Collection;

import ap.util.property.PropertyPath;

public enum ErrorType{
	REPEATSFORMAT(BasePropertyPaths.ERROR.createNewPropertyPath(ErrorKeys.REPEATSFORMAT), ErrorKeys.REPEATSFORMAT),
	WEIGHTFORMAT(BasePropertyPaths.ERROR.createNewPropertyPath(ErrorKeys.WEIGHTFORMAT), ErrorKeys.WEIGHTFORMAT);
	
	private final PropertyPath propertyPath;
	private final String key;

	private ErrorType(final PropertyPath propertyPath, final String key) {
		this.propertyPath = propertyPath;
		this.key = key;
	}
	public PropertyPath getPropertyPath(){
		return propertyPath;
	}
	public String getKey(){
		return key;
	}
	public static ErrorType getErrorType(final int index)throws IndexOutOfBoundsException{
		ErrorType[] errorType = values();
		if(index < 0 || index >= errorType.length){
			throw new IndexOutOfBoundsException();
		}
		return errorType[index];
	}
}
