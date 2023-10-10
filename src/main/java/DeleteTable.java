import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTable {
    public static void main(String[] args) {
        String url = "jdbc:mysql://db4free.net:3306/free_sql_iss_dat";
        String username = "vesmir";
        String password = "vesmirvesmir";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            String tableName = "ISS_data";
            String sqlQuery = "DROP TABLE IF EXISTS ISS_data";
            statement.executeUpdate(sqlQuery);
            System.out.println("Tabulka " + tableName + " byla úspěšně odstraněna");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}