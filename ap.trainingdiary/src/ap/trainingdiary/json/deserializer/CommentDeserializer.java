package ap.trainingdiary.json.deserializer;

import java.io.IOException;
import java.io.File;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.util.TypeUtils;

import ap.json.Deserializer;
import ap.trainingdiary.type.Comment;

public class CommentDeserializer{
	public static Comment load(final File path)throws IOException{
		return fromJSONObject(Deserializer.loadJSONObject(path));
	}
	public static Comment fromJSONObject(JSONObject jsonComment){
		return new Comment(
			jsonComment.getString("Name"),
			jsonComment.getString("Text")
		);
	}
}
