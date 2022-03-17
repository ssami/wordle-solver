package solver.clues;

public class WrongLetterClue extends AbstractCluePredicate {

    private final char letter;

    public WrongLetterClue(char c) {
        this.letter = c;
    }

    @Override
    public boolean test(String candidateWord) {
        for (char c: candidateWord.toCharArray()) {
            if (this.letter == c) {
                return false;
            }
        }
        return true;
    }
}
