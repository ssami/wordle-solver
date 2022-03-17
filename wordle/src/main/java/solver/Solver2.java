package solver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Returns true if you have at least three of the most common letters.
 */
public class Solver2 implements Predicate<String> {

    private static final Set<String> MOST_COMMON_LETTERS = new HashSet(Arrays.asList("E","A","R","I","O","T"));

    @Override
    public boolean test(String s) {
        Set<String> letters = new HashSet(Arrays.asList(s.toUpperCase().split("")));
        letters.retainAll(MOST_COMMON_LETTERS);
        return (letters.size() > 1);
    }
}
