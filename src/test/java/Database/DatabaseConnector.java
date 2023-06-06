package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:h2:C:\\Program Files\\h2\\bin\\test.db";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public void createTable() {
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (userId INTEGER, name VARCHAR(200), email VARCHAR(200), gender VARCHAR(20), status VARCHAR(20) )";
            statement.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUserIntoDatabase(Integer userId, String name, String email, String gender, String status) {
        try (Connection connection = DatabaseConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO users (userId, name, email, gender, status) VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, userId);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, gender);
            statement.setString(5, status);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
