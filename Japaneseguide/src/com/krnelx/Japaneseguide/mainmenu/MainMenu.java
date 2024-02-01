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
            System.out.println("\nГоловне меню:");
            System.out.println("1. Перегляд трагiчних iсторичних подiй");
            System.out.println("2. Перегляд важливих iсторичних подiй");
            System.out.println("3. Перегляд iнших iсторичних подiй");
            System.out.println("4. Пошук ");
            System.out.println("5. Вихiд ");

            String userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1":
                    Tragic tragic = new Tragic();
                    tragic.printTragicHistory(DATA_PATH + "tragic.json");
                    break;
                case "2":
                    Important important = new Important();
                    important.printImportantEvents(DATA_PATH + "important.json");
                    break;
                case "3":
                    Other other = new Other();
                    other.printOtherEvents(DATA_PATH + "other.json");
                    break;
                case "4":
                    System.out.println("\nВведiть ключове слово або цифру по якiй ви бажаєте здiйснити пошук ");
                    String searchTerm = scanner.nextLine();
                    Search.searchInFiles(searchTerm, DATA_PATH + "tragic.json", DATA_PATH + "important.json", DATA_PATH + "other.json");
                    break;

                case "5":
                    System.out.println("Дякуємо за використання додатка. До побачення! ");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Невiрний вибiр. Будь ласка, введiть коректний номер опцii ");
            }
        }
    }
}
