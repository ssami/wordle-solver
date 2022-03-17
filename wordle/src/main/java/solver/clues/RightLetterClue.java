package solver.clues;

public class RightLetterClue extends AbstractCluePredicate {

    private final char letter;

    private final int position;

    public RightLetterClue(char c, int p) {
        this.letter = c;
        this.position = p;
    }


    @Override
    public boolean test(String candidateWord) {
        return (candidateWord.charAt(this.position) == this.letter);
    }
}
