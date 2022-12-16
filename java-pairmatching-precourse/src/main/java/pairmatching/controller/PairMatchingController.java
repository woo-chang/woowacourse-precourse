package pairmatching.controller;

import java.util.List;
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
        return true;
    }

    private void matchingProcess() {
        outputView.printInformation();
        ChoiceResult choice = inputView.readChoice();
        List<Pair> pairs = CourseRepository.findByCourseAndMission(choice.getCourse(),
                choice.getMission());

        if (!pairs.isEmpty()) {
            MatchingStatus status = inputView.readMatchingStatus();
            if (status == MatchingStatus.YES) {
                service.matching(choice, 1);
            }
        }

        outputView.printMatchingResult(service.search(choice));
    }

    private void searchProcess() {
        outputView.printInformation();
        ChoiceResult choice = inputView.readChoice();
        outputView.printMatchingResult(service.search(choice));
    }
}
