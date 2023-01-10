package Model;

import Data.ReadData;

import java.util.List;
import java.util.Map;

public class User {
    String firstName;
    String lastName;
    String email;
    Map<Integer,Income> incomes;
    Map<Integer,Expense> expenses;
    Map<Integer,Debt> debts;
    int id;
    ReadData readData;

    public User(String firstName, String lastName, String email, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        readData = new ReadData();
    }

    public User() {
    }

    public void loadObjects(){
        incomes = readData.readIncomes(id);
        expenses = readData.readExpenses(id);
        debts = readData.readDebts(id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<Integer,Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(Map<Integer,Income> incomes) {
        this.incomes = incomes;
    }

    public Map<Integer,Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(Map<Integer,Expense> expenses) {
        this.expenses = expenses;
    }

    public Map<Integer,Debt> getDebts() {
        return debts;
    }

    public void setDebts(Map<Integer,Debt> debts) {
        this.debts = debts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "First name ='" + firstName + "'" +
                ", Last name ='" + lastName + "'" +
                ", Email ='" + email + "'";
    }
}
