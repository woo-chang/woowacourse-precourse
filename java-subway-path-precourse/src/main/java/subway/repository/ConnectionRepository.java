package subway.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import subway.domain.Connection;
import subway.domain.Station;

public class ConnectionRepository {

    private static final Map<Station, List<Connection>> connectionDB = new HashMap<>();

    public static void addConnection(Station station, Connection connection) {
        List<Connection> connections = connectionDB.putIfAbsent(station, new ArrayList<>());
        connections.add(connection);
    }

}
