public class IllegalTalkFormatException extends Exception {

    public IllegalTalkFormatException(String line) {
        super("Illegal Format of Talk description for line: " + line + "\nTalks description must be of the format:" +
            "Talkname {<Time in min>min|lightning} ");
    }
}
