package pairmatching.controller;

import java.util.List;
import pairmatching.config.PairMatchingConfig;
import pairmatching.domain.Function;
import pairmatching.domain.MatchingStatus;
import pairmatching.domain.Pair;
import pairmatching.dto.Selection;
import pairmatching.repository.CourseRepository;
import pairmatching.service.PairMatchingService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final PairMatchingService pairMatchingService = new PairMatchingService();

    public void process() {
        PairMatchingConfig.configure();
        do {
            outputView.printSelectFunction();
        } while (functionProcess(inputView.readFunction()));
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
        Selection selection = getSelection();
        List<Pair> pairs = CourseRepository.findByCourseAndMission(
                selection.getCourse(), selection.getMission());

        if (pairs.isEmpty()) {
            pairMatchingService.matching(selection, 1);
        }
        if (!pairs.isEmpty()) {
            if (rematchingProcess(selection) == MatchingStatus.NO) {
                return;
            }
        }

        outputView.printMatchingResult(pairMatchingService.search(selection));
    }

    private Selection getSelection() {
        outputView.printPairMatchingInformation();
        outputView.printSelections();
        return inputView.readSelection();
    }


    private MatchingStatus rematchingProcess(Selection selection) {
        MatchingStatus status = getMatchingStatus();
        if (status == MatchingStatus.YES) {
            pairMatchingService.matching(selection, 1);
        }
        return status;
    }

    private MatchingStatus getMatchingStatus() {
        outputView.printRematch();
        return inputView.readMatchingStatus();
    }

    private void searchProcess() {
        Selection selection = getSelection();
        outputView.printMatchingResult(pairMatchingService.search(selection));
    }

    private void clearProcess() {
        pairMatchingService.clear();
        outputView.printClear();
    }
}
