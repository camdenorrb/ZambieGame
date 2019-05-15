package me.camdenorrb.zambiegame.engine.manager;

import me.camdenorrb.zambiegame.base.ModuleBase;
import me.camdenorrb.zambiegame.engine.game.impl.GameTimer;
import me.camdenorrb.zambiegame.engine.gif.Gif;
import me.camdenorrb.zambiegame.engine.gif.processing.PGif;
import me.camdenorrb.zambiegame.struct.lazy.LazyStruct;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static me.camdenorrb.zambiegame.utils.LazyUtils.lazy;


public class PGifManager implements ModuleBase {

	private boolean isEnabled;

	private ConcurrentHashMap<PGif, Integer> gifs = new ConcurrentHashMap<>();


	private final Consumer<GameTimer> loopTask = (timer) -> gifs.forEach((gif, wait) -> {

		if (!gif.shouldPlay()) {
			return;
		}

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

	public void remGif(Gif gif) {
		gifs.entrySet().removeIf(it -> it.getKey().getGif().equals(gif));
	}


	public boolean contains(PGif gif) {
		return gifs.containsKey(gif);
	}

	public boolean contains(Gif gif) {
		return gifs.keySet().stream().anyMatch(it -> it.getGif().equals(gif));
	}

	public void clear() {
		gifs.clear();
	}


	public Set<PGif> getGifs() {
		return Collections.unmodifiableSet(gifs.keySet());
	}

}
