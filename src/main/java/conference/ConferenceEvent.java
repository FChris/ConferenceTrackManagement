package conference;

public record ConferenceEvent(String title, int length) {

    @Override
    public String toString() {
        return this.title;
    }
}
