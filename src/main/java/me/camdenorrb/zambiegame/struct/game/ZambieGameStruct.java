package me.camdenorrb.zambiegame.struct.game;

import me.camdenorrb.zambiegame.engine.game.struct.GameStruct;
import me.camdenorrb.zambiegame.engine.gui.impl.ProcGui;
import me.camdenorrb.zambiegame.entity.base.EntityBase;

import java.util.HashSet;
import java.util.Set;


public abstract class ZambieGameStruct extends GameStruct {

	protected final Set<EntityBase> entities = new HashSet<>();


	public ZambieGameStruct(long tps) {
		super(tps);
	}


	public abstract ProcGui getGui();


	public Set<EntityBase> getEntities() {
		return entities;
	}

}
