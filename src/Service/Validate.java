package Service;

import java.util.Scanner;
import java.util.function.Predicate;

public class Validate {

    public String getAndValidateInput(Scanner scanner, Predicate<String> rule, String message){
        String input = scanner.nextLine();
        if (!rule.test(input)){
            System.out.println(message);
            input = getAndValidateInput(scanner, rule, message);
        }
        return input;
    }
}
