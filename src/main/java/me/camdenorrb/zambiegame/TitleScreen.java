package me.camdenorrb.zambiegame;


import me.camdenorrb.zambiegame.engine.game.impl.GameTimer;
import me.camdenorrb.zambiegame.engine.gui.impl.ProcGui;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Layer;
import me.camdenorrb.zambiegame.engine.menu.impl.TitleMenu;
import me.camdenorrb.zambiegame.engine.menu.struct.MenuStruct;
import me.camdenorrb.zambiegame.engine.music.Song;
import me.camdenorrb.zambiegame.entity.base.EntityBase;
import me.camdenorrb.zambiegame.entity.impl.Huemin;
import me.camdenorrb.zambiegame.fort.impl.HueminFort;
import me.camdenorrb.zambiegame.fort.impl.ZambieFort;
import me.camdenorrb.zambiegame.impl.Size;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.struct.game.ZambieGameStruct;
import me.camdenorrb.zambiegame.struct.lazy.LazyStruct;
import me.camdenorrb.zambiegame.utils.DisplayUtils;
import me.camdenorrb.zambiegame.utils.ResourceUtils;

import java.awt.*;

import static me.camdenorrb.zambiegame.utils.JavaUtils.apply;
import static me.camdenorrb.zambiegame.utils.LazyUtils.lazy;


/**
 * A title screen implementation
 */
public class TitleScreen extends ZambieGameStruct {

	public static final int TPS = 120;

	public static final int CANVAS_WIDTH = 1500;
	public static final int CANVAS_HEIGHT = 750;

	private final ProcGui gui = new ProcGui("ZambieGame", DisplayUtils.getRefreshRate(), new Size(CANVAS_WIDTH, CANVAS_HEIGHT));
	//private final OpenGLGui gui = new OpenGLGui("ZambieGame", new Dimension(1500, 750), false);

	private final LazyStruct<Song> song = lazy(() ->
		new Song("Title Background", ResourceUtils.get("music/wav/title-background.wav")
	));


	/**
	 * Constructs a TitleScreen
	 */
	public TitleScreen() {
		super(TPS);
	}


	/**
	 * Gets the name of the screen
	 *
	 * @return The name of the screen
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

		gui.addElements(Layer.BACKGROUND, new Element.Rectangle(new Color(63, 122, 77), new Pos(0.0, size.getHeight() - 250.0), new Size(size.getWidth(), 250)));

		final int spacing = (int) (size.getWidth() / 15);

		for (int x = 0; x < size.getWidth(); x += spacing) {
			for (int y = 0; y < size.getHeight(); y += spacing) {
				new Huemin(this).spawn(new Pos(x, y));
			}
		}

		final HueminFort hueminFort = new HueminFort(this);
		final ZambieFort zambieFort = new ZambieFort(this);

		hueminFort.spawn(new Pos(-200.0, 0.0));
		zambieFort.spawn(new Pos(gui.getSize().getWidth() - (zambieFort.getWidth() - 200.0), 0.0));

		gui.show();

		song.get().play(true);

		apply(new TitleMenu(this), MenuStruct::build).show();
	}

	/**
	 * Handles stop
	 */
	@Override
	protected void onStop() {
		//gui.hide();
		song.get().close();

		gui.clear();
		entities.clear();

		//System.gc();

		new ZambieGame(gui).start();
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
	 * Gets the GUI for the TitleScreen
	 *
	 * @return The GUI for the TitleScreen
	 */
	@Override
	public ProcGui getGui() {
		return gui;
	}

}