package tdnf.hangmanfx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import tdnf.hangmanfx.model.Game;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox keyboardVBox;

    @FXML
    private Label currentWordLabel;

    @FXML
    private ImageView hangmanImageView;

    private Game game;

    private String hint;

    @FXML
    void handleKeyboard(ActionEvent event) {

        pressKeyboardButton((Button) event.getTarget());
    }

    @FXML
    void handleNextWordButton(ActionEvent event) {
        
        nextWord();
    }

    @FXML
    void handleHintButton(ActionEvent event) {

        showMessage(AlertType.INFORMATION, "Hint", hint);
    }
    
    @FXML
    void handleSettingsButton(ActionEvent event) {
        
        show();
    }

    @FXML
    void initialize() {

        nextWord();
    }

    public void show() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/settings.fxml"));

        Parent root = null;

        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        SettingsController c = loader.getController();
        
        c.setSettings(game.getSettings());

        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle("Settings");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(root);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent()) {
            
            System.out.println(c.getDictionaryComboBox().getSelectionModel().getSelectedItem());

            System.out.println(result.get());

            System.out.println(c.fruitsCheckBox.isSelected());
        }
    }

    public void pressKeyboardButton(Button pressedButton) {

        if (pressedButton.isDisabled()) {
            return;
        }

        pressedButton.setDisable(true);

        String letter = pressedButton.getText();

        boolean isValidLetter = game.guess(letter);

        currentWordLabel.setText(game.toFormattedString());

        if (isValidLetter) {

            pressedButton.getStyleClass().add("correct");

            if (game.isWin()) {

                currentWordLabel.getStyleClass().add("correct");

                disableKeyboard();
            }

        } else {

            pressedButton.getStyleClass().add("wrong");

            if (game.isLost()) {

                currentWordLabel.getStyleClass().add("wrong");

                disableKeyboard();
            }
        }

        updateHangmanImageView();
    }

    private void nextWord() {

        this.game = new Game("apple");
        this.hint = "This is a hint";

        enableKeyboard();
        restartKeyboardColors();
        updateHangmanImageView();
        restartCurrentWordLabel();
    }

    private void restartCurrentWordLabel() {

        currentWordLabel.setText(game.toFormattedString());
        currentWordLabel.getStyleClass().remove("wrong");
        currentWordLabel.getStyleClass().remove("correct");
    }

    private void updateHangmanImageView() {

        String imagePath = "/images/hangman-" + game.getMaxAttemps() + ".png";

        hangmanImageView.setImage(new Image(getClass().getResourceAsStream(imagePath)));
    }

    private void enableKeyboard() {

        setDisableForAllKeyboardButtons(false);
    }

    private void disableKeyboard() {

        setDisableForAllKeyboardButtons(true);
    }

    private void setDisableForAllKeyboardButtons(boolean state) {

        keyboardVBox.lookupAll(".button").forEach(e -> {
            ((Button) e).setDisable(state);
        });
    }

    private void restartKeyboardColors() {

        keyboardVBox.lookupAll(".button").forEach(e -> {
            ((Button) e).getStyleClass().remove("wrong");
            ((Button) e).getStyleClass().remove("correct");
        });
    }

    private void showMessage(AlertType type, String title, String text) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(text);

        alert.showAndWait();
    }
}
