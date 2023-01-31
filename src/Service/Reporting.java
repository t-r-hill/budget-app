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
import java.util.*;
import java.util.stream.IntStream;

import static Service.AccountSummary.lineBreak;


public class Reporting {

    String endOfLastMonth = LocalDate.now().withDayOfMonth(1).minusDays(1).toString();
    String beginningOfLastMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1).toString();
    ReadSummaryData readSummaryData = new ReadSummaryData();
    ReadData readData = new ReadData();

    public Map<LocalDate, BigDecimal> futureBalances(User user, String months){
        BigDecimal endOfLastMonthBalance = readSummaryData.totalIncomeUntoDate(user.getId(), endOfLastMonth)
                .subtract(readSummaryData.totalExpensesUntoDate(user.getId(), endOfLastMonth))
                .subtract(readSummaryData.totalDebtPaymentsUntoDate(user.getId(), endOfLastMonth));

        Map<String, BigDecimal> lastMonthIncomeByFrequency = readSummaryData.monthIncomeByFrequency(user.getId(), beginningOfLastMonth);
        BigDecimal lastMonthRecurringIncome = lastMonthIncomeByFrequency.getOrDefault("monthly", BigDecimal.ZERO).add(lastMonthIncomeByFrequency.getOrDefault("weekly", BigDecimal.ZERO));

        Map<String, BigDecimal> lastMonthExpenseByFrequency = readSummaryData.monthExpensesByFrequency(user.getId(), beginningOfLastMonth);
        BigDecimal lastMonthRecurringExpenses = lastMonthExpenseByFrequency.getOrDefault("weekly", BigDecimal.ZERO).add(lastMonthExpenseByFrequency.getOrDefault("monthly", BigDecimal.ZERO));

        BigDecimal lastMonthDebtPayments = readSummaryData.monthDebtPayments(user.getId(), beginningOfLastMonth);

        BigDecimal monthlyRecurringPnL = lastMonthRecurringIncome.subtract(lastMonthRecurringExpenses).subtract(lastMonthDebtPayments);

        Map<LocalDate, BigDecimal> monthlyBalances = IntStream
                .range(1, Integer.parseInt(months) + 1)
                .collect(
                        HashMap::new,
                        (m, i) -> m.put(
                                LocalDate.parse(endOfLastMonth).plusMonths(i),
                                endOfLastMonthBalance.add(monthlyRecurringPnL.multiply(BigDecimal.valueOf(i)))),
                        Map::putAll
                );

        return monthlyBalances;

    }

    public void printMonthlyBalances(Map<LocalDate, BigDecimal> balances){
            String leftAlignFormat = "| %-10tD | %-10.2f |%n";
            List<LocalDate> sortedDates = new ArrayList<>(balances.keySet());
            sortedDates.sort(null);

            System.out.format("+------------+------------+%n");
            System.out.format("| Date       | Balance    |%n");
            System.out.format("+------------+------------+%n");
                for (LocalDate month : sortedDates){
                    System.out.format(leftAlignFormat, month, balances.get(month));
                }
            System.out.format("+------------+------------+%n");
    }

    public void getFutureBalances(Scanner scanner, User user){
        Validate validate = new Validate();

        System.out.println("How many months in the future would you like to see your balance?");
        String months = validate.getAndValidateInput(scanner, validate.integer, "Please enter an integer");
        printMonthlyBalances(futureBalances(user, months));
    }

    public List<LocalDate> scheduledDebtPaymentDates(Debt debt, boolean fromInitial){
        List<LocalDate> paymentDates = new ArrayList<>();
        LocalDate paymentDate;
        if (fromInitial){
            paymentDate = LocalDate.parse(debt.getPaymentDate());
        } else {
            paymentDate = LocalDate.parse(debt.getDebtPayments().get(readData.getMostRecentDebtPaymentId(debt)).getDate());
        }
        for (int i = 0; i < debt.getTermMonths(); i++){
            paymentDates.add(paymentDate.plusMonths(i));
        }
        return paymentDates;
    }

    public Map<LocalDate, BigDecimal> scheduledDebtBalances(Debt debt, boolean fromInitial){
        Map<LocalDate, BigDecimal> debtBalances = new HashMap<>();
        BigDecimal interestOwed;
        BigDecimal balanceAtDate;
        LocalDate paymentDate;
        DebtPayment mostRecentDebtPayment = debt.getDebtPayments().get(readData.getMostRecentDebtPaymentId(debt));
        if (fromInitial){
            balanceAtDate = debt.getInitialAmount();
            paymentDate = LocalDate.parse(debt.getPaymentDate());
            interestOwed = calculateInterestBetweenDates(debt.getInitialAmount(), debt.getInterestRate(), LocalDate.parse(debt.getDate()), paymentDate);
            balanceAtDate = balanceAtDate.add(interestOwed).subtract(debt.getPaymentAmount());
            debtBalances.put(paymentDate, balanceAtDate);
        } else {
            balanceAtDate = mostRecentDebtPayment.getCurrentBalance();
            paymentDate = LocalDate.parse(debt.getDebtPayments().get(readData.getMostRecentDebtPaymentId(debt)).getDate());
        }
        for (int i = 1; balanceAtDate.compareTo(BigDecimal.ZERO) == 1; i++){
            interestOwed = calculateInterestBetweenDates(balanceAtDate, debt.getInterestRate(),paymentDate.plusMonths(i-1), paymentDate.plusMonths(i));
            balanceAtDate = balanceAtDate.add(interestOwed).subtract(debt.getPaymentAmount());
            debtBalances.put(paymentDate.plusMonths(i),balanceAtDate);
        }
        return debtBalances;
    }

    public void getScheduledDebtBalancesFromInitialAmount(Scanner scanner, User user) {
        int debtId;
        Validate validate = new Validate();
        PrintObjects printObjects = new PrintObjects();

        System.out.println(lineBreak);

        if (!user.getDebts().isEmpty()) {
            // Print out debts and ask user to select which one
            System.out.println("Please enter the ID of the debt which you want to add a payment for, from the table below");
            printObjects.printDebts(user.getDebts());
            debtId = Integer.parseInt(validate.getAndValidateInput(scanner, validate.validId, "Please only enter an ID from the table above", user.getDebts()));
            printMonthlyBalances(scheduledDebtBalances(user.getDebts().get(debtId), true));
        } else {
            System.out.println("You need to create a debt before you can have a debt payment");
        }
    }

    public void getScheduledDebtBalancesFromCurrentAmount(Scanner scanner, User user) {
        int debtId;
        Validate validate = new Validate();
        PrintObjects printObjects = new PrintObjects();

        System.out.println(lineBreak);

        if (!user.getDebts().isEmpty()) {
            // Print out debts and ask user to select which one
            System.out.println("Please enter the ID of the debt which you want to add a payment for, from the table below");
            printObjects.printDebts(user.getDebts());
            debtId = Integer.parseInt(validate.getAndValidateInput(scanner, validate.validId, "Please only enter an ID from the table above", user.getDebts()));
            printMonthlyBalances(scheduledDebtBalances(user.getDebts().get(debtId), false));
        } else {
            System.out.println("You need to create a debt before you can have a debt payment");
        }
    }

    public BigDecimal calculateInterestOwedUntoDate(Debt debt, String date){
        BigDecimal balance;
        LocalDate balanceDate;

        if (debt.getDebtPayments().isEmpty()){
            balance = debt.getInitialAmount();
            balanceDate = LocalDate.parse(debt.getDate());
        } else{
            DebtPayment mostRecentPayment = debt.getDebtPayments().get(readData.getMostRecentDebtPaymentId(debt));
            balance = mostRecentPayment.getCurrentBalance();
            balanceDate = LocalDate.parse(mostRecentPayment.getDate());
        }

        return calculateInterestBetweenDates(balance, debt.getInterestRate(), balanceDate, LocalDate.parse(date));
    }

    public String getDebtNextPaymentDate(Debt debt){
        LocalDate today = LocalDate.now();
        LocalDate nextPaymentDate;
        LocalDate paymentDate = LocalDate.parse(debt.getPaymentDate());
        if (today.getDayOfMonth() < paymentDate.getDayOfMonth()){
            nextPaymentDate = paymentDate.withMonth(today.getMonthValue()).withYear(today.getYear());
        } else {
            nextPaymentDate = paymentDate.withMonth(today.getMonthValue() + 1).withYear(today.getYear());
        }
        return nextPaymentDate.toString();
    }

    public long getDebtNumScheduledPaymentsToDate(Debt debt, LocalDate toDate){
        LocalDate paymentDate = LocalDate.parse(debt.getPaymentDate());
        long months;
        if (toDate.compareTo(paymentDate) == -1){
            months = 0L;
        } else {
            months = paymentDate.until(toDate, ChronoUnit.MONTHS) + 1;
        }
        return months;
    }

    public BigDecimal getDebtScheduledPaymentAmountToDate(Debt debt, LocalDate toDate){
        return debt.getPaymentAmount().multiply(BigDecimal.valueOf(getDebtNumScheduledPaymentsToDate(debt, toDate)));
    }

    public void printDebtStatuses(User user){
        String leftAlignFormat = "| %-6d | %-23s | %-10s | %-17.2f | %-18.2f | %-17s |%n";
        List<Integer> sortedDebtIds = new ArrayList<>(user.getDebts().keySet());
        sortedDebtIds.sort(null);
        Debt debt;
        String status;
        BigDecimal due;
        BigDecimal paid;
        String nextPaymentDate;
        LocalDate today = LocalDate.now();

        System.out.format("+--------+-------------------------+------------+-------------------+--------------------+-------------------+%n");
        System.out.format("| ID     | Lender name             | Status     | Total Payment Due | Total Payment Made | Next Payment Date |%n");
        System.out.format("+--------+-------------------------+------------+-------------------+--------------------+-------------------+%n");
        for (Integer debtId : sortedDebtIds) {
            debt = user.getDebts().get(debtId);
            due = getDebtScheduledPaymentAmountToDate(debt, today);
            paid = readSummaryData.totalDebtPaymentsUntoDateForDebt(debt.getId(), today.toString());
            status = due.compareTo(paid) == 1 ? "In Arrears" : "Up To Date";
            nextPaymentDate = getDebtNextPaymentDate(debt).toString();
            System.out.format(leftAlignFormat, debtId, debt.getLenderName(), status, due, paid, nextPaymentDate);
        }
        System.out.format("+--------+-------------------------+------------+-------------------+--------------------+-------------------+%n");
    }

    BigDecimal calculateInterestBetweenDates(BigDecimal initialBalance, BigDecimal interestRate, LocalDate fromDate, LocalDate toDate){
        BigDecimal days = BigDecimal.valueOf(fromDate.until(toDate, ChronoUnit.DAYS));
        BigDecimal dailyRate = interestRate
                .divide(new BigDecimal("36500"), 8, RoundingMode.HALF_EVEN);
        BigDecimal interestPerDay = dailyRate.multiply(initialBalance);
        return interestPerDay.multiply(days).setScale(2, RoundingMode.HALF_EVEN);
    }
}
