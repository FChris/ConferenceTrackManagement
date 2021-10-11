package conference;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class SessionTest {

    @Test
    public void InitMorningSessionTest() {
        Session morningSession = Session.createMorningSession();
        assertEquals(morningSession.getEndEvent().title(), "Lunch");
        assertEquals(morningSession.getStartTime().compareTo(LocalTime.of(9,0)), 0);
        assertEquals(morningSession.getCurrentEndTime().compareTo(LocalTime.of(9,0)), 0);
        assertEquals(morningSession.getEndTime().compareTo(LocalTime.of(12,0)), 0);
        assertFalse(morningSession.isAllowNonFilled());
        assertTrue(morningSession.getTalks().isEmpty());
    }

    @Test
    public void InitAfternoonSessionTest() {
        Session morningSession = Session.createAfternoonSession();
        assertEquals(morningSession.getEndEvent().title(), "Networking");
        assertEquals(morningSession.getStartTime().compareTo(LocalTime.of(13,0)), 0);
        assertEquals(morningSession.getCurrentEndTime().compareTo(LocalTime.of(13,0)), 0);
        assertEquals(morningSession.getEndTime().compareTo(LocalTime.of(17,0)), 0);
        assertTrue(morningSession.isAllowNonFilled());
        assertTrue(morningSession.getTalks().isEmpty());
    }

    @Test
    public void AddTalkToNewSession() {
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
        assertEquals(session.getTalks().get(0), constraintTalk);

        Talk hourTalk = new Talk("Full hour talk on programmer humour", 60);
        boolean fail = session.addTalk(hourTalk);
        assertFalse(fail);

    }
}