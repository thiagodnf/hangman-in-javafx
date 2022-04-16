package tdnf.hangmanfx.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Dictionary {

    public String language;

    public Map<String, List<String>> categories;

    public Dictionary() {
        this.categories = new HashMap<>();
    }

    public Dictionary(String language) {

        this.language = language;
        this.categories = new HashMap<>();
    }

    public String getTargetWord() {
        return "opa".toUpperCase();
    }
}
