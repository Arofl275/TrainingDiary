package ap.util.property;

public class PropertyNotFoundException extends Exception{
	private static final String errorMessageFormat = "The property %s at position %d was not found.";
	public PropertyNotFoundException(final String property, final int position){
		super(String.format(errorMessageFormat, property, position));
	}
}
