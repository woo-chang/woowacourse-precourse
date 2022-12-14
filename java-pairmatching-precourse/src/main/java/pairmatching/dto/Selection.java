package pairmatching.dto;

import pairmatching.domain.Course;
import pairmatching.domain.Level;

public class Selection {

    private final Course course;
    private final Level level;
    private final String mission;

    public Selection(Course course, Level level, String mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public String getMission() {
        return mission;
    }
}
