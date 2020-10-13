package ap.trainingdiary.type;

public class TrainingPath implements Comparable<TrainingPath>{
	private final Integer year;
	private final Integer month;
	private final Integer day;
	private final Integer training;
	public TrainingPath(
		final Integer year,
		final Integer month,
		final Integer day,
		final Integer training	
	){
		this.year = year;
		this.month = month;
		this.day = day;
		this.training = training;
	}
	private Integer[] getPath(){
		return new Integer[]{
			year,
			month,
			day,
			training
		};
	}
	public int compareTo(final TrainingPath other){
		Integer[] thisPath = getPath();
		Integer[] otherPath = other.getPath();
		final int last = thisPath.length - 1;
		for(int i = 0; i < last; i++){
			int cmp = thisPath[i].compareTo(otherPath[i]);
			if(cmp != 0){
				return cmp;
			}
		}
		return thisPath[last].compareTo(otherPath[last]);
	}
}
