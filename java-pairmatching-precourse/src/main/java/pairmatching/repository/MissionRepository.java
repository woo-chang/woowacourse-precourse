package pairmatching.repository;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.domain.Level;

public class MissionRepository {

    private static final Map<Level, List<String>> missions = new HashMap<>(generateMissions());

    private static Map<Level, ArrayList<String>> generateMissions() {
        return Map.ofEntries(
                new SimpleEntry<>(Level.LEVEL1,
                        new ArrayList<>(List.of("자동차경주", "로또", "숫자야구게임"))),
                new SimpleEntry<>(Level.LEVEL2,
                        new ArrayList<>(List.of("장바구니", "결제", "지하철노선도"))),
                new SimpleEntry<>(Level.LEVEL3, new ArrayList<>()),
                new SimpleEntry<>(Level.LEVEL4, new ArrayList<>(List.of("성능개선", "배포"))),
                new SimpleEntry<>(Level.LEVEL5, new ArrayList<>())
        );
    }

    public static List<String> findByLevel(Level level) {
        return missions.get(level);
    }
}
