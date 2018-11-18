package ru.ncedu.consoleapp.utils;

import java.io.IOException;
import java.util.Scanner;

public class IOUtils {

    public static void printSeparator() {
        System.out.println("--------------------------");
    }

    public static void printOption(String command, String name) {
        System.out.println(command + " - " + name);
    }

    public static void printPrompt() {
        System.out.print("> ");
    }

    public static void showMessageAndWait(String message) {
        printSeparator();
        System.out.println(message);
        waitForEnter();
    }

    public static void waitForEnter() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long getLong() {
        long number;
        Scanner scanner = new Scanner(System.in);
        try {
            printPrompt();
            number = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("It's not a valid number. Please, try again:");
            return getLong();
        }

        return number;
    }

}
