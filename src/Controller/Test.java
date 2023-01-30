package Controller;

import Data.CreateData;
import Data.ReadData;
import Model.Debt;
import Model.DebtPayment;
import Model.User;
import Service.PrintObjects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {

    public static void main(String[] args) {

//        PrintObjects printObjects = new PrintObjects();
//        Scanner scanner = new Scanner(System.in);
//        User user = Main.userMenu(scanner);
//
//        if (user != null){
//            printObjects.printExpenses(user);
//        }
        LocalDate date = LocalDate.parse("2021-12-29");
        BigDecimal balance = new BigDecimal("10000");
        BigDecimal monthlyPnL = new BigDecimal("542");

        LocalDate today = LocalDate.parse("2022-02-28");

        System.out.println(date.until(today, ChronoUnit.MONTHS));

    }

    static public BigDecimal calculateInterestOwedUntoDate(Debt debt, String date){
        ReadData readData = new ReadData();
        BigDecimal balance;
        String balanceDate;

        if (debt.getDebtPayments().isEmpty()){
            balance = debt.getInitialAmount();
            balanceDate = debt.getDate();
        } else{
            DebtPayment mostRecentPayment = debt.getDebtPayments().get(readData.getMostRecentDebtPaymentId(debt));
            balance = mostRecentPayment.getCurrentBalance();
            balanceDate = mostRecentPayment.getDate();
        }

        BigDecimal interestRate = debt.getInterestRate();
        BigDecimal daysSinceLastPayment = BigDecimal.valueOf(LocalDate.parse(balanceDate).until(LocalDate.parse(balanceDate), ChronoUnit.DAYS));

        return balance.multiply(daysSinceLastPayment).multiply(interestRate.divide(new BigDecimal("365"), RoundingMode.HALF_EVEN));
    }

}
