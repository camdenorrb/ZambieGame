package me.camdenorrb.zambiegame.engine.music;

import me.camdenorrb.zambiegame.base.tryblock.TypedTryBlock;
import me.camdenorrb.zambiegame.type.Named;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static me.camdenorrb.zambiegame.engine.music.Song.Status.PAUSED;
import static me.camdenorrb.zambiegame.engine.music.Song.Status.PLAYING;
import static me.camdenorrb.zambiegame.utils.TryUtils.attemptOrBreak;


/**
 * Represents a Song
 */
public class Song implements Named {

	private Clip clip;

	private Status status;


	private long lastFrame;

	private boolean shouldLoop, isClosed;


	private final String name;

	private final AudioInputStream inputStream;


	/**
	 * Constructs a Song instance
	 *
	 * @param name The name of the Song
	 * @param fileInput The file input for the Song
	 */
	public Song(String name, File fileInput) {
		this(name, attemptOrBreak(() -> new FileInputStream(fileInput)));
	}

	/**
	 * Constructs a Song instance
	 *
	 * @param name The name of the Song
	 * @param inputStream The input stream for the Song
	 */
	public Song(String name, InputStream inputStream) {
		this(name, attemptOrBreak(() -> getAudioInputStream(inputStream)));
	}

	/**
	 * Constructs a Song instance
	 *
	 * @param name The name of the Song
	 * @param inputStream The input stream for the Song
	 */
	public Song(String name, AudioInputStream inputStream) {
		this.name = name;
		this.inputStream = inputStream;
	}


	/**
	 * Gets the name of the Song
	 *
	 * @return The name of the Song
	 */
	@Override
	public String getName() {
		return name;
	}


	/**
	 * Plays the Song
	 *
	 * @param shouldLoop If the Song should loop
	 */
	public void play(boolean shouldLoop) {

		if (status == PLAYING || isClosed) return;

		clip = attemptOrBreak((TypedTryBlock<Clip>) AudioSystem::getClip);
		attemptOrBreak(() -> clip.open(inputStream));

		setShouldLooping(shouldLoop);

		clip.start();

		status = PLAYING;
	}

	/**
	 * Pauses the song
	 */
	public void close() {

		if (isClosed) return;

		clip.close();

		isClosed = true;
	}

	/**
	 * Pauses the song
	 */
	public void pause() {

		if (status != PLAYING || isClosed) return;

		lastFrame = clip.getMicrosecondPosition();
		clip.stop();

		status = PAUSED;
	}

	/**
	 * Resumes the Song
	 */
	public void resume() {

		if (status != PAUSED || isClosed) return;

		System.out.println(lastFrame);

		clip.setMicrosecondPosition(lastFrame);
		clip.start();

		status = PLAYING;
	}

	/**
	 * Seeks in the Song
	 *
	 * @param milli The position to seek to in milliseconds
	 */
	public void seek(long milli) {
		clip.setMicrosecondPosition(milli);
	}

	/**
	 * Sets if the Song should loop
	 *
	 * @param shouldLoop If the Song should loop
	 */
	public void setShouldLooping(boolean shouldLoop) {

		if (shouldLoop) {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else {
			clip.loop(0);
		}

		this.shouldLoop = shouldLoop;
	}

	/**
	 * Checks if the song should loop
	 *
	 * @return If the Song should loop
	 */
	public boolean isLooping() {
		return shouldLoop;
	}

	/**
	 * Checks if the song is playing
	 *
	 * @return If the Song should play
	 */
	public boolean isPlaying() {
		return status == PLAYING;
	}


	/**
	 * Gets the status of the Song
	 *
	 * @return The status of the Song
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Gets the input stream of the Song
	 *
	 * @return The input stream of the Song
	 */
	public AudioInputStream getInputStream() {
		return inputStream;
	}


	/**
	 * Represents the status of the song
	 */
	public enum Status {

		STOPPED("Stopped"), PAUSED("Paused"), PLAYING("Playing");


		private final String displayName;

		/**
		 * Constructs a Status type
		 *
		 * @param displayName The display name for the status
		 */
		Status(String displayName) {
			this.displayName = displayName;
		}


		/**
		 * Gets the display name
		 *
		 * @return the display name
		 */
		public String getDisplayName() {
			return displayName;
		}
	}

}
