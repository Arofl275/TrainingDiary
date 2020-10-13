package ap.trainingdiary.json.serializer;

import java.io.IOException;
import java.io.File;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.util.TypeUtils;

import ap.json.Serializer;
import ap.trainingdiary.type.Comment;

public class CommentSerializer{
	public static void write(
		final File path,
		final Comment comment
	)throws IOException{
		Serializer.write(path, toJSONObject(comment));
	}
	public static JSONObject toJSONObject(final Comment comment){
		JSONObject jsonComment = new JSONObject();
		jsonComment.put("Name", comment.getName());
		jsonComment.put("Text", comment.getText());
		return jsonComment;
	}
}
