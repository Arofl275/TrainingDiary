package ap.trainingdiary.languages;

import java.util.Collection;

import ap.util.property.PropertyPath;

public enum Menu{
	ADDCOMMENT(BasePropertyPaths.MENU.createNewPropertyPath(MenuKeys.ADDCOMMENT), MenuKeys.ADDCOMMENT),
	ADDROW(BasePropertyPaths.MENU.createNewPropertyPath(MenuKeys.ADDROW), MenuKeys.ADDROW),
	ADDTEMPLATE(BasePropertyPaths.MENU.createNewPropertyPath(MenuKeys.ADDTEMPLATE), MenuKeys.ADDTEMPLATE),
	ADDTRAINING(BasePropertyPaths.MENU.createNewPropertyPath(MenuKeys.ADDTRAINING), MenuKeys.ADDTRAINING),
	DESELECT(BasePropertyPaths.MENU.createNewPropertyPath(MenuKeys.DESELECT), MenuKeys.DESELECT),
	EDITCOMMENT(BasePropertyPaths.MENU.createNewPropertyPath(MenuKeys.EDITCOMMENT), MenuKeys.EDITCOMMENT),
	EDITROW(BasePropertyPaths.MENU.createNewPropertyPath(MenuKeys.EDITROW), MenuKeys.EDITROW),
	EDITTEMPLATE(BasePropertyPaths.MENU.createNewPropertyPath(MenuKeys.EDITTEMPLATE), MenuKeys.EDITTEMPLATE),
	EDITTRAINING(BasePropertyPaths.MENU.createNewPropertyPath(MenuKeys.EDITTRAINING), MenuKeys.EDITTRAINING),
	MOVEDOWN(BasePropertyPaths.MENU.createNewPropertyPath(MenuKeys.MOVEDOWN), MenuKeys.MOVEDOWN),
	MOVEUP(BasePropertyPaths.MENU.createNewPropertyPath(MenuKeys.MOVEUP), MenuKeys.MOVEUP),
	REMOVE(BasePropertyPaths.MENU.createNewPropertyPath(MenuKeys.REMOVE), MenuKeys.REMOVE),
	REMOVEALL(BasePropertyPaths.MENU.createNewPropertyPath(MenuKeys.REMOVEALL), MenuKeys.REMOVEALL),
	PRINTTOPDF(BasePropertyPaths.MENU.createNewPropertyPath(MenuKeys.PRINTTOPDF), MenuKeys.PRINTTOPDF);
	
	private final PropertyPath propertyPath;
	private final String key;

	private Menu(final PropertyPath propertyPath, final String key) {
		this.propertyPath = propertyPath;
		this.key = key;
	}
	public PropertyPath getPropertyPath(){
		return propertyPath;
	}
	public String getKey(){
		return key;
	}
	public static Menu getMenu(final int index)throws IndexOutOfBoundsException{
		Menu[] menu = values();
		if(index < 0 || index >= menu.length){
			throw new IndexOutOfBoundsException();
		}
		return menu[index];
	}
}
