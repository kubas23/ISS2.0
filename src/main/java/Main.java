import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        Properties properties = new Properties();
        FileInputStream input = new FileInputStream("src/main/resources/application.properties");
        properties.load(input);
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        IssSqlTable.createIfNotExists(url, username, password); // methods of this class: createIfNotExists, insert, delete, print
        TranslateIssRequestFromJson.translate(); // inside this class is another class - WhereIsNowIssRequest class
        IssSqlTable.insert(
                url,
                username,
                password,
                TranslateIssRequestFromJson.longitude,
                TranslateIssRequestFromJson.latitude,
                TranslateIssRequestFromJson.message,
                TranslateIssRequestFromJson.time);
        IssSqlTable.print(url, username, password);

        System.out.println(IssOpenNotifyApi.howManyHumans());
    }
}