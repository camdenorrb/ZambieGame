package me.camdenorrb.zambiegame.engine.physics.struct;

import me.camdenorrb.zambiegame.engine.physics.base.PhysicsEngineBase;


/**
 * A strict structure for physic engines
 */
public abstract class PhysicsEngineStruct implements PhysicsEngineBase {

	/**
	 * The frames per second of
	 */
	private int fps;

	/**
	 * The gravity in meters per second
	 */
	private double gravity;

	/**
	 * The amount of pixels to equal one meter
	 */
	private int pixelsPerMeter;


	/**
	 * Constructs a Physics Engine instance
	 *
	 * @param fps The frames per second
	 * @param gravity The magnitude of gravity
	 * @param pixelsPerMeter The pixels per meter ratio
	 */
	public PhysicsEngineStruct(int fps, double gravity, int pixelsPerMeter) {
		this.fps = fps;
		this.gravity = gravity;
		this.pixelsPerMeter = pixelsPerMeter;
	}


	/**
	 * Gets the FPS for the physics engine
	 *
	 * @return The engine's FPS
	 */
	public int getFps() {
		return fps;
	}

	/**
	 * Gets the value for gravity in (meters/second)
	 *
	 * @return The acceleration of gravity
	 */
	public double getGravity() {
		return gravity;
	}

	/**
	 * Gets the pixel to meter conversion used in the backend
	 *
	 * @return The conversion ratio
	 */
	public int getPixelsPerMeter() {
		return pixelsPerMeter;
	}

	/**
	 * Sets the FPS for the physics engine
	 *
	 * @param fps The new FPS for the engine
	 */
	public void setFps(int fps) {
		this.fps = fps;
	}

	/**
	 * Sets the gravity for the physics engine (meters/second)
	 *
	 * @param gravity The new gravity for the engine
	 */
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	/**
	 * Sets the pixels per meter ratio
	 *
	 * @param pixelsPerMeter The new ratio
	 */
	public void setPixelsPerMeter(int pixelsPerMeter) {
		this.pixelsPerMeter = pixelsPerMeter;
	}

}
