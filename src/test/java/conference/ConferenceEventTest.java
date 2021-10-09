package conference;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConferenceEventTest {

    @Test
    public void FullTalkStringTest() {
        ConferenceEvent lunch = new ConferenceEvent("Lunch", 60);
        String stringOfLunch = "Lunch";
        Assertions.assertEquals(lunch.toString(), stringOfLunch);
    }
}
