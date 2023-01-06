package Data;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData extends Database {
    PreparedStatement preparedStatement;
    Connection connection;
    String connectionString;

    public UpdateData() {
        try {
            connectionString = "jdbc:mysql://localhost/" + db
                    + "?user=" + user + "&password=" + URLEncoder.encode(pass, "UTF-8")
                    + "&useSSL=false&allowPublicKeyRetrieval=true";
       } catch (UnsupportedEncodingException e) {
            System.out.println("Password could not be encoded");
            e.printStackTrace();
        }
    }

    public int updateUser(String first_name, String last_name, String email, int id) {
        connection = null;
        preparedStatement = null;
        int result = 0;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("UPDATE users SET" +
                    "first_name = ?, last_name = ?, email = ?" +
                    "WHERE id = ?;");
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, id);
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

    public int updateIncome(int user_id, BigDecimal amount, String freq, String date, String source, int id) {
        connection = null;
        preparedStatement = null;
        int result = 0;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("UPDATE income SET " +
                    "user_id = ?, amount = ?, freq = ?, date = ?, source = ?" +
                    "WHERE id = ?;");
            preparedStatement.setInt(1, user_id);
            preparedStatement.setBigDecimal(2, amount);
            preparedStatement.setString(3, freq);
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, source);
            preparedStatement.setInt(6, id);
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

    public int updateDebt(int user_id, BigDecimal initial_amount, String lender_name, BigDecimal interest_rate,
                          int term_months, String start_date, String payment_date, BigDecimal payment_amount,
                          int id) {
        connection = null;
        preparedStatement = null;
        int result = 0;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("UPDATE debts SET " +
                    "user_id = ?, initial_amount = ?, lender_name = ?, interest_rate = ?, term_months = ?" +
                    ",start_date = ?, payment_date = ?, payment_amount = ?" +
                    "WHERE id = ?;");
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(5, term_months);
            preparedStatement.setBigDecimal(2, initial_amount);
            preparedStatement.setBigDecimal(4, interest_rate);
            preparedStatement.setBigDecimal(8, payment_amount);
            preparedStatement.setString(3, lender_name);
            preparedStatement.setString(6, start_date);
            preparedStatement.setString(7, payment_date);
            preparedStatement.setInt(9, id);
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
    public int updateDebtPayment(int debt_id, BigDecimal amount, String date, BigDecimal current_balance, int id) {
        connection = null;
        preparedStatement = null;
        int result = 0;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("UPDATE debt_payments SET" +
                    "debt_id = ?, amount = ?, date = ?, current_balance = ?" +
                    "WHERE id = ?;");
            preparedStatement.setInt(1, debt_id);
            preparedStatement.setBigDecimal(2, amount);
            preparedStatement.setBigDecimal(4, current_balance);
            preparedStatement.setString(3, date);
            preparedStatement.setInt(5, id);
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
    public int updateExpense(int user_id, BigDecimal amount, String freq, String date,
                             String category, String desc, int id) {
        connection = null;
        preparedStatement = null;
        int result = 0;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("UPDATE expenses SET " +
                    "user_id = ?, amount = ?, freq = ?, date = ?, category = ?, desc = ?" +
                    "WHERE id = ?;");
            preparedStatement.setInt(1, user_id);
            preparedStatement.setBigDecimal(2, amount);
            preparedStatement.setString(3, freq);
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, category);
            preparedStatement.setString(6, desc);
            preparedStatement.setInt(7, id);
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
