import conference.Talk;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void testExtractTalkFromLineWithMin() {
        String talkLine = "Writing Fast Tests Against Enterprise Rails 60min";
        List<Talk> talks = new ArrayList<>();
        try {
            Main.extractTalkFromLine(talkLine, talks);
            Assertions.assertEquals(1, talks.size());
            Assertions.assertEquals(60, talks.get(0).getLength());
            Assertions.assertEquals("Writing Fast Tests Against Enterprise Rails", talks.get(0).getTitle());
        } catch (IllegalTalkFormatException e) {
            fail();
        }
    }

    @Test
    public void testExtractTalkFromLineWithLightning() {
        String talkLine = "Rails for Python Developers lightning";
        List<Talk> talks = new ArrayList<>();
        try {
            Main.extractTalkFromLine(talkLine, talks);
            Assertions.assertEquals(1, talks.size());
            Assertions.assertEquals(5, talks.get(0).getLength());
            Assertions.assertEquals("Rails for Python Developers", talks.get(0).getTitle());
        } catch (IllegalTalkFormatException e) {
            fail();
        }
    }

    @Test
    public void testExtractTalkFromLineWithFailure() {
        String talkLine = "Messy talk line for 99min but lightning fast rejection";
        List<Talk> talks = new ArrayList<>();
        try {
            Main.extractTalkFromLine(talkLine, talks);
            fail();
        } catch (IllegalTalkFormatException e) {
            Assertions.assertTrue(e.getMessage().contains(talkLine));
        }
    }

    @Test
    public void testExtractTalksFromFile() {
        String path = "./src/test/fixtures/doubleTrackTalkList.txt";
        final List<Talk> talks = Main.readTalkFileList(path);
        Assertions.assertEquals(19, talks.size());
    }

    @Test
    public void testTryMalformedFile() {
        String path = "./src/test/fixtures/malformedTalkList.txt";
        final List<Talk> talks = Main.readTalkFileList(path);
        Assertions.assertEquals(0, talks.size());

    }
}