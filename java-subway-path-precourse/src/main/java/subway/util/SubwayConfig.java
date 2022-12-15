package subway.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import subway.domain.Connection;
import subway.domain.Line;
import subway.domain.Station;
import subway.repository.ConnectionRepository;
import subway.repository.LineRepository;
import subway.repository.StationRepository;

public class SubwayConfig {

    private SubwayConfig() {
    }

    public static void configure() {
        stationConfigure();
        lineConfigure();
        connectionConfigure();
    }

    private static void stationConfigure() {
        StationRepository.deleteAll();
        List<String> stationNames = new ArrayList<>(
                List.of("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역"));
        List<Station> stations = stationNames.stream()
                .map(Station::new)
                .collect(Collectors.toList());
        stations.forEach(StationRepository::addStation);
    }

    private static void lineConfigure() {
        LineRepository.deleteAll();
        List<String> lineNames = new ArrayList<>(List.of("2호선", "3호선", "신분당선"));
        List<Line> lines = lineNames.stream()
                .map(Line::new)
                .collect(Collectors.toList());
        lines.forEach(LineRepository::addLine);
    }

    private static void connectionConfigure() {
        ConnectionRepository.deleteAll();
        List<List<String>> connectionInformation = getConnectionInformation();
        for (List<String> connection : connectionInformation) {
            storeConnection(connection);
        }
    }

    private static List<List<String>> getConnectionInformation() {
        return new ArrayList<>(
                List.of(
                        new ArrayList<>(List.of("교대역", "강남역", "2", "3")),
                        new ArrayList<>(List.of("강남역", "역삼역", "2", "3")),
                        new ArrayList<>(List.of("교대역", "남부터미널역", "3", "2")),
                        new ArrayList<>(List.of("남부터미널역", "양재역", "6", "5")),
                        new ArrayList<>(List.of("양재역", "매봉역", "1", "1")),
                        new ArrayList<>(List.of("강남역", "양재역", "2", "8")),
                        new ArrayList<>(List.of("양재역", "양재시민의숲역", "10", "3"))
                )
        );
    }

    private static void storeConnection(List<String> connection) {
        Station station1 = new Station(connection.get(0));
        Station station2 = new Station(connection.get(1));
        int distance = Integer.parseInt(connection.get(2));
        int time = Integer.parseInt(connection.get(3));
        Connection connection1 = new Connection(station2, distance, time);
        Connection connection2 = new Connection(station1, distance, time);
        ConnectionRepository.addConnection(station1, connection1);
        ConnectionRepository.addConnection(station2, connection2);
    }

}
