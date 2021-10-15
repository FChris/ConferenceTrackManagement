package conference;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.fail;

class TalkTest {

    @Test
    public void testIllegalLenghtTalk() {
        try{
            Talk superLongTalk = new Talk("Introduction Talk ", 360);
            fail();
        } catch (IllegalArgumentException e) {
            //expected
        }
    }

    @Test
    public void fullTalkStringTest() {
        Talk hourTalk = new Talk("Full hour talk on constraint solvers", 60);
        String stringOfHourTalk = "Full hour talk on constraint solvers 60min";
        Assertions.assertEquals(stringOfHourTalk, hourTalk.toString());
    }

    @Test
    public void fullTalkAtNineStringTest() {
        Talk hourTalk = new Talk("Full hour talk on constraint solvers", 60);
        hourTalk.setStartTime(LocalTime.of(9,0));
        String stringOfHourTalk = "09:00AM Full hour talk on constraint solvers 60min";
        Assertions.assertEquals(stringOfHourTalk, hourTalk.toString());
    }

    @Test
    public void lightningTalkStringTest() {
        Talk lightningTalk = new Talk("Lightning talk on constraint solvers", 5);
        String stringOfLightningTalk = "Lightning talk on constraint solvers lightning";
        Assertions.assertEquals(stringOfLightningTalk, lightningTalk.toString());
    }
}