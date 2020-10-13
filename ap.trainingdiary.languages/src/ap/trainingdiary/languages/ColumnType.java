package ap.trainingdiary.languages;

import java.util.Collection;

import ap.util.property.PropertyPath;

public enum ColumnType{
	DONE(BasePropertyPaths.COLUMN.createNewPropertyPath(ColumnKeys.DONE), ColumnKeys.DONE),
	REPEATS(BasePropertyPaths.COLUMN.createNewPropertyPath(ColumnKeys.REPEATS), ColumnKeys.REPEATS),
	WEIGHTKG(BasePropertyPaths.COLUMN.createNewPropertyPath(ColumnKeys.WEIGHTKG), ColumnKeys.WEIGHTKG),
	WEIGHTLB(BasePropertyPaths.COLUMN.createNewPropertyPath(ColumnKeys.WEIGHTLB), ColumnKeys.WEIGHTLB),
	NAMEOFCOMMENT(BasePropertyPaths.COLUMN.createNewPropertyPath(ColumnKeys.NAMEOFCOMMENT), ColumnKeys.NAMEOFCOMMENT),
	NAMEOFCOMMENTCOLUMNS(BasePropertyPaths.COLUMN.createNewPropertyPath(ColumnKeys.NAMEOFCOMMENTCOLUMNS), ColumnKeys.NAMEOFCOMMENTCOLUMNS),
	NAMEOFTEMPLATE(BasePropertyPaths.COLUMN.createNewPropertyPath(ColumnKeys.NAMEOFTEMPLATE), ColumnKeys.NAMEOFTEMPLATE),
	NAMEOFTEMPLATECOLUMNS(BasePropertyPaths.COLUMN.createNewPropertyPath(ColumnKeys.NAMEOFTEMPLATECOLUMNS), ColumnKeys.NAMEOFTEMPLATECOLUMNS);
	
	private final PropertyPath propertyPath;
	private final String key;

	private ColumnType(final PropertyPath propertyPath, final String key) {
		this.propertyPath = propertyPath;
		this.key = key;
	}
	public PropertyPath getPropertyPath(){
		return propertyPath;
	}
	public String getKey(){
		return key;
	}
	public static ColumnType getColumnType(final int index)throws IndexOutOfBoundsException{
		ColumnType[] columnType = values();
		if(index < 0 || index >= columnType.length){
			throw new IndexOutOfBoundsException();
		}
		return columnType[index];
	}
}
