package me.camdenorrb.zambiegame.struct.lazy;

/**
 * A strict structure for lazy calls
 */
public abstract class LazyCallStruct {

	private boolean isCalled;


	/**
	 * Handles calls
	 */
	protected abstract void onCall();


	/**
	 * Submits the call
	 */
	public final void call() {

		if (isCalled) return;

		onCall();
		isCalled = true;
	}

}
