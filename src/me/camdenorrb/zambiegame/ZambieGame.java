package me.camdenorrb.zambiegame;

import me.camdenorrb.zambiegame.engine.game.struct.GameStruct;
import me.camdenorrb.zambiegame.engine.gui.impl.ProcGui;
import me.camdenorrb.zambiegame.entity.base.EntityBase;
import me.camdenorrb.zambiegame.entity.impl.Huemin;
import me.camdenorrb.zambiegame.impl.pos.Pos;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;


public class ZambieGame extends GameStruct {

	private final Set<EntityBase> entities = new HashSet<>();

	private final ProcGui gui = new ProcGui("ZambieGame", new Dimension(1000, 500));


	public ZambieGame() {
		super(40);
	}


	@Override
	public String getName() {
		return "ZambieGame";
	}


	@Override
	protected void onStart() {
		gui.show();

		spawnEntity(new Huemin(this, new Pos<>(0f, 0f)));

		/*
		for (float i = 0; i <= 100; i += 10) {
			spawnEntity(new Huemin(this, new Pos<>(i, i)));
		}

		for (float i = 100; i <= 200; i += 10) {
			spawnEntity(new Huemin(this, new Pos<>(i - 100, 300 - i)));
		}

		for (float i = 0; i < 200; i += 10) {
			spawnEntity(new Huemin(this, new Pos<>(i - 100, 100f)));
		}*/

	}

	@Override
	protected void onStop() {
		gui.hide();
	}

	@Override
	protected void onTick() {
		entities.forEach(EntityBase::tick);
	}


	public ProcGui getGui() {
		return gui;
	}


	public void spawnEntity(EntityBase entity) {
		entities.add(entity);
		gui.addElements(entity.getParts());
	}

}
