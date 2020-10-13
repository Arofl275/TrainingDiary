package ap.trainingdiary.type;
public abstract class AbstractIndexNodeUserObject extends AbctractComparable{
	protected final Integer index;
	public AbstractIndexNodeUserObject(final NodeType nodeType, final Integer index){
		super(nodeType);
		this.index = index;
	}
	public int compareTo(final AbctractComparable other){
		final int cmp = super.compareTo(other);
		if(cmp == 0){
			AbstractIndexNodeUserObject abstractIndexNodeUserObjectOther = (AbstractIndexNodeUserObject) other;
			return index.compareTo(abstractIndexNodeUserObjectOther.index);
		}
		else{
			return cmp;
		}
	}
	public String toString(){
		return index.toString();
	}
	public Integer getIndex(){
		return index;
	}
}
