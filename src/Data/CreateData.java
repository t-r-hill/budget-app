package Data;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.*;

public class CreateData extends Database {
    PreparedStatement preparedStatement;
    Connection connection;
    String connectionString;
    String[] columnNames = {"id"};
    ResultSet generatedKeys;
    int result;

    public CreateData() {
        try {
            connectionString = "jdbc:mysql://localhost/" + db
                    + "?user=" + user + "&password=" + URLEncoder.encode(pass, "UTF-8")
                    + "&useSSL=false&allowPublicKeyRetrieval=true";
       } catch (UnsupportedEncodingException e) {
            System.out.println("Password could not be encoded");
            e.printStackTrace();
        }
    }

    public int createUser(String first_name, String last_name, String email) {
        connection = null;
        preparedStatement = null;
        generatedKeys = null;
        result = 0;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("INSERT INTO users" +
                    "(first_name, last_name, email)" +
                    "VALUES (?, ?, ?);", columnNames);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, email);
            result = preparedStatement.executeUpdate();

            if (result > 0){
                generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()){
                    result = generatedKeys.getInt(1);
                }
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
        return result;
    }

    public int createIncome(int user_id, BigDecimal amount, String freq, String date, String source) {
        connection = null;
        preparedStatement = null;
        result = 0;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("INSERT INTO income " +
                    "(user_id, amount, freq, date, source) " +
                    "VALUES (?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, user_id);
            preparedStatement.setBigDecimal(2, amount);
            preparedStatement.setString(3, freq);
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, source);
            result = preparedStatement.executeUpdate();
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
        return result;
    }

    public int createDebt(int user_id, BigDecimal initial_amount, String lender_name, BigDecimal interest_rate,
                                 int term_months, String start_date, String payment_date, BigDecimal payment_amount) {
        connection = null;
        preparedStatement = null;
        result = 0;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("INSERT INTO debts " +
                    "(user_id, initial_amount, lender_name, interest_rate, term_months, start_date, payment_date, payment_amount) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(5, term_months);
            preparedStatement.setBigDecimal(2, initial_amount);
            preparedStatement.setBigDecimal(4, interest_rate);
            preparedStatement.setBigDecimal(8, payment_amount);
            preparedStatement.setString(3, lender_name);
            preparedStatement.setString(6, start_date);
            preparedStatement.setString(7, payment_date);
            result = preparedStatement.executeUpdate();
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
        return result;
    }
    public int createDebtPayment(int debt_id, BigDecimal amount, String date, BigDecimal current_balance) {
        connection = null;
        preparedStatement = null;
        result = 0;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("INSERT INTO debt_payments " +
                    "(debt_id, amount, date, current_balance) " +
                    "VALUES (?, ?, ?, ?);");
            preparedStatement.setInt(1, debt_id);
            preparedStatement.setBigDecimal(2, amount);
            preparedStatement.setBigDecimal(4, current_balance);
            preparedStatement.setString(3, date);
            result = preparedStatement.executeUpdate();
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
        return result;
    }
    public int createExpense(int user_id, BigDecimal amount, String freq, String date, String category, String desc) {
        connection = null;
        preparedStatement = null;
        result = 0;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("INSERT INTO expenses " +
                    "(user_id, amount, freq, date, category, `desc`) " +
                    "VALUES (?, ?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, user_id);
            preparedStatement.setBigDecimal(2, amount);
            preparedStatement.setString(3, freq);
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, category);
            preparedStatement.setString(6, desc);
            result = preparedStatement.executeUpdate();
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
        return result;
    }
}
