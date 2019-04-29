package me.camdenorrb.zambiegame.struct;

import com.sun.istack.internal.NotNull;


public abstract class LazyCallStruct {

	private boolean isCalled;


	@NotNull
	protected abstract void onCall();


	public final void call() {

		if (isCalled) return;

		onCall();
		isCalled = true;
	}

}
