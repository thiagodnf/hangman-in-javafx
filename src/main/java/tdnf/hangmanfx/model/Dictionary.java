package tdnf.hangmanfx.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import lombok.Getter;

@Getter
public class Dictionary {

    public String language;
    
    public List<Category> categories;

    public Word getTargetWord(Set<String> targetCategories) {

		List<Word> possibleWords = new ArrayList<>();

		for (Category category : categories) {

			if (targetCategories.contains(category.getName())) {

				possibleWords.addAll(category.getWords());
			}
		}
		
		Collections.shuffle(possibleWords);
	
        return possibleWords.get(0);
	}
    
    public String toString() {
    	return getLanguage();
    }
}
