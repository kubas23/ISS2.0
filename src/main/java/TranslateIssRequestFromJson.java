import com.google.gson.Gson;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TranslateIssRequestFromJson {
    IssOpenNotifyApi issOpenNotifyApi = new IssOpenNotifyApi();
    Gson gson = new Gson();
    String timeLocation = null;
    String longitudeLocation = null;
    String latitudeLocation = null;
    String messageLocation = null;

    String messageHumans = null;
    int numbersHumans = 0;
    List<String> name = new ArrayList<>();
    List<String> craft = new ArrayList<>();

    void translateLocation() throws IOException {

        EntityClassISSData issData = gson.fromJson(issOpenNotifyApi.whereIsNowIssRequest(), EntityClassISSData.class);
        EntityClassISSData.IssPosition issPosition = issData.getIss_position();

        longitudeLocation = issPosition.getLongitude();
        latitudeLocation = issPosition.getLatitude();
        messageLocation = issData.getMessage();
        long timestamp = issData.getTimestamp();

        Date date = new Date(timestamp * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeLocation = dateFormat.format(date);
    }
    void translateHumans() throws IOException {

        EntityClassHowManyHumans humansData = gson.fromJson(issOpenNotifyApi.howManyHumans(), EntityClassHowManyHumans.class);
        List<EntityClassHowManyHumans.HumanCraft> humanCrafts = humansData.getPeople();
        name.clear();
        craft.clear();
        for (EntityClassHowManyHumans.HumanCraft humanCraft : humanCrafts) {
             name.add(humanCraft.getName()) ;
             craft.add(humanCraft.getCraft());
        }
        messageHumans = humansData.getMessage();
        numbersHumans = humansData.getNumber();
    }
}
