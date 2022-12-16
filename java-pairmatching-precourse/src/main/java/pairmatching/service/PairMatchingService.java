package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Crew;
import pairmatching.domain.Pair;
import pairmatching.dto.Selection;
import pairmatching.repository.CourseRepository;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.NameRepository;

public class PairMatchingService {

    public List<Pair> matching(Selection selection, int count) {
        try {
            validateCount(count);
            return generatePairs(shuffleNames(selection), selection);
        } catch (IllegalStateException e) {
            return matching(selection, count + 1);
        }
    }

    private void validateCount(int count) {
        if (count == 3) {
            throw new IllegalArgumentException("3회 시도까지 매칭되지 않거나 매칭할 수 있는 경우의 수가 존재하지 않습니다.");
        }
    }

    private List<String> shuffleNames(Selection selection) {
        return Randoms.shuffle(NameRepository.findByCourse(selection.getCourse()));
    }

    private List<Pair> generatePairs(List<String> names, Selection selection) {
        List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < names.size() - 1; i += 2) {
            Pair pair = new Pair(new ArrayList<>(List.of(names.get(i), names.get(i + 1))));
            if (names.size() - i == 3) {
                pair.getNames().add(names.get(names.size() - 1));
            }
            validatePair(selection, pair);
            pairs.add(pair);
        }

        storePairs(selection, pairs);
        return pairs;
    }

    private void validatePair(Selection selection, Pair pair) {
        List<Crew> crews = generateCrews(selection, pair);
        for (int i = 0; i < crews.size() - 1; i++) {
            for (int j = i + 1; j < crews.size(); j++) {
                List<Crew> matchingCrews = CrewRepository.findByCrewAndLevel(
                        crews.get(i), selection.getLevel());
                validateMatchingCrew(matchingCrews, crews.get(j));
            }
        }
    }

    private static List<Crew> generateCrews(Selection selection, Pair pair) {
        return pair.getNames().stream()
                .map(name -> new Crew(selection.getCourse(), name))
                .collect(Collectors.toList());
    }

    private void validateMatchingCrew(List<Crew> matchingCrews, Crew crew) {
        if (matchingCrews.contains(crew)) {
            throw new IllegalStateException("이미 만난적 있는 크루입니다.");
        }
    }

    private void storePairs(Selection selection, List<Pair> pairs) {
        CourseRepository.addCourses(selection.getCourse(), selection.getMission(), pairs);
        for (Pair pair : pairs) {
            storeCrew(pair.getNames(), selection);
        }
    }

    private void storeCrew(List<String> names, Selection choice) {
        for (int i = 0; i < names.size() - 1; i++) {
            for (int j = i + 1; j < names.size(); j++) {
                Crew crew1 = new Crew(choice.getCourse(), names.get(i));
                Crew crew2 = new Crew(choice.getCourse(), names.get(j));
                CrewRepository.addCrew(crew1, crew2, choice.getLevel());
            }
        }
    }

    public List<Pair> search(Selection selection) {
        return CourseRepository.findByCourseAndMission(
                selection.getCourse(), selection.getMission());
    }

    public void clear() {
        CourseRepository.clear();
        CrewRepository.clear();
    }
}
