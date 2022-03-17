package solver.clues;

public class LengthClue extends AbstractCluePredicate {

    private final int validLength;

    public LengthClue(char c, int p) {
        throw new RuntimeException("char and p don't matter");
    }

    public LengthClue(int validLength) {
        this.validLength = validLength;
    }

    @Override
    public boolean test(String candidateWord) {
        return (candidateWord.length() == this.validLength);
    }
}
