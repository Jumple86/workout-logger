package me.ian.workoutrecoder.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BeanConvertUtils {

    private final static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T>T convert(Object object, Class<T> clazz) {
        return objectMapper.convertValue(object, clazz);
    }
}
