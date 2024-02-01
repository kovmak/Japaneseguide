package com.krnelx.Japaneseguide;

import com.krnelx.Japaneseguide.util.Registration;
import com.krnelx.Japaneseguide.util.Authorisation;

import java.util.Scanner;

/**
 * The main class for the Japanese History application.
 */
public class Main {
    private static final String DATA_PATH = "Data/";

    /**
     * The main method to run the Japanese History application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Вiтаємо вас в додатку про Японську iсторiю ");
            System.out.println("1. Вхiд ");
            System.out.println("2. Реєстрацiя +");
            System.out.println("3. Вихiд ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    authorisation(DATA_PATH + "users.json");
                    break;
                case 2:
                    registration(DATA_PATH + "users.json");
                    return;
                case 3:
                    System.out.println("Дякуємо за використання додатка. До побачення! ");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Невiрний вибiр. Будь ласка, виберiть знову ");
            }
        }
    }

    /**
     * Method to handle user authorization.
     *
     * @param filePath The path to the file containing user data.
     */
    private static void authorisation(String filePath) {
        Authorisation authorisation = new Authorisation();
        authorisation.authorisation(filePath);
    }

    /**
     * Method to handle user registration.
     *
     * @param filePath The path to the file containing user data.
     */
    private static void registration(String filePath) {
        Registration registration = new Registration();
        registration.registration(filePath);
    }
}
