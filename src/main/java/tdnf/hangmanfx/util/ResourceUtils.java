package tdnf.hangmanfx.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.media.Media;

public class ResourceUtils {

	public enum ResourceName {

		MUSIC_BACKGROUND,
		MUSIC_FAILURE,
		MUSIC_SUCCESS,
		MUSIC_LOOSE,
		MUSIC_WIN
	}

	private static Map<ResourceName, Object> resources = new HashMap<>();

	static {
		resources.put(ResourceName.MUSIC_BACKGROUND, loadMusics("/musics/background.wav"));
		resources.put(ResourceName.MUSIC_FAILURE, loadMusics("/musics/failure.mp3"));
		resources.put(ResourceName.MUSIC_SUCCESS, loadMusics("/musics/success.mp3"));
		resources.put(ResourceName.MUSIC_LOOSE, loadMusics("/musics/loose.mp3"));
		resources.put(ResourceName.MUSIC_WIN, loadMusics("/musics/win.mp3"));
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

	public static Object getResource(ResourceName resourceName) {
		return resources.get(resourceName);
	}
}
