package me.camdenorrb.zambiegame.engine.manager;

import me.camdenorrb.zambiegame.base.ModuleBase;
import me.camdenorrb.zambiegame.engine.game.impl.GameTimer;
import me.camdenorrb.zambiegame.engine.gif.processing.PGif;
import me.camdenorrb.zambiegame.struct.LazyStruct;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static me.camdenorrb.zambiegame.utils.LazyUtils.lazy;


public class PGifManager implements ModuleBase {

	private boolean isEnabled;

	private ConcurrentHashMap<PGif, Integer> gifs = new ConcurrentHashMap<>();


	private final Runnable loopTask = () -> gifs.forEach((gif, wait) -> {
		if (wait <= 0) {
			gif.playNextFrame();
			gifs.put(gif, gif.getDelayForCurrentFrame() * 10);
		}
		else {
			gifs.put(gif, wait - 1);
		}
	});

	private final LazyStruct<GameTimer> timer = lazy(() ->
		new GameTimer(1, loopTask)
	);


	@Override
	public void enable() {

		if (isEnabled) return;

		timer.get().start();

		isEnabled = true;
	}

	@Override
	public void disable() {

		if (!isEnabled) return;

		timer.get().stop();

		isEnabled = false;
	}


	@Override
	public boolean isEnabled() {
		return isEnabled;
	}


	public void addGif(PGif gif) {
		gifs.put(gif, gif.getDelayForCurrentFrame() * 10);
	}

	public void remGif(PGif gif) {
		gifs.remove(gif);
	}


	public Set<PGif> getGifs() {
		return Collections.unmodifiableSet(gifs.keySet());
	}

}
