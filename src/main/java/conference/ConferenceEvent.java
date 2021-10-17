package conference;

/**
 * Record class that keeps constants for recurring Events during a conference.
 *
 * A conference event has a title and a lenght in minutes.
 */
public record ConferenceEvent(String title, int length) {

    public static ConferenceEvent LUNCH = new ConferenceEvent("Lunch", 60);
    public static ConferenceEvent NETWORKING = new ConferenceEvent("Networking", 60);

    @Override
    public String toString() {
        return this.title;
    }
}
