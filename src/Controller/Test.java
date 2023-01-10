package Controller;

import Data.CreateData;
import Data.ReadData;
import Model.User;

import java.time.LocalDate;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        int returnValue;
        Map<Integer, User> users;

//        CreateData createData = new CreateData();
//        returnValue = createData.createUser("Sweet", "Caroline", "buh.buh.buh@goodtimes.com");
//        System.out.println(returnValue);
//        returnValue = createData.createUser("Henlo", "Fren", "henlo.fren@kek.com");
//        System.out.println(returnValue);

//        ReadData readData = new ReadData();
//        users = readData.readUsers();
//
//        System.out.println(users);
//
//        for (Integer id : users.keySet()){
//            System.out.println("User id = " + id + ", values = " + users.get(id));
//        }

        System.out.println(LocalDate.now().minusMonths(1).withDayOfMonth(1).toString());

    }
}
