package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Course;
import pairmatching.domain.Function;
import pairmatching.domain.Level;
import pairmatching.domain.MatchingStatus;
import pairmatching.dto.Selection;
import pairmatching.repository.MissionRepository;

public class InputView {

    public Function readFunction() {
        try {
            String input = Console.readLine();
            return Function.from(input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readFunction();
        }
    }

    public Selection readSelection() {
        try {
            String input = Console.readLine();
            return generateSelection(input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readSelection();
        }
    }

    private Selection generateSelection(String input) {
        List<String> selection = convertSelection(input);
        validateSelection(selection);
        return makeSelection(selection);
    }

    private static List<String> convertSelection(String input) {
        return Arrays.stream(input.split(","))
                .map(choice -> choice.trim())
                .collect(Collectors.toList());
    }

    private void validateSelection(List<String> choices) {
        if (choices.size() != 3) {
            throw new IllegalArgumentException("과정, 레벨, 미션 모두 입력해야 합니다.");
        }
    }

    private Selection makeSelection(List<String> selection) {
        Course course = Course.from(selection.get(0));
        Level level = Level.from(selection.get(1));
        String mission = selection.get(2);
        List<String> missions = MissionRepository.findByLevel(level);
        validateMission(mission, missions);
        return new Selection(course, level, mission);
    }

    private static void validateMission(String mission, List<String> missions) {
        if (!missions.contains(mission)) {
            throw new IllegalArgumentException("존재하지 않는 미션입니다.");
        }
    }

    public MatchingStatus readMatchingStatus() {
        try {
            String input = Console.readLine();
            return MatchingStatus.from(input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readMatchingStatus();
        }
    }
}
