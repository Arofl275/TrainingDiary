package ap.util.property;

import java.io.File;
import java.io.IOException;

import ap.json.Serializer;
import com.alibaba.fastjson.JSONObject;

public class PropertyTreeSerializer{
	public static void write(
		final File path,
		final PropertyTree propertyTree
	)throws IOException{
		if(propertyTree.getRoot() != null){
			Serializer.write(path, PropertyNodeSerializer.toJSONObject(propertyTree.getRoot()));
		}
	}
}
