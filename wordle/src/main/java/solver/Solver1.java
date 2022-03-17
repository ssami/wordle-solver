package solver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class Solver1 implements Predicate<String> {

    @Override
    public boolean test(String s) {
        Set<String> letters = new HashSet(Arrays.asList(s.split("")));
        return (letters.size() == s.length());
    }
}
