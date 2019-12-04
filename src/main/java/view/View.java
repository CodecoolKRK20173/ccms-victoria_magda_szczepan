package view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class View {
    public static String getLoginInput(){
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void displayMenu(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println(String.format("(%d) %s",i+1,options[i]));
        }
    }

    public static int getUserChoice(int optionsLength) {
        Scanner scan = new Scanner(System.in);
        String userChoice = scan.nextLine();
        validateUserChoice(userChoice, optionsLength);
        return Integer.parseInt(userChoice);
    }

    private static void validateUserChoice(String userChoice, int optionsLength) {
        try {
            int parsedUserChoice = Integer.parseInt(userChoice);
            if (parsedUserChoice > optionsLength  && parsedUserChoice < 1)
                getUserChoice(optionsLength);
        }catch(Exception e){
            printErrorMessage("Invalid input!");
            getUserChoice(optionsLength);
        }
    }

    public static void showPersonList(List<String> people) {
        for (int i  = 0; i < people.size(); i++) {
            System.out.println((i + 1) + ". " + people.get(i));
        }
    }

    public static void displayGrades(Map<String,Integer> grades){
        for (String assigment: grades.keySet()){
            System.out.println(assigment+": "+grades.get(assigment));
        }
    }

    public static String getUserInput(){
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
}
