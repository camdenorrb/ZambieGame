package me.camdenorrb.zambiegame.engine.gui.impl.element.base;

import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.type.Named;
import me.camdenorrb.zambiegame.type.Ranged;


public interface ElementBase extends Named, Ranged {

	Pos getCenter();


	// TODO

	//double getWidth();

	//double getHeight();

}
