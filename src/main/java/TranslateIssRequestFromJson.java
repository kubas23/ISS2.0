import com.google.gson.Gson;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TranslateIssRequestFromJson {
    static String time = null;
    static String longitude = null;
    static String latitude = null;
    static String message = null;

    static void translate() throws IOException {
        Gson gson = new Gson();
        ISSData issData = gson.fromJson(IssOpenNotifyApi.whereIsNowIssRequest(), ISSData.class);
        ISSData.IssPosition issPosition = issData.getIss_position();

        longitude = issPosition.getLongitude();
        latitude = issPosition.getLatitude();
        message = issData.getMessage();
        long timestamp = issData.getTimestamp();

        Date date = new Date(timestamp * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = dateFormat.format(date);
    }
}
