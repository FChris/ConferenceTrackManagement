package conference;

import java.time.LocalTime;


import java.util.ArrayList;
import java.util.List;

public class Session {

    private ConferenceEvent endEvent;
    private List<Talk> talks;
    private LocalTime startTime;
    private LocalTime endTime; // end time of last talk
    private LocalTime currentEndTime;
    private boolean allowNonFilled; // allow that there is a gap between the last talk and the end event

    private Session() {
        // private constructor to prevent wrong initialization
    }

    public static Session createMorningSession() {
        Session session = new Session();
        session.talks = new ArrayList<>();
        session.startTime = LocalTime.of(9, 0);
        session.currentEndTime = LocalTime.of(9, 0);
        session.endTime = LocalTime.of(12,0);
        session.allowNonFilled = false;
        session.endEvent = ConferenceEvent.LUNCH;
        return session;
    }

    public static Session createAfternoonSession() {
        Session session = new Session();
        session.talks = new ArrayList<>();
        session.startTime = LocalTime.of(13, 0);
        session.currentEndTime = LocalTime.of(13, 0);
        session.endTime = LocalTime.of(17,0);
        session.allowNonFilled = true;
        session.endEvent = ConferenceEvent.NETWORKING;
        return session;
    }

    public boolean addTalk(Talk talk) {
        LocalTime newEndTime = currentEndTime.plusMinutes(talk.getLength());
        if (newEndTime.isAfter(endTime)) {
            return false;
        }

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

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getCurrentEndTime() {
        return currentEndTime;
    }

    public boolean isAllowNonFilled() {
        return allowNonFilled;
    }
}
