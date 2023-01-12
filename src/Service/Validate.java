package Service;

import Model.Debt;
import Model.User;

import java.util.Map;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Validate {

    public Predicate<String> dateFormat = x -> x.matches("^20\\d{2}-(?:0[1-9]|1[12])-(?:[0-2][0-9]|3[01])$");
    public Predicate<String> currencyAmount = x -> x.matches("^\\d{1,8}\\.\\d{2}$");
    public Predicate<String> interest = x -> x.matches("^\\d{0,2}\\.\\d{1,4}$");
    public Predicate<String> digits = x -> x.matches("\\d{1,3}");
    public Predicate<String> stringLength45 = x -> x.length() < 45;
    public Predicate<String> stringLength200 = x -> x.length() < 200;
    public Predicate<String> frequency = x -> x.equals("one-off") | x.equals("weekly") | x.equals("monthly");
    public Predicate<String> oneWord = x -> !x.contains(" ");
    public Predicate<String> emailAddress = x -> x.matches("^(.+)@(.+)$");
    public BiPredicate<String, Map<Integer, Debt>> validDebtId = (input, debts) -> {
        if (input == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return debts.containsKey(Integer.parseInt(input));
    };

    public String getAndValidateInput(Scanner scanner, Predicate<String> rule, String message){
        String input = scanner.nextLine();
        if (!rule.test(input)){
            System.out.println(message);
            input = getAndValidateInput(scanner, rule, message);
        }
        return input;
    }

    public String getAndValidateInput(Scanner scanner, BiPredicate<String, Map<Integer, Debt>> rule, String message, Map<Integer, Debt> debts){
        String input = scanner.nextLine();
        if (!rule.test(input, debts)){
            System.out.println(message);
            input = getAndValidateInput(scanner, rule, message, debts);
        }
        return input;
    }
}
