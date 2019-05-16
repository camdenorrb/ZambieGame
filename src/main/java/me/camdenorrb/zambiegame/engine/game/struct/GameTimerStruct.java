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


	/**
	 * Handles the starting of the GameStruct
	 */
	@Override
	public final void start() {

		if (isRunning) return;

		onStart();
		isRunning = true;
	}

	/**
	 * Handles the stopping of the GameStruct
	 */
	@Override
	public final void stop() {

		if (!isRunning) return;

		onStop();
		isRunning = false;
	}


	/**
	 * Returns whether or not the timer is running
	 *
	 * @return If the timer is running
	 */
	@Override
	public final boolean isRunning() {
		return isRunning;
	}

}