package conference;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConferenceEventTest {

    @Test
    public void lunchStringTest() {
        ConferenceEvent lunch = new ConferenceEvent("Lunch", 60);
        String stringOfLunch = "Lunch";
        Assertions.assertEquals(stringOfLunch, lunch.toString());
    }

    @Test
    public void networkingEventStringTest() {
        String stringOfNetworking = "Networking";
        Assertions.assertEquals(ConferenceEvent.NETWORKING.toString(), stringOfNetworking);
    }
}
