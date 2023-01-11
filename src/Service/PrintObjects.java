package Service;

import Model.*;

import java.util.Map;

public class PrintObjects {

    public void printIncomes(User user){
        String leftAlignFormat = "| %-6d | %-10.2f | %-9s | %-10s | %-15s |%n";
        Map<Integer, Income> incomes = user.getIncomes();
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

    public void printDebts(User user){
        String leftAlignFormat = "| %-6d | %-15s | %-14.2f | %-7.4f | %-4d | %-10s | %-14.2f | %-12s |%n";
        Map<Integer, Debt> debts = user.getDebts();
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

    public void printDebtPayments(Debt debt){
        String leftAlignFormat = "| %-6d | %-10.2f | %-17s | %-10s |%n";
        Map<Integer, DebtPayment> debtPayments = debt.getDebtPayments();
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

    public void printExpenses(User user){
        String leftAlignFormat = "| %-6d | %-10.2f | %-9s | %-10s | %-15s | %-20s |%n";
        Map<Integer, Expense> expenses = user.getExpenses();
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
