package me.camdenorrb.zambiegame.engine.game.struct;

import me.camdenorrb.zambiegame.engine.game.base.GameBase;
import me.camdenorrb.zambiegame.engine.game.impl.GameTimer;


public abstract class GameStruct implements GameBase {

	protected long tps;

	private boolean isStarted;


	private final GameTimer timer;

	private static final int MILLIS_IN_SEC = 1_000;


	public GameStruct(long tps) {
		this.tps = tps;
		this.timer = new GameTimer(MILLIS_IN_SEC / tps, this::onTick);
	}


	protected abstract void onStart();

	protected abstract void onStop();

	protected abstract void onTick();


	@Override
	public final void start() {

		if (isStarted) return;

		onStart();
		timer.start();

		isStarted = true;
	}

	@Override
	public final void stop() {

		if (!isStarted) return;

		timer.stop();
		onStop();

		isStarted = false;
	}


	public final boolean isStarted() {
		return isStarted;
	}

	public final void setTps(long tps) {
		timer.setTempo(MILLIS_IN_SEC / tps);
		this.tps = tps;
	}

}
