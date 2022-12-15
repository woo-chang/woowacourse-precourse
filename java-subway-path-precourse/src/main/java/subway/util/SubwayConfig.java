package subway.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import subway.domain.Line;
import subway.domain.Station;
import subway.repository.LineRepository;
import subway.repository.StationRepository;

public class SubwayConfig {

    private SubwayConfig() {
    }

    public static void configure() {
        stationConfigure();
        lineConfigure();
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
        lines.forEach(LineRepository.addLine());
    }

}
