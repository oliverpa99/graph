package com.example.graph;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
	
	/**
	 * Get the JSON representation of the given object
	 * 
	 * @param obj - Object
	 * @return - String
	 */
	public static String toJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Map a JSON String to java class
	 *  
	 * @param s - String
	 * @param obj - Java Class
	 * @return - Java Class Instance
	 */
	@SuppressWarnings("unchecked")
	public static Object createObj(String s, Object obj) {
		
		 ObjectMapper mapper = new ObjectMapper();
		 try {
			return mapper.readValue(s, (Class<Object>) obj);
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		return null;
	}
}

