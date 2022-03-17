package common;

public enum Result {
    WRONG_LETTER,   // letter does not exist in the word
    LETTER_EXISTS,  // letter exists but is not in the right place
    CORRECT_LETTER  // letter exists and in the right place
}

