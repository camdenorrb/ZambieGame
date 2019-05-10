package me.camdenorrb.zambiegame.entity.impl;

import me.camdenorrb.zambiegame.ZambieGame;
import me.camdenorrb.zambiegame.engine.gui.impl.element.Element;
import me.camdenorrb.zambiegame.entity.struct.EntityStruct;
import me.camdenorrb.zambiegame.impl.Pair;
import me.camdenorrb.zambiegame.impl.pos.MutablePos;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.utils.ResourceUtils;

import java.awt.*;
import java.util.Collections;
import java.util.List;


/**
 * A Zambie entity that acts as the enemy in the game.
 */
public class Zambie extends EntityStruct {

	private static final String LEFT_WALK_GIF = "zambie/left-walk.gif";

	private static final String RIGHT_WALK_GIF = "zambie/right-walk.gif";

	private static final String FORWARD_WALK_GIF = "zambie/front-walk.gif";

	private static final String BACKWARDS_WALK_GIF = "zambie/back-walk.gif";


	private Element.Image body;

	private boolean isMoving = true;


	public Zambie(ZambieGame game) {
		super(game);
		//final InputStream inputStream = getClass().getResource("resources/robotcat.jpeg").openStream();
		this.body = new Element.Image(pos, ResourceUtils.get(LEFT_WALK_GIF));
		//this.body = new Element.Rectangle(Color.BLACK, pos, new Dimension(10, 10));
	}

	{
		addCollideHandler(Huemin.class, huemin -> isMoving = false);
	}


	@Override
	public String getName() {
		return "Zambie";
	}

	@Override
	public Pos getCenter() {
		return body.getCenter();
	}


	@Override
	public double getWidth() {
		return body.getSize().getWidth();
	}

	@Override
	public double getHeight() {
		return body.getSize().getHeight();
	}


	@Override
	public List<Element> getParts() {
		return Collections.singletonList(body);
	}


	@Override
	public void onTick() {

		final MutablePos bodyPos = body.getPosition();

		if (!isMoving) return;

		bodyPos.setX(bodyPos.getX() - 1);
		pos.setX(bodyPos.getX() - 1);

		//pos.setX(pos.getX() + ((int) (Math.random() * 20 - 10)));
		//pos.setY(pos.getY() + ((int) (Math.random() * 20 - 10)));

		final Dimension size = game.getGui().getSize();

		if (pos.getX() <= 0) {
			bodyPos.setX(size.getWidth());
			pos.setX(size.getWidth());
		}

		if (bodyPos.getY() >= size.height) {
			bodyPos.setY(0.0);
			pos.setX(0.0);
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

	@Override
	public boolean isInRange(Pos pos) {
		final Pair<Double, Double> distance = body.getCenter().distTo(pos);
		return distance.getValue1() <= body.getSize().width && distance.getValue2() == 0;//<= Math.max(body.getSize().width, body.getSize().height);
	}

	@Override
	protected void onSpawn(Pos pos) {
		body.getPosition().setXY(pos.getX(), pos.getY());
		super.onSpawn(pos);
	}

	/*
	@Override
	public boolean isInRange(Pos pos) {

		final Dimension size = body.getSize();

		final Double x1 = this.pos.getX();
		final Double x2 = pos.getX();

		final Double y1 = this.pos.getY();
		final Double y2 = pos.getY();

		return Math.abs(x1 - x2) <= size.width && Math.abs(y1 - y2) <= size.height;
	}*/

}
