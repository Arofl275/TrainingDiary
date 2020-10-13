package ap.util.property;

import java.util.Map;
import java.util.TreeMap;

public class PropertyNode{
	private final TreeMap<String, String> keyValueMap;
	private final TreeMap<String, PropertyNode> keyPropertyNodeMap;
	public PropertyNode(){
		this.keyValueMap = new TreeMap<String, String>();
		this.keyPropertyNodeMap = new TreeMap<String, PropertyNode>();
	}
	public String getValue(final String key){
		return keyValueMap.get(key);
	}
	public TreeMap<String, String> getKeyValueMap(){
		return new TreeMap<String, String>(keyValueMap);
	}
	public TreeMap<String, PropertyNode> getKeyPropertyNodeMap(){
		return new TreeMap<String, PropertyNode>(keyPropertyNodeMap);
	}
	public PropertyNode getPropertyNode(final String key){
		return keyPropertyNodeMap.get(key);
	}
	public void put(final String key, final String value){
		keyValueMap.put(key, value);
	}
	public void put(final String key, final PropertyNode child){
		keyPropertyNodeMap.put(key, child);
	}
	public void print(){
		for(Map.Entry<String, String> entry: keyValueMap.entrySet()){
			print(entry.getKey(), entry.getValue());
		}
		for(Map.Entry<String, PropertyNode> entry: keyPropertyNodeMap.entrySet()){
			PropertyPath propertyPath = PropertyPath.create(entry.getKey());
			entry.getValue().print(propertyPath);
		}
	}
	public void print(final PropertyPath path){
		for(Map.Entry<String, String> entry: keyValueMap.entrySet()){
			final PropertyPath newPath = path.createNewPropertyPath(entry.getKey());
			print(newPath, entry.getValue());
		}
		for(Map.Entry<String, PropertyNode> entry: keyPropertyNodeMap.entrySet()){
			final PropertyPath newPath = path.createNewPropertyPath(entry.getKey());
			entry.getValue().print(newPath);
		}
	}
	public void print(final PropertyPath path, final String value){
		print(path.toString(), value);
	}
	public void print(final String key, final String value){
		System.out.println(String.format("%s=%s", key, value));
	}
}
