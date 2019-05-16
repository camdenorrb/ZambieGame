package me.camdenorrb.zambiegame.event.base;

/**
 * The base for a gif event handler
 */
public interface GifEventHandlerBase extends EventHandlerBase {

	/**
	 * Handles an update task
	 */
	default void handleUpdate() {}

}