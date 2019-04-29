package me.camdenorrb.zambiegame.type;

import me.camdenorrb.zambiegame.engine.gui.impl.element.Element;

import java.util.List;


public interface Spawnable {

	boolean isSpawned();

	void spawn();


	List<Element> getParts();

}
