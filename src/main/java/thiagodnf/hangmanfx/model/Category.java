package thiagodnf.hangmanfx.model;

import java.util.List;

public class Category {

    private String name;

    private List<Word> words;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    
    public void setWords(List<Word> words) {
        this.words = words;
    }

    public List<Word> getWords() {
        return this.words;
    }
}
