package Service;

import Data.DeleteData;
import Model.*;

import java.util.Map;
import java.util.Scanner;

public class DeleteObjects {

    public void deleteIncome(Scanner scanner, User user){
        Map<Integer, Income> incomes;
        Validate validate = new Validate();
        DeleteData deleteData = new DeleteData();
        PrintObjects printObjects = new PrintObjects();
        int id;
        int deleted;

        System.out.println("Let's find the transaction which you wish to delete");
        incomes = printObjects.printIncomesBetweenDates(user.getIncomes(), scanner);

        if (!incomes.isEmpty()){
            System.out.println("Please enter the ID of the transaction which you want to delete");
            id = Integer.parseInt(validate.getAndValidateInput(scanner, validate.validId, "Please enter an ID from the list above", incomes));
            deleted = deleteData.deleteIncome(id);

            if (deleted > 0){
                System.out.println("The income has been deleted");
                System.out.println(incomes.get(id));
                user.getIncomes().remove(id);
            } else{
                System.out.println("There's been an error deleting the income, please try again");
            }
        } else{
            System.out.println("You have no incomes during this time period");
        }
    }

    public void deleteExpense(Scanner scanner, User user){
        Map<Integer, Expense> expenses;
        Validate validate = new Validate();
        DeleteData deleteData = new DeleteData();
        PrintObjects printObjects = new PrintObjects();
        int id;
        int deleted;

        System.out.println("Let's find the transaction which you wish to delete");
        expenses = printObjects.printExpensesBetweenDates(user.getExpenses(), scanner);

        if (!expenses.isEmpty()){
            System.out.println("Please enter the ID of the transaction which you want to delete");
            id = Integer.parseInt(validate.getAndValidateInput(scanner, validate.validId, "Please enter an ID from the list above", expenses));
            deleted = deleteData.deleteIncome(id);

            if (deleted > 0){
                System.out.println("The expense has been deleted");
                System.out.println(expenses.get(id));
                user.getDebts().remove(id);
            } else{
                System.out.println("There's been an error deleting the expense, please try again");
            }
        } else{
            System.out.println("You have no expenses during this time period");
        }
    }

    public void deleteDebt(Scanner scanner, User user){
        Map<Integer, Debt> debts;
        Validate validate = new Validate();
        DeleteData deleteData = new DeleteData();
        PrintObjects printObjects = new PrintObjects();
        int id;
        int deleted;

        System.out.println("Let's find the transaction which you wish to delete");
        debts = printObjects.printDebtsBetweenDates(user.getDebts(), scanner);
        if (!debts.isEmpty()){
            System.out.println("Please enter the ID of the transaction which you want to delete");
            id = Integer.parseInt(validate.getAndValidateInput(scanner, validate.validId, "Please enter an ID from the list above", debts));
            if (!debts.get(id).getDebtPayments().isEmpty()){
                deleted = deleteData.deleteDebt(id);

                if (deleted > 0){
                    System.out.println("The debt has been deleted");
                    System.out.println(debts.get(id));
                    user.getDebts().remove(id);
                } else{
                    System.out.println("There's been an error deleting the debt, please try again");
                }
            } else {
                System.out.println("There are debt payments recorded to this debt, please delete those first");
            }

        } else{
            System.out.println("You have no debts started during this time");
        }
    }

    public void deleteDebtPayment(Scanner scanner, User user){
        Map<Integer, DebtPayment> debtPayments;
        Validate validate = new Validate();
        DeleteData deleteData = new DeleteData();
        PrintObjects printObjects = new PrintObjects();
        int id;
        int deleted;
        if (!user.getDebts().isEmpty()){
            System.out.println("Let's find the transaction which you wish to delete");
            debtPayments = printObjects.printDebtPaymentsBetweenDates(user.getDebts(), scanner);
            if (!debtPayments.isEmpty()){
                System.out.println("Please enter the ID of the debt payment which you want to delete a payment from");
                id = Integer.parseInt(validate.getAndValidateInput(scanner, validate.validId, "Please enter an ID from the list above", debtPayments));
                deleted = deleteData.deleteDebtPayment(id);

                if (deleted > 0){
                    System.out.println("The debt has been deleted");
                    System.out.println(debtPayments.get(id));
                    debtPayments.remove(id);
                } else{
                    System.out.println("There's been an error deleting the debt, please try again");
                }
            } else {
                System.out.println("There are no debt payments related to this debt during this time period");
            }

        } else{
            System.out.println("You have no debts or debt payments to delete");
        }
    }
}
