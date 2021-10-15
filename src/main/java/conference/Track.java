package conference;

public class Track {

    private Session morningSession;
    private Session afternoonSession;

    public Track() {
        this.morningSession = Session.createMorningSession();
        this.afternoonSession = Session.createAfternoonSession();
    }

    public boolean addTalk(Talk talk) {
        if (morningSession.addTalk(talk)) {
            return true;
        } else if (afternoonSession.addTalk(talk)) {
            return true;
        }
        return false;
    }

    public Session getMorningSession() {
        return morningSession;
    }

    public Session getAfternoonSession() {
        return afternoonSession;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(morningSession.toString());
        builder.append("\n");
        builder.append(afternoonSession.toString());
        return  builder.toString();
    }
}
