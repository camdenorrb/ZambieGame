package me.camdenorrb.zambiegame;

import me.camdenorrb.zambiegame.engine.game.impl.GameTimer;
import me.camdenorrb.zambiegame.engine.gui.impl.ProcGui;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Layer;
import me.camdenorrb.zambiegame.engine.gui.struct.GuiStruct;
import me.camdenorrb.zambiegame.engine.menu.impl.PauseMenu;
import me.camdenorrb.zambiegame.engine.menu.struct.MenuStruct;
import me.camdenorrb.zambiegame.engine.music.Song;
import me.camdenorrb.zambiegame.entity.base.EntityBase;
import me.camdenorrb.zambiegame.entity.impl.Cloud;
import me.camdenorrb.zambiegame.entity.impl.Hero;
import me.camdenorrb.zambiegame.entity.impl.Zambie;
import me.camdenorrb.zambiegame.fort.impl.HueminFort;
import me.camdenorrb.zambiegame.fort.impl.ZambieFort;
import me.camdenorrb.zambiegame.impl.Size;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.listener.base.KeyListenerBase;
import me.camdenorrb.zambiegame.struct.game.ZambieGameStruct;
import me.camdenorrb.zambiegame.struct.lazy.LazyStruct;
import me.camdenorrb.zambiegame.utils.DisplayUtils;
import me.camdenorrb.zambiegame.utils.ResourceUtils;

import java.awt.*;
import java.util.Deque;

import static me.camdenorrb.zambiegame.utils.JavaUtils.apply;
import static me.camdenorrb.zambiegame.utils.LazyUtils.lazy;


/**
 * A zambie game implementation
 */
public class ZambieGame extends ZambieGameStruct {

	public static final int TPS = 120;

	public static final int CANVAS_WIDTH = 1500;
	public static final int CANVAS_HEIGHT = 750;

	public static final double FLOOR_HEIGHT = 250;

	public static final double MIN_ZAMBIE_SPAWN_RATE = 200;


	private boolean isPaused;

	private GameTimer zambieTimer;


	private final ProcGui gui;

	private final LazyStruct<Song> song = lazy(() -> new Song("Game Background", () -> ResourceUtils.get("music/wav/game-background.wav")));

	//private final OpenGLGui gui = new OpenGLGui("ZambieGame", new Dimension(1500, 750), false);


	/**
	 * Constructs a ZambieGame
	 */
	public ZambieGame() {
		this(new ProcGui("ZambieGame", DisplayUtils.getRefreshRate(), new Size(CANVAS_WIDTH, CANVAS_HEIGHT)));
	}

	/**
	 * Constructs a ZambieGame
	 *
	 * @param gui The GUI to utilize
	 */
	public ZambieGame(ProcGui gui) {
		super(TPS);
		this.gui = gui;
	}

	/**
	 * Gets the name of the Game
	 *
	 * @return The name of the Game
	 */
	@Override
	public String getName() {
		return "ZambieGame";
	}


