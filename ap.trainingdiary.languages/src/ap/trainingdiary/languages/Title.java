package ap.trainingdiary.languages;

import java.util.Collection;

import ap.util.property.PropertyPath;

public enum Title{
	ADDCOMMENT(BasePropertyPaths.TITLE.createNewPropertyPath(TitleKeys.ADDCOMMENT), TitleKeys.ADDCOMMENT),
	ADDROW(BasePropertyPaths.TITLE.createNewPropertyPath(TitleKeys.ADDROW), TitleKeys.ADDROW),
	ADDTEMPLATE(BasePropertyPaths.TITLE.createNewPropertyPath(TitleKeys.ADDTEMPLATE), TitleKeys.ADDTEMPLATE),
	ADDTRAINING(BasePropertyPaths.TITLE.createNewPropertyPath(TitleKeys.ADDTRAINING), TitleKeys.ADDTRAINING),
	EDITCOMMENT(BasePropertyPaths.TITLE.createNewPropertyPath(TitleKeys.EDITCOMMENT), TitleKeys.EDITCOMMENT),
	EDITROW(BasePropertyPaths.TITLE.createNewPropertyPath(TitleKeys.EDITROW), TitleKeys.EDITROW),
	EDITTEMPLATE(BasePropertyPaths.TITLE.createNewPropertyPath(TitleKeys.EDITTEMPLATE), TitleKeys.EDITTEMPLATE),
	EDITTRAINING(BasePropertyPaths.TITLE.createNewPropertyPath(TitleKeys.EDITTRAINING), TitleKeys.EDITTRAINING),
	WARNING(BasePropertyPaths.TITLE.createNewPropertyPath(TitleKeys.WARNING), TitleKeys.WARNING),
	TRAININGDIARY(BasePropertyPaths.TITLE.createNewPropertyPath(TitleKeys.TRAININGDIARY), TitleKeys.TRAININGDIARY),
	SPECIFYAFILETOSAVE(BasePropertyPaths.TITLE.createNewPropertyPath(TitleKeys.SPECIFYAFILETOSAVE), TitleKeys.SPECIFYAFILETOSAVE);
	
	private final PropertyPath propertyPath;
	private final String key;

	private Title(final PropertyPath propertyPath, final String key) {
		this.propertyPath = propertyPath;
		this.key = key;
	}
	public PropertyPath getPropertyPath(){
		return propertyPath;
	}
	public String getKey(){
		return key;
	}
	public static Title getTitle(final int index)throws IndexOutOfBoundsException{
		Title[] titles = values();
		if(index < 0 || index >= titles.length){
			throw new IndexOutOfBoundsException();
		}
		return titles[index];
	}
}
