package conference;

import java.time.LocalTime;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The session class is a collection of multiple talks, a specific start-, end- and latest end time.
 * A session ends with a ConferenceEvent.
 */
public class Session {

    private ConferenceEvent endEvent;
    private List<Talk> talks;
    private LocalTime startTime;
    private LocalTime latestEndTime; // end time of last talk
    private LocalTime currentEndTime;

    /**
     * Private constructor to prevent creation of Sessions with wrong initializations.
     * Use the create functions instead.
     */
    private Session() {
        // private constructor to prevent wrong initialization
    }

    /**
     * Create and initialize a Session object for a morning session.
     *
     * Morning sessions start at 9am and end latest at 12pm with a LUNCH Event.
     *
     * @return initialized session object
     */
    public static Session createMorningSession() {
        Session session = new Session();
        session.talks = new ArrayList<>();
        session.startTime = LocalTime.of(9, 0);
        session.currentEndTime = LocalTime.of(9, 0);
        session.latestEndTime = LocalTime.of(12,0);
        session.endEvent = ConferenceEvent.LUNCH;
        return session;
    }

    /**
     * Create and initialize a Session object for an afternoon session.
     *
     * Afternoon sessions start at 13pm and end latest at 17pm with a NETWORKING Event.
     * Networking events start at either 4pm or 5pm flat. The event time is determined
     * by the currentEndTime after all talks are added
     *
     * @return initialized session object
     */
    public static Session createAfternoonSession() {
        Session session = new Session();
        session.talks = new ArrayList<>();
        session.startTime = LocalTime.of(13, 0);
        session.currentEndTime = LocalTime.of(13, 0);
        session.latestEndTime = LocalTime.of(17,0);
        session.endEvent = ConferenceEvent.NETWORKING;
        return session;
    }

    /**
     * Calculate and return the time the end event of the current session starts.
     *
     * MorningSessions always end with the lunch event at 12pm.
     * Afternoon sessions end with a networking event which starts either start at 4pm or 5pm flat,
     * depending on the end time of the last talk.
     * E.g. if the last talk ends at 3:59pm or beforee networking event starts at 4pm, if the last talk ends at 4:01pm
     * or later the networking event will always start at 5pm.
     *
     * @return start time for the endEvent
     */
    public LocalTime getEndEventTime() {
        LocalTime endEventTime;

        if (endEvent.equals(ConferenceEvent.NETWORKING)) {
            if (currentEndTime.isAfter(LocalTime.of(16, 0))) {
                endEventTime = LocalTime.of(17, 0);
            } else {
                endEventTime = LocalTime.of(16, 0);
            }
        } else {
            endEventTime = latestEndTime;
        }

        return endEventTime;
    }

    /**
     * Try to add a talk to the current session.
     *
     * If successful the start time of the talk is set and the currentEndTime of the session is updated.
     * If the talk fits into the current session returns true, if not the session remains as is and false is returned.
     *
     * @param talk which should be added to the session
     * @return true if talk was added, false otherwise
     */
    public boolean addTalk(Talk talk) {
        LocalTime newEndTime = currentEndTime.plusMinutes(talk.getLength());
        if (newEndTime.isAfter(latestEndTime)) {
            return false;
        }

        talk.setStartTime(currentEndTime);
        talks.add(talk);
        currentEndTime = newEndTime;
        return true;
    }

    public ConferenceEvent getEndEvent() {
        return endEvent;
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getLatestEndTime() {
        return latestEndTime;
    }

    public LocalTime getCurrentEndTime() {
        return currentEndTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Talk talk : this.talks) {
            builder.append(talk.toString());
            builder.append("\n");
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mma");
        builder.append(getEndEventTime().format(dtf));
        builder.append(" ");
        builder.append(endEvent.toString());

        return builder.toString();
    }
}
