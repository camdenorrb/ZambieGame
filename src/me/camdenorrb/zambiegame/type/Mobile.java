package me.camdenorrb.zambiegame.type;

import me.camdenorrb.zambiegame.engine.physics.impl.velocity.Velocity;


public interface Mobile {

	Velocity getVelocity();

	void setVelocity(Velocity velocity);

}
