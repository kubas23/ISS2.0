import java.sql.*;

public class IssSqlTable {
    void createIfNotExistsLocationTable(String url, String username, String password) throws SQLException {

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

    static void deleteAllTables(String url, String username, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String[] tables = {"ISS_data", "Humans_data"};
        for (String table : tables) {
            statement.executeUpdate("DROP TABLE IF EXISTS " + table);
            System.out.println("Table " + table + " has been deleted");
        }
    }

    void printHumansTable(String url, String username, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT * FROM Humans_data";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while ((resultSet.next())){
            System.out.print("name: "+ resultSet.getString("name"));
            System.out.println(" || craft: "+ resultSet.getString("craft"));
        }
    }

    void printLocationTable(String url, String username, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT * FROM ISS_data";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            System.out.print("longitude: " + resultSet.getString("longitude"));
            System.out.print("|| latitude: " + resultSet.getString("latitude"));
            System.out.print("|| message: " + resultSet.getString("message"));
            System.out.println("|| time: " + resultSet.getString("time"));
            System.out.println("----------------");
        }
    }

    void insertHumansTable(String url, String username, String password, TranslateIssRequestFromJson translateIssRequestFromJson) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Humans_data (" +
                "name VARCHAR(255)," +
                "craft VARCHAR(255)," +
                "PRIMARY KEY (name, craft)" +
                ")");
        statement.executeUpdate("DELETE FROM Humans_data");

        for (int i = 0; i < translateIssRequestFromJson.name.size(); i++) {
            String name = translateIssRequestFromJson.name.get(i);
            String craft = translateIssRequestFromJson.craft.get(i);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Humans_data (name, craft) VALUES (?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, craft);
            preparedStatement.executeUpdate();
        }
        connection.close();
    }

    void insertLocationTable(String url, String username, String password, String longitude, String latitude, String message, String time) throws SQLException {
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