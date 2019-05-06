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


		private final Dimension size;

		private final MutablePos<Double> position;


		public Text(String data, Dimension size, Pos<Double> position) {
			this.data = data;
			this.size = size;
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
		 * Gets the size of the text
		 *
		 * @return The size of the text
		 */
		public Dimension getSize() {
			return size;
		}

		/**
		 * Gets the position of the text
		 *
		 * @return The position of the text
		 */
		public MutablePos<Double> getPosition() {
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

		private final MutablePos<Double> a, b;


		public Line(Color color, Pos<Double> a, Pos<Double> b) {
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
		public Pos<Double> getA() {
			return a;
		}

		/**
		 * Gets position 'B' for the line
		 *
		 * @return Position B
		 */
		public Pos<Double> getB() {
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


		private final Dimension size;

		private final MutablePos<Double> position;


		public Oval(Pos<Double> position, Dimension size) {
			this.size = size;
			this.position = position.toMutable();
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
		public MutablePos<Double> getPosition() {
			return position;
		}

		/**
		 * Gets the dimensions of the oval
		 *
		 * @return The dimensions of the oval
		 */
		public Dimension getSize() {
			return size;
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


		private final Dimension size;

		private final MutablePos<Double> position;


		public Rectangle(Color color, Pos<Double> position, Dimension size) {
			this.size = size;
			this.color = color;
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
		public MutablePos<Double> getPosition() {
			return position;
		}

		/**
		 * Gets the dimensions of the rectangle
		 *
		 * @return The dimensions of the rectangle
		 */
		public Dimension getSize() {
			return size;
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


		private final MutablePos<Double> left, middle, right;


		public Triangle(MutablePos<Double> left, MutablePos<Double> middle, MutablePos<Double> right) {
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
		public MutablePos<Double> getLeft() {
			return left;
		}

		/**
		 * Gets the middle point of the triangle
		 *
		 * @return The middle point
		 */
		public MutablePos<Double> getMiddle() {
			return middle;
		}

		/**
		 * Gets the right most point of the triangle
		 *
		 * @return The right most point
		 */
		public MutablePos<Double> getRight() {
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


		private final Dimension size;

		private final MutablePos<Double> position;


		public Image(Pos<Double> position, File file) {
			this(position, null, file);
		}

		public Image(Pos<Double> position, Dimension size, File file) {
			this.size = size;
			this.path = file.getPath();
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
		public MutablePos<Double> getPosition() {
			return position;
		}

		/**
		 * Gets the dimensions of the image
		 *
		 * @return The size of the image
		 */
		public Dimension getSize() {
			return size;
		}

	}

}