package Controller;

import Data.ReadData;
import Model.User;
import Service.AddData;
import Service.Validate;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = userMenu(scanner);

        if (user != null){
            // Run main menu

        }

    }

    static User userMenu(Scanner scanner) {
        String lineBreak = "------------------------------";
        boolean exit = false;
        String input;
        Validate validate = new Validate();
        User user = null;
        Predicate<String> rule;

        AddData addData = new AddData();

        ReadData readData = new ReadData();
        Map<Integer, User> users = readData.readUsers();

        System.out.println(lineBreak);
        System.out.println("Do you want to login or create a new user? Please type \"login\" or \"new\" followed by return");
        System.out.println("Or type \"exit\" followed by return to exit the program");

        rule = (String x) -> x.equalsIgnoreCase("login") | x.equalsIgnoreCase("new") | x.equalsIgnoreCase("exit");
        input = validate.getAndValidateInput(scanner, rule, "Please enter only \"login\" or \"new\" followed by return");

        switch (input) {
            case "login":
                System.out.println(lineBreak);
                System.out.println("Please enter the number of the user to login as");
                for (int i : users.keySet()){
                    System.out.println(i + ") " + users.get(i));
                }
                rule = (String x) -> users.containsKey(Integer.valueOf(x));
                input = validate.getAndValidateInput(scanner, rule, "Please enter a number listed above");
                user = users.get(input);
                break;
            case "new":
                user = addData.addUser(scanner, users);
                break;
        }

        // Need to load all transaction history into user object


        return user;
    }
}
