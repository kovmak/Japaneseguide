package com.krnelx.Japaneseguide.mainmenu;

import com.krnelx.Japaneseguide.history.Important;
import com.krnelx.Japaneseguide.history.Other;
import com.krnelx.Japaneseguide.history.Tragic;
import com.krnelx.Japaneseguide.util.Search;

import java.util.Scanner;

/**
 * Class representing the main menu of the Japanese History application.
 */
public class MainMenu {

    /**
     * Method to run the main program loop.
     *
     * @param scanner The Scanner object for user input.
     */
    public void runProgram(Scanner scanner) {
        String DATA_PATH = "Data/";

        while (true) {
            System.out.println("\n\033[2;1mГоловне меню\033[3m\uD83D\uDCDA:");
            System.out.println("1. Перегляд трагічних історичних подій \uD83D\uDCDC");
            System.out.println("2. Перегляд важливих історичних подій \uD83D\uDCDC");
            System.out.println("3. Перегляд інших історичних подій \uD83D\uDCDC");
            System.out.println("4. Пошук \uD83D\uDD0E");
            System.out.println("5. Вихід \uD83D\uDD19");

            String userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1":
                    Tragic tragic = new Tragic();
                    tragic.printTragicHistory(DATA_PATH + "tragic.json");
                    returnToMainMenu(scanner);
                    break;
                case "2":
                    Important important = new Important();
                    important.printImportantEvents(DATA_PATH + "important.json");
                    returnToMainMenu(scanner);
                    break;
                case "3":
                    Other other = new Other();
                    other.printOtherEvents(DATA_PATH + "other.json");
                    returnToMainMenu(scanner);
                    break;
                case "4":
                    System.out.println("\nВведіть ключове слово або цифру по якій ви бажаєте здійснити пошук \uD83D\uDD8B");
                    String searchTerm = scanner.nextLine();
                    Search.searchInFiles(searchTerm, DATA_PATH + "tragic.json", DATA_PATH + "important.json", DATA_PATH + "other.json");
                    returnToMainMenu(scanner);
                    break;

                case "5":
                    System.out.println("Дякуємо за використання додатка. До побачення! \uD83D\uDC4B\uD83C\uDFFC");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Невірний вибір. Будь ласка, введіть коректний номер опції ❗");
            }
        }
    }

    /**
     * Method to prompt the user to return to the main menu.
     *
     * @param scanner The Scanner object for user input.
     */
    private void returnToMainMenu(Scanner scanner) {
        System.out.println("\nХочете повернутися на головне меню ❓ (Так/Ні)");
        String returnToMainMenuChoice = scanner.nextLine();
        if (!returnToMainMenuChoice.equalsIgnoreCase("Так")) {
            System.out.println("Дякуємо за використання додатка. До побачення! \uD83D\uDC4B\uD83C\uDFFC");
            System.exit(0);
        }
    }
}
