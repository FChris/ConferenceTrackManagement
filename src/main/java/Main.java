import conference.Talk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    private static Pattern MINUTE_PATTERN = Pattern.compile("[0-9][0-9]*min|lightning");

    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    public static List<Talk> readTalkFileList(String path) {
        throw new RuntimeException("Not implemented");
    }

    public static void extractTalkFromLine(final String line, final List<Talk> talks) throws IllegalTalkFormatException {
        throw new RuntimeException("Not implemented");
    }

}
