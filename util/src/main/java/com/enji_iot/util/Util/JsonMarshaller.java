package com.enji_iot.util.Util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;


/**
 * Json数据解析器
 * 
 */
public class JsonMarshaller {

	/**
	 * 
	 * 转化为对象列表
	 * 
	 * @param src
	 * @param target
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@SuppressWarnings("rawtypes")
	public static List unmarshal2Objects(String src, Class target) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		List result = null;
			result = mapper.readValue(new StringReader(src),
					TypeFactory.collectionType(ArrayList.class, target));
		return result;
	}
	
	/**
	 * 转化为对象
	 * 
	 * @param src
	 * @param target
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object unmarshal(String src, Class target) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		return mapper.readValue(new StringReader(src), target);
	}
	
	/**
	 * 对象转化为json字符串
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public static String unmarshal2Json(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		String json = mapper.writeValueAsString(obj);
		return json;
	}
	
	/**
	 * java对象转换为json字符串
	 */
	public static String marshal(Object obj) throws JsonGenerationException,JsonMappingException,IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		String json = null;
		json = mapper.writeValueAsString(obj);
		return json;
	}
}




