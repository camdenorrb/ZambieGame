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
	 * blah blah
	 * @return blah
	 */
	public Supplier<AudioInputStream> getInputStreamSupplier() {
		return inputStreamSupplier;
	}


	public void play() {
		if (clip != null && clip.isActive()) {
			return;
		}

		clip = createNewAudioClip().orElseThrow(OutOfMemoryError::new);

		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
	}

	public void stop() {
		if (!clip.isActive()) {
			return;
		}

		clip.close();
	}


	public void pause() {
		if (!clip.isActive()) {
			return;
		}

		clip.stop();
	}

	public void resume() {
		if (clip.isActive()) {
			return;
		}

		clip.start();
	}

	public void reset() {
		clip.close();

		play();
	}


	/**
	 * Checks if the song is playing
	 *
	 * @return If the Song should play
	 */
	public boolean isPlaying() {
		return clip.isActive();
	}


	private Optional<AudioInputStream> getAudioInputStream() {
		return Optional.ofNullable(this.inputStreamSupplier.get());
	}

	private Optional<Clip> createNewAudioClip() {

		final Optional<AudioInputStream> stream = getAudioInputStream();

		if (!stream.isPresent()) {
			return Optional.empty();
		}

		try {
			Clip clip = AudioSystem.getClip();
			clip.open(stream.get());

			return Optional.of(clip);
		}
		catch (IOException ex) {
			System.out.println("shit broked [0]");
		}
		catch (LineUnavailableException ex) {
			System.out.println("shit broked [1]");
		}

		return Optional.empty();
	}

}
