package pairmatching.domain;

import java.util.List;
import java.util.StringJoiner;

public class Pair {

    private final List<String> names;

    public Pair(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }

    @Override
    public String toString() {
//        StringJoiner joiner = new StringJoiner(" : ");
//        for (String name : names) {
//            joiner.add(name);
//        }
//        return joiner.toString();
        return String.join(" : ", names);
    }
}
