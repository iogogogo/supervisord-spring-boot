package com.iogogogo.supervisord.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParserFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by tao.zeng on 2021/6/25.
 */
@Slf4j
public class JsonParse {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * The constant EMPTY_OBJECT.
     */
    public final static String EMPTY_OBJECT = "{}";
    /**
     * The constant EMPTY_LIST.
     */
    public final static String EMPTY_LIST = "[]";

    static {
        // Java8 日期序列化反序列化处理
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        OBJECT_MAPPER
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true)
                .configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true)
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true)
                // .configure(com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT, true)
                .configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, false);
    }

    /**
     * To json string.
     *
     * @param bean the bean
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public static String toJson(Object bean) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(bean);
    }

    /**
     * Try to json string.
     *
     * @param bean the bean
     * @return the string
     */
    public static String tryToJson(Object bean) {
        try {
            return OBJECT_MAPPER.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            log.warn("tryToJson ", e);
        }
        return bean instanceof Collection ? EMPTY_LIST : EMPTY_OBJECT;
    }

    /**
     * To json pretty printer string.
     *
     * @param bean the bean
     * @return the string
     */
    public static String toJsonPrettyPrinter(Object bean) {
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            log.warn("toJsonPrettyPrinter ,", e);
        }
        return bean instanceof Collection ? EMPTY_LIST : EMPTY_OBJECT;
    }

    /**
     * Parse t.
     *
     * @param <T>  the type parameter
     * @param json the json
     * @param clz  the clz
     * @return the t
     * @throws IOException the io exception
     */
    public static <T> T parse(String json, Class<T> clz) throws IOException {
        return OBJECT_MAPPER.readValue(json, clz);
    }

    /**
     * Parse t.
     *
     * @param <T>  the type parameter
     * @param json the json
     * @param type the type
     * @return the t
     * @throws IOException the io exception
     */
    public static <T> T parse(String json, TypeReference<T> type) throws IOException {
        return OBJECT_MAPPER.readValue(json, type);
    }

    /**
     * Try parse t.
     *
     * @param <T>  the type parameter
     * @param json the json
     * @param clz  the clz
     * @return the t
     */
    public static <T> T tryParse(String json, Class<T> clz) {
        try {
            return OBJECT_MAPPER.readValue(json, clz);
        } catch (IOException e) {
            log.warn("tryParse ,", e);
        }
        return null;
    }

    /**
     * Try parse t.
     *
     * @param <T>  the type parameter
     * @param json the json
     * @param type the type
     * @return the t
     */
    public static <T> T tryParse(String json, TypeReference<T> type) {
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            log.warn("tryParse ,", e);
        }
        return null;
    }

    /**
     * Parse map map.
     *
     * @param json the json
     * @return the map
     */
    public static Map<String, Object> parseMap(String json) {
        return JsonParserFactory.getJsonParser().parseMap(json);
    }

    /**
     * Parse list list.
     *
     * @param json the json
     * @return the list
     */
    public static List<Object> parseList(String json) {
        return JsonParserFactory.getJsonParser().parseList(json);
    }

}
