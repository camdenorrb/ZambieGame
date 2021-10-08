package me.camdenorrb.zambiegame.engine.gui.impl.element.impl;

import me.camdenorrb.zambiegame.engine.gif.Gif;
import me.camdenorrb.zambiegame.engine.gui.impl.element.base.ElementBase;
import me.camdenorrb.zambiegame.engine.physics.impl.Distance;
import me.camdenorrb.zambiegame.event.base.ClickEventHandlerBase;
import me.camdenorrb.zambiegame.impl.Size;
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

import static java.lang.Math.max;
import static java.util.Objects.requireNonNull;
import static me.camdenorrb.zambiegame.utils.JavaUtils.apply;


/**
 * The element structure for all elements placed on the GUI
 */
public abstract class Element implements ElementBase {

	private final UUID uuid = UUID.randomUUID();


	/**
	 * Constructs an element instance
	 */
	private Element() {}


	/**
	 * Gets the center of the element
	 *
	 * @return The center of the element
	 */
	public abstract Pos getCenter();

	/**
	 * Gets the size of the element
	 *
	 * @return The size of the element
	 */
	public abstract Size getSize();


	/**
	 * Gets the UUID (Universally Unique Identifier) of the element
	 *
	 * @return The UUID of the element
	 */
	public UUID getUUID() {
		return uuid;
	}

	/**
	 * Gets the position of the element
	 *
	 * @return The position of the element
	 */
	public abstract MutablePos getPosition();


	/**
	 * Converts the element to a human readable string
	 *
	 * @return The human readable string
	 */
	@Override
	public String toString() {
		return getName();
	}

	/**
	 * The Text Element
	 */
	public final static class Text extends Element {

		private String data;


		private final float fontSize;

		private final MutablePos position;


		/**
		 * Constructs a text instance
		 *
		 * @param data The data for the text
		 * @param position The position of the text
		 */
		public Text(String data, Pos position) {
			this(data, 14, position);
		}

		/**
		 * Constructs a text instance
		 *
		 * @param data The data for the text
		 * @param fontSize The font size of the text
		 * @param position The position of the text
		 */
		public Text(String data, float fontSize, Pos position) {
			this.data = data;
			this.fontSize = fontSize;
			this.position = position.toMutable();
		}


		/**
		 * Gets the name of the element
		 *
		 * @return The name of the element
		 */
		@Override
		public String getName() {
			return "Text";
		}

