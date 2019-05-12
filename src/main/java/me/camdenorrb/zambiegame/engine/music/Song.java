package me.camdenorrb.zambiegame.engine.music;

import me.camdenorrb.zambiegame.type.Named;

import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static me.camdenorrb.zambiegame.utils.TryUtils.attemptOrBreak;


public class Song implements Named {

	private boolean loop;


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


	public void play() {
	//	AudioSystem.
	//inputStream.
	}

	public void pause() {

	}

	public void resume() {

	}

	public void seek(long milli) {

	}

	public boolean isPlaying() {
		// TODO
		return true;
	}


	public AudioInputStream getInputStream() {
		return inputStream;
	}

}
