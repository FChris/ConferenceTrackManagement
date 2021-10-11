package conference;

public class Track {

    private Session morningSession;
    private Session afternoonSession;

    public Track() {
        this.morningSession = Session.createMorningSession();
        this.afternoonSession = Session.createAfternoonSession();
    }

    public boolean addTalk(Talk talk) {
        throw new RuntimeException("Not implemented");
    }
}
