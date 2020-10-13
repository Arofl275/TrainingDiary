package ap.trainingdiary.json.deserializer;

import java.io.IOException;
import java.io.File;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.util.TypeUtils;

import ap.json.Deserializer;
import ap.trainingdiary.type.Template;
import ap.trainingdiary.type.TrainingRow;

public class TemplateDeserializer{
	public static Template load(final File path)throws IOException{
		return fromJSONObject(Deserializer.loadJSONObject(path));
	}
	public static Template fromJSONObject(JSONObject jsonTemplate){
		return Template.create(
			jsonTemplate.getString("Name"),
			TrainingRowsDeserializer.fromJSONArray(jsonTemplate.getJSONArray("TrainingData"))
		);
	}
}
