package thiagodnf.hangmanfx.model;

import thiagodnf.hangmanfx.util.ArrayUtils;

public class Game {

    private Settings settings;

    private String[] targetWord;

    private String[] currentWord;

    private String hint;

    private int maxAttemps;

    private int winCounter;

    private int loseCounter;

    public Game() {
        this.settings = new Settings();
        this.winCounter = 0;
        this.loseCounter = 0;
    }

    public void nextWord() {

        Word word = settings.getTargetWord();

        this.targetWord = word.getName().toUpperCase().split("");
        this.currentWord = ArrayUtils.replace(this.targetWord, "_");
        this.hint = word.getHint();
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

    public void addWin() {

        this.winCounter++;
    }

    public void addLose() {

        this.loseCounter++;
    }
    
    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getHint() {
        return this.hint;
    }

    public int getWinCounter() {
        return this.winCounter;
    }

    public int getLoseCounter() {
        return this.loseCounter;
    }

    public Settings getSettings() {
        return this.settings;
    }
}
