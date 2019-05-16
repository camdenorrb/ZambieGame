package me.camdenorrb.zambiegame.type;

/**
 * Represents a Damagable element
 */
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

	/**
	 * Initiates damage
	 *
	 * @param amount The amount of damage
	 */
	default void damage(int amount) {
		setHealth(getHealth() - amount);
	}

	/**
	 * Checks if this is dead
	 *
	 * @return If this is dead
	 */
	default boolean isDead() {
		return getHealth() <= 0;
	}

}