public class EntityClassHowManyHumans {
    public class People {
        private String name;
        private String craft;

        public String getName() {
            return name;
        }
        public String getCraft() {
            return craft;
        }
    }
    private String message;
    private int number;
    private People people;

    public People getPeople() {
        return people;
    }

    public String getMessage() {
        return message;
    }

    public int getNumber() {
        return number;
    }
}
