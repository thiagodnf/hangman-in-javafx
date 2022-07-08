package thiagodnf.hangmanfx;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import thiagodnf.hangmanfx.util.ResourceUtils;

public class Launcher extends Application {

	public static void main(String[] args) {
    	launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("HangmanFX");
//        primaryStage.setMinHeight(480);
//        primaryStage.setMinWidth(640);
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

        	@Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        primaryStage.getIcons().add((Image) ResourceUtils.getResource(ResourceUtils.ResourceName.APPLICATION_LOGO));

        primaryStage.show();
    }
}
