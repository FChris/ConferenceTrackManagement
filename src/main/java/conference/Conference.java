package conference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Conference {

    private List<Track> tracks;

    public Conference(List<Talk> talks) {
        this.tracks = createTracksFromTalks(talks);
    }

    private List<Track> createTracksFromTalks(List<Talk> talks) {
        // sort talks by length so we can always start with the longest talk
        talks.sort(Comparator.comparingInt(Talk::getLength).reversed());

        ArrayList<Track> tracks = new ArrayList<>();

        Iterator<Talk> iter = talks.iterator();
        Talk current = null;
        if (iter.hasNext()) {
            current = iter.next();
        }

        boolean success = false;
        while (iter.hasNext()) {
            if (success) {
                current = iter.next();
            } else if (current != null){
                Track newTrack = new Track();
                newTrack.addTalk(current);
                tracks.add(newTrack);
                success = true;
                continue;
            }

            success = false;
            for (Track t : tracks) {
                success = t.addTalk(current);

                if (success) {
                    break;
                }
            }
        }

        return tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
