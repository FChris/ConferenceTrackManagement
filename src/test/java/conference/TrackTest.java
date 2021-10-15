package conference;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrackTest {

    @Test
    public void testAddTalkToEmptyTrack() {
        Track track = new Track();
        Talk hourTalk = new Talk("Test Talk for an hour", 60);
        boolean success = track.addTalk(hourTalk);
        Assertions.assertTrue(success);
        Assertions.assertEquals(track.getMorningSession().getTalks().get(0),hourTalk);
    }

    @Test
    public void testAddTalkToTrackWithFullMorningSession() {
        Track track = new Track();
        Talk threeHourTalk = new Talk("Test Talk for three hours", 180);
        boolean success = track.addTalk(threeHourTalk);
        Assertions.assertTrue(success);

        Talk hourTalk = new Talk("Test Talk for an hour in the afternoon", 60);
        success = track.addTalk(hourTalk);
        Assertions.assertTrue(success);
        Assertions.assertEquals(track.getAfternoonSession().getTalks().get(0), hourTalk);
    }

    @Test
    public void testTrackToString() {
        Track track = new Track();
        Talk threeHourTalk1 = new Talk("Three hour talk", 180);
        Talk threeHourTalk2 = new Talk("Three hour talk number two", 180);
        track.addTalk(threeHourTalk1);
        track.addTalk(threeHourTalk2);

        assertEquals("""
                        09:00AM Three hour talk 180min
                        12:00PM Lunch
                        01:00PM Three hour talk number two 180min
                        04:00PM Networking""",
                track.toString());
    }
}
