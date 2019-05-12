package me.camdenorrb.zambiegame.engine.music;

import me.camdenorrb.zambiegame.base.tryblock.TypedTryBlock;
import me.camdenorrb.zambiegame.impl.Player;
import me.camdenorrb.zambiegame.struct.LazyStruct;
import me.camdenorrb.zambiegame.type.Named;
import me.camdenorrb.zambiegame.utils.LazyUtils;
import me.camdenorrb.zambiegame.utils.TryUtils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static me.camdenorrb.zambiegame.engine.music.Song.Status.*;
import static me.camdenorrb.zambiegame.utils.TryUtils.attemptOrBreak;


public class Song implements Named {

	private Clip clip;

	private Status status;


	private long lastFrame;

	private boolean shouldLoop;


	private final String name;

	private final AudioInputStream inputStream;


	public Song(String name, File fileInput) {
		this(name, attemptOrBreak(() -> new FileInputStream(fileInput)));
	}

	public Song(String name, InputStream inputStream) {
		this(name, attemptOrBreak(() -> getAudioInputStream(inputStream)));
	}

	public Song(String name, AudioInputStream inputStream) {
		this.name = name;
		this.inputStream = inputStream;
	}


	@Override
	public String getName() {
		return name;
	}


	public void play(boolean shouldLoop) {

		if (status == PLAYING) return;

		clip = TryUtils.attemptOrBreak((TypedTryBlock<Clip>) AudioSystem::getClip);
		attemptOrBreak(() -> clip.open(inputStream));

		setShouldLooping(shouldLoop);

		clip.start();

		status = PLAYING;
	}

	public void pause() {

		if (status != PLAYING) return;

		lastFrame = clip.getMicrosecondPosition();
		clip.stop();

		status = PAUSED;
	}

	public void resume() {

		if (status != PAUSED) return;

		clip.setMicrosecondPosition(lastFrame);
		clip.start();

		status = PLAYING;
	}

	public void seek(long milli) {
		clip.setMicrosecondPosition(milli);
	}

	public void setShouldLooping(boolean shouldLoop) {

		if (shouldLoop) {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else {
			clip.loop(0);
		}

		this.shouldLoop = shouldLoop;
	}

	public boolean isLooping() {
		return shouldLoop;
	}

	public boolean isPlaying() {
		return status == PLAYING;
	}


	public Status getStatus() {
		return status;
	}

	public AudioInputStream getInputStream() {
		return inputStream;
	}


	public enum Status {

		STOPPED("Stopped"), PAUSED("Paused"), PLAYING("Playing");


		private final String displayName;

		Status(String displayName) {
			this.displayName = displayName;
		}


		public String getDisplayName() {
			return displayName;
		}
	}

}