	/**
	 * Handles start
	 */
	@Override
	protected void onStart() {

		//spawnEntity(new Huemin(this, new Pos(0f, 0f)));

		final Size size = gui.getSize();


		/*
		for (int x = 0; x < size.width; x += 15) {
			for (int y = 0; y < size.height; y += 15) {
				new Huemin(this).spawn(new Pos(x, y));
			}
		}*/

		//gui.addElements(new Element.Rectangle(new Color(155, 118, 83), new Pos(0f, (float) size.height - 125), new Dimension(size.width, 125)));
		//gui.addElements(new Element.Text("Hello how are you?", new Dimension(1000, 100), new Pos(0f, 0f)));
		new Cloud(this, .1).spawn(new Pos(0, 0));
		new Cloud(this, -.1).spawn(new Pos(gui.getSize().getWidth() - Cloud.WIDTH, gui.getSize().getHeight()));

		gui.addElements(
			Layer.BACKGROUND,
			new Element.Rectangle(new Color(63, 122, 77), new Pos(0.0, size.getHeight() - FLOOR_HEIGHT), new Size(size.getWidth(), 250))
		);


		// On the ground
		//double startingY = 500.0;





		/*
		for (int i = 0; i <= 200; i += 10) {
			new Huemin(this).spawn(new Pos(10.0, i + startingY));
		}*/

	/*	for (int i = 0; i <= 200; i += 10) {
			new Zambie(this).spawn(new Pos(size.getWidth() - 10.0, i + startingY));
		}*/


		//int startingY = 500;

		// start - Huemin arrow (Right)

		final HueminFort hueminFort = new HueminFort(this);
		final ZambieFort zambieFort = new ZambieFort(this);



		/*
		// TOP
		for (double i = 0; i <= 100; i += 10) {
			new Huemin(this).spawn(new Pos(i + 100, i + startingY));
		}

		// MIDDLE
		for (double i = 0; i < 200; i += 10) {
			new Huemin(this).spawn(new Pos(i, 100.0 + startingY));
		}

		// BOTTOM
		for (double i = 100; i <= 200; i += 10) {
			new Huemin(this).spawn(new Pos(i, (300 - i) + startingY));
		}
		// end

		for (double i = 100; i > 0; i -= 10) {
			new Zambie(this).spawn(new Pos(size.width - i - 100 - 16, i + startingY));
		}

		for (double i = 200; i > 0; i -= 10) {
			new Zambie(this).spawn(new Pos(size.width - i - 16, 100.0 + startingY));
		}

		for (double i = 100; i <= 200; i += 10) {
			new Zambie(this).spawn(new Pos(size.width - i - 16, (300 - i) + startingY));
		}*/


		hueminFort.spawn(new Pos(-200.0, 0.0));
		zambieFort.spawn(new Pos(gui.getSize().getWidth() - (zambieFort.getWidth() - 200.0), 0.0));

		new Hero(this).spawn(new Pos(gui.getSize().getWidth() / 3, gui.getSize().getHeight() - FLOOR_HEIGHT));
		//new Huemin(this).spawn(hueminFort.getEntitySpawnPos());
		//new Zambie(this).spawn(zambieFort.getEntitySpawnPos());


		final ZambieGame gameThisRef = this;

		zambieTimer = new GameTimer(1000, gameTimer -> {

			final Pos spawnPoint = zambieFort.getEntitySpawnPos();

			final double spawnY = Math.random() * ((spawnPoint.getY() - 10) - (CANVAS_HEIGHT - FLOOR_HEIGHT)) + (CANVAS_HEIGHT - FLOOR_HEIGHT);

			new Zambie(gameThisRef).spawn(new Pos(spawnPoint.getX(), spawnY));

			if (gameTimer.getTempo() > MIN_ZAMBIE_SPAWN_RATE) {
				gameTimer.setTempo(gameTimer.getTempo() - (int) (((gameTimer.getTempo() - MIN_ZAMBIE_SPAWN_RATE) / gameTimer.getTempo()) * 5));
			}

		});

		gui.addKeyListener(new KeyListenerBase() {
			@Override
			public void onKeyPress(GuiStruct gui, int keyCode) {
				if (keyCode != 80 || isPaused) return;
				pause();
			}
		});

		zambieTimer.start();

		//gui.show();

		song.get().play();
	}


	/**
	 * Handles stop
	 */
	@Override
	protected void onStop() {

		entities.clear();

		gui.clear();
		timer.stop();
		zambieTimer.stop();

		song.get().stop();
	}


	/**
	 * Handles ticking
	 *
	 * @param timer The timer doing the ticking
	 */
	@Override
	protected void onTick(GameTimer timer) {
		entities.forEach(EntityBase::tick);
	}


	/**
	 * Gets the GUI
	 *
	 * @return The GUI
	 */
	@Override
	public ProcGui getGui() {
		return gui;
	}


	/**
	 * Gets the song for the Game
	 *
	 * @return The song for the Game
	 */
	public Song getSong() {
		return song.get();
	}

	/**
	 * Gets the Entities in the Game
	 *
	 * @return The Entities in the Game
	 */
	public Deque<EntityBase> getEntities() {
		return entities;
	}

	/**
	 * Pauses the Game
	 */
	public void pause() {

		if (isPaused) return;

		timer.stop();
		zambieTimer.stop();
		song.get().pause();

		apply(new PauseMenu(this), MenuStruct::build).show();

		isPaused = true;
	}

	/**
	 * Resumes the Game
	 */
	public void resume() {

		if (!isPaused) return;

		timer.start();
		zambieTimer.start();
		song.get().resume();

		isPaused = false;
	}


	/**
	 * Checks if the game is paused
	 *
	 * @return If the game is paused
	 */
	public boolean isPaused() {
		return isPaused;
	}

	/*public void spawnFort(FortBase fort) {
		// TODO: Set fort in list, possibly make foreground/background stuff in Fort class
		//gui.addElements(fort.getParts());
	}*/


	/*
	  Spawns an entity

	  @param entity The entity to spawn
	 */
	/*
	public void spawnEntity(EntityBase entity) {

		if (entity.isSpawned()) return;

		entity.spawn();
		entities.add(entity);
		gui.addElements(entity.getParts());
	}*/

}
