package common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class WordDictionary {

    private final List<String> localDict;
    private final Random randomizer;

    public WordDictionary() throws IOException {
        this.localDict = loadDictionary();
        randomizer = new Random();
    }

    private List<String> loadDictionary() throws IOException {
        Path dictPath = Paths.get("src/main/resources/wordle-answers-alphabetical.txt");
        return Files.readAllLines(dictPath);
    }

    public String getRandomWord() {
        int randomIndex = this.randomizer.nextInt(this.localDict.size());
        return this.localDict.get(randomIndex);
    }

    public List<String> getDictionary() {
        return this.localDict;
    }
}
