package Data;

import Model.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ReadData extends Database {
    PreparedStatement preparedStatement;
    Connection connection;
    String connectionString;
    ResultSet resultSet;

    public ReadData() {
        try {
            connectionString = "jdbc:mysql://localhost/" + db
                    + "?user=" + user + "&password=" + URLEncoder.encode(pass, "UTF-8")
                    + "&useSSL=false&allowPublicKeyRetrieval=true";
       } catch (UnsupportedEncodingException e) {
            System.out.println("Password could not be encoded");
            e.printStackTrace();
        }
    }

    public Map<Integer, User> readUsers() {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        Map<Integer,User> users = new HashMap<>();

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT * FROM users;");
            resultSet = preparedStatement.executeQuery();
            // Assign to POJO
            while (resultSet.next()){
                User user = new User(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getInt("id")
                );
                users.put(
                        resultSet.getInt("id"),
                        user);
            }

        } catch (SQLException e) {
            System.out.println("SQL exception occurred");
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Exception occurred - couldn't close connection");
                e.printStackTrace();
            }
        }
        return users;
    }

    public Map<Integer, Income> readIncomes(int user_id) {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        Map<Integer, Income> incomes = new HashMap<>();

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT * FROM income WHERE user_id = ?;");
            preparedStatement.setInt(1, user_id);
            resultSet = preparedStatement.executeQuery();
            // Assign to POJO
            while (resultSet.next()){
                Income income = new Income(
                        resultSet.getInt("user_id"),
                        resultSet.getBigDecimal("amount"),
                        resultSet.getString("freq"),
                        resultSet.getDate("date").toString(),
                        resultSet.getString("source"),
                        resultSet.getInt("id")
                );
                incomes.put(
                        resultSet.getInt("id"),
                        income
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL exception occurred");
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Exception occurred - couldn't close connection");
                e.printStackTrace();
            }
        }
        return incomes;
    }

    public Map<Integer, Debt> readDebts(int user_id) {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        Map<Integer, Debt> debts = new HashMap<>();

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT * FROM debts WHERE user_id = ?;");
            preparedStatement.setInt(1, user_id);
            resultSet = preparedStatement.executeQuery();
            //Assign to POJO
            while (resultSet.next()){
                Debt debt = new Debt(
                        resultSet.getInt("user_id"),
                        resultSet.getString("lender_name"),
                        resultSet.getBigDecimal("initial_amount"),
                        resultSet.getBigDecimal("interest_rate"),
                        resultSet.getInt("term_months"),
                        resultSet.getDate("start_date").toString(),
                        resultSet.getBigDecimal("payment_amount"),
                        resultSet.getDate("payment_date").toString(),
                        resultSet.getInt("is_repaid"),
                        resultSet.getInt("id")
                );

                debts.put(
                        debt.getId(),
                        debt
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL exception occurred");
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Exception occurred - couldn't close connection");
                e.printStackTrace();
            }
        }

        for (int debtId : debts.keySet()){
            debts.get(debtId).setDebtPayments(readDebtPayments(debtId));
        }
        return debts;
    }
    public Map<Integer, DebtPayment> readDebtPayments(int debt_id) {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        Map<Integer, DebtPayment> debtPayments = new HashMap<>();

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT * FROM debt_payments WHERE debt_id = ?;");
            preparedStatement.setInt(1, debt_id);
            resultSet = preparedStatement.executeQuery();
            //Assign to POJO
            while (resultSet.next()){
                DebtPayment debtPayment = new DebtPayment(
                        resultSet.getInt("debt_id"),
                        resultSet.getDate("date").toString(),
                        resultSet.getBigDecimal("amount"),
                        resultSet.getBigDecimal("current_balance"),
                        resultSet.getInt("id")
                );
                debtPayments.put(
                        resultSet.getInt("id"),
                        debtPayment
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL exception occurred");
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Exception occurred - couldn't close connection");
                e.printStackTrace();
            }
        }
        return debtPayments;
    }
    public Map<Integer, Expense> readExpenses(int user_id) {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        Map<Integer, Expense> expenses = new HashMap<>();

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT * FROM expenses WHERE user_id = ?;");
            preparedStatement.setInt(1, user_id);
            resultSet = preparedStatement.executeQuery();
            //Assign to POJO
            while (resultSet.next()){
                Expense expense = new Expense(
                        resultSet.getInt("user_id"),
                        resultSet.getBigDecimal("amount"),
                        resultSet.getDate("date").toString(),
                        resultSet.getString("freq"),
                        resultSet.getString("category"),
                        resultSet.getString("desc"),
                        resultSet.getInt("id")
                );
                expenses.put(
                        resultSet.getInt("id"),
                        expense
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL exception occurred");
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Exception occurred - couldn't close connection");
                e.printStackTrace();
            }
        }
        return expenses;
    }

    public Integer getMostRecentDebtPaymentId(Debt debt){
        connection = null;
        preparedStatement = null;
        resultSet = null;
        Integer debtPaymentId = null;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT dp.id FROM debt_payments dp " +
                    "JOIN debts d " +
                    "ON dp.debt_id = d.id " +
                    "JOIN " +
                    "(SELECT debt_id, max(date) last_date " +
                    "FROM debt_payments " +
                    "WHERE debt_id = ?) m " +
                    "ON dp.debt_id = m.debt_id " +
                    "AND dp.date = m.last_date " +
                    "WHERE d.id = ?;");
            preparedStatement.setInt(1, debt.getId());
            preparedStatement.setInt(2, debt.getId());
            resultSet = preparedStatement.executeQuery();
            //Assign to POJO
            while (resultSet.next()){
                debtPaymentId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL exception occurred");
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Exception occurred - couldn't close connection");
                e.printStackTrace();
            }
        }
        return debtPaymentId;
    }
}
