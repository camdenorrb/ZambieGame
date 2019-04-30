package me.camdenorrb.zambiegame.struct;

import com.sun.istack.internal.NotNull;


/**
 * A strict structure for lazy calls
 */
public abstract class LazyCallStruct {

	private boolean isCalled;


	/**
	 * Handles calls
	 */
	@NotNull
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
