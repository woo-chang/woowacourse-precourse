package pairmatching.domain;

import java.util.List;
import java.util.Set;

public class Pair {

    private final List<String> names;

    public Pair(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }
}
