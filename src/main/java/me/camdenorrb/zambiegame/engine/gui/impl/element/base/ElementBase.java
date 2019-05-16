package me.camdenorrb.zambiegame.engine.gui.impl.element.base;

import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.type.Named;
import me.camdenorrb.zambiegame.type.Ranged;


/**
 * The basics for every element
 */
public interface ElementBase extends Named, Ranged {

	/**
	 * Gets the center for the element
	 *
	 * @return The center of the element
	 */
	Pos getCenter();


	// TODO

	//double getWidth();

	//double getHeight();

}
