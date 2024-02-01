package com.krnelx.Japaneseguide.util;

import com.google.gson.*;
import com.krnelx.Japaneseguide.mainmenu.MainMenu;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Authorisation class provides methods for authenticating users based on a JSON file containing username-password pairs.
 */
public class Authorisation {
    /**
     * Authenticates users based on the provided JSON file containing username-password pairs.
     *
     * @param fileName The name of the JSON file containing user credentials.
     */
    public static void authorisation(String fileName) {
        Scanner scanner = new Scanner(System.in);

        boolean authenticationSuccessful = false;

        while (!authenticationSuccessful) {
            try {
                // Зчитуємо JSON з файлу
                JsonArray jsonArray = new Gson().fromJson(new FileReader(fileName), JsonArray.class);
                System.out.println("\nВхiд в облiковий запис :");
                System.out.print("Введiть username: ");
                String enteredUsername = scanner.nextLine();

                System.out.print("Введiть password: ");
                String enteredPassword = scanner.nextLine();

                // Проходимо по всiх об'єктах масиву
                for (JsonElement jsonElement : jsonArray) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();

                    // Отримуємо iнформацiю з JSON
                    String storedUsername = jsonObject.get("username").getAsString();
                    String storedPassword = jsonObject.get("password").getAsString();

                    // Перевiрка введених даних
                    if (enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword)) {
                        System.out.println("Вхiд успiшний ");

                        MainMenu mainApp = new MainMenu();
                        mainApp.runProgram(scanner);

                        authenticationSuccessful = true;
                        break; // Вихiд iз циклу, оскiльки аутентифiкацiя вдалася
                    }
                }
                // Якщо цей код викликався, то аутентифiкацiя не вдалася
                System.out.println("Помилка входу. Неправильне iм'я користувача або пароль ");
            } catch (JsonParseException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}