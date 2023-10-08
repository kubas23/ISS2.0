import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        String issAnswer = null;

        String time = null;
        String longitude = null;
        String latitude = null;
        String message = null;

        //sql table
        String url = "jdbc:mysql://localhost:3306/new_schema";
        String username = "root";
        String password = "HPMini100e";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            String sqlQuery = "CREATE TABLE IF NOT EXISTS ISS_data (" +
                    "longitude VARCHAR(255)," +
                    "latitude VARCHAR(255)," +
                    "message VARCHAR(255)," +
                    "time VARCHAR(255)" +
                    ")";
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // -------------------


        HttpClient httpClient = HttpClients.createDefault(); /*provadeni HTTP pozadavku*/
        HttpGet question = new HttpGet("http://api.open-notify.org/iss-now.json"); /*pokousime stahnout data z URL*/

        try {
            HttpResponse answer = httpClient.execute(question);
            if (answer.getStatusLine().getStatusCode() == 200) {
                /* nejpopularnejsi stavove kody:
                200 OK, 201 Created, 204 No Content, 400 Bad Request, 401 Unauthorized, 403 Forbidden, 404 Not Found,
                500 Internal Server Error,502 Bad Gateway, 503 Service Unavailable, Gateway Timeout */
                issAnswer = EntityUtils.toString(answer.getEntity());

                Gson gson = new Gson();
                ISSData issData = gson.fromJson(issAnswer, ISSData.class);

                ISSData.IssPosition issPosition = issData.getIss_position();
                longitude = issPosition.getLongitude();
                latitude = issPosition.getLatitude();
                message = issData.getMessage();
                long timestamp = issData.getTimestamp();

                System.out.println("longitude: " + longitude);
                System.out.println("latitude: " + latitude);
                System.out.println("message: " + message);

                SimpleDateFormat dateFormat = new SimpleDateFormat();// citelny format timestamp
                Date date = new Date(timestamp * 1000);
                time = dateFormat.format(date);
                System.out.println("timestamp: " + time);
            } else {
                System.err.println("data loading error, code: " + answer.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        insertDataIntoTable(longitude, latitude, message, time);
    }
    public static void insertDataIntoTable(String longitude, String latitude, String message, String time) {
        String url = "jdbc:mysql://localhost:3306/new_schema";
        String username = "root";
        String password = "HPMini100e";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO ISS_data (longitude, latitude, message, time) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, longitude);
            preparedStatement.setString(2, latitude);
            preparedStatement.setString(3, message);
            preparedStatement.setString(4, time);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("it is ok");
            } else {
                System.err.println("error");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}