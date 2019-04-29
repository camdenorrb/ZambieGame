package me.camdenorrb.zambiegame.engine.game.struct;

import me.camdenorrb.zambiegame.engine.game.base.GameTimerBase;


public abstract class GameTimerStruct implements GameTimerBase {

	private boolean isRunning;


	protected abstract void onStart();

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
