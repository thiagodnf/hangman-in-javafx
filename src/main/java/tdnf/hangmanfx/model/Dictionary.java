package tdnf.hangmanfx.model;

import java.util.List;

import lombok.Getter;

@Getter
public class Dictionary {

    public String language;
    
    public List<Category> categories;

//    public Map<String, List<String>> categories;

//    public Dictionary() {
//        this.categories = new HashMap<>();
//    }
//
//    public Dictionary(String language) {
//
//        this.language = language;
//        this.categories = new HashMap<>();
//    }
//
//    public String getTargetWord() {
//        return "opa".toUpperCase();
//    }
//    
    public String toString() {
    	return getLanguage();
    }
}
