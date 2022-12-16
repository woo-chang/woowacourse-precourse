package pairmatching.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.domain.Level;

public class MissionRepository {

    private static final Map<Level, List<String>> missions = new HashMap<>();

    public static void addMission(Level level, String mission) {
        missions.computeIfAbsent(level, m -> new ArrayList<>()).add(mission);
    }
}
