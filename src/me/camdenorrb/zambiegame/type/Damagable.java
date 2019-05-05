package me.camdenorrb.zambiegame.type;

public interface Damagable extends Killable {

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


	default void damage(int amount) {
		setHealth(getHealth() - amount);
	}

	default boolean isDead() {
		return getHealth() <= 0;
	}

}