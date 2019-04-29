package me.camdenorrb.zambiegame.entity.base;

import me.camdenorrb.zambiegame.type.*;


public interface EntityBase extends Named, Mobile, Posed, Tickable, Killable, Spawnable, Teleportable {

	int getHealth();

	void setHealth(int health);

}