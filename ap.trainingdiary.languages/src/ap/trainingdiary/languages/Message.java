package ap.trainingdiary.languages;

import java.util.Collection;

import ap.util.property.PropertyPath;

public enum Message{
	COMMENTEXISTS(BasePropertyPaths.MESSAGE.createNewPropertyPath(MessageKeys.COMMENTEXISTS), MessageKeys.COMMENTEXISTS),
	TEMPLATEEXISTS(BasePropertyPaths.MESSAGE.createNewPropertyPath(MessageKeys.TEMPLATEEXISTS), MessageKeys.TEMPLATEEXISTS),
	TRAININGEXISTS(BasePropertyPaths.MESSAGE.createNewPropertyPath(MessageKeys.TRAININGEXISTS), MessageKeys.TRAININGEXISTS);
	
	private final PropertyPath propertyPath;
	private final String key;

	private Message(final PropertyPath propertyPath, final String key) {
		this.propertyPath = propertyPath;
		this.key = key;
	}
	public PropertyPath getPropertyPath(){
		return propertyPath;
	}
	public String getKey(){
		return key;
	}
	public static Message getMessage(final int index)throws IndexOutOfBoundsException{
		Message[] message = values();
		if(index < 0 || index >= message.length){
			throw new IndexOutOfBoundsException();
		}
		return message[index];
	}
}
