package me.camdenorrb.zambiegame.engine.gui.impl.element.impl;

import me.camdenorrb.zambiegame.engine.gif.Gif;
import me.camdenorrb.zambiegame.engine.gui.impl.element.base.ElementBase;
import me.camdenorrb.zambiegame.impl.pos.MutablePos;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.struct.lazy.LazyStruct;
import me.camdenorrb.zambiegame.utils.LazyUtils;
import me.camdenorrb.zambiegame.utils.TryUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.UUID;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.util.Objects.requireNonNull;
import static me.camdenorrb.zambiegame.utils.JavaUtils.apply;


/**
 * The element structure for all elements placed on the GUI
 */
public abstract class Element implements ElementBase {

	private Element() {}


	private final UUID uuid = UUID.randomUUID();


	public abstract Pos getCenter();

	public abstract Dimension getSize();


	public UUID getUUID() {
		return uuid;
	}

	public abstract MutablePos getPosition();


	/**
	 * The Text Element
	 */
	public final static class Text extends Element {

		private String data;


		private final Dimension size;

		private final MutablePos position;


		public Text(String data, Dimension size, Pos position) {
			this.data = data;
			this.size = size;
			this.position = position.toMutable();
		}


		@Override
		public String getName() {
			return "Text";
		}

		@Override
		public boolean isInRange(Pos pos) {
			return abs(position.getX() - pos.getX()) >= size.width && abs(position.getY() - pos.getY()) >= size.height;
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
		public MutablePos getPosition() {
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

		@Override
		public Pos getCenter() {
			// TODO
			return null;
		}
	}

	/**
	 * The Line Element
	 */
	public final static class Line extends Element {

		private Color color;

		private final MutablePos start, end;


		public Line(Color color, Pos start, Pos end) {
			this.start = start.toMutable();
			this.end = end.toMutable();
			this.color = color;
		}


		@Override
		public String getName() {
			return "Line";
		}


		@Override
		public boolean isInRange(Pos pos) {

			final double distX1 = start.getX() - pos.getX();
			final double distX2 = end.getX() - start.getX();

			final double distY1 = start.getY() - pos.getY();
			final double distY2 = end.getY() - start.getY();

			return distX1 * distY2 - distY1 * distX2 == 0;
		}

		/**
		 * Gets position 'A' for the line
		 *
		 * @return Position A
		 */
		public MutablePos getStart() {
			return start;
		}

		/**
		 * Gets position 'B' for the line
		 *
		 * @return Position B
		 */
		public MutablePos getEnd() {
			return end;
		}


		private double getWidth() {
			return end.getX() - start.getX();
		}

		private double getHeight() {
			return end.getY() - start.getY();
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


		@Override
		public MutablePos getPosition() {
			return getStart();
		}


		@Override
		public Dimension getSize() {
			return new Dimension((int) getWidth(), (int) getHeight());
		}

		@Override
		public Pos getCenter() {
			return new Pos(start.getX() + (getWidth() / 2), start.getY() + (getHeight() / 2));
		}
	}


	/**
	 * The Oval Element
	 */
	public final static class Oval extends Element {


		private Color color;


		private final Dimension size;

		private final MutablePos position;


		public Oval(Pos position, Dimension size) {
			this.size = size;
			this.position = position.toMutable();
		}


		@Override
		public String getName() {
			return "Oval";
		}

		@Override
		public boolean isInRange(Pos pos) {
			// Can just be distance from center
			return false;
		}

		/**
		 * Gets the position of the oval
		 *
		 * @return The position of the oval
		 */
		public MutablePos getPosition() {
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

		@Override
		public Pos getCenter() {
			// TODO
			return null;
		}
	}


	/**
	 * The Rectangle Element
	 */
	public final static class Rectangle extends Element {

		private Color color;


		private final Dimension size;

		private final MutablePos position;


		// TODO: Take in primitives as a constructor
		public Rectangle(Color color, Pos position, Dimension size) {
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
		public MutablePos getPosition() {
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


		public Pos getTopLeft() {
			return position;
		}

		public Pos getTopRight() {
			return new Pos(position.getX() + size.width, position.getY());
		}

		public Pos getBottomLeft() {
			return new Pos(position.getX(), position.getY() + size.height);
		}

		public Pos getBottomRight() {
			return new Pos(position.getX() + size.getWidth(), position.getY() + size.getHeight());
		}

		public Pos[] getCorners() {
			return new Pos[] { getTopLeft(), getTopRight(), getBottomLeft(), getBottomRight() };
		}


		@Override
		public boolean isInRange(Pos pos) {
			// TODO
			return false;
		}

		@Override
		public Pos getCenter() {
			// TODO
			return null;
		}
	}


	/**
	 * The Triangle Element
	 */
	// TODO: Make filled and outline
	public final static class Triangle extends Element {

		private Color color;


		private final MutablePos left, middle, right;


		public Triangle(MutablePos left, MutablePos middle, MutablePos right) {
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
		public MutablePos getLeft() {
			return left;
		}

		/**
		 * Gets the middle point of the triangle
		 *
		 * @return The middle point
		 */
		public MutablePos getMiddle() {
			return middle;
		}

		/**
		 * Gets the right most point of the triangle
		 *
		 * @return The right most point
		 */
		public MutablePos getRight() {
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

		@Override
		public boolean isInRange(Pos pos) {
			// TODO
			return false;
		}

		@Override
		public Pos getCenter() {
			// TODO
			return null;
		}

		@Override
		public MutablePos getPosition() {
			return getLeft();
		}

		@Override
		public Dimension getSize() {
			return new Dimension((int) (right.getX() - left.getX()), (int) (max(left.getY(), right.getY()) - middle.getY()));
		}
	}


	/**
	 * The Image Element
	 */
	public final static class Image extends Element {

		private boolean isResized;

		private InputStream imageStream;


		private final Dimension size;

		private final MutablePos position;


		private final LazyStruct<BufferedImage> image = LazyUtils.lazy(() ->
			TryUtils.attemptOrBreak(() -> ImageIO.read(imageStream))
		);


		public Image(Pos position, InputStream imageStream) {

			this.imageStream = imageStream;
			this.position = position.toMutable();

			this.size = new Dimension(image.get().getWidth(), image.get().getHeight());
		}

		public Image(Pos position, Dimension size, InputStream imageStream) {
			this.size = size;
			this.imageStream = imageStream;
			this.position = position.toMutable();
		}


		@Override
		public String getName() {
			return "Image";
		}

		@Override
		public boolean isInRange(Pos pos) {
			// TODO
			return true;
		}

		public boolean isInPixelRange(Pos pos) {
			// TODO
			return true;
		}

		@Override
		public Pos getCenter() {

			final double halfWidth = size.getWidth() / 2;
			final double halfHeight = size.getHeight() / 2;

			return new Pos(position.getX() + halfWidth, position.getY() + halfHeight);
		}

	/*	*//**
		 * Gets the file for the image
		 *
		 * @return The file for the image
		 *//*
		public File getFile() {
			return imageFile;
		}*/

		public BufferedImage getImage() {
			return image.get();
		}

		public InputStream getImageStream() {
			return imageStream;
		}

		/**
		 * Gets the position of the image
		 *
		 * @return The position of the image
		 */
		public MutablePos getPosition() {
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

		public boolean isResized() {
			return isResized;
		}

		public void setIsResized(boolean isResized) {
			this.isResized = isResized;
		}

	}

	/**
	 * The Gif Element
	 */
	public final static class GifElem extends Element {

		private boolean isResized;


		private final Gif gif;

		private final Dimension size;

		private final MutablePos position;

		private final Gif.Frame firstFrame;


		public GifElem(Pos position, InputStream imageStream) {
			this(position, apply(new Gif(), it -> it.read(imageStream)));
		}

		public GifElem(Pos position, Gif gif) {
			this(position, new Dimension(gif.getFrames().get(0).getImage().getWidth(), gif.getFrames().get(0).getImage().getHeight()), gif);
		}

		public GifElem(Pos position, Dimension size, Gif gif) {
			this.gif = gif;
			this.size = size;
			this.position = position.toMutable();
			this.firstFrame = requireNonNull(gif.getFrames().get(0));
		}


		@Override
		public String getName() {
			return "Image";
		}

		@Override
		public boolean isInRange(Pos pos) {
			// TODO
			return true;
		}

		public boolean isInPixelRange(Pos pos) {
			// TODO
			return true;
		}


		@Override
		public Pos getCenter() {

			final double halfWidth = firstFrame.getImage().getWidth() / 2;
			final double halfHeight = firstFrame.getImage().getHeight() / 2;

			return new Pos(position.getX() + halfWidth, position.getY() + halfHeight);
		}


		/**
		 * Gets the file for the image
		 *
		 * @return The file for the image
		 *//*
		public File getFile() {
			return imageFile;
		}*/


		public Gif getGif() {
			return gif;
		}

		/**
		 * Gets the position of the image
		 *
		 * @return The position of the image
		 */
		public MutablePos getPosition() {
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


		public boolean isResized() {
			return isResized;
		}

		public void setIsResized(boolean isResized) {
			this.isResized = isResized;
		}

	}

}