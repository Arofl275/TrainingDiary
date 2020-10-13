package ap.util.property;

import java.util.Iterator;
import java.util.List;

public class PropertyTree{
	private PropertyNode root = null;
	public PropertyTree(){}
	public PropertyNode getRoot(){
		return root;
	}
	public String getValue(final PropertyPath path) throws PropertyNotFoundException{
		List<String> pathList = path.getPathList();
		if(root == null){
			final int position = 0;
			throw new PropertyNotFoundException(pathList.get(position), position);
		}
		else{
			int position = 0;
			final int last = pathList.size() - 1;
			PropertyNode node = root;
			Iterator<String> it = pathList.iterator();
			for(; position < last; position++){
				String key = it.next();
				node = node.getPropertyNode(key);
				if(node == null){
					throw new PropertyNotFoundException(key, position);
				}
			}
			String key = it.next();
			String value = node.getValue(key);
			if(value == null){
				throw new PropertyNotFoundException(key, position);
			}
			return value;
		}
	}
	public void setValue(final PropertyPath path, final String value){
		List<String> pathList = path.getPathList();
		if(root == null){
			root = new PropertyNode();
		}
		int position = 0;
		final int last = pathList.size() - 1;
		PropertyNode node = root;
		Iterator<String> it = pathList.iterator();
		for(; position < last; position++){
			String key = it.next();
			PropertyNode child = node.getPropertyNode(key);
			if(child == null){
				child = new PropertyNode();
				node.put(key, child);
			}
			node = child;
		}
		node.put(it.next(), value);
	}
	public void print(){
		if(root != null){
			root.print();
		}
	}
}
