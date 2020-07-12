package com.okta.taxcalculator.Helper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;

import java.util.List;
import java.util.Map;

public class JsonHelper {
    protected static final ObjectMapper jsonObjectMapper = buildObjectMapper();
    static {
        jsonObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jsonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonObjectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        jsonObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
    public JsonHelper()
    {
    }
    public static ObjectMapper buildObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }
    public static ObjectMapper getObjectMapper() {
        return jsonObjectMapper;
    }
    public static <T> T fromJson(String json, Class<T> classType) throws Exception {
        return unmarshal(json, classType);
    }
    public static <T> T unmarshal(String json, Class<T> classType) throws Exception {
        if ((json == null) || (json.isEmpty())) {
            return null;
        } else {
            try {
                return getObjectMapper().readValue(json, classType);
            } catch (Exception var3) {
                throw new Exception(var3.getMessage());
            }
        }
    }
    public static List unmarshalCollection(String json, Class classType) throws Exception {
        try {
            CollectionType e = getObjectMapper().getTypeFactory().constructCollectionType(List.class, classType);
            return (List)getObjectMapper().readValue(json.toString(), e);
        } catch (Exception var3) {
            throw new Exception(var3.getMessage());
        }
    }
    public static Map unmarshalMap(String json, Class classType) throws Exception {
        try {
            MapLikeType e = getObjectMapper().getTypeFactory().constructMapLikeType(Map.class, String.class, classType);
            return (Map)getObjectMapper().readValue(json.toString(), e);
        } catch (Exception var3) {
            throw new Exception(var3.getMessage());
        }
    }
    public static String toJson(Object object) throws Exception {
        return marshal(object, false);
    }
    public static String marshal(Object object) throws Exception {
        return marshal(object, false);
    }
    public static String marshal(Object object, boolean pretty) throws Exception {
        if(object == null) {
            return "";
        } else {
            try {
                return pretty?jsonObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object):jsonObjectMapper.writeValueAsString(object);
            } catch (Exception var3) {
                throw new Exception(var3.getMessage());
            }
        }
    }
}
