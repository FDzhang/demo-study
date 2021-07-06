package com.example.demovalidator.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * 除以1万
 */
public class IWanSerialize extends JsonSerializer<Object> {

    private final DecimalFormat df = new DecimalFormat(",###.00");

    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if ((o instanceof Number)) {
            jsonGenerator.writeString(df.format((((Number) o).doubleValue() / 10000)));
        }
    }
}