package solver.clues;

public class WrongPositionClue extends AbstractCluePredicate {

    private final char letter;

    private final int position;

    public WrongPositionClue(char c, int p) {
        this.letter = c;
        this.position = p;
    }

    @Override
    public boolean test(String candidateWord) {
        return (candidateWord.charAt(this.position) != this.letter);
    }
}
