package Service;

import Data.ReadSummaryData;
import Model.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class AccountSummary {
    ReadSummaryData readSummaryData;
    String lastMonth;
    String today;
    static String lineBreak = "------------------------------";

    public AccountSummary(){
        readSummaryData = new ReadSummaryData();
        today = LocalDate.now().toString();
        lastMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1).toString();
    }

    public BigDecimal accountBalance(User user){
        BigDecimal income = readSummaryData.totalIncomeUntoDate(user.getId(), today);
        BigDecimal expenses = readSummaryData.totalExpensesUntoDate(user.getId(), today);
        BigDecimal debtPayments = readSummaryData.totalDebtPaymentsUntoDate(user.getId(), today);

        return income.subtract(expenses).subtract(debtPayments);
    }

    public BigDecimal lastMonthIncome(User user){
        return readSummaryData.monthIncome(user.getId(), lastMonth);
    }

    public BigDecimal lastMonthExpenses(User user){
        BigDecimal expenses = readSummaryData.monthExpenses(user.getId(), lastMonth);
        BigDecimal debtPayments = readSummaryData.monthDebtPayments(user.getId(), lastMonth);

        return expenses.add(debtPayments);
    }

    public BigDecimal lastMonthPnL(User user){
        return lastMonthIncome(user).subtract(lastMonthExpenses(user));
    }

    public BigDecimal totalDebt(User user){
        return readSummaryData.totalDebts(user.getId());
    }

    public BigDecimal cashToDebtRatio(User user){
        return accountBalance(user).divide(totalDebt(user), RoundingMode.HALF_EVEN);
    }

    public void printSummary(User user){

        String leftAlignFormat = "| %-30s | %-10.2f |%n";

        System.out.format("+---------------------------------------------+%n");
        System.out.format("|               Account Summary               |%n");
        System.out.format("+--------------------------------+------------+%n");
        System.out.format(leftAlignFormat, "Current balance", accountBalance(user));
        System.out.format(leftAlignFormat, "Current balance of total debt", totalDebt(user));
        System.out.format(leftAlignFormat, "Cash to Debt Ratio", cashToDebtRatio(user));
        System.out.format(leftAlignFormat, "Last month's income", lastMonthIncome(user));
        System.out.format(leftAlignFormat, "Last month's expenses", lastMonthExpenses(user));
        System.out.format(leftAlignFormat, "Last month's PnL", lastMonthPnL(user));
        System.out.format("+--------------------------------+------------+%n");
    }
}
