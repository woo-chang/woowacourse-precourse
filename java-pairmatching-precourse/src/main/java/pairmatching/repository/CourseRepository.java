package pairmatching.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.domain.Course;
import pairmatching.domain.Pair;

public class CourseRepository {

    private static final Map<Course, Map<String, List<Pair>>> courses = new HashMap<>();

    public static void addCourses(Course course, String mission, Pair pair) {
        courses.computeIfAbsent(course, m -> new HashMap<>())
                .computeIfAbsent(mission, p -> new ArrayList<>()).add(pair);
    }
}
