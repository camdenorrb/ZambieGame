package me.camdenorrb.zambiegame.entity.impl;

import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.entity.struct.EntityStruct;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.struct.game.ZambieGameStruct;
import me.camdenorrb.zambiegame.utils.ResourceUtils;

import java.awt.*;
import java.util.Collections;
import java.util.List;


public class Cloud extends EntityStruct {

	public static final double WIDTH = 128;
	public static final double HEIGHT = 128;

	protected static final String CLOUD1_PATH = "cloud/cloud-1.png";

	protected Element.Image body = new Element.Image(pos, ResourceUtils.get(CLOUD1_PATH));

	public Cloud(ZambieGameStruct game, double xVel) {
		super(Integer.MAX_VALUE, game);
		velocity.setX(xVel);
	}

	@Override
	public String getName() {
		return "Cloud";
	}

	@Override
	public List<Element> getParts() {
		return Collections.singletonList(body);
	}

	@Override
	public Pos getCenter() {
		return body.getCenter();
	}

	@Override
	protected void onTick() {

		moveBy(velocity.getX(), 0);

		final Dimension guiSize = game.getGui().getSize();

		if (pos.getX() < 0) {
			teleport(guiSize.width, pos.getY());
		}
		else if (pos.getX() >= guiSize.width) {
			teleport(0, pos.getY());
		}

		if (pos.getY() < 0) {
			teleport(pos.getX(), guiSize.getHeight());
		}
		else if (pos.getY() >= guiSize.height) {
			teleport(pos.getX(), 0);
		}

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
	public boolean isInRange(Pos pos) {
		return false;
	}

	@Override
	public void moveBy(double x, double y) {
		super.moveBy(x, y);
		body.getPosition().add(x, y);
	}

	@Override
	protected void onTeleport(double x, double y) {
		super.onTeleport(x, y);
		body.getPosition().setXY(x, y);
	}
}
