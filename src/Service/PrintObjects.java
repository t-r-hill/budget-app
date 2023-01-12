package Service;

import Model.*;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PrintObjects {

    public Map<Integer, Income> printIncomesBetweenDates(Map<Integer, Income> items, Scanner scanner){
        String[] dates =  getDatesFromUser(scanner);

        Map<Integer, Income> filteredItems = items
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getDate().compareTo(dates[0]) > 0 & entry.getValue().getDate().compareTo(dates[1]) < 0)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue));

        printIncomes(filteredItems);
        return filteredItems;
    }

    public Map<Integer, Expense> printExpensesBetweenDates(Map<Integer, Expense> items, Scanner scanner){
        String[] dates = getDatesFromUser(scanner);

        Map<Integer, Expense> filteredItems = items
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getDate().compareTo(dates[0]) > 0 & entry.getValue().getDate().compareTo(dates[1]) < 0)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue));

        printExpenses(filteredItems);
        return filteredItems;
    }

    public Map<Integer, DebtPayment> printDebtPaymentsBetweenDates(Map<Integer, Debt> debts, Scanner scanner){
        int debtId;
        Map<Integer, DebtPayment> debtPayments;
        Validate validate = new Validate();

        if (!debts.isEmpty()){
            System.out.println("Please enter the ID of the debt which you want to view payments for, from the table below");
            printDebts(debts);
            debtId = Integer.parseInt(validate.getAndValidateInput(scanner, validate.validId, "Please only enter an ID from the table above", debts));
            debtPayments = debts.get(debtId).getDebtPayments();

            String[] dates = getDatesFromUser(scanner);

            Map<Integer, DebtPayment> filteredItems = debtPayments
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().getDate().compareTo(dates[0]) > 0 & entry.getValue().getDate().compareTo(dates[1]) < 0)
                    .collect(Collectors.toMap(Map.Entry::getKey,
                            Map.Entry::getValue));

            printDebtPayments(filteredItems);
        } else {
            System.out.println("You need to create a debt before you can have a debt payment");
        }
        return null;
    }

    public Map<Integer, Debt> printDebtsBetweenDates(Map<Integer, Debt> items, Scanner scanner){
        String[] dates = getDatesFromUser(scanner);

        Map<Integer, Debt> filteredItems = items
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getDate().compareTo(dates[0]) > 0 & entry.getValue().getDate().compareTo(dates[1]) < 0)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue));

        printDebts(filteredItems);
        return filteredItems;
    }
    public void printIncomes(Map<Integer, Income> incomes){
        String leftAlignFormat = "| %-6d | %-10.2f | %-9s | %-10s | %-15s |%n";
        Income income;

        System.out.format("+--------+------------+-----------+------------+-----------------+%n");
        System.out.format("| ID     | Amount     | Frequency | Date       | Source          |%n");
        System.out.format("+--------+------------+-----------+------------+-----------------+%n");
        for (int id : incomes.keySet()){
            income = incomes.get(id);
            System.out.format(leftAlignFormat, income.getId(), income.getAmount(), income.getFreq(), income.getDate(),
                    income.getSource());
        }
        System.out.format("+--------+------------+-----------+------------+-----------------+%n");
    }

    public void printDebts(Map<Integer, Debt> debts){
        String leftAlignFormat = "| %-6d | %-23s | %-14.2f | %-7.4f | %-4d | %-10s | %-14.2f | %-12s |%n";
        Debt debt;

        System.out.format("+--------+-------------------------+----------------+---------+------+------------+----------------+--------------+%n");
        System.out.format("| ID     | Lender name             | Initial Amount | Rate    | Term | Start Date | Payment Amount | Payment Date |%n");
        System.out.format("+--------+-------------------------+----------------+---------+------+------------+----------------+--------------+%n");
        for (int id : debts.keySet()){
            debt = debts.get(id);
            System.out.format(leftAlignFormat, debt.getId(), debt.getLenderName(), debt.getInitialAmount(), debt.getInterestRate(),
                    debt.getTermMonths(), debt.getDate(), debt.getPaymentAmount(), debt.getPaymentDate());
        }
        System.out.format("+--------+-------------------------+----------------+---------+------+------------+----------------+--------------+%n");
    }

    public void printDebtPayments(Map<Integer, DebtPayment> debtPayments){
        String leftAlignFormat = "| %-6d | %-10.2f | %-17s | %-10s |%n";
        DebtPayment debtPayment;

        System.out.format("+--------+------------+-------------------+------------+%n");
        System.out.format("| ID     | Amount     | Remaining Balance | Date       |%n");
        System.out.format("+--------+------------+-------------------+------------+%n");
        for (int id : debtPayments.keySet()){
            debtPayment = debtPayments.get(id);
            System.out.format(leftAlignFormat, debtPayment.getId(), debtPayment.getAmount(),
                    debtPayment.getCurrentBalance(), debtPayment.getDate());
        }
        System.out.format("+--------+------------+-------------------+------------+%n");
    }

    public void printExpenses(Map<Integer, Expense> expenses){
        String leftAlignFormat = "| %-6d | %-10.2f | %-9s | %-10s | %-15s | %-20s |%n";
        Expense expense;

        System.out.format("+--------+------------+-----------+------------+-----------------+----------------------+%n");
        System.out.format("| ID     | Amount     | Frequency | Date       | Category        | Description          |%n");
        System.out.format("+--------+------------+-----------+------------+-----------------+----------------------+%n");
        for (int id : expenses.keySet()){
            expense = expenses.get(id);
            System.out.format(leftAlignFormat, expense.getId(), expense.getAmount(), expense.getFreq(), expense.getDate(),
                    expense.getCategory(), expense.getDesc());
        }
        System.out.format("+--------+------------+-----------+------------+-----------------+----------------------+%n");
    }

    static String[] getDatesFromUser(Scanner scanner){
        Validate validate = new Validate();
        Predicate<String> dateFormat = x -> x.matches("^20\\d{2}-(?:0[1-9]|1[12])-(?:[0-2][0-9]|3[01])$");
        String[] dates;

        System.out.println("Please enter the least recent date in the range which you wish to view");
        String dateFrom = validate.getAndValidateInput(scanner, dateFormat,"Please enter a date in the format YYYY-MM-DD");

        System.out.println("Please enter the most recent date in the range which you wish to view");
        String dateTo = validate.getAndValidateInput(scanner, dateFormat,"Please enter a date in the format YYYY-MM-DD");

        dates = new String[]{dateFrom, dateTo};
        return dates;
    }
}
