package ap.trainingdiary.languages;

import java.io.IOException;

import ap.util.property.PropertyNode;
import ap.util.property.PropertyNotFoundException;
import ap.util.property.PropertyPath;
import ap.util.property.PropertyTree;
import ap.util.property.PropertyTreeDeserializer;
import ap.util.property.PropertyTreeSerializer;

import ap.trainingdiary.languages.Button;
import ap.trainingdiary.languages.ColumnType;
import ap.trainingdiary.languages.DateFormat;
import ap.trainingdiary.languages.ErrorType;
import ap.trainingdiary.languages.Message;
import ap.trainingdiary.languages.Menu;
import ap.trainingdiary.languages.Month;
import ap.trainingdiary.languages.NodeName;
import ap.trainingdiary.languages.Title;

public class Properties{
	private static Properties properties;
	static{
		properties = new Properties();
	}
	public static Properties getInstance(){
		return properties;
	}
	private PropertyTree propertyTree;
	public Properties(){
		try{
			this.propertyTree = PropertyTreeDeserializer.load("ap/trainingdiary/languages", "Properties.json");
		}
		catch(IOException e){
			propertyTree = new PropertyTree();
		}	
	}
	public String getValue(final Button button){
		try{
			return propertyTree.getValue(button.getPropertyPath());
		}
		catch(PropertyNotFoundException e){
			return button.getKey();
		}
	}
	public String getValue(final ColumnType columnType){
		try{
			return propertyTree.getValue(columnType.getPropertyPath());
		}
		catch(PropertyNotFoundException e){
			return columnType.getKey();
		}
	}
	public String getValue(final DateFormat dateFormat){
		try{
			return propertyTree.getValue(dateFormat.getPropertyPath());
		}
		catch(PropertyNotFoundException e){
			return dateFormat.getKey();
		}
	}
	public String getValue(final ErrorType errorType){
		try{
			return propertyTree.getValue(errorType.getPropertyPath());
		}
		catch(PropertyNotFoundException e){
			return errorType.getKey();
		}
	}
	public String getValue(final Menu menu){
		try{
			return propertyTree.getValue(menu.getPropertyPath());
		}
		catch(PropertyNotFoundException e){
			return menu.getKey();
		}
	}
	public String getValue(final Message message){
		try{
			return propertyTree.getValue(message.getPropertyPath());
		}
		catch(PropertyNotFoundException e){
			return message.getKey();
		}
	}
	public String getValue(final Month month){
		try{
			return propertyTree.getValue(month.getPropertyPath());
		}
		catch(PropertyNotFoundException e){
			return month.getKey();
		}
	}
	public String getValue(final NodeName nodeName){
		try{
			return propertyTree.getValue(nodeName.getPropertyPath());
		}
		catch(PropertyNotFoundException e){
			return nodeName.getKey();
		}
	}
	public String getValue(final Title title){
		try{
			return propertyTree.getValue(title.getPropertyPath());
		}
		catch(PropertyNotFoundException e){
			return title.getKey();
		}
	}
	public String getValue(final TrainingType trainingType){
		try{
			return propertyTree.getValue(trainingType.getPropertyPath());
		}
		catch(PropertyNotFoundException e){
			return trainingType.getKey();
		}
	}
}
