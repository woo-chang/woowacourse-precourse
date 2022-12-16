package subway.dto;

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
}
