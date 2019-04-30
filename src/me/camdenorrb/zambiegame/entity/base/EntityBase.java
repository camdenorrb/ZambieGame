package me.camdenorrb.zambiegame.entity.base;

import me.camdenorrb.zambiegame.type.*;


/**
 * The basics for an Entity
 */
public interface EntityBase extends Named, Mobile, Posed, Pieced, Tickable, Killable, Spawnable, Teleportable {

	/**
	 * Gets the health of the entity
	 *
	 * @return The health of the entity
	 */
	int getHealth();

	/**
	 * Sets the entity's health
	 *
	 * @param health The new health of the entity
	 */
	void setHealth(int health);

}