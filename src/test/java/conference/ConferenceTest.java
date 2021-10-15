package conference;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.fail;

class ConferenceTest {

    @Test
    public void testOneTrackConference() {
        Talk intro = new Talk("Introduction Talk ", 180);
        Talk fullAfternoon = new Talk("Test Talk for 4 hours", 240);

        Conference conference = new Conference(Arrays.asList(intro, fullAfternoon));

        Assertions.assertEquals(conference.getTracks().size(), 1);
        Assertions.assertEquals(conference.getTracks().get(0).getMorningSession().getTalks().get(0), intro);
        Assertions.assertEquals(conference.getTracks().get(0).getAfternoonSession().getTalks().get(0), fullAfternoon);
    }

    @Test
    public void testTwoTrackConference() {
        List<Talk> talks = getTalkList();
        Conference conference = new Conference(talks);

        Assertions.assertEquals(conference.getTracks().size(), 2);
        int talksInConf = conference.getTracks().stream()
                .mapToInt(x -> x.getMorningSession().getTalks().size() + x.getAfternoonSession().getTalks().size())
                .sum();
        Assertions.assertEquals(talks.size(), talksInConf);
    }

    @Test
    public void testThreeTrackConference() {
        List<Talk> talks = getTalkList();
        talks.add(new Talk("Stackoverflow is your best friend", 60));
        Conference conference = new Conference(talks);

        Assertions.assertEquals(conference.getTracks().size(), 3);
        int talksInConf = conference.getTracks().stream()
                .mapToInt(x -> x.getMorningSession().getTalks().size() + x.getAfternoonSession().getTalks().size())
                .sum();
        Assertions.assertEquals(talks.size(), talksInConf);
    }



    private List<Talk> getTalkList() {
        return Arrays.asList(
                new Talk("Writing Fast Tests Against Enterprise Rails", 60),
                new Talk("Overdoing it in Python ", 45),
                new Talk("Lua for the Masses", 30),
                new Talk("Ruby Errors from Mismatched Gem Versions", 45),
                new Talk("Common Ruby Errors", 45),
                new Talk("Rails for Python Developers", 5),
                new Talk("Communicating Over Distance", 60),
                new Talk("Accounting-Driven Development", 45),
                new Talk("Woah", 30),
                new Talk("Sit Down and Write", 30),
                new Talk("Pair Programg vs Noise", 45),
                new Talk("Rails Magic", 60),
                new Talk("Ruby on Rails: Why We Should Move On", 60),
                new Talk("Clojure Ate Scala (on my project)", 45),
                new Talk("Programg in the Boondocks of Seattle", 30),
                new Talk("Ruby vs. Clojure for Back-End Development", 30),
                new Talk("Ruby on Rails Legacy App Maintenance", 60),
                new Talk("A World Without HackerNews", 30),
                new Talk("User Interface CSS in Rails Apps", 30));
    }
}


