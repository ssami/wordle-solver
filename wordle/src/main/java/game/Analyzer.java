package game;

import java.io.IOException;

public class Analyzer {

    public static void main(String[] args) throws IOException {
        int numTimes = 100;
        int numRights = 0;
        int totalNumGuesses = 0;
        int avgGuessesForEach;
        double percentageRights = 0.0;

        for (int i=0; i<numTimes; i++) {
            Wordle w = new Wordle();
            GameStats stats = w.run();
            if (stats.isRight()) {
                numRights++;
                totalNumGuesses += stats.getNumGuesses();
            }
            avgGuessesForEach = totalNumGuesses/numTimes;
            System.out.println("");
            percentageRights = (double)(numRights)/(double)numTimes*100;
            System.out.println("Percentage right guesses: " + percentageRights);
            System.out.println("Avg times to guess: " + avgGuessesForEach);
        }
    }


}
