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


/**
 * Handles the processing for Gifs
 */
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


	/**
	 * Enables the PGifManager
	 */
	@Override
	public void enable() {

		if (isEnabled) return;

		timer.get().start();

		isEnabled = true;
	}

	/**
	 * Disables the PGifManager
	 */
	@Override
	public void disable() {

		if (!isEnabled) return;

		timer.get().stop();

		isEnabled = false;
	}


	/**
	 * Checks if the manager is enabled
	 *
	 * @return If the manager is enabled
	 */
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}


	/**
	 * Adds a Gif to the manager
	 *
	 * @param gif The gif to add
	 */
	public void addGif(PGif gif) {
		gifs.put(gif, gif.getDelayForCurrentFrame() * 10);
	}

	/**
	 * Removes a Gif from the manager
	 *
	 * @param gif The gif to remove
	 */
	public void remGif(PGif gif) {
		gifs.remove(gif);
	}

	/**
	 * Removes a Gif from the manager
	 *
	 * @param gif The gif to remove
	 */
	public void remGif(Gif gif) {
		gifs.entrySet().removeIf(it -> it.getKey().getGif().equals(gif));
	}


	/**
	 * Checks if the manager contains a gif
	 *
	 * @param gif The gif to look for
	 *
	 * @return If the gif exists in the manager
	 */
	public boolean contains(PGif gif) {
		return gifs.containsKey(gif);
	}

	/**
	 * Checks if the manager contains a gif
	 *
	 * @param gif The gif to look for
	 *
	 * @return If the gif exists in the manager
	 */
	public boolean contains(Gif gif) {
		return gifs.keySet().stream().anyMatch(it -> it.getGif().equals(gif));
	}

	/**
	 * Clears the manager of all gifs
	 */
	public void clear() {
		gifs.clear();
	}


	/**
	 * Gets all the gifs in the manager
	 * @return
	 */
	public Set<PGif> getGifs() {
		return Collections.unmodifiableSet(gifs.keySet());
	}

}
