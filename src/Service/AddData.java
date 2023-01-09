package Service;

import Data.CreateData;
import Model.User;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class AddData {
    Validate validate;
    CreateData createData;

    String lineBreak = "------------------------------";

    public AddData(){
        validate = new Validate();
        createData = new CreateData();
    }
    public User addUser(Scanner scanner, Map<Integer, User> users){
        String firstName;
        String lastName;
        String email;
        int id;

        Predicate<String> oneWord = x -> !x.contains(" ");
        Predicate<String> emailAddress = x -> x.matches("^(.+)@(.+)$");

        System.out.println(lineBreak);

        System.out.println("Please enter your first name");
        firstName = validate.getAndValidateInput(scanner, oneWord, "Please enter your first name as one word");

        System.out.println("Please enter your last name");
        lastName = validate.getAndValidateInput(scanner, oneWord, "Please enter your last name as one word");

        System.out.println("Please enter your email");
        email = validate.getAndValidateInput(scanner, emailAddress, "Please enter your email name as one word");

        User user = new User(firstName, lastName, email);
        id = createData.createUser(firstName, lastName, email);

        if (id > 0){
            System.out.println("Your user has been created");
            System.out.println(user);
            users.put(id, user);
        } else{
            System.out.println("There's been an error creating the user, please try again");
        }
        return user;
    }
}
