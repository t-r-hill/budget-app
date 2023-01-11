package Data;

import Model.User;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ReadSummaryData extends Database {
    PreparedStatement preparedStatement;
    Connection connection;
    String connectionString;
    ResultSet resultSet;

    public ReadSummaryData() {
        try {
            connectionString = "jdbc:mysql://localhost/" + db
                    + "?user=" + user + "&password=" + URLEncoder.encode(pass, "UTF-8")
                    + "&useSSL=false&allowPublicKeyRetrieval=true";
        } catch (UnsupportedEncodingException e) {
            System.out.println("Password could not be encoded");
            e.printStackTrace();
        }
    }

    public BigDecimal totalIncome(int userId) {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        BigDecimal total = BigDecimal.valueOf(0);

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT SUM(amount) FROM income WHERE user_id = ?;");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            // Assign to POJO
            while (resultSet.next()) {
                total = resultSet.getBigDecimal(1);
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
        return total == null ? BigDecimal.ZERO : total;
    }

    public BigDecimal monthIncome(int userId, String month) {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        BigDecimal total = BigDecimal.valueOf(0);

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT SUM(amount) FROM income " +
                    "WHERE user_id = ? " +
                    "AND date >= ? " +
                    "AND date < DATE(DATE_ADD(?, INTERVAL 1 MONTH));");
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2,month);
            preparedStatement.setString(3,month);
            resultSet = preparedStatement.executeQuery();
            // Assign to POJO
            while (resultSet.next()) {
                total = resultSet.getBigDecimal(1);
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
        return total == null ? BigDecimal.ZERO : total;
    }

    public BigDecimal totalExpenses(int userId) {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        BigDecimal total = BigDecimal.valueOf(0);

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT SUM(amount) FROM expenses WHERE user_id = ?;");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            // Assign to POJO
            while (resultSet.next()) {
                total = resultSet.getBigDecimal(1);
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
        return total == null ? BigDecimal.ZERO : total;
    }

    public BigDecimal monthExpenses(int userId, String month) {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        BigDecimal total = BigDecimal.valueOf(0);

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT SUM(amount) FROM expenses " +
                    "WHERE user_id = ? " +
                    "AND date >= ? " +
                    "AND date < DATE(DATE_ADD(?, INTERVAL 1 MONTH));");
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2,month);
            preparedStatement.setString(3,month);
            resultSet = preparedStatement.executeQuery();
            // Assign to POJO
            while (resultSet.next()) {
                total = resultSet.getBigDecimal(1);
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
        return total == null ? BigDecimal.ZERO : total;
    }

    public BigDecimal totalDebtPayments(int userId) {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        BigDecimal total = BigDecimal.valueOf(0);

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT SUM(debt_payments.amount) " +
                    "FROM debts JOIN debt_payments ON debts.id = debt_payments.debt_id " +
                    "WHERE debts.user_id = ?;");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            // Assign to POJO
            while (resultSet.next()) {
                total = resultSet.getBigDecimal(1);
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
        return total == null ? BigDecimal.ZERO : total;
    }

    public BigDecimal monthDebtPayments(int userId, String month) {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        BigDecimal total = BigDecimal.valueOf(0);

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT SUM(debt_payments.amount) " +
                    "FROM debts JOIN debt_payments ON debts.id = debt_payments.debt_id " +
                    "WHERE debts.user_id = ? " +
                    "AND debt_payments.date >= ? " +
                    "AND debt_payments.date < DATE(DATE_ADD(?, INTERVAL 1 MONTH));");
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2,month);
            preparedStatement.setString(3,month);
            resultSet = preparedStatement.executeQuery();
            // Assign to POJO
            while (resultSet.next()) {
                total = resultSet.getBigDecimal(1);
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
        return total == null ? BigDecimal.ZERO : total;
    }

    public BigDecimal totalDebts(int userId) {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        BigDecimal total = BigDecimal.valueOf(0);

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT sum(current_balance) FROM debt_payments dp " +
                    "JOIN debts d " +
                    "ON dp.debt_id = d.id " +
                    "JOIN " +
                    "(SELECT debt_id, max(date) last_date " +
                    "FROM debt_payments " +
                    "GROUP BY debt_id) m " +
                    "ON dp.debt_id = m.debt_id " +
                    "AND dp.date = m.last_date " +
                    "WHERE d.user_id = ?;");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            // Assign to POJO
            while (resultSet.next()) {
                total = resultSet.getBigDecimal(1);
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
        return total == null ? BigDecimal.ZERO : total;
    }
}
