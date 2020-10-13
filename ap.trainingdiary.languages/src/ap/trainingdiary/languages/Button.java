package ap.trainingdiary.languages;

import java.util.Collection;

import ap.util.property.PropertyPath;

public enum Button{
	CANCEL(BasePropertyPaths.BUTTON.createNewPropertyPath(ButtonKeys.CANCEL), ButtonKeys.CANCEL),
	NO(BasePropertyPaths.BUTTON.createNewPropertyPath(ButtonKeys.NO), ButtonKeys.NO),
	OK(BasePropertyPaths.BUTTON.createNewPropertyPath(ButtonKeys.OK), ButtonKeys.OK),
	YES(BasePropertyPaths.BUTTON.createNewPropertyPath(ButtonKeys.YES), ButtonKeys.YES);
	
	private final PropertyPath propertyPath;
	private final String key;

	private Button(final PropertyPath propertyPath, final String key) {
		this.propertyPath = propertyPath;
		this.key = key;
	}
	public PropertyPath getPropertyPath(){
		return propertyPath;
	}
	public String getKey(){
		return key;
	}
	public static Button getButton(final int index)throws IndexOutOfBoundsException{
		Button[] buttons = values();
		if(index < 0 || index >= buttons.length){
			throw new IndexOutOfBoundsException();
		}
		return buttons[index];
	}
}
