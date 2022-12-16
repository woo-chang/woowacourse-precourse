package subway.dto;

import java.util.Collections;
import java.util.List;

public class SearchResult {

    private final List<String> stations;
    private final int distance;
    private final int time;

    public SearchResult(List<String> stations, int distance, int time) {
        this.stations = stations;
        this.distance = distance;
        this.time = time;
    }

    public List<String> getStations() {
        return Collections.unmodifiableList(stations);
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
