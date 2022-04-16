package tdnf.hangmanfx.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import tdnf.hangmanfx.util.FileUtils;

@Getter
@Setter
public class Settings {

	private List<Dictionary> dictionaries;

	private Dictionary selectedDictionary;

	private Set<String> categories;

	public Settings() {

		this.dictionaries = Arrays.asList(
			FileUtils.loadDictionary("en-us.json")
		);

		this.categories = new HashSet<>(Set.of("fruits", "carMakers"));

		this.selectedDictionary = this.dictionaries.get(0);
	}

	public Word getTargetWord() {

		List<Word> possibleWords = new ArrayList<>();

		for (Category category : selectedDictionary.getCategories()) {

			if (categories.contains(category.getName())) {

				possibleWords.addAll(category.getWords());
			}
		}
		
		Collections.shuffle(possibleWords);
	
        return possibleWords.get(0);
	}
}
