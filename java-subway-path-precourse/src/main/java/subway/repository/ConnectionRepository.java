package subway.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import subway.domain.Connection;
import subway.domain.Station;

public class ConnectionRepository {

    private static final Map<Station, List<Connection>> connectionDB = new HashMap<>();

    public static Map<Station, List<Connection>> connections() {
        return Collections.unmodifiableMap(connectionDB);
    }

    public static void addConnection(Station station, Connection connection) {
        connectionDB.computeIfAbsent(station, c -> new ArrayList<>()).add(connection);
    }

    public static List<Connection> findByStation(Station station) {
        return connectionDB.get(station);
    }

    public static void deleteAll() {
        connectionDB.clear();
    }

}
