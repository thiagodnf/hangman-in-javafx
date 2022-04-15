package tdnf.hangmanfx;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private VBox keyboardVBox;

	@FXML
	private Label currentWordLabel;

	private List<String> targetWord;

	private List<String> currentWord;

	private int maxAttemps;

	@FXML
	void handleKeyboard(ActionEvent event) {

		pressKeyboardButton((Button) event.getTarget());
	}

	@FXML
	void initialize() {

		restartGame();
	}

	public void pressKeyboardButton(Button pressedButton) {

		if (pressedButton.isDisabled()) {
			return;
		}

		pressedButton.setDisable(true);

		String letter = pressedButton.getText();

		if (isValidLetter(letter)) {

			for (int i = 0; i < targetWord.size(); i++) {

				if (targetWord.get(i).equalsIgnoreCase(letter)) {
					currentWord.set(i, letter);
				}
			}

			printCurrentWord();

			if (isEndGame()) {

				showSuccess("You win!");
				restartGame();
			}

		} else {

			maxAttemps--;

			if (maxAttemps == 0) {

				showError("You loose!");
				restartGame();
			}
		}
	}

	public void printCurrentWord() {

		StringBuilder builder = new StringBuilder();

		for (String letter : currentWord) {

			if (letter.isBlank()) {
				builder.append("_").append(" ");
			} else {
				builder.append(letter).append(" ");
			}
		}

		currentWordLabel.setText(builder.toString());
	}

	public boolean isValidLetter(String letter) {

		return targetWord.contains(letter);
	}

	public boolean isEndGame() {

		return targetWord.toString().contentEquals(currentWord.toString());
	}

	private void restartGame() {

		this.targetWord = Arrays.asList("Q", "W", "E", "Q");
		this.currentWord = Arrays.asList("", "", "", "");

		this.maxAttemps = 3;

		activateAllKeyboardButtons();
		printCurrentWord();
	}

	private void activateAllKeyboardButtons() {

		keyboardVBox.lookupAll(".button").forEach(e -> {
			((Button) e).setDisable(false);
		});
	}

	private void showError(String text) {
		showMessage(AlertType.ERROR, "Ooops...", text);
	}

	private void showSuccess(String text) {
		showMessage(AlertType.INFORMATION, "Congratulations!", text);
	}

	private void showMessage(AlertType type, String title, String text) {

		Alert alert = new Alert(type);

		alert.setTitle(title);
		alert.setHeaderText(text);

		alert.showAndWait();
	}

}
