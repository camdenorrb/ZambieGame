package me.camdenorrb.zambiegame.engine.game.impl;

import me.camdenorrb.zambiegame.engine.game.struct.GameTimerStruct;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static me.camdenorrb.zambiegame.utils.TryUtils.attemptOrPrintErr;


/**
 * Handles the TPS (Ticks Per Second) of the game
 */
public class GameTimer extends GameTimerStruct {

	private long tempo;


	private final Consumer<GameTimer> onTick;

	private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();


	/**
	 * Constructs a GameTimer
	 *
	 * @param tempo The tempo at which the timer will run, in milliseconds
	 * @param onTick The method to call each tick
	 */
	public GameTimer(long tempo, Consumer<GameTimer> onTick) {
		this.tempo = tempo;
		this.onTick = onTick;
	}


	/**
	 * Handles the starting of the timer
	 */
	@Override
	protected void onStart() {

		if (executor.isShutdown()) {
			executor = Executors.newSingleThreadScheduledExecutor();
		}

		executor.scheduleAtFixedRate(() -> attemptOrPrintErr(() -> onTick.accept(this)), tempo, tempo, TimeUnit.MILLISECONDS);
	}

	/**
	 * Handles the stopping of the timer
	 */
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
			executor = Executors.newSingleThreadScheduledExecutor();
			executor.scheduleAtFixedRate(() -> attemptOrPrintErr(() -> onTick.accept(this)), tempo, tempo, TimeUnit.MILLISECONDS);
		}

		this.tempo = tempo;
	}

	public long getTempo() {
		return tempo;
	}

}