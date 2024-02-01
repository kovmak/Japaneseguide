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
            System.out.println("\033[2;1mВітаємо вас в додатку про Японську історію \uD83C\uDFB4\uD83D\uDCDC \033[3m");
            System.out.println("1. Вхід \uD83D\uDC64");
            System.out.println("2. Реєстрація +\uD83D\uDC64");
            System.out.println("3. Вихід \uD83D\uDD19");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    authorisation(DATA_PATH + "users.json");
                    break;
                case 2:
                    registration(DATA_PATH + "users.json");
                    return;
                case 3:
                    System.out.println("Дякуємо за використання додатка. До побачення! \uD83D\uDC4B\uD83C\uDFFC");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Невірний вибір. Будь ласка, виберіть знову ❗");
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
