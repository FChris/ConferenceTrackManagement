package conference;

/**
 * A track is a pair of sessions which in turn contain talks.
 *
 * The track class is used to make sessions more manageable and represent the real life structure of the conference
 * better. A track consists of a morning and an afternoon session.
 */
public class Track {

    private Session morningSession;
    private Session afternoonSession;

    /**
     * Initialize a Track with a pair of sessions.
     */
    public Track() {
        this.morningSession = Session.createMorningSession();
        this.afternoonSession = Session.createAfternoonSession();
    }

    /**
     * Tries to add a talk to this session.
     *
     * First tries to add the talk to the morningSession and if unsuccessful tries to add it to the afternoon session.
     * A talk can be added to a session if the current end time of a session + the length of the talk does not exceed
     * the latest end time of a session.
     *
     * @param talk that should be added to this track
     *
     * @return true if talk was added to this track, false otherwise
     */
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
