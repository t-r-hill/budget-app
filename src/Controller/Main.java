package Controller;

import Data.ReadData;
import Model.User;
import Service.*;

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
                    addTransactionsMenu(scanner, user);
                    break;
                case "3":
                    // Delete transaction menu
                    deleteTransactionsMenu(scanner, user);
                    break;
                case "4":
                    // View transactions menu
                    viewTransactionsMenu(scanner, user);
                    break;
                case "5":
                    // Reports menu
                    reportingMenu(scanner, user);
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

        input = validate.getAndValidateInput(scanner, validate.login, "Please enter only \"login\" or \"new\" followed by return");

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

    static void addTransactionsMenu(Scanner scanner, User user){
        boolean exit = false;
        String input;
        Predicate<String> rule;
        Validate validate = new Validate();
        AddObject addObject = new AddObject();

        while (!exit){
            System.out.println(lineBreak);
            System.out.println("What do you want add?");
            System.out.println("1) Income");
            System.out.println("2) Expense");
            System.out.println("3) Debt");
            System.out.println("4) Debt Payment");
            System.out.println("5) Return to main menu");

            rule = "12345"::contains;
            input = validate.getAndValidateInput(scanner, rule, "Please enter an option from the list above followed by return");

            switch (input) {
                case "1":
                    // Add income
                    addObject.addIncome(scanner, user);
                    break;
                case "2":
                    // Add expense
                    addObject.addExpense(scanner, user);
                    break;
                case "3":
                    // Add debt
                    addObject.addDebt(scanner, user);
                    break;
                case "4":
                    // Add debt payment
                    addObject.addDebtPayment(scanner, user);
                    break;
                case "5":
                    // Exit loop and return to main menu
                    exit = true;
                    break;

            }
        }
    }

    static void deleteTransactionsMenu(Scanner scanner, User user){
        boolean exit = false;
        String input;
        Predicate<String> rule;
        Validate validate = new Validate();
        DeleteObjects deleteObjects = new DeleteObjects();

        while (!exit){
            System.out.println(lineBreak);
            System.out.println("What do you want to delete?");
            System.out.println("1) Income");
            System.out.println("2) Expense");
            System.out.println("3) Debt");
            System.out.println("4) Debt Payment");
            System.out.println("5) Return to main menu");

            rule = "12345"::contains;
            input = validate.getAndValidateInput(scanner, rule, "Please enter an option from the list above followed by return");

            switch (input) {
                case "1":
                    // Delete income
                    deleteObjects.deleteIncome(scanner, user);
                    break;
                case "2":
                    // Delete expense
                    deleteObjects.deleteExpense(scanner, user);
                    break;
                case "3":
                    // Delete debt
                    deleteObjects.deleteDebt(scanner, user);
                    break;
                case "4":
                    // Delete debt payment
                    deleteObjects.deleteDebtPayment(scanner, user);
                    break;
                case "5":
                    // Exit loop and return to main menu
                    exit = true;
                    break;

            }
        }
    }

    static void viewTransactionsMenu(Scanner scanner, User user){
        boolean exit = false;
        String input;
        Predicate<String> rule;
        Validate validate = new Validate();
        PrintObjects printObjects = new PrintObjects();

        while (!exit){
            System.out.println(lineBreak);
            System.out.println("What do you want to view?");
            System.out.println("1) Income");
            System.out.println("2) Expense");
            System.out.println("3) Debt");
            System.out.println("4) Debt Payment");
            System.out.println("5) Return to main menu");

            rule = "12345"::contains;
            input = validate.getAndValidateInput(scanner, rule, "Please enter an option from the list above followed by return");

            switch (input) {
                case "1":
                    // Print income
                    printObjects.printIncomesBetweenDates(user.getIncomes(), scanner);
                    break;
                case "2":
                    // Print expense
                    printObjects.printExpensesBetweenDates(user.getExpenses(), scanner);
                    break;
                case "3":
                    // Print debt
                    printObjects.printDebtsBetweenDates(user.getDebts(), scanner);
                    break;
                case "4":
                    // Print debt payment
                    printObjects.printDebtPaymentsBetweenDates(user.getDebts(), scanner);
                    break;
                case "5":
                    // Exit loop and return to main menu
                    exit = true;
                    break;

            }
        }
    }

    static void reportingMenu(Scanner scanner, User user){
        boolean exit = false;
        String input;
        Predicate<String> rule;
        Validate validate = new Validate();
        Reporting reporting = new Reporting();

        while (!exit){
            System.out.println(lineBreak);
            System.out.println("What report do you want to run?");
            System.out.println("1) Future Balance Estimate");
            System.out.println("2) Predicted Debt Balances from current amount");
            System.out.println("3) Scheduled Debt Balances from initial amount");
            System.out.println("4) Debt Statuses");
            System.out.println("5) Return to main menu");

            rule = "12345"::contains;
            input = validate.getAndValidateInput(scanner, rule, "Please enter an option from the list above followed by return");

            switch (input) {
                case "1":
                    // Print Future Balance
                    reporting.getFutureBalances(scanner, user);
                    break;
                case "3":
                    // Print scheduled debt balances
                    reporting.getScheduledDebtBalancesFromInitialAmount(scanner, user);
                    break;
                case "2":
                    // Print predicted debt balances
                    reporting.getScheduledDebtBalancesFromCurrentAmount(scanner, user);
                    break;
                case "4":
                    // Print debt statuses
                    reporting.printDebtStatuses(user);
                    break;
                case "5":
                    // Exit loop and return to main menu
                    exit = true;
                    break;

            }
        }
    }
}
