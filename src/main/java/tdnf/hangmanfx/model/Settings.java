package tdnf.hangmanfx.model;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import tdnf.hangmanfx.util.FileUtils;

@Getter
public class Settings {

    private List<Dictionary> dictionaries;

    private int selectedDictionary;

    public Settings() {

        this.dictionaries = Arrays.asList(
            FileUtils.loadDictionary("en-us.json"),
            FileUtils.loadDictionary("pt-br.json")
        );

        this.selectedDictionary = 0;
    }

    public String getTargetWord() {
        // TODO Auto-generated method stub
        return null;
    }

}
