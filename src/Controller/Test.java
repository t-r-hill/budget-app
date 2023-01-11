package Controller;

import Data.CreateData;
import Data.ReadData;
import Model.User;
import Service.PrintObjects;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        PrintObjects printObjects = new PrintObjects();
        Scanner scanner = new Scanner(System.in);
        User user = Main.userMenu(scanner);

        if (user != null){
            printObjects.printExpenses(user);
        }
    }
}
