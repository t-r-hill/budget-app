package Data;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData extends Database {
    PreparedStatement preparedStatement;
    Connection connection;
    String connectionString;

    public DeleteData() {
        try {
            connectionString = "jdbc:mysql://localhost/" + db
                    + "?user=" + user + "&password=" + URLEncoder.encode(pass, "UTF-8")
                    + "&useSSL=false&allowPublicKeyRetrieval=true";
       } catch (UnsupportedEncodingException e) {
            System.out.println("Password could not be encoded");
            e.printStackTrace();
        }
    }

    public int deleteUser(int id) {
        connection = null;
        preparedStatement = null;
        int result = 0;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("DELETE FROM users " +
                    "WHERE id = ?;");
            preparedStatement.setInt(1, id);
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

    public int deleteIncome(int id) {
        connection = null;
        preparedStatement = null;
        int result = 0;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("DELETE FROM income " +
                    "WHERE id = ?;");
            preparedStatement.setInt(1, id);
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

    public int deleteDebt(int id) {
        connection = null;
        preparedStatement = null;
        int result = 0;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("DELETE FROM debts " +
                    "WHERE id = ?;");
            preparedStatement.setInt(1, id);
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
    public int deleteDebtPayment(int id) {
        connection = null;
        preparedStatement = null;
        int result = 0;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("DELETE FROM debt_payments " +
                    "WHERE id = ?;");
            preparedStatement.setInt(1, id);
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
    public int deleteExpense(int id) {
        connection = null;
        preparedStatement = null;
        int result = 0;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("DELETE FROM expenses " +
                    "WHERE id = ?;");
            preparedStatement.setInt(1, id);
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
