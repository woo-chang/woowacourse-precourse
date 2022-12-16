package pairmatching.view;

import java.util.List;
import java.util.StringJoiner;
import pairmatching.domain.Course;
import pairmatching.domain.Function;
import pairmatching.domain.Level;
import pairmatching.domain.MatchingStatus;
import pairmatching.domain.Pair;
import pairmatching.repository.MissionRepository;

public class OutputView {

    public void printSelectFunction() {
        System.out.println(OutputMessage.FUNCTION.getMessage());
        for (Function function : Function.values()) {
            System.out.println(function.toString());
        }
    }

    public void printPairMatchingInformation() {
        System.out.println(OutputMessage.LINE.getMessage());
        System.out.println(String.format(OutputMessage.COURSE.getMessage(), getCourses()));
        System.out.println(OutputMessage.MISSION.getMessage());
        System.out.println(printLevelPerMission());
        System.out.println(OutputMessage.LINE.getMessage());
    }

    private String getCourses() {
        StringJoiner joiner = new StringJoiner(" | ");
        for (Course course : Course.values()) {
            joiner.add(course.getName());
        }
        return joiner.toString();
    }

    private String printLevelPerMission() {
        StringJoiner levelJoiner = new StringJoiner("\n");
        for (Level level : Level.values()) {
            StringJoiner missionJoiner = new StringJoiner(" | ");
            joinMissions(level, missionJoiner);
            levelJoiner.add(String.format(OutputMessage.LEVEL_PER_MISSION.getMessage(),
                    level.getName(), missionJoiner.toString()));
        }
        return levelJoiner.toString();
    }

    private void joinMissions(Level level, StringJoiner joiner) {
        MissionRepository.findByLevel(level)
                .stream()
                .forEach(mission -> joiner.add(mission));
    }

    public void printSelections() {
        System.out.println(OutputMessage.SELECT.getMessage());
        System.out.println(OutputMessage.SELECT_EXAMPLE.getMessage());
    }

    public void printRematch() {
        System.out.println(OutputMessage.REMATCH.getMessage());
        System.out.println(getMatchingStatus());
    }

    private String getMatchingStatus() {
        StringJoiner joiner = new StringJoiner(" | ");
        for (MatchingStatus status : MatchingStatus.values()) {
            joiner.add(status.getValue());
        }
        return joiner.toString();
    }

    public void printMatchingResult(List<Pair> pairs) {
        System.out.println(OutputMessage.MATCHING_RESULT.getMessage());
        for (Pair pair : pairs) {
            System.out.println(pair);
        }
    }

    public void printClear() {
        System.out.println(OutputMessage.CLEAR.getMessage());
    }

    public static void printErrorMessage(String message) {
        System.out.println(String.format(OutputMessage.ERROR.getMessage(), message));
    }
}
