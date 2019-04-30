package me.camdenorrb.zambiegame.engine.game.struct;

import me.camdenorrb.zambiegame.engine.game.base.GameTimerBase;


/**
 * A strict setup for game timers
 */
public abstract class GameTimerStruct implements GameTimerBase {

	private boolean isRunning;


	/**
	 * Handles start
	 */
	protected abstract void onStart();

	/**
	 * Handles stop
	 */
	protected abstract void onStop();


	@Override
	public final void start() {

		if (isRunning) return;

		onStart();
		isRunning = true;
	}

	@Override
	public final void stop() {

		if (!isRunning) return;

		onStop();
		isRunning = false;
	}


	@Override
	public final boolean isRunning() {
		return isRunning;
	}

}
