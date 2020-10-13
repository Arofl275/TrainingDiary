package ap.trainingdiary.json.serializer;

import java.io.IOException;
import java.io.File;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.util.TypeUtils;

import ap.json.Serializer;
import ap.trainingdiary.type.Training;

public class TrainingSerializer{
	public static void write(
		final File path,
		final Training training
	)throws IOException{
		Serializer.write(path, toJSONObject(training));
	}
	public static JSONObject toJSONObject(final Training training){
		JSONObject jsonTraining = new JSONObject();
		jsonTraining.put("Day", training.getDay());
		jsonTraining.put("Month", training.getMonth());
		jsonTraining.put("Year", training.getYear());
		jsonTraining.put("TrainingIndex", training.getTrainingIndex());
		jsonTraining.put("TrainingData", TrainingRowsSerializer.toJSONArray(training.getTrainingData()));
		jsonTraining.put("Comment", training.getComment());
		return jsonTraining;
	}
}
