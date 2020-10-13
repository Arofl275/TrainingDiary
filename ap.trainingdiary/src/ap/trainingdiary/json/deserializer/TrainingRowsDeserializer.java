package ap.trainingdiary.json.deserializer;

import java.io.IOException;
import java.io.File;

import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.util.TypeUtils;

import ap.json.Deserializer;

import ap.trainingdiary.type.TrainingRow;

public class TrainingRowsDeserializer{
	public static List<TrainingRow> load(final File path)throws IOException{
		return fromJSONArray(Deserializer.loadJSONArray(path));
	}
	public static List<TrainingRow> fromJSONArray(JSONArray jsonTrainingRows){
		List<TrainingRow> trainingData = new LinkedList<TrainingRow>();
		for(int i = 0; i < jsonTrainingRows.size(); i++){
			trainingData.add(TrainingRowDeserializer.fromJSONObject(jsonTrainingRows.getJSONObject(i)));
		}
		return trainingData;
	}
}
