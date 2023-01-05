package Controller;

import Data.CreateData;

public class Test {

    public static void main(String[] args) {
        int returnValue;
        CreateData createData = new CreateData();
        returnValue = createData.CreateUser("The", "Rock");
        System.out.println(returnValue);

    }
}
