package thiagodnf.hangmanfx.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import lombok.Getter;
import thiagodnf.hangmanfx.model.Dictionary;
import thiagodnf.hangmanfx.model.Settings;

@Getter
public class SettingsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Dictionary> dictionaryComboBox;

    @FXML
    private CheckBox carMakersCheckBox;

    @FXML
    private CheckBox fruitsCheckBox;

    private List<CheckBox> categoriesCheckBoxes;

    private Settings settings;

    @FXML
    private void initialize() {

    	this.categoriesCheckBoxes = Arrays.asList(
    		carMakersCheckBox,
    		fruitsCheckBox
		);
    }

    public void load(Settings settings) {

        this.settings = settings;

        for (Dictionary dictionary : settings.getDictionaries()) {
            dictionaryComboBox.getItems().add(dictionary);
        }

        dictionaryComboBox.getSelectionModel().select(settings.getSelectedDictionary());

		for (CheckBox checkBox : categoriesCheckBoxes) {

			String id = checkBox.getId();

			if (settings.getCategories().contains(id)) {
				checkBox.setSelected(true);
			}
		}
    }

    public void save(){

    	this.settings.setSelectedDictionary(dictionaryComboBox.getSelectionModel().getSelectedItem());

		for (CheckBox checkBox : categoriesCheckBoxes) {

			String id = checkBox.getId();

			if (checkBox.isSelected()) {
				settings.getCategories().add(id);
			} else {
				settings.getCategories().remove(id);
			}
		}
    }
}
