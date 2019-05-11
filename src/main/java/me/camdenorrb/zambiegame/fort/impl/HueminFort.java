package me.camdenorrb.zambiegame.fort.impl;

import me.camdenorrb.zambiegame.ZambieGame;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.fort.struct.FortStruct;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.utils.ResourceUtils;

import java.util.Collections;
import java.util.List;


public class HueminFort extends FortStruct {

	private Element.Image body = new Element.Image(pos, ResourceUtils.get("fort/huemin-castle.png"));


	public HueminFort(ZambieGame game) {
		super(game);
	}


	@Override
	public String getName() {
		return "HueminFort";
	}


	@Override
	public Pos getCenter() {
		return isSpawned() ? body.getCenter() : null;
	}


	@Override
	protected void onSpawn(Pos pos) {
		body.getPosition().setXY(pos.getX(), pos.getY());
		super.onSpawn(pos);
	}

	@Override
	protected void onTeleport(Pos toPos) {
		if (!isSpawned()) return;
		body.getPosition().setXY(toPos.getX(), toPos.getY());
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
	public List<Element> getParts() {
		return Collections.singletonList(body);
	}

}