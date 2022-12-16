package subway.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.Connection;
import subway.domain.Station;
import subway.dto.SearchResult;
import subway.repository.ConnectionRepository;
import subway.repository.StationRepository;

public class SubwayService {

    public SearchResult findShortestDistance(Station startStation, Station arrivalStation) {
        validate(startStation, arrivalStation);
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(
                DefaultWeightedEdge.class);
        addVertex(graph);
        setDistanceEdgeWeight(graph);
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        List<String> shortestPath = dijkstraShortestPath.getPath(startStation.getName(),
                        arrivalStation.getName())
                .getVertexList();
        return generateResult(shortestPath);
    }

    public SearchResult findShortestTime(Station startStation, Station arrivalStation) {
        validate(startStation, arrivalStation);
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(
                DefaultWeightedEdge.class);
        addVertex(graph);
        setTimeEdgeWeight(graph);
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        List<String> shortestPath = dijkstraShortestPath.getPath(startStation.getName(),
                        arrivalStation.getName())
                .getVertexList();
        return generateResult(shortestPath);
    }

    private void validate(Station startStation, Station arrivalStation) {
        if (startStation.equals(arrivalStation)) {
            throw new IllegalArgumentException("출발역과 도착역이 동일합니다.");
        }
    }

    private void addVertex(WeightedMultigraph<String, DefaultWeightedEdge> graph) {
        List<Station> stations = StationRepository.stations();
        for (Station station : stations) {
            graph.addVertex(station.getName());
        }
    }

    private void setDistanceEdgeWeight(WeightedMultigraph<String, DefaultWeightedEdge> graph) {
        List<Station> stations = new ArrayList<>(StationRepository.stations());
        Map<Station, List<Connection>> connectionDB = ConnectionRepository.connections();
        while (!stations.isEmpty()) {
            Station station = stations.get(0);
            List<Connection> connections = connectionDB.get(station);
            for (Connection connection : connections) {
                addDistanceEdge(graph, stations, station, connection);
            }
            stations.remove(station);
        }
    }

    private void addDistanceEdge(WeightedMultigraph<String, DefaultWeightedEdge> graph,
            List<Station> stations, Station station, Connection connection) {
        if (stations.contains(connection.getStation())) {
            graph.setEdgeWeight(graph.addEdge(station.getName(), connection.getStation().getName()),
                    connection.getDistance());
        }
    }

    private void setTimeEdgeWeight(WeightedMultigraph<String, DefaultWeightedEdge> graph) {
        List<Station> stations = new ArrayList<>(StationRepository.stations());
        Map<Station, List<Connection>> connectionDB = ConnectionRepository.connections();
        while (!stations.isEmpty()) {
            Station station = stations.get(0);
            List<Connection> connections = connectionDB.get(station);
            for (Connection connection : connections) {
                addTimeEdge(graph, stations, station, connection);
            }
            stations.remove(station);
        }
    }

    private void addTimeEdge(WeightedMultigraph<String, DefaultWeightedEdge> graph,
            List<Station> stations, Station station, Connection connection) {
        if (stations.contains(connection.getStation())) {
            graph.setEdgeWeight(graph.addEdge(station.getName(), connection.getStation().getName()),
                    connection.getTime());
        }
    }

    private SearchResult generateResult(List<String> shortestPath) {
        List<Station> stations = generateStations(shortestPath);
        List<String> paths = new ArrayList<>();
        int distance = 0;
        int time = 0;
        for (int i = 0; i < stations.size() - 1; i++) {
            paths.add(stations.get(i).getName());
            Connection connection = searchConnection(stations, i);
            distance += connection.getDistance();
            time += connection.getTime();
        }
        return new SearchResult(paths, distance, time);
    }

    private Connection searchConnection(List<Station> stations, int i) {
        return ConnectionRepository.connections().get(stations.get(i))
                .stream()
                .filter(con -> con.getStation().equals(stations.get(i + 1)))
                .findFirst()
                .get();
    }

    private List<Station> generateStations(List<String> shortestPath) {
        return shortestPath.stream()
                .map(Station::new)
                .collect(Collectors.toList());
    }
}
