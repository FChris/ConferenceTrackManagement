package conference;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TrackTest {

    @Test
    public void addTalkToEmptyTrack() {
        Track track = new Track();
        Talk hourTalk = new Talk("Test Talk for an hour", 60);
        boolean success = track.addTalk(hourTalk);
        Assertions.assertTrue(success);
        Assertions.assertEquals(track.getMorningSession().getTalks().get(0),hourTalk);
    }

    @Test
    public void addTalkToTrackWithFullMorningSession() {
        Track track = new Track();
        Talk threeHourTalk = new Talk("Test Talk for 3 hours", 180);
        boolean success = track.addTalk(threeHourTalk);
        Assertions.assertTrue(success);

        Talk hourTalk = new Talk("Test Talk for an hour in the afternoon", 60);
        success = track.addTalk(hourTalk);
        Assertions.assertTrue(success);
        Assertions.assertEquals(track.getAfternoonSession().getTalks().get(0), hourTalk);
    }
}
