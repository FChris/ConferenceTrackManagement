package conference;

import java.time.LocalTime;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private ConferenceEvent endEvent;
    private List<Talk> talks;
    private LocalTime startTime;
    private LocalTime endTime; // end time of last talk
    private LocalTime currentEndTime;

    private Session() {
        // private constructor to prevent wrong initialization
    }

    public static Session createMorningSession() {
        Session session = new Session();
        session.talks = new ArrayList<>();
        session.startTime = LocalTime.of(9, 0);
        session.currentEndTime = LocalTime.of(9, 0);
        session.endTime = LocalTime.of(12,0);
        session.endEvent = ConferenceEvent.LUNCH;
        return session;
    }

    public static Session createAfternoonSession() {
        Session session = new Session();
        session.talks = new ArrayList<>();
        session.startTime = LocalTime.of(13, 0);
        session.currentEndTime = LocalTime.of(13, 0);
        session.endTime = LocalTime.of(17,0);
        session.endEvent = ConferenceEvent.NETWORKING;
        return session;
    }

    public boolean addTalk(Talk talk) {
        LocalTime newEndTime = currentEndTime.plusMinutes(talk.getLength());
        if (newEndTime.isAfter(endTime)) {
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

    public LocalTime getEndTime() {
        return endTime;
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

        if (endEvent.equals(ConferenceEvent.NETWORKING)) {
            String networkingTime;
            if (currentEndTime.isAfter(LocalTime.of(16, 0))) {
                networkingTime = LocalTime.of(17, 0).format(dtf);
            } else {
                networkingTime = LocalTime.of(16, 0).format(dtf);
            }
            builder.append(networkingTime);
            builder.append(" ");
            builder.append(endEvent.toString());
        } else {
            builder.append(currentEndTime.format(dtf));
            builder.append(" ");
            builder.append(endEvent.toString());
        }

        return builder.toString();
    }
}
