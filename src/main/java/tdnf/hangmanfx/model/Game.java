package tdnf.hangmanfx.model;

import lombok.Getter;
import tdnf.hangmanfx.util.ArrayUtils;

@Getter
public class Game {
    
    private Settings settings;

    private String[] targetWord;

    private String[] currentWord;

    private int maxAttemps;

    public Game(String targetWord) {

        if (targetWord.isBlank()) {
            throw new IllegalArgumentException("The target word must not be blank");
        }

        if (targetWord.length() > 15) {
            throw new IllegalArgumentException("The target word must have <= 15 charactes");
        }
        
        this.settings = new Settings();
        
        nextWord();
    }
    
    public void nextWord() {
        
        this.targetWord ="opa".split("");
        this.currentWord = ArrayUtils.replace(this.targetWord, "_");
        this.maxAttemps = 6;
    }

    public boolean isWin() {

        return ArrayUtils.equals(this.targetWord, this.currentWord);
    }

    public boolean isLost() {

        return maxAttemps == 0;
    }

    public String toFormattedString() {

        return ArrayUtils.join(this.currentWord, " ");
    }

    public boolean guess(String letter) {

        boolean isValidLetter = false;

        for (int i = 0; i < targetWord.length; i++) {

            if (targetWord[i].equalsIgnoreCase(letter)) {

                currentWord[i] = targetWord[i];

                isValidLetter = true;
            }
        }

        if (!isValidLetter) {

            this.maxAttemps--;
        }

        return isValidLetter;
    }

    public int getMaxAttemps() {
        return this.maxAttemps;
    }
}
