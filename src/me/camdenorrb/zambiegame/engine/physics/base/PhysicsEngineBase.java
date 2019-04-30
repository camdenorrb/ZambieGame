package me.camdenorrb.zambiegame.engine.physics.base;

import me.camdenorrb.zambiegame.entity.base.EntityBase;


/**
 * The basics for a physics engine
 */
public interface PhysicsEngineBase {

	/**
	 * Handles the ticking of an entity
	 *
	 * @param entity The entity to tick
	 */
	void tickEntity(EntityBase entity);

}
