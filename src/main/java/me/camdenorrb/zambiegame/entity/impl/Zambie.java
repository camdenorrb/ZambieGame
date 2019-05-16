package me.camdenorrb.zambiegame.entity.impl;

import me.camdenorrb.zambiegame.engine.menu.impl.GameOverMenu;
import me.camdenorrb.zambiegame.ZambieGame;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.engine.menu.struct.MenuStruct;
import me.camdenorrb.zambiegame.engine.physics.impl.Distance;
import me.camdenorrb.zambiegame.entity.struct.EntityStruct;
import me.camdenorrb.zambiegame.impl.Size;
import me.camdenorrb.zambiegame.impl.pos.MutablePos;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.utils.ResourceUtils;

import java.util.Collections;
import java.util.List;

import static me.camdenorrb.zambiegame.utils.JavaUtils.apply;


/**
 * A Zambie entity that acts as the enemy in the game.
 */
public class Zambie extends EntityStruct {

	public static final int HITBOX_WIDTH = 15;

	public static final int HITBOX_HEIGHT = 23;


	private static final String LEFT_WALK_GIF = "zambie/left-walk.gif";

	private static final String RIGHT_WALK_GIF = "zambie/right-walk.gif";

	private static final String FORWARD_WALK_GIF = "zambie/front-walk.gif";

	private static final String BACKWARDS_WALK_GIF = "zambie/back-walk.gif";


	private Element body;

	private boolean isMoving = true;


	private final MutablePos hitboxCenterPos = new MutablePos(0, 0);


	/**
	 * Construct a Zambie instance
	 *
	 * @param game The game to spawn the Zambie in
	 */
	public Zambie(ZambieGame game) {
		super(game);
		//final InputStream inputStream = getClass().getResource("resources/robotcat.jpeg").openStream();
		this.body = new Element.GifElem(pos, ResourceUtils.get(LEFT_WALK_GIF));
		//this.body = new Element.Rectangle(Color.BLACK, pos, new Dimension(10, 10));
	}

	// Init
	{
		addCollideHandler(Huemin.class, huemin -> isMoving = false);
		//addCollideHandler(HueminFort.class, fort -> {}/*TODO Game over*/);
	}


	/**
	 * Gets the name of the Entity
	 *
	 * @return The name of the Entity
	 */
	@Override
	public String getName() {
		return "Zambie";
	}

	/**
	 * Gets the center of the Entity
	 *
	 * @return The center of the Entity
	 */
	@Override
	public Pos getCenter() {
		return body.getCenter();
	}


	/**
	 * Gets the width of the Entity
	 *
	 * @return The width of the Entity
	 */
	@Override
	public double getWidth() {
		return body.getSize().getWidth();
	}

	/**
	 * Gets the height of the Entity
	 *
	 * @return The height of the Entity
	 */
	@Override
	public double getHeight() {
		return body.getSize().getHeight();
	}


	/**
	 * Gets the parts of the Entity
	 *
	 * @return The parts of the Entity
	 */
	@Override
	public List<Element> getParts() {
		return Collections.singletonList(body);
	}


	/**
	 * Handles the ticking of an Entity
	 */
	@Override
	public void onTick() {

		final MutablePos bodyPos = body.getPosition();

		if (!isMoving) return;

		moveBy(-.75, 0);

		//pos.setX(pos.getX() + ((int) (Math.random() * 20 - 10)));
		//pos.setY(pos.getY() + ((int) (Math.random() * 20 - 10)));

		final Size size = game.getGui().getSize();

		if (pos.getX() <= 0) {

			if (game instanceof ZambieGame) {
				((ZambieGame) game).getSong().pause();
				apply(new GameOverMenu((ZambieGame) game), MenuStruct::build).show();
				game.getTimer().stop();
			}
			else {
				bodyPos.setX(size.getWidth());
				pos.setX(size.getWidth());
			}
			// TODO: Play game over screen
			//bodyPos.setX(size.getWidth());
			//pos.setX(size.getWidth());
		}

		if (bodyPos.getY() >= size.getHeight()) {
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

	/**
	 * Checks if the Entity is in range
	 *
	 * @param pos The position to check
	 *
	 * @return If the position is in range
	 */
	@Override
	public boolean isInRange(Pos pos) {
		final Distance distance = hitboxCenterPos.distAbsTo(pos);
		return distance.getX() <= (HITBOX_WIDTH / 2.0) && distance.getY() <= (HITBOX_HEIGHT / 2.0);//<= Math.max(body.getSize().width, body.getSize().height);
	}


	/**
	 * Handles the spawning of an Entity
	 *
	 * @param pos The position to spawn at
	 */
	@Override
	protected void onSpawn(Pos pos) {

		hitboxCenterPos.setX(pos.getX() + (getWidth() / 2));
		hitboxCenterPos.setY(pos.getY() + (getHeight() / 2));

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

	/**
	 * Moves the Entity by a certain amount
	 *
	 * @param x The x distance to move by
	 * @param y The y distance to move by
	 */
	@Override
	public void moveBy(double x, double y) {

		super.moveBy(x, y);

		hitboxCenterPos.add(x, y);
		body.getPosition().add(x, y);
	}
}
