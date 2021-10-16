/**
 * This IllegalTalkFormatException is a checked exception which should be thrown if a line contains a wrongly
 * formatted string representation of a talk.
 *
 * Input lines for the conference track management should contain 1 talk per line with the following rules.
 * - Talk titles may not contain numbers or the word lightning.
 * - The line contains one duration indicator: Either the word "lightning" or <dd>min where dd is a number indicating
 * the duration in minutes.
 *
 * If the formatting rules are violated this exception should be thrown.
 */
public class IllegalTalkFormatException extends Exception {

    /**
     * Initialize the IllegalTalkFormatException with a message containing the malformed line
     *
     * @param line containing a talk representation which is malformed
     */
    public IllegalTalkFormatException(String line) {
        super("Illegal Format of Talk description for line: " + line + "\nTalks description must be of the format:" +
            "Talkname {<Time in min>min|lightning} ");
    }
}
