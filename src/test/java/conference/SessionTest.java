package conference;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class SessionTest {

    @Test
    public void initMorningSessionTest() {
        Session morningSession = Session.createMorningSession();
        assertEquals(morningSession.getEndEvent().title(), "Lunch");
        assertEquals(morningSession.getStartTime().compareTo(LocalTime.of(9,0)), 0);
        assertEquals(morningSession.getCurrentEndTime().compareTo(LocalTime.of(9,0)), 0);
        assertEquals(morningSession.getEndTime().compareTo(LocalTime.of(12,0)), 0);
        assertTrue(morningSession.getTalks().isEmpty());
    }

    @Test
    public void initAfternoonSessionTest() {
        Session morningSession = Session.createAfternoonSession();
        assertEquals(morningSession.getEndEvent().title(), "Networking");
        assertEquals(morningSession.getStartTime().compareTo(LocalTime.of(13,0)), 0);
        assertEquals(morningSession.getCurrentEndTime().compareTo(LocalTime.of(13,0)), 0);
        assertEquals(morningSession.getEndTime().compareTo(LocalTime.of(17,0)), 0);
        assertTrue(morningSession.getTalks().isEmpty());
    }

    @Test
    public void morningSessionToStringTest() {
        Session morningSession = Session.createMorningSession();
        morningSession.addTalk(new Talk("Three hour talk", 180));
        assertEquals("09:00AM Three hour talk 180min\n" +
                "12:00PM Lunch", morningSession.toString());
    }

    @Test
    public void afternoonShortSessionToStringTest() {
        Session morningSession = Session.createAfternoonSession();
        morningSession.addTalk(new Talk("Three hour talk", 180));
        assertEquals("01:00PM Three hour talk 180min\n" +
                "04:00PM Networking", morningSession.toString());
    }

    @Test
    public void afternoonLongSessionToStringTest() {
        Session morningSession = Session.createAfternoonSession();
        morningSession.addTalk(new Talk("Three hour talk", 210));
        assertEquals("01:00PM Three hour talk 180min\n" +
                "05:00PM Networking", morningSession.toString());
    }

    @Test
    public void addTalkToNewSession() {
        Talk hourTalk = new Talk("Full hour talk on constraint solvers", 60);
        Session session = Session.createMorningSession();
        boolean success = session.addTalk(hourTalk);

        assertTrue(success);
        assertEquals(session.getTalks().get(0), hourTalk);
    }

    @Test
    public void AddTalkToFullSession() {
        Talk constraintTalk = new Talk("3 hour talk on constraint solvers", 180);
        Session session = Session.createMorningSession();
        boolean success = session.addTalk(constraintTalk);

        assertTrue(success);
        assertEquals(constraintTalk, session.getTalks().get(0));

        Talk hourTalk = new Talk("Full hour talk on programmer humour", 60);
        boolean fail = session.addTalk(hourTalk);
        assertFalse(fail);

    }
}