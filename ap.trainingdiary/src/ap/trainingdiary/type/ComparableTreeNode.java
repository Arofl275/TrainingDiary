package ap.trainingdiary.type;

import javax.swing.tree.DefaultMutableTreeNode;

public class ComparableTreeNode implements Comparable<ComparableTreeNode>{
	private final DefaultMutableTreeNode node;
	public ComparableTreeNode(final DefaultMutableTreeNode node){
		this.node = node;
	}
	public int compareTo(final ComparableTreeNode other){
		return getUserObject().compareTo(other.getUserObject());
	}
	public DefaultMutableTreeNode getNode(){
		return node;
	}
	public AbctractComparable getUserObject(){
		return (AbctractComparable) node.getUserObject();
	}
	public NodeType getNodeType(){
		return getUserObject().getNodeType();
	}
}
