package Service;

import Data.DeleteData;
import Model.DebtPayment;
import Model.Income;
import Model.User;

import java.util.Map;
import java.util.Scanner;

public class DeleteObjects {

    public void deleteIncome(Scanner scanner, User user){
        Map<Integer, Income> incomes;
        Validate validate = new Validate();
        DeleteData deleteData = new DeleteData();
        PrintObjects printObjects = new PrintObjects();
        int id;
        int deleted;

        System.out.println("Let's find the transaction which you wish to delete");
        incomes = printObjects.printIncomesBetweenDates(user.getIncomes(), scanner);
        System.out.println("Please enter the ID of the transaction which you want to delete");
        id = Integer.parseInt(validate.getAndValidateInput(scanner, validate.validId, "Please enter an ID from the list above", incomes));
        deleted = deleteData.deleteIncome(id);

        if (id > 0){
            System.out.println("The income has been deleted");
            System.out.println(incomes.get(id));
            user.getIncomes().remove(id);
        } else{
            System.out.println("There's been an error deleting the income, please try again");
        }

    }
}
