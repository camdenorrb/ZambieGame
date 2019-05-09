package me.camdenorrb.zambiegame;

import me.camdenorrb.zambiegame.engine.game.struct.GameStruct;
import me.camdenorrb.zambiegame.engine.gui.impl.ProcGui;
import me.camdenorrb.zambiegame.engine.gui.impl.element.Element;
import me.camdenorrb.zambiegame.entity.base.EntityBase;
import me.camdenorrb.zambiegame.entity.impl.Huemin;
import me.camdenorrb.zambiegame.entity.impl.Zambie;
import me.camdenorrb.zambiegame.fort.base.FortBase;
import me.camdenorrb.zambiegame.fort.impl.HueminFort;
import me.camdenorrb.zambiegame.impl.pos.Pos;

import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * A zambie game implementation
 */
public class ZambieGame extends GameStruct {

	private final Set<EntityBase> entities = new HashSet<>();

	private final ProcGui gui = new ProcGui("ZambieGame", 60, new Dimension(1500, 750));


	public ZambieGame() {
		super(20);
	}


	@Override
	public String getName() {
		return "ZambieGame";
	}


	@Override
	protected void onStart() {

		//spawnEntity(new Huemin(this, new Pos<>(0f, 0f)));

		final Dimension size = gui.getSize();

		/*for (int x = 0; x < size.width; x += 80) {
			for (int y = 0; y < size.height; y += 80) {
				spawnEntity(new Huemin(this, new Pos<>((float) x, (float) y)));
			}
		}*/

		//gui.addElements(new Element.Rectangle(new Color(155, 118, 83), new Pos<>(0f, (float) size.height - 125), new Dimension(size.width, 125)));
		//gui.addElements(new Element.Text("Hello how are you?", new Dimension(1000, 100), new Pos<>(0f, 0f)));
		gui.addElements(new Element.Rectangle(new Color(63, 122, 77), new Pos<>(0.0, size.height - 250.0), new Dimension(size.width, 250)));


		// On the ground
		double startingY = 500.0;

		for (int i = 0; i <= 200; i += 10) {
			spawnEntity(new Huemin(this, new Pos<>(10.0, i + startingY)));
		}

		for (int i = 0; i <= 200; i += 10) {
			spawnEntity(new Zambie(this, new Pos<>(size.width - 10.0, i + startingY)));
		}

		//int startingY = 500;

		// start - Huemin arrow (Right)


		// TOP
		for (double i = 0; i <= 100; i += 10) {
			spawnEntity(new Huemin(this, new Pos<>(i + 100, i + startingY)));
		}

		// MIDDLE
		for (double i = 0; i < 200; i += 10) {
			spawnEntity(new Huemin(this, new Pos<>(i, 100.0 + startingY)));
		}

		// BOTTOM
		for (double i = 100; i <= 200; i += 10) {
			spawnEntity(new Huemin(this, new Pos<>(i, (300 - i) + startingY)));
		}
		// end

		for (double i = 100; i > 0; i -= 10) {
			spawnEntity(new Zambie(this, new Pos<>(size.width - i - 100 - 16, i + startingY)));
		}

		for (double i = 200; i > 0; i -= 10) {
			spawnEntity(new Zambie(this, new Pos<>(size.width - i - 16, 100.0 + startingY)));
		}

		for (double i = 100; i <= 200; i += 10) {
			spawnEntity(new Zambie(this, new Pos<>(size.width - i - 16, (300 - i) + startingY)));
		}



		spawnFort(new HueminFort(new Pos<>(-200.0, 0.0)));
		gui.show();
	}


	@Override
	protected void onStop() {
		gui.hide();
	}

	@Override
	protected void onTick() {
		entities.forEach(EntityBase::tick);
	}


	/**
	 * Gets the GUI
	 *
	 * @return The GUI
	 */
	public ProcGui getGui() {
		return gui;
	}


	public Set<EntityBase> getEntities() {
		return Collections.unmodifiableSet(entities);
	}

	public void spawnFort(FortBase fort) {
		// TODO: Set fort in list, possibly make foreground/background stuff in Fort class
		gui.addElements(fort.getParts());
	}

	/**
	 * Spawns an entity
	 *
	 * @param entity The entity to spawn
	 */
	public void spawnEntity(EntityBase entity) {
		entities.add(entity);
		gui.addElements(entity.getParts());
	}

}
