package me.camdenorrb.zambiegame.engine.music;

import me.camdenorrb.zambiegame.type.Named;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.function.Supplier;

import static me.camdenorrb.zambiegame.utils.TryUtils.attemptOrBreak;


/**
 * Represents a Song
 */
public class Song implements Named {

	private Clip clip;

	private final String name;
	private final Supplier<AudioInputStream> inputStreamSupplier;


	/**
	 * Construct a Song
	 *
	 * @param name The name of the song
	 * @param inputStreamSupplier The input stream supplier for the song
	 */
	public Song(String name, Supplier<InputStream> inputStreamSupplier) {
		this.name = name;
		this.inputStreamSupplier = () -> attemptOrBreak(() -> AudioSystem.getAudioInputStream(inputStreamSupplier.get()));
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
	 * Gets the input stream supplier
	 *
	 * @return The audio input stream supplier
	 */
	public Supplier<AudioInputStream> getInputStreamSupplier() {
		return inputStreamSupplier;
	}

	/**
	 * Plays the song
	 */
	public void play() {
		if (clip != null && clip.isActive()) {
			return;
		}

		clip = createNewAudioClip().orElseThrow(OutOfMemoryError::new);

		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
	}


	/**
	 * Stops the song
	 */
	public void stop() {
		if (clip == null || !clip.isActive()) {
			return;
		}

		clip.close();
	}


	/**
	 * Pauses the song
	 */
	public void pause() {
		if (clip == null || !clip.isActive()) {
			return;
		}

		clip.stop();
	}

	/**
	 * Resumes the song
	 */
	public void resume() {
		if (clip == null || clip.isActive()) {
			return;
		}

		clip.start();
	}

	/**
	 * Resets the song
	 */
	public void reset() {
		if (clip != null) {
			clip.close();
		}

		play();
	}


	/**
	 * Checks if the song is playing
	 *
	 * @return If the Song should play
	 */
	public boolean isPlaying() {
		return clip != null && clip.isActive();
	}


	/**
	 * Gets the audio input stream
	 *
	 * @return The audio input stream
	 */
	private Optional<AudioInputStream> getAudioInputStream() {
		return Optional.ofNullable(this.inputStreamSupplier.get());
	}

	/**
	 * Creates a new audio clip
	 *
	 * @return The new audio clip
	 */
	private Optional<Clip> createNewAudioClip() {

		final Optional<AudioInputStream> stream = getAudioInputStream();

		if (!stream.isPresent()) {
			return Optional.empty();
		}

		try {
			final Clip clip = AudioSystem.getClip();
			clip.open(stream.get());

			return Optional.of(clip);
		}
		catch (IOException ex) {
			System.out.println("IOException [0]");
		}
		catch (LineUnavailableException ex) {
			System.out.println("LineUnavailable [1]");
		}

		return Optional.empty();
	}

}
