package conference;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Talk {

    private LocalTime startTime;
    private String title;
    private int length;

    public Talk(String title, int length) {
        if (length > 240) {
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


