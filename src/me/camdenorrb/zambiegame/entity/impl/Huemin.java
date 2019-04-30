package me.camdenorrb.zambiegame.entity.impl;


import me.camdenorrb.zambiegame.ZambieGame;
import me.camdenorrb.zambiegame.engine.gui.impl.element.Element;
import me.camdenorrb.zambiegame.entity.struct.EntityStruct;
import me.camdenorrb.zambiegame.impl.pos.MutablePos;
import me.camdenorrb.zambiegame.impl.pos.Pos;

import java.awt.*;
import java.io.File;
import java.util.Collections;
import java.util.List;


/**
 * A Huemin entity that acts as the good guy in the game.
 */
public class Huemin extends EntityStruct {

	private static final String LEFT_IMAGE_PATH = "/resources/human/left";

	private static final String RIGHT_IMAGE_PATH = "/resources/human/right";

	private static final String HUMAN_FRONT_PATH = "/resources/human-front";

	private static final String HUMAN_BACK_PATH = "/resources/human-back";



	private final ZambieGame game;

	private Element.Image body;


	public Huemin(ZambieGame game, Pos<Float> pos) {
		super(pos);
		this.game = game;
		//final InputStream inputStream = getClass().getResource("resources/robotcat.jpeg").openStream();
		this.body = new Element.Image(pos, new Dimension(10, 10), new File("/home/camdenorrb/Documents/Programming/Intellij/Games/ZambieGame/src/resources/giphy.gif"));
		//this.body = new Element.Rectangle(Color.BLACK, pos, new Dimension(10, 10));
	}

	@Override
	public String getName() {
		return "Huemin";
	}


	@Override
	protected void onSpawn() {
		game.spawnEntity(this);
	}

	@Override
	public void onKill() {
		game.getGui().remElements(getParts());
	}

	@Override
	public List<Element> getParts() {
		return Collections.singletonList(body);
	}

	@Override
	public void teleport(Pos pos) {

	}

	@Override
	public void tick() {

		final MutablePos<Float> pos = body.getPosition();
		pos.setX(pos.getX() + 10);

		if (pos.getX() >= game.getGui().getSize().width) {
			pos.setX(0f);
		}

	}

}
