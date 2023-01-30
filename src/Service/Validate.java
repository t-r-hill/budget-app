package Service;

import Model.Debt;
import Model.Transaction;
import Model.User;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Validate {

    public Predicate<String> dateFormat = x -> {
        if (x == null) return false;
        try{
            LocalDate.parse(x);
        } catch (DateTimeParseException dtpe){
            return false;
        }
        return true;
    };
    public Predicate<String> dateFormatMonth = x -> {
        if (x == null) return false;
        try{
            LocalDate.parse(x);
        } catch (DateTimeParseException dtpe){
            return false;
        }
        return LocalDate.parse(x).getDayOfMonth() == 1;
    };
    public Predicate<String> integer = x -> {
        try{
            Integer.parseInt(x);
        } catch (NumberFormatException nfe){
            return false;
        }
        return true;
    };
    public Predicate<String> currencyAmount = x -> x.matches("^\\d{1,8}\\.\\d{2}$");
    public Predicate<String> interest = x -> x.matches("^\\d{0,2}\\.\\d{1,4}$");
    public Predicate<String> digits = x -> x.matches("\\d{1,3}");
    public Predicate<String> stringLength45 = x -> x.length() < 45;
    public Predicate<String> stringLength200 = x -> x.length() < 200;
    public Predicate<String> frequency = x -> x.equals("one-off") | x.equals("weekly") | x.equals("monthly");
    public Predicate<String> oneWord = x -> !x.contains(" ");
    public Predicate<String> emailAddress = x -> x.matches("^(.+)@(.+)$");
    public BiPredicate<String, Map<Integer, ? extends Transaction>> validId = (input, items) -> {
        if (input == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return items.containsKey(Integer.parseInt(input));
    };

    public String getAndValidateInput(Scanner scanner, Predicate<String> rule, String message){
        String input = scanner.nextLine();
        if (!rule.test(input)){
            System.out.println(message);
            input = getAndValidateInput(scanner, rule, message);
        }
        return input;
    }

    public String getAndValidateInput(Scanner scanner, BiPredicate<String, Map<Integer, ? extends Transaction>> rule, String message, Map<Integer, ? extends Transaction> items){
        String input = scanner.nextLine();
        if (!rule.test(input, items)){
            System.out.println(message);
            input = getAndValidateInput(scanner, rule, message, items);
        }
        return input;
    }
}
