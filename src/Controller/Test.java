package Controller;

import Data.CreateData;

public class Test {

    public static void main(String[] args) {
        int returnValue;
        CreateData createData = new CreateData();
        returnValue = createData.createUser("The", "Rock", "the.rock@wwe.com");
        System.out.println(returnValue);

    }
}
