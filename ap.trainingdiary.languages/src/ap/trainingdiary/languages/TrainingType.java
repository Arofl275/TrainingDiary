package ap.trainingdiary.languages;

import java.util.Collection;

import ap.util.property.PropertyPath;

public enum TrainingType{
	BENCHPRESS(BasePropertyPaths.TRAININGTYPE.createNewPropertyPath(TrainingTypeKeys.BENCHPRESS), TrainingTypeKeys.BENCHPRESS),
	DEADLIFT(BasePropertyPaths.TRAININGTYPE.createNewPropertyPath(TrainingTypeKeys.DEADLIFT), TrainingTypeKeys.DEADLIFT),
	SQUATS(BasePropertyPaths.TRAININGTYPE.createNewPropertyPath(TrainingTypeKeys.SQUATS), TrainingTypeKeys.SQUATS);
	
	private final PropertyPath propertyPath;
	private final String key;

	private TrainingType(final PropertyPath propertyPath, final String key) {
		this.propertyPath = propertyPath;
		this.key = key;
	}
	public PropertyPath getPropertyPath(){
		return propertyPath;
	}
	public String getKey(){
		return key;
	}
	public static TrainingType getTrainingType(final int index)throws IndexOutOfBoundsException{
		TrainingType[] trainingType = values();
		if(index < 0 || index >= trainingType.length){
			throw new IndexOutOfBoundsException();
		}
		return trainingType[index];
	}
}
