package pairmatching.controller;

import java.util.List;
import pairmatching.config.PairMatchingConfig;
import pairmatching.domain.Function;
import pairmatching.domain.MatchingStatus;
import pairmatching.domain.Pair;
import pairmatching.dto.ChoiceResult;
import pairmatching.repository.CourseRepository;
import pairmatching.service.PairMatchingService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final PairMatchingService service = new PairMatchingService();

    public void process() {
        PairMatchingConfig.configure();
        Function function;
        do {
            function = inputView.readFunction();
        } while (functionProcess(function));
    }

    private boolean functionProcess(Function function) {
        if (function == Function.QUIT) {
            return false;
        }
        if (function == Function.MATCHING) {
            matchingProcess();
        }
        if (function == Function.SEARCH) {
            searchProcess();
        }
        if (function == Function.CLEAR) {
            clearProcess();
        }
        return true;
    }

    private void matchingProcess() {
        outputView.printInformation();
        ChoiceResult choice = inputView.readChoice();
        List<Pair> pairs = CourseRepository.findByCourseAndMission(choice.getCourse(),
                choice.getMission());

        if (notEmpthCase(choice, pairs)) {
            return;
        }

        emptyCase(choice, pairs);

        outputView.printMatchingResult(service.search(choice));
    }

    private void emptyCase(ChoiceResult choice, List<Pair> pairs) {
        if (pairs.isEmpty()) {
            service.matching(choice, 1);
        }
    }

    private boolean notEmpthCase(ChoiceResult choice, List<Pair> pairs) {
        if (!pairs.isEmpty()) {
            MatchingStatus status = inputView.readMatchingStatus();
            if (status == MatchingStatus.YES) {
                service.matching(choice, 1);
            }
            if (status == MatchingStatus.NO) {
                return true;
            }
        }
        return false;
    }

    private void searchProcess() {
        outputView.printInformation();
        ChoiceResult choice = inputView.readChoice();
        outputView.printMatchingResult(service.search(choice));
    }

    private void clearProcess() {
        service.clear();
        OutputView.printMessage("초기화 되었습니다.");
    }
}
