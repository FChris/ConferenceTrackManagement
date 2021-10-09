package conference;

public record Talk(String title, int length) {

    @Override
    public String toString() {
        if (this.length <= 5) {
            return this.title + " " + "lightning";
        }
        return this.title + " " + this.length + "min";
    }
}


