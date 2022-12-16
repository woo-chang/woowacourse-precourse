package pairmatching.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;

public class CrewRepository {

    private static final Map<Crew, Map<Level, List<Crew>>> crews = new HashMap<>();

    public static void addCrew(Crew crew1, Crew crew2, Level level) {
        crews.computeIfAbsent(crew1, l -> new HashMap<>())
                .computeIfAbsent(level, c -> new ArrayList<>()).add(crew2);
        crews.computeIfAbsent(crew2, l -> new HashMap<>())
                .computeIfAbsent(level, c -> new ArrayList<>()).add(crew1);
    }
}
