package com.krnelx.Japaneseguide.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * JsonDeserializer implementation for deserializing JSON elements to LocalDate objects.
 */
public class LocalDateDeserializer implements JsonDeserializer<LocalDate> {

    /**
     * Deserializes a JsonElement to a LocalDate object.
     *
     * @param json       The JsonElement to be deserialized.
     * @param typeOfT    The type of the object to be deserialized.
     * @param context    The context for deserialization.
     * @return A LocalDate object representing the deserialized date.
     * @throws JsonParseException if the JsonElement cannot be deserialized to a LocalDate.
     */
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
    }
}
