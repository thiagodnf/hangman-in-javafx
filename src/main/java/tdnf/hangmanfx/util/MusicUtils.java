package tdnf.hangmanfx.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import tdnf.hangmanfx.util.ResourceUtils.ResourceName;

public class MusicUtils {

    public static void playWin() {

        Media media = (Media) ResourceUtils.getResource(ResourceName.MUSIC_WIN);

        playMusic(media, 1);
    }

    public static void playGameOver() {

        Media media = (Media) ResourceUtils.getResource(ResourceName.MUSIC_GAME_OVER);

        playMusic(media, 1);
    }

    public static void playSuccess() {

        Media media = (Media) ResourceUtils.getResource(ResourceName.MUSIC_SUCCESS);

        playMusic(media, 1);
    }

    public static void playFailure() {

        Media media = (Media) ResourceUtils.getResource(ResourceName.MUSIC_FAILURE);

        playMusic(media, 1);
    }

    public static void playBackgroundMusic() {

        Media media = (Media) ResourceUtils.getResource(ResourceName.MUSIC_BACKGROUND);

        playMusic(media, MediaPlayer.INDEFINITE);
    }

    public static void playMusic(Media media, int cycleCount) {

        // Instantiating MediaPlayer class
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Sets the audio playback volume. Its effect will be clamped to
        // the range [0.0, 1.0].
        mediaPlayer.setVolume(1.0);

        // by setting this property to true, the audio will be played
        mediaPlayer.setAutoPlay(true);

        // Play the music in loop
        mediaPlayer.setCycleCount(cycleCount);
    }

}
