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
        throw new RuntimeException("Not implemented");
    }

    public static Session createAfternoonSession() {
        throw new RuntimeException("Not implemented");
    }

    public boolean addTalk(Talk talk) {
        throw new RuntimeException("Not implemented");
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
