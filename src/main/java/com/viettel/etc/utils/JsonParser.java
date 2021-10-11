package com.viettel.etc.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Comment
 *
 * @author hungnd
 */
@Log4j2
public class JsonParser {

	private static ObjectMapper mObjectMapper;


	private static ObjectMapper getMapper() {

		if (mObjectMapper == null) {
			mObjectMapper = new ObjectMapper();
			mObjectMapper.registerModule(new JavaTimeModule());
			mObjectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		}
		return mObjectMapper;
	}

	/**
	 * Maps json string to specified class
	 *
	 * @param json   string to parse
	 * @param tClass class of object in which json will be parsed
	 * @param <T>    generic parameter for tClass
	 * @return mapped T class instance
	 * @throws IOException
	 */
	public static <T> T entity(String json, Class<T> tClass) {
		try {
			return getMapper().readValue(json, tClass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Maps json string to {@link ArrayList} of specified class object instances
	 *
	 * @param json   string to parse
	 * @param tClass class of object in which json will be parsed
	 * @param <T>    generic parameter for tClass
	 * @return mapped T class instance
	 * @throws IOException
	 */
	public static <T> ArrayList<T> arrayList(String json, Class<T> tClass) throws IOException {
		TypeFactory typeFactory = getMapper().getTypeFactory();
		JavaType type = typeFactory.constructCollectionType(ArrayList.class, tClass);
		return getMapper().readValue(json, type);
	}

	/**
	 * Writes specified object as string
	 *
	 * @param object object to write
	 * @return result json
	 * @throws IOException
	 */
	public static String toJson(Object object) {
		try {
			return getMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

