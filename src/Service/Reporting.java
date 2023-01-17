package Service;

import Data.ReadData;
import Data.ReadSummaryData;
import Model.Debt;
import Model.DebtPayment;
import Model.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Scanner;

public class Reporting {

    String endOfLastMonth = LocalDate.now().withDayOfMonth(1).minusDays(1).toString();
    String beginningOfLastMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1).toString();
    ReadSummaryData readSummaryData = new ReadSummaryData();

    public BigDecimal futureBalance(User user, String month){
        BigDecimal endOfLastMonthBalance = readSummaryData.totalIncomeUntoDate(user.getId(), endOfLastMonth)
                .subtract(readSummaryData.totalExpensesUntoDate(user.getId(), endOfLastMonth))
                .subtract(readSummaryData.totalDebtPaymentsUntoDate(user.getId(), endOfLastMonth));
        Map<String, BigDecimal> lastMonthIncomeByFrequency = readSummaryData.monthIncomeByFrequency(user.getId(), beginningOfLastMonth);
        BigDecimal lastMonthRecurringIncome = lastMonthIncomeByFrequency.get("monthly").add(lastMonthIncomeByFrequency.get("weekly"));
        Map<String, BigDecimal> lastMonthExpenseByFrequency = readSummaryData.monthExpensesByFrequency(user.getId(), beginningOfLastMonth);
        BigDecimal lastMonthRecurringExpenses = lastMonthExpenseByFrequency.get("weekly").add(lastMonthExpenseByFrequency.get("monthly"));
        BigDecimal lastMonthDebtPayments = readSummaryData.monthDebtPayments(user.getId(), beginningOfLastMonth);
        BigDecimal monthlyRecurringPnL = lastMonthRecurringIncome.subtract(lastMonthRecurringExpenses).subtract(lastMonthDebtPayments);

        BigDecimal monthsInFuture = BigDecimal.valueOf((LocalDate.parse(month).getMonthValue() - LocalDate.now().getMonthValue()) +
                (12 * (LocalDate.parse(month).getYear() - LocalDate.now().getYear())));

        return endOfLastMonthBalance.add(monthsInFuture.multiply(monthlyRecurringPnL));

    }

    public void getFutureBalance(Scanner scanner, User user){
        Validate validate = new Validate();

        System.out.println("What date in the future would you like to see your balance? Please enter a date at the beginning of a month e.g. '2025-01-01'");
        String month = validate.getAndValidateInput(scanner, validate.dateFormatMonth, "Please enter a date in the format 'YYYY-MM-01'");
        System.out.println(futureBalance(user, month));
    }

    public void debtPaymentsUntoDate(Scanner scanner, User user){

    }

    public BigDecimal calculateInterestOwedUntoDate(Debt debt, String date){
        ReadData readData = new ReadData();
        // Might need to check if debtpaymentid is null
        DebtPayment mostRecentPayment = debt.getDebtPayments().get(readData.getMostRecentDebtPaymentId(debt));
        BigDecimal balance = mostRecentPayment.getCurrentBalance();
        String balanceDate = mostRecentPayment.getDate();
        BigDecimal interestRate = debt.getInterestRate();
        BigDecimal daysSinceLastPayment = BigDecimal.valueOf(LocalDate.parse(balanceDate).until(LocalDate.parse(balanceDate), ChronoUnit.DAYS));

        return balance.multiply(daysSinceLastPayment).multiply(interestRate.divide(new BigDecimal("365"), RoundingMode.HALF_EVEN));
    }
}