		/**
		 * Checks if a position is in range of this element
		 *
		 * @param pos The position to check
		 *
		 * @return If it is in range of the element
		 */
		@Override
		public boolean isInRange(Pos pos) {

			/*
			final Distance distance = pos.distTo(position);

			final double distX = distance.getX();
			final double distY = distance.getY();

			return distX > 0 && distX <= .getWidth()
				&& distY > 0 && distY <= size.getHeight();
				*/
			return false;
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
		public Size getSize() {
			return null;
		}

		/**
		 * Gets the font size of the text
		 *
		 * @return The font size of the text
		 */
		public float getFontSize() {
			return fontSize;
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

		/**
		 * Gets the center of the text
		 *
		 * @return The center of the text
		 */
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


		/**
		 * Constructs a Line instance
		 *
		 * @param color The color of the line
		 * @param start The start of the line
		 * @param end The end of the line
		 */
		public Line(Color color, Pos start, Pos end) {
			this.start = start.toMutable();
			this.end = end.toMutable();
			this.color = color;
		}

		/**
		 * Gets the name of the element
		 *
		 * @return The name of the element
		 */
		@Override
		public String getName() {
			return "Line";
		}


		/**
		 * Checks if a position is in range of this element
		 *
		 * @param pos The position to check
		 *
		 * @return If it is in range of the element
		 */
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


		/**
		 * Gets the width of the element
		 *
		 * @return The width of the element
		 */
		private double getWidth() {
			return end.getX() - start.getX();
		}

		/**
		 * Gets the height of the element
		 *
		 * @return The height of the element
		 */
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


		/**
		 * Gets the position of the element
		 *
		 * @return The position of the element
		 */
		@Override
		public MutablePos getPosition() {
			return getStart();
		}


		/**
		 * Gets the size of the element
		 *
		 * @return The size of the element
		 */
		@Override
		public Size getSize() {
			return new Size((int) getWidth(), (int) getHeight());
		}

		/**
		 * Gets the center of the element
		 *
		 * @return The center of the element
		 */
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


		private final Size size;

		private final MutablePos position;


		/**
		 * Constructs an Oval instance
		 *
		 * @param position The position of the Oval
		 * @param size The size of the Oval
		 */
		public Oval(Pos position, Size size) {
			this.size = size;
			this.position = position.toMutable();
		}


		/**
		 * Gets the name of the element
		 *
		 * @return The name of the element
		 */
		@Override
		public String getName() {
			return "Oval";
		}

		/**
		 * Checks if a position is in range of this element
		 *
		 * @param pos The position to check
		 *
		 * @return If it is in range of the element
		 */
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
		public Size getSize() {
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

		/**
		 * Gets the center of the element
		 *
		 * @return The center of the element
		 */
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


		private final Size size;

		private final MutablePos position;


		// TODO: Take in primitives as a constructor

		/**
		 * Constructs a Rectangle instance
		 *
		 * @param color The color of the rectangle
		 * @param position The position of the rectangle
		 * @param size The size of the rectangle
		 */
		public Rectangle(Color color, Pos position, Size size) {
			this.size = size;
			this.color = color;
			this.position = position.toMutable();
		}


		/**
		 * Gets the name of the element
		 *
		 * @return The name of the element
		 */
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
		public Size getSize() {
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


		/**
		 * Gets the top left of the element
		 *
		 * @return The top left of the element
		 */
		public Pos getTopLeft() {
			return position;
		}

		/**
		 * Gets the top right of the element
		 *
		 * @return The top right of the element
		 */
		public Pos getTopRight() {
			return new Pos(position.getX() + size.getWidth(), position.getY());
		}

		/**
		 * Gets the bottom left of the element
		 *
		 * @return The bottom left of the element
		 */
		public Pos getBottomLeft() {
			return new Pos(position.getX(), position.getY() + size.getHeight());
		}

		/**
		 * Gets the bottom right of the element
		 *
		 * @return The bottom right of the element
		 */
		public Pos getBottomRight() {
			return new Pos(position.getX() + size.getWidth(), position.getY() + size.getHeight());
		}

		/**
		 * Gets the corners of the rectangle
		 *
		 * @return The corners of the rectangle
		 */
		public Pos[] getCorners() {
			return new Pos[] { getTopLeft(), getTopRight(), getBottomLeft(), getBottomRight() };
		}


		/**
		 * Checks if a position is in range of this element
		 *
		 * @param pos The position to check
		 *
		 * @return If it is in range of the element
		 */
		@Override
		public boolean isInRange(Pos pos) {

			final Distance distance = pos.distTo(position);

			final double distX = distance.getX();
			final double distY = distance.getY();

			return distX > 0 && distX <= size.getWidth()
				&& distY > 0 && distY <= size.getHeight();
		}

		/**
		 * Gets the center of the rectangle
		 *
		 * @return The center of the rectangle
		 */
		@Override
		public Pos getCenter() {
			return new Pos(position.getX() + (size.getWidth() / 2), position.getY() + (size.getHeight() / 2));
		}
	}


	/**
	 * The Triangle Element
	 */
	// TODO: Make filled and outline
	public final static class Triangle extends Element {

		private Color color;


		private final MutablePos left, middle, right;

		/**
		 * Constructs a Triangle instance
		 *
		 * @param left The left point of the triangle
		 * @param middle The middle point of the triangle
		 * @param right The right point of the triangle
		 */
		public Triangle(MutablePos left, MutablePos middle, MutablePos right) {
			this.left = left;
			this.middle = middle;
			this.right = right;
		}


		/**
		 * Gets the name of the element
		 *
		 * @return The name of the element
		 */
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

		/**
		 * Checks if a position is in range of this element
		 *
		 * @param pos The position to check
		 *
		 * @return If it is in range of the element
		 */
		@Override
		public boolean isInRange(Pos pos) {
			// TODO
			return false;
		}

		/**
		 * Gets the center for the triangle
		 *
		 * @return The center of the triangle
		 */
		@Override
		public Pos getCenter() {
			// TODO
			return null;
		}

		/**
		 * Gets the position for the triangle
		 *
		 * @return The position of the triangle
		 */
		@Override
		public MutablePos getPosition() {
			return getLeft();
		}

		/**
		 * Gets the size for the triangle
		 *
		 * @return The size of the triangle
		 */
		@Override
		public Size getSize() {
			return new Size((int) (right.getX() - left.getX()), (int) (max(left.getY(), right.getY()) - middle.getY()));
		}
	}


	/**
	 * The Image Element
	 */
	public final static class Image extends Element {

		private boolean isResized;

		private InputStream imageStream;


		private final Size size;

		private final MutablePos position;


		private final LazyStruct<BufferedImage> image = LazyUtils.lazy(() ->
			TryUtils.attemptOrBreak(() -> ImageIO.read(imageStream))
		);


		/**
		 * Constructs an Image instance
		 *
		 * @param position The position of the Image
		 * @param imageStream An input stream for the Image
		 */
		public Image(Pos position, InputStream imageStream) {

			this.imageStream = imageStream;
			this.position = position.toMutable();

			this.size = new Size(image.get().getWidth(), image.get().getHeight());
		}

		/**
		 * Constructs an Image instance
		 *
		 * @param position The position of the Image
		 * @param size The size of the image
		 * @param imageStream An input stream for the Image
		 */
		public Image(Pos position, Size size, InputStream imageStream) {
			this.size = size;
			this.imageStream = imageStream;
			this.position = position.toMutable();
		}


		/**
		 * Gets the name of the element
		 *
		 * @return The name of the element
		 */
		@Override
		public String getName() {
			return "Image";
		}

		/**
		 * Checks if a position is in range of this element
		 *
		 * @param pos The position to check
		 *
		 * @return If it is in range of the element
		 */
		@Override
		public boolean isInRange(Pos pos) {
			// TODO
			return true;
		}

		/*
		public boolean isInPixelRange(Pos pos) {
			// TODO
			return true;
		}
		*/

		/**
		 * Gets the center for the image
		 *
		 * @return The center of the image
		 */
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

		/**
		 * Gets the buffered image for the image
		 *
		 * @return The buffered image of the image
		 */
		public BufferedImage getImage() {
			return image.get();
		}

		/**
		 * Gets the input stream for the image
		 *
		 * @return The input stream of the image
		 */
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
		public Size getSize() {
			return size;
		}

		/**
		 * Checks if the image is resized
		 *
		 * @return If the image is resized
		 */
		public boolean isResized() {
			return isResized;
		}

		/**
		 * Sets if the image is resized
		 *
		 * @param isResized Whether or not the image is resized
		 */
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

		private final Size size;

		private final MutablePos position;

		private final Gif.Frame firstFrame;


		/**
		 * Construct a Gif Element instance
		 *
		 * @param position The position of the Gif Element
		 * @param imageStream The input stream of the Gif Element
		 */
		public GifElem(Pos position, InputStream imageStream) {
			this(position, apply(new Gif(), it -> it.read(imageStream)));
		}

		/**
		 * Construct a Gif Element instance
		 *
		 * @param position The position of the Gif Element
		 * @param gif The gif of the Gif Element
		 */
		public GifElem(Pos position, Gif gif) {
			this(position, new Size(gif.getFrames().get(0).getImage().getWidth(), gif.getFrames().get(0).getImage().getHeight()), gif);
		}

		/**
		 * Construct a Gif Element instance
		 *
		 * @param position The position of the Gif Element
		 * @param size The size of the Gif Element
		 * @param gif The gif of the Gif Element
		 */
		public GifElem(Pos position, Size size, Gif gif) {
			this.gif = gif;
			this.size = size;
			this.position = position.toMutable();
			this.firstFrame = requireNonNull(gif.getFrames().get(0));
		}


		/**
		 * Gets the name of the element
		 *
		 * @return The name of the element
		 */
		@Override
		public String getName() {
			return "Gif";
		}

		/**
		 * Checks if a position is in range of this element
		 *
		 * @param pos The position to check
		 *
		 * @return If it is in range of the element
		 */
		@Override
		public boolean isInRange(Pos pos) {
			// TODO
			return true;
		}

		/*
		public boolean isInPixelRange(Pos pos) {
			// TODO
			return true;
		}
		*/

		/**
		 * Gets the center for the gif
		 *
		 * @return The center of the gif
		 */
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


		/**
		 * Gets the gif for the GifElem
		 *
		 * @return The gif of the GifElem
		 */
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
		public Size getSize() {
			return size;
		}


		/**
		 * Checks if the gif is resized
		 *
		 * @return If the gif is resized
		 */
		public boolean isResized() {
			return isResized;
		}

		/**
		 * Sets if the gif is resized
		 *
		 * @param isResized If the gif is resized
		 */
		public void setIsResized(boolean isResized) {
			this.isResized = isResized;
		}

	}

	/**
	 * The Button Element
	 */
	public final static class Button extends Element {

		private final Element element;

		private final ClickEventHandlerBase handler;


		/**
		 * Construct a Button instance
		 *
		 * @param element The element for the Button
		 * @param handler The handler for the Button
		 */
		public Button(Element element, ClickEventHandlerBase handler) {
			this.element = element;
			this.handler = handler;
		}


		/**
		 * Gets the name of the element
		 *
		 * @return The name of the element
		 */
		@Override
		public String getName() {
			return "Button [" + element.getName() + ']';
		}

		/**
		 * Checks if a position is in range of this element
		 *
		 * @param pos The position to check
		 *
		 * @return If it is in range of the element
		 */
		@Override
		public boolean isInRange(Pos pos) {
			return element.isInRange(pos);
		}


		/**
		 * Gets the center of the element
		 *
		 * @return The center of the element
		 */
		@Override
		public Pos getCenter() {
			return element.getCenter();
		}

		/**
		 * Handles the clicking of the button
		 */
		public void handleClick() {
			handler.onClick();
		}


		/**
		 * Gets the position of the button
		 *
		 * @return The position of the image
		 */
		public MutablePos getPosition() {
			return element.getPosition();
		}

		/**
		 * Gets the dimensions of the button
		 *
		 * @return The size of the image
		 */
		public Size getSize() {
			return element.getSize();
		}


		/**
		 * Gets the element of the button
		 *
		 * @return The element of the button
		 */
		public Element getElement() {
			return element;
		}

	}

}