package ap.trainingdiary.json.serializer;

import java.io.IOException;
import java.io.File;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.util.TypeUtils;

import ap.json.Serializer;
import ap.trainingdiary.type.Template;

public class TemplateSerializer{
	public static void write(
		final File path,
		final Template template
	)throws IOException{
		Serializer.write(path, toJSONObject(template));
	}
	public static JSONObject toJSONObject(final Template template){
		JSONObject jsonTemplate = new JSONObject();
		jsonTemplate.put("Name", template.getName());
		jsonTemplate.put("TrainingData", TrainingRowsSerializer.toJSONArray(template.getTrainingData()));
		return jsonTemplate;
	}
}
