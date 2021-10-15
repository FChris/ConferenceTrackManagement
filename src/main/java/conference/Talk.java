package conference;

public record Talk(String title, int length) {

    public Talk {
        if (length > 240) {
            throw new IllegalArgumentException("Talk is too long. Maximum length for a Talk is 4 hours (240 Minutes)");
        }
    }

    @Override
    public String toString() {
        if (this.length <= 5) {
            return this.title + " " + "lightning";
        }
        return this.title + " " + this.length + "min";
    }
}


