package Service;

import Model.*;

import java.util.Map;
import java.util.stream.Collectors;

public class PrintObjects {

    public <T extends Transaction> void printIncomesBetweenDates(Map<Integer, T> items, String dateFrom, String dateTo){
        Map<Integer, T> filteredItems = items
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getDate().compareTo(dateFrom) > 0 & entry.getValue().getDate().compareTo(dateTo) < 0)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue));

        printIncomes((Map<Integer, Income>) filteredItems);
    }

    public <T extends Transaction> void printExpensesBetweenDates(Map<Integer, T> items, String dateFrom, String dateTo){
        Map<Integer, T> filteredItems = items
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getDate().compareTo(dateFrom) > 0 & entry.getValue().getDate().compareTo(dateTo) < 0)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue));

        printExpenses((Map<Integer, Expense>) filteredItems);
    }

    public <T extends Transaction> void printDebtPaymentsBetweenDates(Map<Integer, T> items, String dateFrom, String dateTo){
        Map<Integer, T> filteredItems = items
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getDate().compareTo(dateFrom) > 0 & entry.getValue().getDate().compareTo(dateTo) < 0)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue));

        printDebtPayments((Map<Integer, DebtPayment>) filteredItems);
    }

    public <T extends Transaction> void printDebtsBetweenDates(Map<Integer, T> items, String dateFrom, String dateTo){
        Map<Integer, T> filteredItems = items
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getDate().compareTo(dateFrom) > 0 & entry.getValue().getDate().compareTo(dateTo) < 0)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue));

        printDebts((Map<Integer, Debt>) filteredItems);
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
        String leftAlignFormat = "| %-6d | %-15s | %-14.2f | %-7.4f | %-4d | %-10s | %-14.2f | %-12s |%n";
        Debt debt;

        System.out.format("+--------+-----------------+----------------+---------+------+------------+----------------+--------------+%n");
        System.out.format("| ID     | Lender name     | Initial Amount | Rate    | Term | Start Date | Payment Amount | Payment Date |%n");
        System.out.format("+--------+-----------------+----------------+---------+------+------------+----------------+--------------+%n");
        for (int id : debts.keySet()){
            debt = debts.get(id);
            System.out.format(leftAlignFormat, debt.getId(), debt.getLenderName(), debt.getInitialAmount(), debt.getInterestRate(),
                    debt.getTermMonths(), debt.getDate(), debt.getPaymentAmount(), debt.getPaymentDate());
        }
        System.out.format("+--------+-----------------+----------------+---------+------+------------+----------------+--------------+%n");
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
}