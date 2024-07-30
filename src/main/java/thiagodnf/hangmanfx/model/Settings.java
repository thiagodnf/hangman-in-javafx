package thiagodnf.hangmanfx.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import thiagodnf.hangmanfx.util.FileUtils;

public class Settings {

    private List<Dictionary> dictionaries;

    private Dictionary selectedDictionary;

    private Set<String> categories;

    public Settings() {

        this.dictionaries = Arrays.asList(FileUtils.loadDictionary("en-us.json"));

        this.categories = new HashSet<>(Set.of("fruits", "carMakers"));

        this.selectedDictionary = this.dictionaries.get(0);
    }

    public Word getTargetWord() {
        return selectedDictionary.getTargetWord(categories);
    }

    public List<Dictionary> getDictionaries() {
        return this.dictionaries;
    }

    public Dictionary getSelectedDictionary() {
        return selectedDictionary;
    }

    public Set<String> getCategories() {
        return this.categories;
    }

    public void setSelectedDictionary(Dictionary selectedDictionary) {
        this.selectedDictionary = selectedDictionary;
    }

}
