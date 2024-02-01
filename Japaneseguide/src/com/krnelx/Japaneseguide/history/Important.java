package com.krnelx.Japaneseguide.history;

import com.google.gson.*;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class for handling and printing important historical events from a JSON file.
 */
public class Important {

    /**
     * Method for printing important events from a JSON file.
     *
     * @param filePath The path to the JSON file containing important events.
     */
    public void printImportantEvents(String filePath) {
        try {
            // Читання JSON з файлу
            JsonObject jsonObject = new Gson().fromJson(new FileReader(filePath), JsonObject.class);

            if (jsonObject != null) {
                // Припускаємо, що важливi подiї зберiгаються у властивостi з назвою "Important History"
                JsonArray jsonArray = jsonObject.getAsJsonArray("Important History");

                if (jsonArray != null) {
                    for (JsonElement jsonElement : jsonArray) {
                        JsonObject eventObject = jsonElement.getAsJsonObject();
                        printEventInfo(eventObject);
                    }
                } else {
                    System.out.println("Помилка: Властивiсть 'Important History' не знайдена у файлi або вона не є масивом ");
                }
            } else {
                System.out.println("Помилка читання файлу або файл порожнiй ");
            }

        } catch (JsonParseException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for printing information about an event.
     *
     * @param eventObject The JSON object representing an important event.
     */
    private void printEventInfo(JsonObject eventObject) {
        System.out.println("Iнформацiя про важливi iсторичнi подii:");
        System.out.println("Подiя: " + getStringFieldValue(eventObject, "event"));
        System.out.println("Дата: " + getStringFieldValue(eventObject, "date"));
        System.out.println("Iнформацiя: " + getStringFieldValue(eventObject, "info"));
        System.out.println();
    }

    /**
     * Method for retrieving the value of a string field from a JSON object.
     *
     * @param jsonObject The JSON object from which to retrieve the field value.
     * @param fieldName  The name of the string field.
     * @return The string value of the specified field, or an empty string if the field is not present or not a JSON primitive.
     */
    private String getStringFieldValue(JsonObject jsonObject, String fieldName) {
        JsonElement jsonElement = jsonObject.get(fieldName);
        if (jsonElement != null && jsonElement.isJsonPrimitive()) {
            return jsonElement.getAsString();
        }
        return ""; // Або iнше значення за замовчуванням, якщо поле вiдсутнє або не є JSON-примiтивом
    }
}