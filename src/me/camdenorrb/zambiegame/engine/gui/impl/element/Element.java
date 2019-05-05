package me.camdenorrb.zambiegame.engine.gui.impl.element;

import me.camdenorrb.zambiegame.impl.pos.MutablePos;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.type.Named;

import java.awt.*;
import java.io.File;


/**
 * The element structure for all elements placed on the GUI
 */
public abstract class Element implements Named {

	private Element() {}


	/**
	 * The Text Element
	 */
	public final static class Text extends Element {

		private String data;

		private MutablePos<Float> position;


		public Text(String data, Pos<Float> position) {
			this.data = data;
			this.position = position.toMutable();
		}


		@Override
		public String getName() {
			return "Text";
		}


		/**
		 * Gets the text data
		 *
		 * @return The text data
		 */
		public String getData() {
			return data;
		}

		/**
		 * Gets the position of the text
		 *
		 * @return The position of the text
		 */
		public MutablePos<Float> getPosition() {
			return position;
		}


		/**
		 * Sets the data for the text
		 *
		 * @param data The new data
		 */
		public void setData(String data) {
			this.data = data;
		}

	}

	/**
	 * The Line Element
	 */
	public final static class Line extends Element {

		private Color color;

		private final MutablePos<Float> a, b;


		public Line(Color color, Pos<Float> a, Pos<Float> b) {
			this.a = a.toMutable();
			this.b = b.toMutable();
			this.color = color;
		}


		@Override
		public String getName() {
			return "Line";
		}


		/**
		 * Gets position 'A' for the line
		 *
		 * @return Position A
		 */
		public Pos<Float> getA() {
			return a;
		}

		/**
		 * Gets position 'B' for the line
		 *
		 * @return Position B
		 */
		public Pos<Float> getB() {
			return b;
		}


		/**
		 * Gets the color for the line
		 *
		 * @return The color of the line
		 */
		public Color getColor() {
			return color;
		}

		/**
		 * Sets the color of the line
		 *
		 * @param color The new color
		 */
		public void setColor(Color color) {
			this.color = color;
		}

	}


	/**
	 * The Oval Element
	 */
	public final static class Oval extends Element {


		private Color color;


		private final Dimension dimension;

		private final MutablePos<Float> position;


		public Oval(Pos<Float> position, Dimension dimension) {
			this.position = position.toMutable();
			this.dimension = dimension;
		}


		@Override
		public String getName() {
			return "Oval";
		}


		/**
		 * Gets the position of the oval
		 *
		 * @return The position of the oval
		 */
		public MutablePos<Float> getPosition() {
			return position;
		}

		/**
		 * Gets the dimensions of the oval
		 *
		 * @return The dimensions of the oval
		 */
		public Dimension getDimension() {
			return dimension;
		}


		/**
		 * Gets the color for the oval
		 *
		 * @return The color of the oval
		 */
		public Color getColor() {
			return color;
		}

		/**
		 * Sets the color of the oval
		 *
		 * @param color The new color
		 */
		public void setColor(Color color) {
			this.color = color;
		}

	}


	/**
	 * The Rectangle Element
	 */
	public final static class Rectangle extends Element {

		private Color color;


		private final Dimension dimension;

		private final MutablePos<Float> position;


		public Rectangle(Color color, Pos<Float> position, Dimension dimension) {
			this.color = color;
			this.dimension = dimension;
			this.position = position.toMutable();
		}


		@Override
		public String getName() {
			return "Rectangle";
		}


		/**
		 * Gets the position of the rectangle
		 *
		 * @return The position of the rectangle
		 */
		public MutablePos<Float> getPosition() {
			return position;
		}

		/**
		 * Gets the dimensions of the rectangle
		 *
		 * @return The dimensions of the rectangle
		 */
		public Dimension getDimension() {
			return dimension;
		}


		/**
		 * Gets the color for the rectangle
		 *
		 * @return The color of the rectangle
		 */
		public Color getColor() {
			return color;
		}

		/**
		 * Sets the color of the rectangle
		 *
		 * @param color The new color
		 */
		public void setColor(Color color) {
			this.color = color;
		}

	}


	/**
	 * The Triangle Element
	 */
	public final static class Triangle extends Element {

		private Color color;


		private final MutablePos<Float> left, middle, right;


		public Triangle(MutablePos<Float> left, MutablePos<Float> middle, MutablePos<Float> right) {
			this.left = left;
			this.middle = middle;
			this.right = right;
		}


		@Override
		public String getName() {
			return "Triangle";
		}


		/**
		 * Gets the left most point of the triangle
		 *
		 * @return The left most point
		 */
		public MutablePos<Float> getLeft() {
			return left;
		}

		/**
		 * Gets the middle point of the triangle
		 *
		 * @return The middle point
		 */
		public MutablePos<Float> getMiddle() {
			return middle;
		}

		/**
		 * Gets the right most point of the triangle
		 *
		 * @return The right most point
		 */
		public MutablePos<Float> getRight() {
			return right;
		}


		/**
		 * Gets the color for the triangle
		 *
		 * @return The color of the triangle
		 */
		public Color getColor() {
			return color;
		}

		/**
		 * Sets the color of the triangle
		 *
		 * @param color The new color
		 */
		public void setColor(Color color) {
			this.color = color;
		}

	}


	/**
	 * The Image Element
	 */
	public final static class Image extends Element {

		private String path;


		private final Dimension dimension;

		private final MutablePos<Float> position;


		public Image(Pos<Float> position, File file) {
			this(position, null, file);
		}

		public Image(Pos<Float> position, Dimension dimension, File file) {
			this.path = file.getPath();
			this.dimension = dimension;
			this.position = position.toMutable();
		}


		@Override
		public String getName() {
			return "Image";
		}


		/**
		 * Gets the path for the image
		 *
		 * @return The path of the image
		 */
		public String getPath() {
			return path;
		}

		/**
		 * Gets the position of the image
		 *
		 * @return The position of the image
		 */
		public MutablePos<Float> getPosition() {
			return position;
		}

		/**
		 * Gets the dimensions of the image
		 *
		 * @return The dimension of the image
		 */
		public Dimension getDimension() {
			return dimension;
		}

	}

}