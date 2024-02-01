package com.krnelx.Japaneseguide.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * JsonSerializer implementation for serializing LocalDate objects to JSON format.
 */
public class LocalDateSerializer implements JsonSerializer<LocalDate> {

    /**
     * DateTimeFormatter for formatting LocalDate objects to the specified pattern.
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Serializes a LocalDate object to a JsonElement with the specified date format.
     *
     * @param localDate               The LocalDate object to be serialized.
     * @param type                    The type of the object to be serialized.
     * @param jsonSerializationContext The context for serialization.
     * @return A JsonElement representing the serialized LocalDate.
     */
    @Override
    public JsonElement serialize(LocalDate localDate, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(formatter.format(localDate));
    }
}
