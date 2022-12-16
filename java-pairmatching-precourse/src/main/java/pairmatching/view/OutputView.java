package pairmatching.view;

import java.util.StringJoiner;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.repository.MissionRepository;

public class OutputView {

    private static final String ERROR = "[ERROR] %s";
    private static final String LINE = "#############################################";
    private static final String COURSE = "과정: %s";
    private static final String MISSION = "미션:";
    private static final String LEVEL_PER_MISSION = "  - %s: %s";

    public void printInformation() {
        System.out.println(LINE);
        System.out.println(String.format(COURSE, generateCourseInformation()));
        System.out.println(MISSION);
        printLevelPerMission();
        System.out.println(LINE);
    }

    private String generateCourseInformation() {
        StringJoiner joiner = new StringJoiner(" | ");
        for (Course course : Course.values()) {
            joiner.add(course.getName());
        }
        return joiner.toString();
    }

    private void printLevelPerMission() {
        for (Level level : Level.values()) {
            StringJoiner joiner = new StringJoiner(" | ");
            MissionRepository.findByLevel(level)
                    .stream()
                    .forEach(mission -> joiner.add(mission));
            System.out.println(String.format(LEVEL_PER_MISSION, level.getName(), joiner));
        }
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        System.out.println(String.format(ERROR, message));
    }
}
