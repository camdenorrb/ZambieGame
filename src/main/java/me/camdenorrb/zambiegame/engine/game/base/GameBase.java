package me.camdenorrb.zambiegame.engine.game.base;

import me.camdenorrb.zambiegame.type.Named;


/**
 * The basics for every game
 */
public interface GameBase extends Named {

	/**
	 * Starts the game
	 */
	void start();

	/**
	 * Stops the game
	 */
	void stop();

}
