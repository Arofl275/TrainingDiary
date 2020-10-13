package ap.util.property;

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

import ap.json.Deserializer;
import com.alibaba.fastjson.JSONObject;

public class PropertyTreeDeserializer{
	private static String resourcePathFormat1 = "%s/%s/%s/%s"; // packageName, country, language, fileName
	private static String resourcePathFormat2 = "%s/%s/%s"; // packageName, language, fileName
	public static PropertyTree load(
		final String packageName,
		final String fileName
	)throws IOException{
		Locale locale = Locale.getDefault();
		String country = locale.getCountry();
		String language = locale.getLanguage();
		InputStream inputStream = getResourceAsStream(String.format(resourcePathFormat1, packageName, country, language, fileName));
		if(inputStream == null){
			inputStream = getResourceAsStream(String.format(resourcePathFormat2, packageName, language, fileName));
		}
		if(inputStream != null){
			final JSONObject jsonObject = Deserializer.loadJSONObject(inputStream);
			return createPropertyTree(jsonObject);
		}
		return null;
	}
	private static void createPropertyTree(final PropertyTree propertyTree, final PropertyPath propertyPath, final Object object){
		if(object instanceof String){
			propertyTree.setValue(propertyPath, (String) object);
		}
		else{
			JSONObject jsonObject = (JSONObject) object;
			for(String pathItem: jsonObject.keySet()){
				final PropertyPath newPropertyPath = propertyPath.createNewPropertyPath(pathItem);
				final Object newObject = jsonObject.get(pathItem);
				createPropertyTree(propertyTree, newPropertyPath, newObject);
			}
		}
	}
	private static PropertyTree createPropertyTree(final JSONObject jsonObject){
		final PropertyTree propertyTree = new PropertyTree();
		try{
			for(String pathItem: jsonObject.keySet()){
				final PropertyPath propertyPath = PropertyPath.create(pathItem);
				final Object object = jsonObject.get(pathItem);
				createPropertyTree(propertyTree, propertyPath, object);
			}
		}
		catch(NullPointerException | IllegalArgumentException e){
			// will never throw
		}
		return propertyTree;
	}
	private static void createPropertyTree(final PropertyTree propertyTree, final PropertyPath propertyPath, final JSONObject jsonObject){
		try{
			for(String pathItem: jsonObject.keySet()){
				final PropertyPath newPropertyPath = propertyPath.createNewPropertyPath(pathItem);
				final Object object = jsonObject.get(pathItem);
				createPropertyTree(propertyTree, propertyPath, object);
			}
		}
		catch(NullPointerException | IllegalArgumentException e){
			// will never throw
		}
	}
	private static InputStream getResourceAsStream(final String path){
		return PropertyTreeDeserializer.class.getClassLoader().getResourceAsStream(path);
	}
}
