import java.sql.*;

public class IssSqlTable {
    static void createIfNotExists(String url, String username, String password) throws SQLException {

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String sqlQuery = "CREATE TABLE IF NOT EXISTS ISS_data (" +
                "longitude DOUBLE," +
                "latitude DOUBLE," +
                "message VARCHAR(12)," +
                "time TIMESTAMP PRIMARY KEY" +
                ")";
        statement.executeUpdate(sqlQuery);
    }

    static void delete(String url, String username, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String tableName = "ISS_data";
        String sqlQuery = "DROP TABLE IF EXISTS ISS_data";
        statement.executeUpdate(sqlQuery);
        System.out.println("Table " + tableName + " has been deleted");
    }

    static void print(String url, String username, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT * FROM ISS_data";
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            String longitude = resultSet.getString("longitude");
            String latitude = resultSet.getString("latitude");
            String message = resultSet.getString("message");
            String time = resultSet.getString("time");

            System.out.println("longitude: " + longitude);
            System.out.println("latitude: " + latitude);
            System.out.println("message: " + message);
            System.out.println("time: " + time);
            System.out.println("------------------------");
        }
    }
    static void insert(String url, String username, String password, String longitude, String latitude, String message, String time) throws SQLException {

        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO ISS_data (longitude, latitude, message, time) VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, longitude);
        preparedStatement.setString(2, latitude);
        preparedStatement.setString(3, message);
        preparedStatement.setString(4, time);

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("data loaded successfully");
        } else {
            System.err.println("data not loaded");
        }
    }
}