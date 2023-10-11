import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TranslateIssRequestFromJson {
    static Gson gson = new Gson();
    static String timeLocation = null;
    static String longitudeLocation = null;
    static String latitudeLocation = null;
    static String messageLocation = null;

    static String messageHumans = null;
    static int numbersHumans = 0;
    static Set<String> peopleName = new HashSet<>();
    static Set<String> peopleCraft = new HashSet<>();

    static void translateLocation() throws IOException {

        EntityClassISSData issData = gson.fromJson(IssOpenNotifyApi.whereIsNowIssRequest(), EntityClassISSData.class);
        EntityClassISSData.IssPosition issPosition = issData.getIss_position();

        longitudeLocation = issPosition.getLongitude();
        latitudeLocation = issPosition.getLatitude();
        messageLocation = issData.getMessage();
        long timestamp = issData.getTimestamp();

        Date date = new Date(timestamp * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeLocation = dateFormat.format(date);
    }

    static void translateHumans() throws IOException {
        EntityClassHowManyHumans humansData = gson.fromJson(IssOpenNotifyApi.howManyHumans(), EntityClassHowManyHumans.class);
        EntityClassHowManyHumans.People people = humansData.getPeople();
        messageHumans = humansData.getMessage();
        numbersHumans = humansData.getNumber();

    }
}
