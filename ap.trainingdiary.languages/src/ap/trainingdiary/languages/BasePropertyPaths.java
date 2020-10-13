package ap.trainingdiary.languages;

import ap.util.property.PropertyPath;

class BasePropertyPaths{
	private static PropertyPath basePropertyPath = PropertyPath.create("TrainingDiary");
	public static PropertyPath create(final String pathItem){
		return basePropertyPath.createNewPropertyPath(pathItem);
	}
	public static final PropertyPath BUTTON = create("Button");
	public static final PropertyPath COLUMN = create("Column");
	public static final PropertyPath DATEFORMAT = create("DateFormat");
	public static final PropertyPath ERROR = create("Error");
	public static final PropertyPath MENU = create("Menu");
	public static final PropertyPath MESSAGE = create("Message");
	public static final PropertyPath MONTH = create("Month");
	public static final PropertyPath NODENAME = create("Nodename");
	public static final PropertyPath TITLE = create("Title");
	public static final PropertyPath TRAININGTYPE = create("TrainingType");
}
