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

	private static final String LEFT_WALK_PATH = "resources/huemin/left-walk.gif";

	private static final String RIGHT_WALK_PATH = "resources/huemin/right-walk.gif";

	private static final String FORWARD_WALK_PATH = "resources/huemin/front-walk.gif";

	private static final String BACKWARDS_WALK_PATH = "resources/huemin/back-walk.gif";



	private final ZambieGame game;

	private Element.Image body;


	public Huemin(ZambieGame game, Pos<Double> pos) {
		super(pos);
		this.game = game;
		//final InputStream inputStream = getClass().getResource("resources/robotcat.jpeg").openStream();
		this.body = new Element.Image(pos, new File(RIGHT_WALK_PATH));
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

		final MutablePos<Double> pos = body.getPosition();
		pos.setX(pos.getX() + 10);
		//pos.setX(pos.getX() + ((int) (Math.random() * 20 - 10)));
		//pos.setY(pos.getY() + ((int) (Math.random() * 20 - 10)));

		final Dimension size = game.getGui().getSize();

		if (pos.getX() >= size.width) {
			pos.setX(0.0);
		}

		if (pos.getY() >= size.height) {
			pos.setY(0.0);
		}

		/*
		if (pos.getX() < 0) {
			pos.setX((float) size.width);
		}

		if (pos.getY() < 0) {
			pos.setY((float) size.height);
		}
		 */
	}

}
