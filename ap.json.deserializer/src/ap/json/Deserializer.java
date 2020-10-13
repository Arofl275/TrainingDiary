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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.io.Reader;

public class Deserializer{
	public static final String fileToString(final File file) throws IOException{
		return FileUtils.readFileToString(file, "utf-8");
	}
	private static String inputStreamToString(final InputStream inputStream) throws IOException{
		StringBuilder stringBuilder = new StringBuilder();
		try(
			Reader reader = new BufferedReader(
				new InputStreamReader(
					inputStream,
					Charset.forName(StandardCharsets.UTF_8.name())
				)
			)
		){
			int c = -1;
			while((c = reader.read()) != -1){
				stringBuilder.append((char) c);
			}
		}
		return stringBuilder.toString();
	}
	public static final JSONArray JSONArrayFromString(final String jsonString){
		return JSON.parseArray(jsonString);
	}
	public static final JSONObject JSONObjectFromString(final String jsonString){
		return JSON.parseObject(jsonString);
	}
	public static final JSONArray loadJSONArray(final File path) throws IOException{
		return JSONArrayFromString(fileToString(path));
	}
	public static final JSONObject loadJSONObject(final File path) throws IOException{
		return JSONObjectFromString(fileToString(path));
	}
	public static final BigDecimal loadBigDecimal(final File path) throws IOException{
		return TypeUtils.castToBigDecimal(fileToString(path));
	}
	public static final BigInteger loadBigInteger(final File path) throws IOException{
		return TypeUtils.castToBigInteger(fileToString(path));
	}
	public static final Boolean loadBoolean(final File path) throws IOException{
		return TypeUtils.castToBoolean(fileToString(path));
	}
	public static final Integer loadInteger(final File path) throws IOException{
		return TypeUtils.castToInt(fileToString(path));
	}
	public static final String loadString(final File path) throws IOException{
		return TypeUtils.castToString(fileToString(path));
	}
	public static final JSONArray loadJSONArray(final InputStream inputStream) throws IOException{
		return JSONArrayFromString(inputStreamToString(inputStream));
	}
	public static final JSONObject loadJSONObject(final InputStream inputStream) throws IOException{
		return JSONObjectFromString(inputStreamToString(inputStream));
	}
}
