package me.camdenorrb.zambiegame.engine.game.base;

/**
 * The basics for every game timer
 */
public interface GameTimerBase {

	/**
	 * Starts the timer
	 */
	void start();

	/**
	 * Stops the timer
	 */
	void stop();

	/**
	 * Tells whether or not the timer is running
	 *
	 * @return if the timer is running
	 */
	boolean isRunning();

}
