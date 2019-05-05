package me.camdenorrb.zambiegame.engine.game.impl;

import me.camdenorrb.zambiegame.engine.game.struct.GameTimerStruct;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Handles the TPS (Ticks Per Second) of the game
 */
public class GameTimer extends GameTimerStruct {

	private long tempo;


	private final Runnable onTick;

	private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();


	public GameTimer(long tempo, Runnable onTick) {
		this.tempo = tempo;
		this.onTick = onTick;
	}


	@Override
	protected void onStart() {
		executor.scheduleAtFixedRate(onTick, tempo, tempo, TimeUnit.MILLISECONDS);
	}

	@Override
	protected void onStop() {
		executor.shutdownNow();
	}


	/**
	 * Sets the tempo to the new rate
	 *
	 * @param tempo The tempo the game should run
	 */
	public void setTempo(long tempo) {

		if (isRunning()) {
			executor.shutdownNow();
			executor.scheduleAtFixedRate(onTick, tempo, tempo, TimeUnit.MILLISECONDS);
		}

		this.tempo = tempo;
	}

}