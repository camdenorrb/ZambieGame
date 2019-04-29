package me.camdenorrb.zambiegame.engine.physics.struct;

import me.camdenorrb.zambiegame.engine.physics.base.PhysicsEngineBase;


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
	private int pixelPerMeter;


	public PhysicsEngineStruct(int fps, double gravity, int pixelPerMeter) {
		this.fps = fps;
		this.gravity = gravity;
		this.pixelPerMeter = pixelPerMeter;
	}


	public int getFps() {
		return fps;
	}

	public double getGravity() {
		return gravity;
	}

	public int getPixelPerMeter() {
		return pixelPerMeter;
	}


	public void setFps(int fps) {
		this.fps = fps;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public void setPixelPerMeter(int pixelPerMeter) {
		this.pixelPerMeter = pixelPerMeter;
	}

}
