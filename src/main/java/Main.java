import conference.Conference;
import conference.Talk;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    private static final Pattern MINUTE_PATTERN = Pattern.compile("[0-9][0-9]*min|lightning");

    public static void main(String[] args) {

        Options opts = new Options();
        opts.addOption("f", "file", true,"File input with a list of talks");
        opts.addOption("i", "interactive", false,"Interactive input of talks line by line");
        opts.addOption("h", "help", false, "Display this message");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(opts, args);

            if (cmd.hasOption("i")) {
                printConferenceFromConsoleInput();
            }

            if (cmd.hasOption("f")) {
                final String arg = cmd.getOptionValue("f");
                printConferenceFromFile(arg);
            }

            if (cmd.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("Conference Track Manager", opts);
            }
        } catch (ParseException e) {
            System.out.println("ERROR: Could not parse commandline options");
        }
    }

    private static void printConferenceFromConsoleInput() {
        System.out.println("Please write one talk per line.");
        System.out.println("Two finish the input enter an empty line.");
        Scanner in = new Scanner(System.in);

        ArrayList<Talk> talks = new ArrayList<>();

        String line = in.nextLine();
        while (!line.trim().isEmpty()) {
            try {
                extractTalkFromLine(line, talks);
            } catch (IllegalTalkFormatException e) {
                System.out.println("ERROR: " + e.getMessage());
            }

            line = in.nextLine();
        }

        Conference conf = new Conference(talks);
        System.out.println();
        System.out.println(conf);

    }

    private static void printConferenceFromFile(String filePath) {
        final List<Talk> talks = readTalkFileList(filePath);
        if (!talks.isEmpty()) {
            Conference conf = new Conference(talks);
            System.out.println(conf);
        }
    }

    public static List<Talk> readTalkFileList(String path) {
        ArrayList<Talk> talks = new ArrayList<>();
        AtomicBoolean error = new AtomicBoolean(false);

        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.forEach(l -> {
                try {
                    extractTalkFromLine(l, talks);
                } catch (IllegalTalkFormatException e) {
                    System.out.println("ERROR: " + e.getMessage());
                    error.set(true);
                }
            });
        } catch (IOException e) {
            System.out.println("ERROR: Could not read " + e.getMessage());
            System.out.println("Aborting creation of schedule.");
            error.set(true);
        }

        if (error.get()) {
            System.out.println("\nNo schedule created due to error.");
            return new ArrayList<>();
        }

        return talks;
    }

    public static void extractTalkFromLine(final String line, final List<Talk> talks) throws IllegalTalkFormatException {
        final Matcher matcher = MINUTE_PATTERN.matcher(line);

        final List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group(0));
        }

        // check format of input line
        if (matches.isEmpty() | matches.size() > 1) {
            throw new IllegalTalkFormatException(line);
        }

        final String duration = matches.get(0);
        int length;
        if (duration.equalsIgnoreCase("lightning")) {
            length = 5;
        } else {
            String durationInMin = duration.replace("min", "");
            durationInMin = durationInMin.replace(" ", "");
            length = Integer.parseInt(durationInMin);
        }

        Talk talk = new Talk(line.replace(duration, "").trim(), length);
        talks.add(talk);
    }
}
