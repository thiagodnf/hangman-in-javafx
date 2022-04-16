package tdnf.hangmanfx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import lombok.Getter;
import tdnf.hangmanfx.model.Dictionary;
import tdnf.hangmanfx.model.Settings;

@Getter
public class SettingsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> dictionaryComboBox;

    @FXML
    private CheckBox carManufacturerCheckBox;

    @FXML
    public CheckBox fruitsCheckBox;

    @FXML
    void initialize() {

    }

    public void setSettings(Settings settings) {

        for (Dictionary d : settings.getDictionaries()) {
            dictionaryComboBox.getItems().add(d.getLanguage());
        }

        dictionaryComboBox.getSelectionModel().select(settings.getSelectedDictionary());

    }
}
