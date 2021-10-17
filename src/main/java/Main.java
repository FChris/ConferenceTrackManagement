import conference.Conference;
import conference.Talk;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Main class of the Conference Track Management which provides an interface to the user and prints
 * the results to the console.
 */
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

            if (cmd.hasOption("i") && !cmd.hasOption("f")) {
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

    /**
     * This method offers a cli interface to the user to input talk descriptions line by line and receive
     * a conference schedule.
     *
     * Input descriptions are read line by line until an empty line is read.
     * After the lines are read a conference object with tracks is created and the conferences string
     * representation is printed to the console.
     */
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

    /**
     * This method reads input talk descriptions from a file and prints a schedule to the console.
     *
     * The file is read line by line where each line may contain exactly one talk description.
     * After the lines are read a conference object with tracks is created and the conferences string
     * representation is printed to the console.
     */
    private static void printConferenceFromFile(String filePath) {
        final List<Talk> talks = readTalkFileList(filePath);
        if (!talks.isEmpty()) {
            Conference conf = new Conference(talks);
            System.out.println(conf);
        }
    }

    /**
     * This method reads input talk descriptions from a file and returns a list of Talk objects.
     *
     * The file is read line by line where each line may contain exactly one talk description.
     * If a malformed line is read or the file cannot be read, parsing is aborted, an error message
     * is printed to console and an empty list is returned.
     *
     * @return list with Talk objects or empty list if file contains malformed lines
     */
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

    /**
     * Extracts a information from a line description, creates a Talk object and adds it to the given list of talks.
     *
     * This method is used this way instead of returning the talk object so it can be used in stream lambdas.
     * The line must contain a title of the talk and exactly one duration description.
     * The title is a simple string and the duration description is either the word lightning or the string "<dd>min"
     * where dd is a number describing the duration in minutes.
     *
     * @param line with the description for the talk
     * @param talks list to which the Talk object will be added
     * @throws IllegalTalkFormatException if the format of the line is incorrect
     */
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

        try{
            Talk talk = new Talk(line.replace(duration, "").trim(), length);
            talks.add(talk);
        } catch (IllegalArgumentException ex) {
            throw new IllegalTalkFormatException(line);
        }
    }
}
