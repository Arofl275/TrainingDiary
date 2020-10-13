package ap.json;

import com.alibaba.fastjson.util.TypeUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.File;
import java.io.IOException;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.io.FileUtils;

public class Serializer{
	public static String toString(final Object obj){
		String result = null;
		try(
			SerializeWriter out = new SerializeWriter();
		){
			JSONSerializer serializer = new JSONSerializer(out);
			serializer.write(obj);
			result = out.toString();
		}
		return result;
	}
	public static void stringToFile(final File filePath, final String data) throws IOException{
		FileUtils.write(filePath, data, "utf-8");
	}
	public static void write(final File filePath, final Object obj) throws IOException{
		stringToFile(filePath, toString(obj));
	}
}
