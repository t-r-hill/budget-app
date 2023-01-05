package Data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateData extends Database {
    PreparedStatement preparedStatement;
    Connection connection;
    String connectionString;

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

    public int CreateUser(String first_name, String last_name) {
        connection = null;
        preparedStatement = null;
        int result = 0;

        try {
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("INSERT INTO users" +
                    "(first_name, last_name)" +
                    "VALUES (?, ?);");
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
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
