package me.camdenorrb.zambiegame.entity.impl;


import me.camdenorrb.zambiegame.engine.game.impl.GameTimer;
import me.camdenorrb.zambiegame.engine.gif.Gif;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Layer;
import me.camdenorrb.zambiegame.engine.physics.impl.Distance;
import me.camdenorrb.zambiegame.entity.struct.EntityStruct;
import me.camdenorrb.zambiegame.impl.pos.MutablePos;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.struct.game.ZambieGameStruct;
import me.camdenorrb.zambiegame.struct.lazy.LazyStruct;
import me.camdenorrb.zambiegame.utils.ResourceUtils;

import java.awt.*;
import java.util.Collections;
import java.util.List;

import static me.camdenorrb.zambiegame.utils.GifUtils.lazyLoad;


/**
 * A Huemin entity that acts as the good guy in the game.
 */
public class Huemin extends EntityStruct {

	public static final int HITBOX_WIDTH = 13;

	public static final int HITBOX_HEIGHT = 23;


	protected static final String LEFT_WALK_PATH = "huemin/left-walk.gif";

	protected static final String RIGHT_WALK_PATH = "huemin/right-walk.gif";

	protected static final String UP_WALK_PATH = "huemin/up-walk.gif";

	protected static final String DOWN_WALK_PATH = "huemin/down-walk.gif";


	protected static final String ATTACK_SWORD_PATH = "huemin/attack-sword.gif";


	protected Element.GifElem body;


	protected volatile boolean isCollided;

	protected volatile boolean isMoving = true;


	private final MutablePos hitboxCenterPos = new MutablePos(0, 0);

	protected final LazyStruct<Gif> upWalkGif = lazyLoad(ResourceUtils.get(UP_WALK_PATH));
	protected final LazyStruct<Gif> downWalkGif = lazyLoad(ResourceUtils.get(DOWN_WALK_PATH));
	protected final LazyStruct<Gif> leftWalkGif = lazyLoad(ResourceUtils.get(LEFT_WALK_PATH));
	protected final LazyStruct<Gif> rightWalkGif = lazyLoad(ResourceUtils.get(RIGHT_WALK_PATH));

	private final LazyStruct<Gif> attackSwordGif = lazyLoad(ResourceUtils.get(ATTACK_SWORD_PATH));


	public Huemin(ZambieGameStruct game) {
		super(game);
		//final InputStream inputStream = getClass().getResource("resources/robotcat.jpeg").openStream();
		this.body = new Element.GifElem(pos, rightWalkGif.get());
		//this.body = new Element.Rectangle(Color.BLACK, pos, new Dimension(10, 10));
	}


	{
		addCollideHandler(Zambie.class, zambie -> {

			if (isCollided) return;

			isMoving = false;
			isCollided = true;

			//zambie.kill();
			changeBody(attackSwordGif.get());

			new GameTimer(440, (timer) -> {

				if (zambie.isDead()) {
					timer.stop();
					isMoving = true;
					isCollided = false;
					return;
				}

				System.out.println("Here");
				zambie.damage(5);

			}).start();
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

		moveBy(1, 0);

		//pos.setX(pos.getX() + ((int) (Math.random() * 20 - 10)));
		//pos.setY(pos.getY() + ((int) (Math.random() * 20 - 10)));

		final Dimension guiSize = game.getGui().getSize();

		if (bodyPos.getX() < 0) {
			teleport(guiSize.width, bodyPos.getY());
		}
		else if (bodyPos.getX() >= guiSize.width) {
			teleport(0, bodyPos.getY());
		}

		if (bodyPos.getY() < 0) {
			teleport(bodyPos.getX(), guiSize.getHeight());
		}
		else if (bodyPos.getY() >= guiSize.height) {
			teleport(bodyPos.getX(), 0);
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
		final Distance distance = hitboxCenterPos.distTo(pos);
		return distance.getX() <= (HITBOX_WIDTH / 2.0) && distance.getY() <= (HITBOX_HEIGHT / 2.0);//<= Math.max(body.getSize().width, body.getSize().height);
	}


	private void changeBody(Gif newBodyGif) {

		final Element.GifElem newBody = new Element.GifElem(body.getPosition(), newBodyGif);

		/*final MutablePos bodyPos = newBody.getPosition();

		final Dimension size = newBody.getSize();
		final Dimension lastSize = body.getSize();

		bodyPos.add(lastSize.width - size.width, lastSize.height - size.height);*/
		if (body != null) game.getGui().remElements(Layer.ENTITY, body);
		body = newBody;
		game.getGui().addElements(Layer.ENTITY, newBody);
	}

	@Override
	protected void onTeleport(double x, double y) {

		hitboxCenterPos.setXY(x, y);
		body.getPosition().setXY(x, y);

		super.onTeleport(x, y);
	}

	@Override
	public void moveBy(double x, double y) {

		super.moveBy(x, y);

		hitboxCenterPos.add(x, y);
		body.getPosition().add(x, y);


		if (x < 0) {
			if (!body.getGif().equals(leftWalkGif.get())) {
				changeBody(leftWalkGif.get());
			}
		}
		else if (x > 0) {
			if (!body.getGif().equals(rightWalkGif.get())) {
				changeBody(rightWalkGif.get());
			}
		}
		else if (y < 0) {
			if ( !body.getGif().equals(downWalkGif.get())) {
				changeBody(upWalkGif.get());
			}
		}
		else if (y > 0) {
			if (!body.getGif().equals(upWalkGif.get())) {
				changeBody(downWalkGif.get());
			}
		}
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
