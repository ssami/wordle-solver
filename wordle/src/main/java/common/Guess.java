package common;

import java.util.List;

public class Guess {

    private final String word;

    private List<Result> result;


    public Guess(String word, List<Result> results) {
        this.word = word;
        this.result = results;
    }

    void setResult(List<Result> result) {
        this.result = result;
    }

    public String getWord() {
        return word;
    }

    public List<Result> getResult() {
        return result;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder("Word guessed: ");
        buffer.append(word);
        buffer.append("\n");
        for (Result r : result) {
            buffer.append(r.toString());
            buffer.append("/");
        }
        return buffer.toString();
    }
}
