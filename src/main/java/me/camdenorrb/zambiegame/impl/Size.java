package me.camdenorrb.zambiegame.impl;

/**
 * Represents a Size
 */
public class Size {

	private final double width, height;


	/**
	 * Constructs a Size
	 *
	 * @param width The width
	 * @param height The height
	 */
	public Size(double width, double height) {
		this.width = width;
		this.height = height;
	}


	/**
	 * Gets the width
	 *
	 * @return The width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Gets the height
	 *
	 * @return The height
	 */
	public double getHeight() {
		return height;
	}

}
