package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Crew;
import pairmatching.domain.Pair;
import pairmatching.dto.ChoiceResult;
import pairmatching.repository.CourseRepository;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.NameRepository;

public class PairMatchingService {

    public List<Pair> matching(ChoiceResult choice, int count) {
        try {
            if (count == 3) {
                throw new IllegalArgumentException("3회 시도까지 매칭되지 않거나 매칭할 수 있는 경우의 수가 존재하지 않습니다.");
            }
            List<String> names = Randoms.shuffle(NameRepository.findByCourse(choice.getCourse()));
            return generatePairs(choice, names);
        } catch (IllegalStateException e) {
            return matching(choice, count + 1);
        }
    }

    private List<Pair> generatePairs(ChoiceResult choice, List<String> names) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < names.size(); i += 2) {
            Pair pair = new Pair(new ArrayList<>(List.of(names.get(i), names.get(i + 1))));
            if (names.size() - i == 3) {
                pair.getNames().add(names.get(names.size() - 1));
            }
            validate(choice, pair);
            pairs.add(pair);
        }
        storePairs(choice, pairs);
        return pairs;
    }

    private void validate(ChoiceResult choice, Pair pair) {
        List<String> names = pair.getNames();
        List<Crew> crews = names.stream()
                .map(name -> new Crew(choice.getCourse(), name))
                .collect(Collectors.toList());
        for (int i = 0; i < crews.size() - 1; i++) {
            for (int j = i + 1; j < crews.size(); j++) {
                List<Crew> matchingCrews = CrewRepository.findByCrewAndLevel(crews.get(i),
                        choice.getLevel());
                validateMatchingCrew(matchingCrews, crews.get(j));
            }
        }
    }

    private void validateMatchingCrew(List<Crew> matchingCrews, Crew crew) {
        if (matchingCrews.contains(crew)) {
            throw new IllegalStateException("이미 만난적 있는 크루입니다.");
        }
    }

    private void storePairs(ChoiceResult choice, List<Pair> pairs) {
        CourseRepository.addCourses(choice.getCourse(), choice.getMission(), pairs);
        for (Pair pair : pairs) {
            storeCrew(pair.getNames(), choice);
        }
    }

    private void storeCrew(List<String> names, ChoiceResult choice) {
        for (int i = 0; i < names.size() - 1; i++) {
            for (int j = i + 1; j < names.size(); j++) {
                Crew crew1 = new Crew(choice.getCourse(), names.get(i));
                Crew crew2 = new Crew(choice.getCourse(), names.get(j));
                CrewRepository.addCrew(crew1, crew2, choice.getLevel());
            }
        }
    }

    public List<Pair> search(ChoiceResult choice) {
        return CourseRepository.findByCourseAndMission(choice.getCourse(), choice.getMission());
    }

    public void clear() {
        CourseRepository.clear();
        CrewRepository.clear();
    }
}
