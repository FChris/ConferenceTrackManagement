package conference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * This conference class builds a list of tracks from a given list of talks.
 * <p>
 * The conference class assigns talks to tracks and builds the list of tracks such that
 * each talk finds a slot. The primary objective is to accommodate each talk while trying to fill each track
 * as much as possible. There may be sessions which are not optimally filled, if the talk durations do not allow it.
 * During this process talks are assigned start times when they are added to a session in a track.
 * <p>
 * The underlying problem of assigning talks to tracks in a conference is similar to the bin packing problem, where
 * the sessions in the tracks represent bins and the talks represent the items to be packed.
 * The chosen algorithm corresponds to the the First-fit-decreasing algorithm.
 * The algorithm works as follows:
 * <p>
 *     <ol>
 *     <li>Sort the list of talks in descending order (longest first)</li>
 *     <li>Create an empty track</li>
 *     <li>Place an item into the first track it fits
 *      <ol>
 *          <li>If the talk does not fit into any track, create a new track and add the talk to it</li>
 *          <li>If a new track was created, add it to the list of tracks</li>
 *      </ol>
 *      </li>
 *      <li>Repeat Step 2 until every item is assigned</li>
 *      <li>Return the list of tracks</li>
 *     </ol>
 * </p>
 */
public class Conference {

    private List<Track> tracks;

    /**
     * Creates and initializes a conference object by assigning all talks to a track and saving the track to the
     * track list.
     *
     * @param talks that will be assigned to tracks, list of talks is not expected to be sorted.
     */
    public Conference(List<Talk> talks) {
        this.tracks = createTracksFromTalks(talks);
    }

    /*
     * Assign all talks to tracks. For algorithm description see the javadoc of this class
     *
     * @param talks - unsorted list with Talk objects that will be assigned to tracks
     * @return list ot Track objects containing the talks
     */
    private List<Track> createTracksFromTalks(List<Talk> talks) {
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
            } else if (current != null) {
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tracks.size(); i++) {
            builder.append("Track ");
            builder.append(i + 1);
            builder.append(":\n");
            builder.append(tracks.get(i).toString());

            if (i + 1 < tracks.size()) {
                builder.append("\n\n");
            }
        }

        return builder.toString();
    }
}
