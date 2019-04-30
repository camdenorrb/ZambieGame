package me.camdenorrb.zambiegame.engine.game.impl;

import me.camdenorrb.zambiegame.engine.game.struct.GameTimerStruct;
import me.camdenorrb.zambiegame.utils.TimerUtils;

import java.util.Timer;


/**
 * Handles the TPS (Ticks Per Second) of the game
 */
public class GameTimer extends GameTimerStruct {

	private long tempo;

	private final Runnable onTick;
	private final Timer timer = new Timer();


	public GameTimer(long tempo, Runnable onTick) {
		this.tempo = tempo;
		this.onTick = onTick;
	}


	@Override
	protected void onStart() {
		TimerUtils.scheduleRepeat(timer, 0, tempo, onTick);
	}

	@Override
	protected void onStop() {
		timer.cancel();
	}


	/**
	 * Sets the tempo to the new rate
	 *
	 * @param tempo The tempo the game should run
	 */
	public void setTempo(long tempo) {

		if (isRunning()) {
			timer.cancel();
			TimerUtils.scheduleRepeat(timer, 0, tempo, onTick);
		}

		this.tempo = tempo;
	}

}
