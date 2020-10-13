package ap.util.property;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class PropertyNodeSerializer{
	public static JSONObject toJSONObject(
		final PropertyNode propertyNode
	)throws IOException{
		JSONObject jsonObject = new JSONObject();
		for(Map.Entry<String, String> entry: propertyNode.getKeyValueMap().entrySet()){
			jsonObject.put(entry.getKey(), entry.getValue());
		}
		for(Map.Entry<String, PropertyNode> entry: propertyNode.getKeyPropertyNodeMap().entrySet()){
			jsonObject.put(entry.getKey(), toJSONObject(entry.getValue()));
		}
		return jsonObject;
	}
}
