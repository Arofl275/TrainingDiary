package ap.trainingdiary.languages;

import java.util.Collection;

import ap.util.property.PropertyPath;

public enum Month{
	JANUARY(BasePropertyPaths.MONTH.createNewPropertyPath(MonthKeys.JANUARY), MonthKeys.JANUARY),
	FEBRUARY(BasePropertyPaths.MONTH.createNewPropertyPath(MonthKeys.FEBRUARY), MonthKeys.FEBRUARY),
	MARCH(BasePropertyPaths.MONTH.createNewPropertyPath(MonthKeys.MARCH), MonthKeys.MARCH),
	APRIL(BasePropertyPaths.MONTH.createNewPropertyPath(MonthKeys.APRIL), MonthKeys.APRIL),
	MAY(BasePropertyPaths.MONTH.createNewPropertyPath(MonthKeys.MAY), MonthKeys.MAY),
	JUNE(BasePropertyPaths.MONTH.createNewPropertyPath(MonthKeys.JUNE), MonthKeys.JUNE),
	JULY(BasePropertyPaths.MONTH.createNewPropertyPath(MonthKeys.JULY), MonthKeys.JULY),
	AUGUST(BasePropertyPaths.MONTH.createNewPropertyPath(MonthKeys.AUGUST), MonthKeys.AUGUST),
	SEPTEMBER(BasePropertyPaths.MONTH.createNewPropertyPath(MonthKeys.SEPTEMBER), MonthKeys.SEPTEMBER),
	OCTOBER(BasePropertyPaths.MONTH.createNewPropertyPath(MonthKeys.OCTOBER), MonthKeys.OCTOBER),
	NOVEMBER(BasePropertyPaths.MONTH.createNewPropertyPath(MonthKeys.NOVEMBER), MonthKeys.NOVEMBER),
	DECEMBER(BasePropertyPaths.MONTH.createNewPropertyPath(MonthKeys.DECEMBER), MonthKeys.DECEMBER);
	
	private final PropertyPath propertyPath;
	private final String key;

	private Month(final PropertyPath propertyPath, final String key){
		this.propertyPath = propertyPath;
		this.key = key;
	}
	public PropertyPath getPropertyPath(){
		return propertyPath;
	}
	public String getKey(){
		return key;
	}
	public static Month getMonth(final int index)throws IndexOutOfBoundsException{
		Month[] months = values();
		if(index < 0 || index >= months.length){
			throw new IndexOutOfBoundsException();
		}
		return months[index];
	}
}
