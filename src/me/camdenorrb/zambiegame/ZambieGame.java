package me.camdenorrb.zambiegame;

import me.camdenorrb.zambiegame.engine.game.struct.GameStruct;
import me.camdenorrb.zambiegame.engine.gui.impl.ProcGui;
import me.camdenorrb.zambiegame.engine.gui.impl.element.Element;
import me.camdenorrb.zambiegame.entity.base.EntityBase;
import me.camdenorrb.zambiegame.entity.impl.Huemin;
import me.camdenorrb.zambiegame.impl.pos.Pos;

import java.awt.*;
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
		gui.show();

		//spawnEntity(new Huemin(this, new Pos<>(0f, 0f)));

		final Dimension size = gui.getSize();

		/*for (int x = 0; x < size.width; x += 80) {
			for (int y = 0; y < size.height; y += 80) {
				spawnEntity(new Huemin(this, new Pos<>((float) x, (float) y)));
			}
		}*/

		//gui.addElements(new Element.Rectangle(new Color(155, 118, 83), new Pos<>(0f, (float) size.height - 125), new Dimension(size.width, 125)));
		gui.addElements(new Element.Text("Hello", new Pos<>(0f, 0f)));
		gui.addElements(new Element.Rectangle(new Color(63, 122, 77), new Pos<>(0f, (float) size.height - 250), new Dimension(size.width, 250)));

		int startingY = 500;

		for (float i = 0; i <= 100; i += 10) {
			spawnEntity(new Huemin(this, new Pos<>(i, i + startingY)));
		}

		for (float i = 100; i <= 200; i += 10) {
			spawnEntity(new Huemin(this, new Pos<>(i - 100, (300 - i) + startingY)));
		}

		for (float i = 0; i < 200; i += 10) {
			spawnEntity(new Huemin(this, new Pos<>(i - 100, 100f + startingY)));
		}

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
