package Service;

import Data.CreateData;
import Model.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class AddObject {
    Validate validate;
    CreateData createData;

    String lineBreak = "------------------------------";

    public AddObject(){
        validate = new Validate();
        createData = new CreateData();
    }
    public User addUser(Scanner scanner, Map<Integer, User> users){
        String firstName;
        String lastName;
        String email;
        int id;

        Predicate<String> oneWord = x -> !x.contains(" ");
        Predicate<String> emailAddress = x -> x.matches("^(.+)@(.+)$");

        System.out.println(lineBreak);

        System.out.println("Please enter your first name");
        firstName = validate.getAndValidateInput(scanner, oneWord, "Please enter your first name as one word");

        System.out.println("Please enter your last name");
        lastName = validate.getAndValidateInput(scanner, oneWord, "Please enter your last name as one word");

        System.out.println("Please enter your email");
        email = validate.getAndValidateInput(scanner, emailAddress, "Please enter your email name as one word");

        id = createData.createUser(firstName, lastName, email);
        User user = new User(firstName, lastName, email, id);

        if (id > 0){
            System.out.println("Your user has been created");
            System.out.println(user);
            users.put(id, user);
        } else{
            System.out.println("There's been an error creating the user, please try again");
        }
        return user;
    }

    public void addExpense(Scanner scanner, User user){
        BigDecimal amount;
        String freq;
        String date;
        String category;
        String desc;
        int id;

        Predicate<String> stringLength45 = x -> Expense.categories.contains(x);
        Predicate<String> stringLength200 = x -> x.length() < 200;
        Predicate<String> currencyAmount = x -> x.matches("^\\d{1,8}\\.\\d{2}$");
        Predicate<String> dateFormat = x -> x.matches("^20\\d{2}-(?:0[1-9]|1[12])-(?:[0-2][0-9]|3[01])$");
        Predicate<String> frequency = x -> x.equals("one-off") | x.equals("weekly") | x.equals("monthly");

        System.out.println(lineBreak);

        System.out.println("Please enter the value of the expense");
        amount = new BigDecimal(validate.getAndValidateInput(scanner, currencyAmount, "Please enter an amount in the format xxxx.00"));

        System.out.println("Please enter the date of the expense");
        date = validate.getAndValidateInput(scanner, dateFormat, "Please enter the date as YYYY-MM-DD");

        System.out.println("Please enter whether this is a recurring expense. Type 'one-off', 'weekly' or 'monthly'");
        freq = validate.getAndValidateInput(scanner, frequency, "Please enter only 'one-off', 'weekly' or 'monthly'");

        System.out.println("Please enter the category of the expense:");
        System.out.println(Expense.categories.toString());
        category = validate.getAndValidateInput(scanner, stringLength45, "Please only enter one of the above values");

        System.out.println("Please enter a description of the expense");
        desc = validate.getAndValidateInput(scanner, stringLength200, "Please enter no more than 200 characters");

        id = createData.createExpense(user.getId(), amount, freq, date, category, desc);
        Expense expense = new Expense(user.getId(), amount, freq, date, category, desc, id);

        if (id > 0){
            System.out.println("Your income has been added");
            System.out.println(expense);
            user.getExpenses().put(id, expense);
        } else{
            System.out.println("There's been an error adding the expense, please try again");
        }
    }

    public void addIncome(Scanner scanner, User user){
        BigDecimal amount;
        String freq;
        String date;
        String source;
        int id;

        Predicate<String> stringLength = x -> x.length() < 45;
        Predicate<String> currencyAmount = x -> x.matches("^\\d{1,8}\\.\\d{2}$");
        Predicate<String> dateFormat = x -> x.matches("^20\\d{2}-(?:0[1-9]|1[12])-(?:[0-2][0-9]|3[01])$");
        Predicate<String> frequency = x -> x.equals("one-off") | x.equals("weekly") | x.equals("monthly");

        System.out.println(lineBreak);

        System.out.println("Please enter the value of the income");
        amount = new BigDecimal(validate.getAndValidateInput(scanner, currencyAmount, "Please enter an amount in the format xxxx.00"));

        System.out.println("Please enter the date you received the income");
        date = validate.getAndValidateInput(scanner, dateFormat, "Please enter the date as YYYY-MM-DD");

        System.out.println("Please enter whether this is a recurring income. Type 'one-off', 'weekly' or 'monthly'");
        freq = validate.getAndValidateInput(scanner, frequency, "Please enter only 'one-off', 'weekly' or 'monthly'");

        System.out.println("Please enter the source of the income");
        source = validate.getAndValidateInput(scanner, stringLength, "Please enter no more than 45 characters");

        id = createData.createIncome(user.getId(), amount, freq, date, source);
        Income income = new Income(user.getId(), amount, freq, date, source, id);

        if (id > 0){
            System.out.println("Your income has been added");
            System.out.println(income);
            user.getIncomes().put(id, income);
        } else{
            System.out.println("There's been an error adding the income, please try again");
        }
    }

    public void addDebt(Scanner scanner, User user){
        BigDecimal initialAmount;
        BigDecimal interestRate;
        BigDecimal paymentAmount;
        int termMonths;
        String lenderName;
        String startDate;
        String paymentDate;
        int id;

        Predicate<String> stringLength = x -> x.length() < 45;
        Predicate<String> currencyAmount = x -> x.matches("^\\d{1,8}\\.\\d{2}$");
        Predicate<String> dateFormat = x -> x.matches("^20\\d{2}-(?:0[1-9]|1[12])-(?:[0-2][0-9]|3[01])$");
        Predicate<String> interest = x -> x.matches("^\\d{0,2}\\.\\d{1,4}$");
        Predicate<String> digits = x -> x.matches("\\d{1,3}");

        System.out.println(lineBreak);

        System.out.println("Please enter the value of the initial amount of the debt");
        initialAmount = new BigDecimal(validate.getAndValidateInput(scanner, currencyAmount, "Please enter an amount in the format xxxx.00"));

        System.out.println("Please enter the amount of the monthly payment");
        paymentAmount = new BigDecimal(validate.getAndValidateInput(scanner, currencyAmount, "Please enter an amount in the format xxxx.00"));

        System.out.println("Please enter the interest rate as a percent with at least one decimal point eg. 1.99");
        interestRate = new BigDecimal(validate.getAndValidateInput(scanner, interest, "Please enter an interest rate with at least one dp "));

        System.out.println("Please enter the term of the loan in months");
        termMonths = Integer.parseInt(validate.getAndValidateInput(scanner, digits, "Please enter a term up to 999"));

        System.out.println("Please enter the date the loan was originated");
        startDate = validate.getAndValidateInput(scanner, dateFormat, "Please enter the date as YYYY-MM-DD");

        System.out.println("Please enter the date of your first recurring monthly payment");
        paymentDate = validate.getAndValidateInput(scanner, dateFormat, "Please enter the date as YYYY-MM-DD");

        System.out.println("Please enter the name of the lender for the loan");
        lenderName = validate.getAndValidateInput(scanner, stringLength, "Please enter no more than 45 characters");

        id = createData.createDebt(user.getId(), initialAmount, lenderName, interestRate, termMonths, startDate, paymentDate, paymentAmount);
        Debt debt = new Debt(user.getId(), lenderName, initialAmount, interestRate, termMonths, startDate, paymentAmount, paymentDate, 0, id);

        if (id > 0){
            System.out.println("Your debt has been added");
            System.out.println(debt);
            user.getDebts().put(id, debt);
        } else{
            System.out.println("There's been an error adding the debt, please try again");
        }
    }

    public void addDebtPayment(Scanner scanner, User user){
        int debtId = 0;
        BigDecimal amount;
        BigDecimal currentBalance;
        String date;
        int id;

        Predicate<String> currencyAmount = x -> x.matches("^\\d{1,8}\\.\\d{2}$");
        Predicate<String> dateFormat = x -> x.matches("^20\\d{2}-(?:0[1-9]|1[12])-(?:[0-2][0-9]|3[01])$");

        System.out.println(lineBreak);

        System.out.println("Please enter the value of the payment");
        amount = new BigDecimal(validate.getAndValidateInput(scanner, currencyAmount, "Please enter an amount in the format xxxx.00"));

        System.out.println("Please enter the date you made the payment");
        date = validate.getAndValidateInput(scanner, dateFormat, "Please enter the date as YYYY-MM-DD");

        System.out.println("Please enter the value of the payment");
        currentBalance = new BigDecimal(validate.getAndValidateInput(scanner, currencyAmount, "Please enter an amount in the format xxxx.00"));

        // Print out debts and ask user to select which one

        id = createData.createDebtPayment(debtId, amount, date, currentBalance);
        DebtPayment debtPayment = new DebtPayment(debtId, date, amount, currentBalance, id);

        if (id > 0){
            System.out.println("Your debt payment has been added");
            System.out.println(debtPayment);
            user.getDebts().get(debtId).getDebtPayments().put(id, debtPayment);
        } else{
            System.out.println("There's been an error adding the income, please try again");
        }
    }
}
