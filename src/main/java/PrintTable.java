import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrintTable {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/new_schema";
        String username = "root";
        String password = "HPMini100e";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
