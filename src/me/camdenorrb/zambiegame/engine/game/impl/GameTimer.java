package me.camdenorrb.zambiegame.engine.game.impl;

import me.camdenorrb.zambiegame.engine.game.struct.GameTimerStruct;
import me.camdenorrb.zambiegame.utils.TimerUtils;

import java.util.Timer;


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


	public void setTempo(long tempo) {

		if (isRunning()) {
			timer.cancel();
			TimerUtils.scheduleRepeat(timer, 0, tempo, onTick);
		}

		this.tempo = tempo;
	}

}
