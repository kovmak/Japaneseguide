package com.krnelx.Japaneseguide.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.krnelx.Japaneseguide.persistence.entity.impl.User;
import com.krnelx.Japaneseguide.persistence.exception.EntityArgumentException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Utility class for user registration and related operations.
 */
public class Registration {

    /**
     * Reads user data from a JSON file and returns a list of User objects.
     *
     * @param filePath The path to the JSON file containing user data.
     * @return A list of User objects read from the file.
     */
    public static List<User> readUsersFromFile(String filePath) {
        List<User> userList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Створюємо Gson з можливістю десеріалізації LocalDate
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                    .create();

            // Визначаємо тип для десеріалізації списку користувачів
            Type userListType = new TypeToken<List<User>>() {
            }.getType();

            // Читаємо вміст файлу та десеріалізуємо його у список користувачів
            userList = gson.fromJson(reader, userListType);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }

    /**
     * Writes a list of User objects to a JSON file.
     *
     * @param users    The list of User objects to be written to the file.
     * @param filePath The path to the JSON file where user data will be stored.
     */
    private static void writeUsersToFile(List<User> users, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Створюємо Gson з красивим виведенням
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                    .setPrettyPrinting().create();

            // Перетворюємо список користувачів в JSON та записуємо у файл
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs user registration, taking input from the user and storing the information in a JSON file.
     *
     * @param filePath The path to the JSON file where user data will be stored.
     */
    public void registration(String filePath) {
        Scanner scanner = new Scanner(System.in);
        List<User> users = readUsersFromFile(filePath);

        try {
            // Введення даних користувача
            UUID userId = UUID.randomUUID();
            System.out.println("\n\033[2;1mРеєстрація облікового запису\033[3m +\uD83D\uDC64 :");
            System.out.print("Напишіть username (name.name): ");
            String username = scanner.nextLine();
            System.out.print("Напишіть password (не меньше 8 символів): ");
            String password = scanner.nextLine();
            System.out.print("Напишіть email (name@gmail.com): ");
            String email = scanner.nextLine();
            System.out.print("Напишіть birthday (YYYY-MM-DD): ");
            LocalDate birthday = LocalDate.parse(scanner.nextLine());

            if (userAlreadyExists(users, email, username)) {
                System.out.println("Помилка: Користувач з такою ж адресою електронної пошти або ім'ям користувача вже існує ❗");
                registration(filePath);
            }

            // Створення об'єкта користувача та додавання його до списку
            User user = new User(userId, password, email, birthday, username);
            users.add(user);

            // Перезапис файлу JSON з оновленим списком користувачів
            writeUsersToFile(users, filePath);

            System.out.println("Реєстрація успішна ✅");

            // Автоматично викликаємо метод авторизації
            Authorisation.authorisation(filePath); // Передайте шлях до файла, де зберігаються дані користувачів

        } catch (EntityArgumentException e) {
            // Обробка помилок, якщо вони виникли під час створення користувача
            for (String error : e.getErrors()) {
                System.out.println("Помилка: ❗" + error);
            }
        } catch (DateTimeParseException e) {
            // Обробка помилок, якщо дата введена невірно
            System.out.println(
                    "Помилка: Неправильний формат дати. Будь ласка, введіть дату у форматі YYYY-MM-DD ❗");
        } finally {
            scanner.close();
        }
    }

    /**
     * Checks if a user with the given email or username already exists in the list of users.
     *
     * @param users    The list of User objects to check against.
     * @param email    The email to check for duplication.
     * @param username The username to check for duplication.
     * @return true if a user with the given email or username already exists, false otherwise.
     */
    private boolean userAlreadyExists(List<User> users, String email, String username) {
        for (User user : users) {
            if (user.getEmail().equals(email) || user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}