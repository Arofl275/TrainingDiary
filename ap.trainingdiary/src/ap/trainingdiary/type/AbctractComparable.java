package ap.trainingdiary.type;
public abstract class AbctractComparable implements Comparable<AbctractComparable>{
	protected final NodeType nodeType;
	public AbctractComparable(final NodeType nodeType){
		this.nodeType = nodeType;
	}
	public int compareTo(final AbctractComparable other){
		return nodeType.compareTo(other.nodeType);
	}
	public NodeType getNodeType(){
		return nodeType;
	}
}
