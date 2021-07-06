package com.example.demovalidator.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * 小数
 */
public class IDoubleSerialize extends JsonSerializer<Double> {
    /**
     * 保留两位小数, 每三位用 ',' 分隔
     */
    private final DecimalFormat df = new DecimalFormat(",###.00");

    @Override
    public void serialize(Double aDouble, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (aDouble != null) {
            jsonGenerator.writeString(df.format(aDouble));
        }
    }
}