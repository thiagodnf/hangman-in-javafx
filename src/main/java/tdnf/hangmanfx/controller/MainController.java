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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import tdnf.hangmanfx.model.Game;
import tdnf.hangmanfx.util.ResourceUtils;
import tdnf.hangmanfx.util.ResourceUtils.ResourceName;

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

		playBackgroundMusic();

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

		SettingsController c = loader.getController();

		c.setSettings(game.getSettings());

		c.load();

		Dialog<ButtonType> dialog = new Dialog<>();

		dialog.setTitle("Settings");
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		dialog.getDialogPane().setContent(root);

		Optional<ButtonType> result = dialog.showAndWait();

		if (result.isPresent()) {

			c.save();
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

			playSuccess();
			
			pressedButton.getStyleClass().add("correct");

			if (game.isWin()) {

				playWin();
				
				currentWordLabel.getStyleClass().add("correct");

				disableKeyboard();
			}

		} else {
			
			playFailure();

			pressedButton.getStyleClass().add("wrong");

			if (game.isLost()) {

				playLoose();
				
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
	
	private void playWin() {
		
		Media media = (Media) ResourceUtils.getResource(ResourceName.MUSIC_WIN);

		playMusic(media,  1.0, 1);
	}

	
	private void playLoose() {
		
		Media media = (Media) ResourceUtils.getResource(ResourceName.MUSIC_LOOSE);

		playMusic(media,  1.0, 1);
	}

	private void playSuccess() {
		
		Media media = (Media) ResourceUtils.getResource(ResourceName.MUSIC_SUCCESS);

		playMusic(media,  1.0, 1);
	}
	
	private void playFailure() {
		
		Media media = (Media) ResourceUtils.getResource(ResourceName.MUSIC_FAILURE);

		playMusic(media,  1.0, 1);
	}
	
	private void playBackgroundMusic() {

		Media media = (Media) ResourceUtils.getResource(ResourceName.MUSIC_BACKGROUND);

		playMusic(media, 0.1, MediaPlayer.INDEFINITE);
	}

	private void playMusic(Media media, double volume, int cycleCount) {

		// Instantiating MediaPlayer class
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		
		// Sets the audio playback volume. Its effect will be clamped to 
		// the range [0.0, 1.0].
		mediaPlayer.setVolume(volume);

		// by setting this property to true, the audio will be played
		mediaPlayer.setAutoPlay(true);

		// Play the music in loop
		mediaPlayer.setCycleCount(cycleCount);
	}
}
