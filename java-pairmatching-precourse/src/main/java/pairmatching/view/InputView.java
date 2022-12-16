package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import pairmatching.domain.Course;
import pairmatching.domain.Function;
import pairmatching.domain.Level;
import pairmatching.domain.MatchingStatus;
import pairmatching.dto.ChoiceResult;
import pairmatching.repository.MissionRepository;

public class InputView {

    private static final String FUNCTION_MESSAGE = "기능을 선택하세요.";
    private static final String CHOICE_MESSAGE = "과정, 레벨, 미션을 선택하세요.";
    private static final String CHOICE_EXAMPLE = "ex) 백엔드, 레벨1, 자동차경주";
    private static final String MATCH_MESSAGE = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?";

    public Function readFunction() {
        try {
            explainFunction();
            String input = Console.readLine();
            return Function.from(input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readFunction();
        }
    }

    private void explainFunction() {
        OutputView.printMessage(FUNCTION_MESSAGE);
        for (Function value : Function.values()) {
            OutputView.printMessage(value.toString());
        }
    }

    public ChoiceResult readChoice() {
        try {
            OutputView.printMessage(CHOICE_MESSAGE);
            OutputView.printMessage(CHOICE_EXAMPLE);
            String input = Console.readLine();
            return convertChoice(input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readChoice();
        }
    }

    private ChoiceResult convertChoice(String input) {
        List<String> choices = Arrays.stream(input.split(","))
                .map(choice -> choice.trim())
                .collect(Collectors.toList());
        validateChoices(choices);
        return generateChoiceResult(choices);
    }

    private void validateChoices(List<String> choices) {
        if (choices.size() != 3) {
            throw new IllegalArgumentException("과정, 레벨, 미션 모두 입력해야 합니다.");
        }
    }

    private ChoiceResult generateChoiceResult(List<String> choices) {
        Course course = Course.from(choices.get(0));
        Level level = Level.from(choices.get(1));
        String mission = choices.get(2);
        List<String> missions = MissionRepository.findByLevel(level);
        if (!missions.contains(mission)) {
            throw new IllegalArgumentException("존재하지 않는 미션입니다.");
        }
        return new ChoiceResult(course, level, mission);
    }

    public MatchingStatus readMatchingStatus() {
        try {
            explainMatchingStatus();
            String input = Console.readLine();
            return MatchingStatus.from(input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readMatchingStatus();
        }
    }

    private void explainMatchingStatus() {
        OutputView.printMessage(MATCH_MESSAGE);
        StringJoiner joiner = new StringJoiner(" | ");
        for (MatchingStatus status : MatchingStatus.values()) {
            joiner.add(status.getValue());
        }
        OutputView.printMessage(joiner.toString());
    }
}
