package tdnf.hangmanfx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import tdnf.hangmanfx.model.Game;
import tdnf.hangmanfx.util.MusicUtils;

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

		showMessage(AlertType.INFORMATION, "Hint", game.getHint());
	}

	@FXML
	void handleSettingsButton(ActionEvent event) {

		show();
	}

	@FXML
	void initialize() {

	    MusicUtils.playBackgroundMusic();

		this.game = new Game();

		nextWord();
	}

	public void show() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/settings.fxml"));

		Parent root = null;

		try {
			root = (Parent) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		SettingsController controller = loader.getController();

		controller.load(game.getSettings());

		Dialog<ButtonType> dialog = new Dialog<>();

		dialog.setTitle("Settings");
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		dialog.getDialogPane().setContent(root);

		Optional<ButtonType> result = dialog.showAndWait();

		if (result.isPresent() && result.get() == ButtonType.OK) {

		    controller.save();
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

		    MusicUtils.playSuccess();
			
			pressedButton.getStyleClass().add("correct");

			if (game.isWin()) {

			    MusicUtils.playWin();
				
				currentWordLabel.getStyleClass().add("correct");

				disableKeyboard();
			}

		} else {
			
			MusicUtils.playFailure();

			pressedButton.getStyleClass().add("wrong");

			if (game.isLost()) {

			    MusicUtils.playGameOver();
				
				currentWordLabel.getStyleClass().add("wrong");

				disableKeyboard();
			}
		}

		updateHangmanImageView();
	}

	private void nextWord() {

		this.game.nextWord();

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
