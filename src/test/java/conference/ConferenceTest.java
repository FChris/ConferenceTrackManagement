package conference;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ConferenceTest {

    @Test
    public void testOneTrackConference() {
        Talk intro = new Talk("Introduction Talk ", 180);
        Talk fullAfternoon = new Talk("Test Talk for 4 hours", 240);

        Conference conference = new Conference(Arrays.asList(intro, fullAfternoon));

        Assertions.assertEquals(1, conference.getTracks().size());
        Assertions.assertEquals(intro, conference.getTracks().get(0).getMorningSession().getTalks().get(0));
        Assertions.assertEquals(fullAfternoon, conference.getTracks().get(0).getAfternoonSession().getTalks().get(0));
    }

    @Test
    public void testTwoTrackConference() {
        List<Talk> talks = getTalkList();
        Conference conference = new Conference(talks);

        Assertions.assertEquals(2, conference.getTracks().size());
        int talksInConf = conference.getTracks().stream()
                .mapToInt(x -> x.getMorningSession().getTalks().size() + x.getAfternoonSession().getTalks().size())
                .sum();
        Assertions.assertEquals(talks.size(), talksInConf);
    }

    @Test
    public void testThreeTrackConference() {
        ArrayList<Talk> talks = new ArrayList<>(getTalkList()); // add second list to allow extending
        talks.add(new Talk("Stackoverflow is your best friend", 60));
        Conference conference = new Conference(talks);

        Assertions.assertEquals(conference.getTracks().size(), 3);
        int talksInConf = conference.getTracks().stream()
                .mapToInt(x -> x.getMorningSession().getTalks().size() + x.getAfternoonSession().getTalks().size())
                .sum();
        Assertions.assertEquals(talks.size(), talksInConf);
    }

    @Test
    public void testConferenceString() {
        Conference conf = new Conference(getTalkList());
        String confString = """
                Track 1:
                09:00AM Writing Fast Tests Against Enterprise Rails 60min
                10:00AM Communicating Over Distance 60min
                11:00AM Rails Magic 60min
                12:00PM Lunch
                01:00PM Ruby on Rails: Why We Should Move On 60min
                02:00PM Ruby on Rails Legacy App Maintenance 60min
                03:00PM Overdoing it in Python  45min
                03:45PM Ruby Errors from Mismatched Gem Versions 45min
                04:30PM Lua for the Masses 30min
                05:00PM Networking
                
                Track 2:
                09:00AM Common Ruby Errors 45min
                09:45AM Accounting-Driven Development 45min
                10:30AM Pair Programg vs Noise 45min
                11:15AM Clojure Ate Scala (on my project) 45min
                12:00PM Lunch
                01:00PM Woah 30min
                01:30PM Sit Down and Write 30min
                02:00PM Programg in the Boondocks of Seattle 30min
                02:30PM Ruby vs. Clojure for Back-End Development 30min
                03:00PM A World Without HackerNews 30min
                03:30PM User Interface CSS in Rails Apps 30min
                04:00PM Rails for Python Developers lightning
                05:00PM Networking""";

        Assertions.assertEquals(confString, conf.toString());
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


