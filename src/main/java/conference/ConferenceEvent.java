package conference;

public record ConferenceEvent(String title, int length) {

    public static ConferenceEvent LUNCH = new ConferenceEvent("Lunch", 60);
    public static ConferenceEvent NETWORKING = new ConferenceEvent("Networking", 60);

    @Override
    public String toString() {
        return this.title;
    }
}
