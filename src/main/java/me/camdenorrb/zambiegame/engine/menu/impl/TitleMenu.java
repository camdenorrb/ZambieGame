package me.camdenorrb.zambiegame.engine.menu.impl;

import me.camdenorrb.zambiegame.TitleScreen;
import me.camdenorrb.zambiegame.ZambieGame;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.engine.menu.struct.MenuStruct;
import me.camdenorrb.zambiegame.impl.Size;
import me.camdenorrb.zambiegame.impl.pos.Pos;

import java.awt.*;


/**
 *
 */
public class TitleMenu extends MenuStruct {

	private final TitleScreen titleScreen;

	/**
	 * Constructs a Title Menu
	 *
	 * @param titleScreen The title screen
	 */
	public TitleMenu(TitleScreen titleScreen) {
		super(titleScreen.getGui());
		this.titleScreen = titleScreen;

	}

	/**
	 * Handles the building of the Menu
	 */
	@Override
	public void onBuild() {

		final double halfGuiWidth = ZambieGame.CANVAS_WIDTH / 2.0;
		final double halfGuiHeight = ZambieGame.CANVAS_HEIGHT / 2.0;

		final Pos centerPos = new Pos(halfGuiWidth, halfGuiHeight);

		addPart(
			new Element.Rectangle(Color.CYAN.darker(), new Pos(centerPos.getX() - (halfGuiWidth / 2), centerPos.getY() - (halfGuiHeight / 2)), new Size(halfGuiWidth, halfGuiHeight))
		);

		addPart(
			new Element.Text("Zambie Game", 20, new Pos(centerPos.getX() - 67.5, centerPos.getY() - 100))
		);

		addPart(
			new Element.Button(
				new Element.Rectangle(
					Color.GREEN.darker(),
					new Pos(centerPos.getX() - 50, centerPos.getY() - 25),
					new Size(100, 50)
				),
				titleScreen::stop
			)
		);

		addPart(
			new Element.Text(
				"Start",
				20,
				new Pos(centerPos.getX() - 22.5, centerPos.getY() + 5)
			)
		);

		addPart(
			new Element.Button(
				new Element.Rectangle(
					Color.RED.darker(),
					new Pos(centerPos.getX() - 50, centerPos.getY() - 25 + 70), new Size(100, 50)
				),
				() -> System.exit(0)
			)
		);

		addPart(
			new Element.Text(
				"Exit",
				20,
				new Pos(centerPos.getX() - 18.5, centerPos.getY() + 75)
			)
		);
	}

}