package conference;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TalkTest {

    @Test
    public void FullTalkStringTest() {
        Talk hourTalk = new Talk("Full hour talk on constraint solvers", 60);
        String stringOfHourTalk = "Full hour talk on constraint solvers 60min";
        Assertions.assertEquals(hourTalk.toString(), stringOfHourTalk);
    }

    @Test
    public void LightningTalkStringTest() {
        Talk hourTalk = new Talk("Full hour talk on constraint solvers", 5);
        String stringOfHourTalk = "Full hour talk on constraint solvers lightning";
        Assertions.assertEquals(hourTalk.toString(), stringOfHourTalk);
    }
}