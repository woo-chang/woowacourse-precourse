package pairmatching.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.domain.Course;

public class NameRepository {

    private static final Map<Course, List<String>> names = new HashMap<>();

    public static void addNames(Course course, String name) {
        names.computeIfAbsent(course, n -> new ArrayList<>()).add(name);
    }
}
