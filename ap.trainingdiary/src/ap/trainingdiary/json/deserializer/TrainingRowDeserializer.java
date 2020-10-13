package ap.trainingdiary.json.deserializer;

import java.io.IOException;
import java.io.File;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.util.TypeUtils;

import ap.json.Deserializer;

import ap.trainingdiary.type.TrainingRow;

public class TrainingRowDeserializer{
	public static TrainingRow load(final File path)throws IOException{
		return fromJSONObject(Deserializer.loadJSONObject(path));
	}
	public static TrainingRow fromJSONObject(JSONObject jsonTrainingRow){
		return new TrainingRow(
			jsonTrainingRow.getBigDecimal("Weight"),
			jsonTrainingRow.getInteger("Repeats")
		);
	}
}
