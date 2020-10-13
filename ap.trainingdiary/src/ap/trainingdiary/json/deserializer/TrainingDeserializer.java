package ap.trainingdiary.json.deserializer;

import java.io.IOException;
import java.io.File;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.util.TypeUtils;

import ap.json.Deserializer;
import ap.trainingdiary.type.Training;
import ap.trainingdiary.type.TrainingRow;

public class TrainingDeserializer{
	public static Training load(final File path)throws IOException{
		return fromJSONObject(Deserializer.loadJSONObject(path));
	}
	public static Training fromJSONObject(JSONObject jsonTraining){
		return Training.create(
			jsonTraining.getIntValue("Day"),
			jsonTraining.getIntValue("Month"),
			jsonTraining.getIntValue("Year"),
			jsonTraining.getIntValue("TrainingIndex"),
			TrainingRowsDeserializer.fromJSONArray(jsonTraining.getJSONArray("TrainingData")),
			jsonTraining.getString("Comment")
		);
	}
}
