package Service;

import Data.ReadData;
import Data.ReadSummaryData;
import Model.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountSummary {
    ReadSummaryData readSummaryData;
    String date;
    static String lineBreak = "------------------------------";

    public AccountSummary(){
        readSummaryData = new ReadSummaryData();
        date = LocalDate.now().minusMonths(1).withDayOfMonth(1).toString();
    }

    public BigDecimal accountBalance(User user){
        BigDecimal income = readSummaryData.totalIncome(user.getId());
        BigDecimal expenses = readSummaryData.totalExpenses(user.getId());
        BigDecimal debtPayments = readSummaryData.totalDebtPayments(user.getId());

        return income.subtract(expenses).subtract(debtPayments);
    }

    public BigDecimal lastMonthIncome(User user){
        BigDecimal income = readSummaryData.monthIncome(user.getId(), date);

        return income;
    }

    public BigDecimal lastMonthExpenses(User user){
        BigDecimal expenses = readSummaryData.monthExpenses(user.getId(), date);
        BigDecimal debtPayments = readSummaryData.monthDebtPayments(user.getId(), date);

        return expenses.add(debtPayments);
    }

    public BigDecimal lastMonthPnL(User user){
        BigDecimal income = readSummaryData.monthIncome(user.getId(), date);
        BigDecimal expenses = readSummaryData.monthExpenses(user.getId(), date);
        BigDecimal debtPayments = readSummaryData.monthDebtPayments(user.getId(), date);

        return income.subtract(expenses).subtract(debtPayments);
    }

    public BigDecimal totalDebt(User user){
        return readSummaryData.totalDebts(user.getId());
    }

    public void printSummary(User user){
        System.out.println(lineBreak);
        System.out.println("Current balance = " + accountBalance(user));
        System.out.println("Last month's income = "  + lastMonthIncome(user));
        System.out.println("Last month's expenses = " + lastMonthExpenses(user));
        System.out.println("Last month's PnL = " + lastMonthPnL(user));
        System.out.println("Current balance of total debt = " + totalDebt(user));
    }
}
