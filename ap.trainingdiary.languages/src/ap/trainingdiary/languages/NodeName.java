package ap.trainingdiary.languages;

import java.util.Collection;

import ap.util.property.PropertyPath;

public enum NodeName{
	COMMENTS(BasePropertyPaths.NODENAME.createNewPropertyPath(NodeKeys.COMMENTS), NodeKeys.COMMENTS),
	TEMPLATES(BasePropertyPaths.NODENAME.createNewPropertyPath(NodeKeys.TEMPLATES), NodeKeys.TEMPLATES),
	TRAININGS(BasePropertyPaths.NODENAME.createNewPropertyPath(NodeKeys.TRAININGS), NodeKeys.TRAININGS);
	
	private final PropertyPath propertyPath;
	private final String key;

	private NodeName(final PropertyPath propertyPath, final String key) {
		this.propertyPath = propertyPath;
		this.key = key;
	}
	public PropertyPath getPropertyPath(){
		return propertyPath;
	}
	public String getKey(){
		return key;
	}
	public static NodeName getNodeName(final int index)throws IndexOutOfBoundsException{
		NodeName[] nodeName = values();
		if(index < 0 || index >= nodeName.length){
			throw new IndexOutOfBoundsException();
		}
		return nodeName[index];
	}
}
