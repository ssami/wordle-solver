package solver;

import common.Guess;
import common.WordDictionary;
import solver.clues.AbstractCluePredicate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Solver {

    private final WordDictionary dictionary;

    private final List<AbstractCluePredicate> clues;

    private final List<String> candidateWords;

    public Solver() throws IOException {
        this.dictionary = new WordDictionary();
        this.clues = new ArrayList<>();
        this.candidateWords = new ArrayList<>(this.dictionary.getDictionary());
        Collections.shuffle(this.candidateWords);
        String firstGuess = pickFirstGuess();
        this.candidateWords.add(0, firstGuess);
    }

    /**
     * 1. Word must have 5 distinct letters so we can get as many clues as possible.
     * 2. Favor the most used English letters. See
     * https://www3.nd.edu/~busiforc/handouts/cryptography/letterfrequencies.html
     * which puts this as E,A,R,I,O,T
     */
    private String pickFirstGuess() {
        Predicate<String> filter1 = new Solver1();
        Predicate<String> filter2 = new Solver2();
        Optional<String> firstGuess = this.candidateWords.stream()
                .filter(filter1.and(filter2))
                .findAny();
        return firstGuess.get();
    }

    public String run(Guess resultGuess) throws solver.NoValidCandidateException {
        generateClues(resultGuess);
        return generateGuess();
    }

    private String generateGuess() throws solver.NoValidCandidateException {
        Optional<String> candidate = this.candidateWords.stream()
                .filter(this::testWithClues)
                .findFirst();
        if (candidate.isPresent()) {
            return candidate.get();
        }
        else throw new solver.NoValidCandidateException("no valid candidates found!");
    }

    private void generateClues(Guess resultGuess) {
        boolean isRemove = false;
        char[] wordCharArray = resultGuess.getWord().toCharArray();

        for (int i=0; i<wordCharArray.length; i++) {
            char c = wordCharArray[i];
            switch(resultGuess.getResult().get(i)){
                case WRONG_LETTER:
                    this.clues.add(new solver.clues.WrongLetterClue(c));
                    isRemove = true; // no longer consider this word
                    break;
                case LETTER_EXISTS:
                    this.clues.add(new solver.clues.WrongPositionClue(c, i));
                    isRemove = true; // no longer consider this word
                    break;
                case CORRECT_LETTER:
                    this.clues.add(new solver.clues.RightLetterClue(c, i));
                    break;
            }
        }
        if (isRemove) {
            candidateWords.remove(resultGuess.getWord());
        }
    }

    private boolean testWithClues(String word) {
        // can't use stream/reduce with this.clues
        // because return type of "and" is Predicate,
        // not AbstractCluePredicate
        Predicate<String> base = x -> true;
        for (AbstractCluePredicate p: this.clues) {
            base = p.and(base);
        }
        return base.test(word);
    }
}
