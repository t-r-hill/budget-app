package Service;

import Data.CreateData;
import Data.ReadData;
import Model.*;

import java.math.BigDecimal;
import java.util.HashMap;
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
        User user = null;

        System.out.println(lineBreak);

        System.out.println("Please enter your first name");
        firstName = validate.getAndValidateInput(scanner, validate.oneWord, "Please enter your first name as one word");

        System.out.println("Please enter your last name");
        lastName = validate.getAndValidateInput(scanner, validate.oneWord, "Please enter your last name as one word");

        System.out.println("Please enter your email");
        email = validate.getAndValidateInput(scanner, validate.emailAddress, "Please enter your email address in a valid xxxx@xxx.xxx format");

        id = createData.createUser(firstName, lastName, email);

        if (id > 0){
            System.out.println("Your user has been created");
            user = new User(firstName, lastName, email, id);
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
        Expense expense = null;

        System.out.println(lineBreak);

        System.out.println("Please enter the value of the expense");
        amount = new BigDecimal(validate.getAndValidateInput(scanner, validate.currencyAmount, "Please enter an amount in the format xxxx.00"));

        System.out.println("Please enter the date of the expense");
        date = validate.getAndValidateInput(scanner, validate.dateFormat, "Please enter the date as YYYY-MM-DD");

        System.out.println("Please enter whether this is a recurring expense. Type 'one-off', 'weekly' or 'monthly'");
        freq = validate.getAndValidateInput(scanner, validate.frequency, "Please enter only 'one-off', 'weekly' or 'monthly'");

        System.out.println("Please enter the category of the expense:");
        System.out.println(Expense.categories.toString());
        category = validate.getAndValidateInput(scanner, validate.stringLength45, "Please only enter one of the above values");

        System.out.println("Please enter a description of the expense");
        desc = validate.getAndValidateInput(scanner, validate.stringLength200, "Please enter no more than 200 characters");

        id = createData.createExpense(user.getId(), amount, freq, date, category, desc);

        if (id > 0){
            System.out.println("Your income has been added");
            expense = new Expense(user.getId(), amount, freq, date, category, desc, id);
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

        System.out.println(lineBreak);

        System.out.println("Please enter the value of the income");
        amount = new BigDecimal(validate.getAndValidateInput(scanner, validate.currencyAmount, "Please enter an amount in the format xxxx.00"));

        System.out.println("Please enter the date you received the income");
        date = validate.getAndValidateInput(scanner, validate.dateFormat, "Please enter the date as YYYY-MM-DD");

        System.out.println("Please enter whether this is a recurring income. Type 'one-off', 'weekly' or 'monthly'");
        freq = validate.getAndValidateInput(scanner, validate.frequency, "Please enter only 'one-off', 'weekly' or 'monthly'");

        System.out.println("Please enter the source of the income");
        source = validate.getAndValidateInput(scanner, validate.stringLength45, "Please enter no more than 45 characters");

        id = createData.createIncome(user.getId(), amount, freq, date, source);

        if (id > 0){
            System.out.println("Your income has been added");
            Income income = new Income(user.getId(), amount, freq, date, source, id);
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

        System.out.println(lineBreak);

        System.out.println("Please enter the value of the initial amount of the debt");
        initialAmount = new BigDecimal(validate.getAndValidateInput(scanner, validate.currencyAmount, "Please enter an amount in the format xxxx.00"));

        System.out.println("Please enter the amount of the monthly payment");
        paymentAmount = new BigDecimal(validate.getAndValidateInput(scanner, validate.currencyAmount, "Please enter an amount in the format xxxx.00"));

        System.out.println("Please enter the interest rate as a percent with at least one decimal point eg. 1.99");
        interestRate = new BigDecimal(validate.getAndValidateInput(scanner, validate.interest, "Please enter an interest rate with at least one dp "));

        System.out.println("Please enter the term of the loan in months");
        termMonths = Integer.parseInt(validate.getAndValidateInput(scanner, validate.digits, "Please enter a term up to 999"));

        System.out.println("Please enter the date the loan was originated");
        startDate = validate.getAndValidateInput(scanner, validate.dateFormat, "Please enter the date as YYYY-MM-DD");

        System.out.println("Please enter the date of your first recurring monthly payment");
        paymentDate = validate.getAndValidateInput(scanner, validate.dateFormat, "Please enter the date as YYYY-MM-DD");

        System.out.println("Please enter the name of the lender for the loan");
        lenderName = validate.getAndValidateInput(scanner, validate.stringLength45, "Please enter no more than 45 characters");

        id = createData.createDebt(user.getId(), initialAmount, lenderName, interestRate, termMonths, startDate, paymentDate, paymentAmount);

        if (id > 0){
            System.out.println("Your debt has been added");
            Debt debt = new Debt(user.getId(), lenderName, initialAmount, interestRate, termMonths, startDate, paymentAmount, paymentDate, 0, id);
            System.out.println(debt);
            user.getDebts().put(id, debt);
            user.getDebts().get(id).setDebtPayments(new HashMap<>());
        } else{
            System.out.println("There's been an error adding the debt, please try again");
        }
    }

    public void addDebtPayment(Scanner scanner, User user){
        int debtId;
        BigDecimal amount;
        BigDecimal currentBalance;
        String date;
        int id;
        PrintObjects printObjects = new PrintObjects();

        System.out.println(lineBreak);

        if (!user.getDebts().isEmpty()){
            // Print out debts and ask user to select which one
            System.out.println("Please enter the ID of the debt which you want to add a payment for, from the table below");
            printObjects.printDebts(user.getDebts());
            debtId = Integer.parseInt(validate.getAndValidateInput(scanner, validate.validId, "Please only enter an ID from the table above", user.getDebts()));

            System.out.println("Please enter the value of the payment");
            amount = new BigDecimal(validate.getAndValidateInput(scanner, validate.currencyAmount, "Please enter an amount in the format xxxx.00"));

            System.out.println("Please enter the date you made the payment");
            date = validate.getAndValidateInput(scanner, validate.dateFormat, "Please enter the date as YYYY-MM-DD");

            System.out.println("Please enter the remaining balance");
            currentBalance = calculateCurrentBalance(user.getDebts().get(debtId), amount, date);

            id = createData.createDebtPayment(debtId, amount, date, currentBalance);

            if (id > 0){
                System.out.println("Your debt payment has been added");
                DebtPayment debtPayment = new DebtPayment(debtId, date, amount, currentBalance, id);
                System.out.println(debtPayment);
                user.getDebts().get(debtId).getDebtPayments().put(id, debtPayment);
            } else{
                System.out.println("There's been an error adding the income, please try again");
            }
        } else{
            System.out.println("You need to create a debt before you can create a debt payment");
        }
    }

    public BigDecimal calculateCurrentBalance(Debt debt, BigDecimal paymentAmount, String paymentDate){
        ReadData readData = new ReadData();
        Reporting reporting = new Reporting();
        BigDecimal previousBalance = debt.getDebtPayments().get(readData.getMostRecentDebtPaymentId(debt)).getCurrentBalance();
        BigDecimal interestCharged = reporting.calculateInterestOwedUntoDate(debt, paymentDate);

        return previousBalance.add(interestCharged).subtract(paymentAmount);
    }
}
