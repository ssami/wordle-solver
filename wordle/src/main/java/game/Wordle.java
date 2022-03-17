package game;

import common.Guess;
import common.Result;
import common.WordDictionary;
import solver.NoValidCandidateException;
import solver.Solver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Wordle {

    private static final int NUMBER_GUESSES = 6;

    private int numGuesses = 0;

    private Set<Character> charSet = new HashSet<>();

    private boolean isGuessedRight = false;

    private final String theWord;

    protected Wordle() throws IOException {
        WordDictionary localDict = new WordDictionary();
        this.theWord = localDict.getRandomWord();
        System.out.println("The word to guess is... " + theWord);
        for (char c: theWord.toCharArray()) {
            this.charSet.add(c);
        }
    }

    private Guess respondToGuess(String candidate) {
        numGuesses++;
        int numRightLetters = 0;

        List<Result> results = new ArrayList<>();
        char[] characters = candidate.toCharArray();
        for (int i = 0; i < characters.length; i++){
            if (characters[i] == theWord.charAt(i)) {
                results.add(Result.CORRECT_LETTER);
                numRightLetters++;
            }
            else if (this.charSet.contains(characters[i])){
                results.add(Result.LETTER_EXISTS);
            }
            else {
                results.add(Result.WRONG_LETTER);
            }
        }
        if (numRightLetters == candidate.length()) {
            this.isGuessedRight = true;
        }
        Guess result = new Guess(candidate, results);
        if (this.isGuessedRight) {
            System.out.println("You win! You guessed " + candidate + " correctly!");
        }
        System.out.println(result);
        return result;
    }

    private boolean isGameDone() {
        return (this.isGuessedRight || numGuesses >= NUMBER_GUESSES);
    }


    public GameStats run() throws IOException {
        Solver solver = new Solver();
        Guess previousGuess = new Guess("", new ArrayList<>());
        while (!this.isGameDone()) {
            try {
                String candidate = solver.run(previousGuess);
                previousGuess = this.respondToGuess(candidate);
            } catch (NoValidCandidateException ex) {
                System.err.println("No valid candidates were found in dictionary! Exiting... ");
                break;
            }
        }

        return new GameStats(this.numGuesses, this.isGuessedRight);
    }

    public static void main(String[] args) throws IOException {
        Wordle w = new Wordle();
        GameStats stats = w.run();
        System.out.println(stats);
    }
}
