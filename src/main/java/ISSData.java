//trida pro uchovani informaci o ISS
public class ISSData {
    public class IssPosition {
        private String longitude;
        private String latitude;
        public String getLongitude() {
            return longitude;
        }
        public String getLatitude() {
            return latitude;
        }
    }
    private IssPosition iss_position;
    private String message;
    private long timestamp;
    public IssPosition getIss_position() {
        return iss_position;
    }
    public String getMessage() {
        return message;
    }
    public long getTimestamp() {
        return timestamp;
    }
}