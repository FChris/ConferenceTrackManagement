package conference;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is the internal representation of a talk in the conference assigned to a time slot.
 *
 * The talk consists of a title, a length and after it has been assigned to a time slot a start time.
 * The maximum duration of a talk is {@value MAX_TALK_LEGNTH}. At the moment this value is not configurable
 * as the structure of the whole conference is fixed as well.
 */
public class Talk {

    /**
     * Maximum length of a talk in minutes
     */
    public static final int MAX_TALK_LEGNTH = 240; // final as long as conference structure is not configurable

    private LocalTime startTime;
    private String title;
    private int length;

    /**
     * Initializes a new Talk with the given title and length.
     *
     * @param title of the talk
     * @param length of the talk in minues
     */
    public Talk(String title, int length) {
        if (length > MAX_TALK_LEGNTH) {
            throw new IllegalArgumentException("Talk is too long. Maximum length for a Talk is 4 hours (240 Minutes)");
        }

        this.title = title;
        this.length = length;
    }

    @Override
    public String toString() {
        String startTimeString = "";
        if (startTime != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mma");
            startTimeString = startTime.format(dtf) + " ";
        }

        if (this.length <= 5) {
            return startTimeString + this.title + " " + "lightning";
        }
        return startTimeString + this.title + " " + this.length + "min";
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
}


