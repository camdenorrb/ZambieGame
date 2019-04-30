package me.camdenorrb.zambiegame.type;

import me.camdenorrb.zambiegame.engine.gui.impl.element.Element;

import java.util.List;


/**
 * A type that holds pieces
 */
public interface Pieced {

	/**
	 * Gets the elements
	 *
	 * @return The pieces
	 */
	List<Element> getParts();

}
