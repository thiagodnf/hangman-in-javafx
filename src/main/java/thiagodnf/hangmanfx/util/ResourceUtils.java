package thiagodnf.hangmanfx.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class ResourceUtils {

	public enum ResourceName {

		MUSIC_BACKGROUND,
		MUSIC_FAILURE,
		MUSIC_SUCCESS,
		MUSIC_GAME_OVER,
		MUSIC_WIN,
		APPLICATION_LOGO
	}

    private static Map<ResourceName, Object> resources = new HashMap<>();

    static {
        resources.put(ResourceName.MUSIC_BACKGROUND, loadMusics("/musics/background.wav"));
        resources.put(ResourceName.MUSIC_FAILURE, loadMusics("/musics/failure.mp3"));
        resources.put(ResourceName.MUSIC_SUCCESS, loadMusics("/musics/success.mp3"));
        resources.put(ResourceName.MUSIC_GAME_OVER, loadMusics("/musics/game-over.wav"));
        resources.put(ResourceName.MUSIC_WIN, loadMusics("/musics/win.mp3"));
        resources.put(ResourceName.APPLICATION_LOGO, loadImage("/images/logo.png"));
    }

	public static URI getResourceToURI(String name) {

		try {
			return ResourceUtils.class.getResource(name).toURI();
		} catch (URISyntaxException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	public static Media loadMusics(String name) {

		return new Media(getResourceToURI(name).toString());
	}

	public static Image loadImage(String name) {

		return new Image(getResourceToURI(name).toString());
	}

	public static Object getResource(ResourceName resourceName) {

	    return resources.get(resourceName);
	}
}
