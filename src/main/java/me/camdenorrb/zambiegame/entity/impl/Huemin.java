package me.camdenorrb.zambiegame.entity.impl;


import me.camdenorrb.zambiegame.ZambieGame;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.entity.struct.EntityStruct;
import me.camdenorrb.zambiegame.impl.Pair;
import me.camdenorrb.zambiegame.impl.pos.MutablePos;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.utils.ResourceUtils;

import java.awt.*;
import java.util.Collections;
import java.util.List;


/**
 * A Huemin entity that acts as the good guy in the game.
 */
public class Huemin extends EntityStruct {

	public static final int HITBOX_WIDTH = 13;

	public static final int HITBOX_HEIGHT = 23;


	private static final String LEFT_WALK_PATH = "huemin/left-walk.gif";

	private static final String RIGHT_WALK_PATH = "huemin/right-walk.gif";

	private static final String FORWARD_WALK_PATH = "huemin/front-walk.gif";

	private static final String BACKWARDS_WALK_PATH = "huemin/back-walk.gif";


	private Element.GifElem body;



	private boolean isCollided;

	private boolean isMoving = true;


	private final MutablePos hitboxCenterPos = new MutablePos(0, 0);



	public Huemin(ZambieGame game) {
		super(game);
		//final InputStream inputStream = getClass().getResource("resources/robotcat.jpeg").openStream();
		this.body = new Element.GifElem(pos, ResourceUtils.get(RIGHT_WALK_PATH));
		//this.body = new Element.Rectangle(Color.BLACK, pos, new Dimension(10, 10));
	}


	{
		addCollideHandler(Zambie.class, zambie -> {

			if (isCollided) return;

			isMoving = false;
			isCollided = true;

			kill();
			this.body = new Element.GifElem(body.getPosition(), ResourceUtils.get("huemin/attack-sword.gif"));
			spawn(pos);
		});
	}


	@Override
	public String getName() {
		return "Huemin";
	}

	@Override
	public Pos getCenter() {
		return body.getCenter();
	}


	@Override
	protected void onSpawn(Pos pos) {

		hitboxCenterPos.setX(pos.getX() + (getWidth() / 2));
		hitboxCenterPos.setY(pos.getY() + (getHeight() / 2));

		body.getPosition().setXY(pos.getX(), pos.getY());

		super.onSpawn(pos);
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
	public double getWidth() {
		return body.getSize().width;
	}

	@Override
	public double getHeight() {
		return body.getSize().height;
	}


	@Override
	public void onTick() {

		final MutablePos bodyPos = body.getPosition();

		if (!isMoving) return;

		pos.add(1, 0);
		bodyPos.add(1, 0);
		hitboxCenterPos.add(1, 0);
		//pos.setX(pos.getX() + ((int) (Math.random() * 20 - 10)));
		//pos.setY(pos.getY() + ((int) (Math.random() * 20 - 10)));

		final Dimension size = game.getGui().getSize();

		if (bodyPos.getX() >= size.width) {
			bodyPos.setX(0.0);
			pos.setX(0.0);

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
		final Pair<Double, Double> distance = hitboxCenterPos.distTo(pos);
		return distance.getValue1() <= (HITBOX_WIDTH / 2) && distance.getValue2() <= (HITBOX_HEIGHT / 2);//<= Math.max(body.getSize().width, body.getSize().height);
	}

		/*
		final Dimension size = body.getSize();

		final Double x1 = this.pos.getX();
		final Double x2 = pos.getX();

		final Double y1 = this.pos.getY();
		final Double y2 = pos.getY();

		return Math.abs(x1 - x2) <= size.width && Math.abs(y1 - y2) <= size.height;
	}*/

}
