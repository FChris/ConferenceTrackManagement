package conference;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        Assertions.assertEquals(hourTalk.toString(), stringOfHourTalk);
    }

    @Test
    public void lightningTalkStringTest() {
        Talk lightningTalk = new Talk("Lightning talk on constraint solvers", 5);
        String stringOfLightningTalk = "Lightning talk on constraint solvers lightning";
        Assertions.assertEquals(lightningTalk.toString(), stringOfLightningTalk);
    }
}