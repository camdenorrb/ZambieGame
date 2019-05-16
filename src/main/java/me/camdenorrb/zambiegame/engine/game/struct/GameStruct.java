package me.camdenorrb.zambiegame.engine.game.struct;

import me.camdenorrb.zambiegame.engine.game.base.GameBase;
import me.camdenorrb.zambiegame.engine.game.impl.GameTimer;


/**
 * A strict setup for games
 */
public abstract class GameStruct implements GameBase {

	protected long tps;

	private boolean isStarted;


	protected final GameTimer timer;

	private static final int MILLIS_IN_SEC = 1_000;


	/**
	 * Constructs a GameStruct
	 *
	 * @param tps The TPS the game will run at
	 */
	public GameStruct(long tps) {
		this.tps = tps;
		this.timer = new GameTimer(MILLIS_IN_SEC / tps, this::onTick);
	}


	/**
	 * Handles start
	 */
	protected abstract void onStart();

	/**
	 * Handles stop
	 */
	protected abstract void onStop();

	/**
	 * Handles tick
	 */
	protected abstract void onTick(GameTimer timer);


	/**
	 * Handles the starting of the GameStruct
	 */
	@Override
	public final void start() {

		if (isStarted) return;

		onStart();
		timer.start();

		isStarted = true;
	}

	/**
	 * Handles the stopping of the GameStruct
	 */
	@Override
	public final void stop() {

		if (!isStarted) return;

		onStop();
		timer.stop();

		isStarted = false;
	}


	/**
	 * Gets the timer for the GameStruct
	 *
	 * @return The GameStruct's timer
	 */
	public GameTimer getTimer() {
		return timer;
	}

	/**
	 * Tells whether or not it's started
	 *
	 * @return If it's started
	 */
	public final boolean isStarted() {
		return isStarted;
	}

	/**
	 * The TPS (Ticks Per Second) of the game
	 *
	 * @param tps The new tps
	 */
	public final void setTps(long tps) {
		timer.setTempo(MILLIS_IN_SEC / tps);
		this.tps = tps;
	}

}