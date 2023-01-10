package Controller;

import Data.ReadData;
import Model.User;
import Service.AccountSummary;
import Service.AddObject;
import Service.Validate;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    static String lineBreak = "------------------------------";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = userMenu(scanner);

        if (user != null){
            mainMenu(scanner, user);
        }
    }

    static void mainMenu(Scanner scanner, User user){
        boolean exit = false;
        String input;
        Predicate<String> rule;
        Validate validate = new Validate();
        AccountSummary accountSummary;

        while (!exit){
            System.out.println(lineBreak);
            System.out.println("What would you like to do? Please select one of the following options below by entering a number");
            System.out.println("1) View account summary");
            System.out.println("2) Add/Edit transactions");
            System.out.println("3) Delete transaction");
            System.out.println("4) View previous transactions");
            System.out.println("5) Run reports");
            System.out.println("6) Exit");

            rule = "123456"::contains;
            input = validate.getAndValidateInput(scanner, rule, "Please enter an option from the list above followed by return");

            switch (input) {
                case "1":
                    // Print account summary
                    accountSummary = new AccountSummary();
                    accountSummary.printSummary(user);
                    break;
                case "2":
                    // Add new transaction menu
                    break;
                case "3":
                    // Delete transaction menu
                    break;
                case "4":
                    // View transactions menu
                    break;
                case "5":
                    // Reports menu
                    break;
                case "6":
                    exit = true;
                    break;
            }
        }

    }
    static User userMenu(Scanner scanner) {
        String input;
        Validate validate = new Validate();
        User user = null;
        Predicate<String> rule;

        AddObject addObject = new AddObject();

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
                user = users.get(Integer.parseInt(input));
                break;
            case "new":
                user = addObject.addUser(scanner, users);
                break;
        }

        if (user != null) user.loadObjects();

        return user;
    }

    static void addUpdateTransactionsMenu(Scanner scanner, User user){
        boolean exit = false;
        String input;
        Predicate<String> rule;
        Validate validate = new Validate();

        while (!exit){
            System.out.println(lineBreak);
            System.out.println("What do you want add?");
            System.out.println("1) Income");
            System.out.println("2) Expense");
            System.out.println("3) Debt");
            System.out.println("4) Debt Payment");
            System.out.println("5) Return to main menu");

            rule = "1234"::contains;
            input = validate.getAndValidateInput(scanner, rule, "Please enter an option from the list above followed by return");

            switch (input) {
                case "1":
                    // Add income

                    break;
                case "2":
                    // Add expense
                    break;
                case "3":
                    // Add debt
                    break;
                case "4":
                    // Add debt payment
                    break;
                case "5":
                    // Exit loop and return to main menu
                    exit = true;
                    break;

            }
        }
    }
}
