package ap.trainingdiary.json.serializer;

import java.io.IOException;
import java.io.File;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.util.TypeUtils;

import ap.json.Serializer;

import ap.trainingdiary.type.TrainingRow;

public class TrainingRowsSerializer{
	public static void write(final File path, final List<TrainingRow> trainingData)throws IOException{
		Serializer.write(path, toJSONArray(trainingData));
	}
	public static JSONArray toJSONArray(final List<TrainingRow> trainingData){
		JSONArray jsonTrainingData = new JSONArray();
		for(TrainingRow trainingRow: trainingData){
			jsonTrainingData.add(TrainingRowSerializer.toJSONObject(trainingRow));
		}
		return jsonTrainingData;
	}
}
