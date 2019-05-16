package me.camdenorrb.zambiegame.base;

/**
 * Represents a Module's basics
 */
public interface ModuleBase {

	/**
	 * Called when the Module is enabled
	 */
	void enable();

	/**
	 * Called when the Module is disabled
	 */
	void disable();


	/**
	 * Tells whether or not the Module in enabled
	 *
	 * @return If the Module in enabled
	 */
	boolean isEnabled();

}
