package me.camdenorrb.zambiegame.engine.gui.impl;


import me.camdenorrb.zambiegame.engine.gif.processing.PGif;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.engine.gui.struct.GuiStruct;
import me.camdenorrb.zambiegame.engine.manager.PGifManager;
import me.camdenorrb.zambiegame.impl.Size;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.awt.*;
import java.util.*;


/**
 * Processing implementation of a GUI
 */
public class ProcGui extends GuiStruct {

	private String title;


	private final Applet applet;

	private final Size size;


	private final PGifManager gifManager = new PGifManager();

	private final Map<UUID, PImage> imageCache = new HashMap<>();

	private final Map<UUID, PGif> gifCache = new HashMap<>();


	/**
	 * Constructs a Processing GUI instance
	 *
	 * @param title The title for the GUI
	 * @param frameRate The frame rate for the GUI
	 * @param size The size for the GUI
	 */
	public ProcGui(String title, float frameRate, Size size) {
		this.size = size;
		this.title = title;
		this.applet = new Applet(this, frameRate);
	}


	/**
	 * Gets the name of the GUI
	 *
	 * @return The name of the GUI
	 */
	@Override
	public String getName() {
		return "Processing GUI";
	}


	/**
	 * Handles initialization of the GUI
	 */
	@Override
	protected void onInit() {
		PApplet.runSketch(new String[] { Applet.class.getName() }, applet);
		setTitle(title);
	}

	/**
	 * Handles the showing of the GUI
	 */
	@Override
	public void onShow() {
		gifManager.enable();
		applet.getSurface().setVisible(true);
	}

	/**
	 * Handles the hiding of the GUI
	 */
	@Override
	public void onHide() {
		gifManager.disable();
		applet.getSurface().setVisible(false);
	}


	/**
	 * Handles the removing of an element on the GUI
	 *
	 * @param element The element to remove
	 */
	@Override
	protected void onRemove(Element element) {
		if (element instanceof Element.GifElem) {

			final PGif gif = gifCache.get(element.getUUID());
			if (gif == null) return;

			gifManager.remGif(gif);
			gif.setCurrentFrameIndex(0);
		}
		/*if (element instanceof Element.Image || element instanceof Element.GifElem) {
			imageCache.remove(element.getUUID());
		}*/
	}


	/**
	 * Sets the frame rate of the GUI
	 *
	 * @param frameRate The new frame rate
	 */
	public void setFrameRate(float frameRate) {
		applet.setFrameRate(frameRate);
	}

	/**
	 * Gets the frame rate of the GUI
	 *
	 * @return The frame rate of the GUI
	 */
	public float getFrameRate() {
		return applet.getFrameRate();
	}


	/**
	 * Gets the title of the GUI
	 *
	 * @return The title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the size of the GUI
	 *
	 * @return The size
	 */
	public Size getSize() {
		return size;
	}


	/**
	 * Sets the title of the GUI
	 *
	 * @param title The new Title
	 */
	public void setTitle(String title) {
		applet.getSurface().setTitle(title);
		this.title = title;
	}

	/**
	 * Gets the applet of the GUI
	 *
	 * @return The applet
	 */
	public Applet getApplet() {
		return applet;
	}

	/**
	 * Clears the GUI of all elements
	 */
	@Override
	public void clear() {

		gifCache.clear();
		gifManager.clear();
		imageCache.clear();

		super.clear();
	}

	/**
	 * Gets the keys pressed on the GUI
	 *
	 * @return The keys pressed
	 */
	public Set<Integer> getKeysPressed() {
		return applet.getKeysPressed();
	}

	/**
	 * The Applet used in the backend of the GUI
	 */
	public class Applet extends PApplet {

		private float frameRate;

		private final ProcGui gui;

		private Set<Integer> keysPressed = new HashSet<>();


		/**
		 * Constructs a Processing Applet
		 *
		 * @param gui The Processing GUI
		 * @param frameRate The frame rate for the Applet
		 */
		private Applet(ProcGui gui, float frameRate) {
			this.gui = gui;
			this.frameRate = frameRate;
		}


		/**
		 * Sets the settings of the Applet
		 */
		@Override
		public void settings() {
			size((int) size.getWidth(), (int) size.getHeight()/*, FX2D*/);
		}

		/**
		 * Sets up the Applet
		 */
		@Override
		public void setup() {
			hint(ENABLE_STROKE_PURE);
			hint(ENABLE_ASYNC_SAVEFRAME);
		}

		/**
		 * Draws the Applet
		 */
		@Override
		public void draw() {
			frameRate(frameRate);
			background(128, 218, 235);
			getElements().forEach(this::draw);
		}

		/**
		 * Handles the clicking of a mouse on the Applet
		 *
		 * @param event The mouse click event
		 */
		@Override
		public void mouseClicked(MouseEvent event) {
			super.mouseClicked(event);

			final Pos pos = new Pos(event.getX(), event.getY());

			elements.values().stream()
				.flatMap(Collection::parallelStream)
				.filter(it -> it instanceof Element.Button)
				.filter(it -> it.isInRange(pos))
				.forEach(it -> ((Element.Button) it).handleClick());
		}

		/**
		 * Handles the key pressed on an Applet
		 *
		 * @param event The key press event
		 */
		@Override
		public void keyPressed(KeyEvent event) {
			super.keyPressed(event);

			final int keyCode = event.getKeyCode();

			keysPressed.add(keyCode);
			keyListeners.forEach(it -> it.onKeyPress(gui, keyCode));
		}

		/**
		 * Handles the key released on an Applet
		 *
		 * @param event The key release event
		 */
		@Override
		public void keyReleased(KeyEvent event) {
			super.keyReleased();

			final int keyCode = event.getKeyCode();

			keysPressed.remove(keyCode);
			keyListeners.forEach(it -> it.onKeyRelease(gui, keyCode));
		}


		/**
		 * Draws a Rectangle on the Applet
		 *
		 * @param pos The position
		 * @param color The color
		 * @param size The size
		 */
		private void drawRect(Pos pos, Color color, Size size) {
			fill(color.getRGB());
			rect((float) pos.getX(), (float) pos.getY(), (float) size.getWidth(), (float) size.getHeight());
			noFill();
		}

		/**
		 * Draws a Oval on the Applet
		 *
		 * @param pos The position
		 * @param color The color
		 * @param size The size
		 */
		private void drawOval(Pos pos, Color color, Size size) {
			fill(color.getRGB());
			ellipse((float) pos.getX(), (float) pos.getY(), (float) size.getWidth(), (float) size.getHeight());
			noFill();
		}

		/**
		 * Draws Text on the Applet
		 *
		 * @param pos The position
		 * @param fontSize The font size
		 * @param content The content
		 * @param size The size of the box
		 */
		private void drawText(Pos pos, float fontSize, String content, Size size) {
			fill(Color.BLACK.getRGB());
			textSize(fontSize);
			text(content, (float) pos.getX(), (float) pos.getY(), (float) size.getWidth(), (float) size.getHeight());
			noFill();
		}

		/**
		 * Draws a Line on the Applet
		 *
		 * @param start The starting position
		 * @param end The ending position
		 * @param color The color
		 */
		private void drawLine(Pos start, Pos end, Color color) {
			stroke(color.getRGB());
			line((float) start.getX(), (float) start.getY(), (float) end.getX(), (float) end.getY());
			noStroke();
		}

		/**
		 * Draws an Image on the Applet
		 *
		 * @param pos The position of the image
		 * @param image The image to draw
		 */
		private void drawImage(Pos pos, PImage image) {
			image(image, (float) pos.getX(), (float) pos.getY());
		}

		/**
		 * Draws a Triangle on teh Applet
		 *
		 * @param left The left point
		 * @param middle The middle point
		 * @param right The right point
		 * @param color The color
		 */
		private void drawTriangle(Pos left, Pos middle, Pos right, Color color) {
			fill(color.getRGB());
			triangle((float) left.getX(), (float) left.getY(), (float) middle.getX(), (float) middle.getY(), (float) right.getX(), (float) right.getY());
			noFill();
		}


        /**
		 * Draws an ambiguous element
		 *
		 * @param element The ambiguous element
		 */
		private void draw(Element element) {

			if (element instanceof Element.Button) {
				draw(((Element.Button) element).getElement());
				return;
			}
			if (element instanceof Element.Text) {

				final Element.Text text = (Element.Text) element;

				drawText(text.getPosition(), text.getFontSize(), text.getData(), text.getSize());
			}

			else if (element instanceof Element.Line) {

				final Element.Line line = (Element.Line) element;

				drawLine(line.getStart(), line.getEnd(), line.getColor());
			}

			else if (element instanceof Element.Oval) {

				final Element.Oval oval = (Element.Oval) element;

				drawOval(oval.getPosition(), oval.getColor(), oval.getSize());
			}

			else if (element instanceof Element.Triangle) {

				final Element.Triangle triangle = (Element.Triangle) element;

				final Pos left = triangle.getLeft();
				final Pos middle = triangle.getMiddle();
				final Pos right = triangle.getRight();

				drawTriangle(left, middle, right, triangle.getColor());
			}

			else if (element instanceof Element.Rectangle) {

				final Element.Rectangle rectangle = (Element.Rectangle) element;

				drawRect(rectangle.getPosition(), rectangle.getColor(), rectangle.getSize());
			}

			else if (element instanceof Element.Image) {

				final Element.Image image = (Element.Image) element;

				final Size size = image.getSize();

				final PImage pImage = imageCache.computeIfAbsent(image.getUUID(), id ->
					new PImage(image.getImage())
				);

				if (image.isResized()) {
					pImage.resize((int) size.getWidth(), (int) size.getHeight());
				}

				drawImage(image.getPosition(), pImage);
			}

			else if (element instanceof Element.GifElem) {

				final Element.GifElem gifElem = (Element.GifElem) element;

				final Size size = gifElem.getSize();

				final PGif pGif = gifCache.computeIfAbsent(gifElem.getUUID(), id ->
					new PGif(gifElem.getGif())
				);

				if (!gifManager.contains(pGif)) {
					gifManager.addGif(pGif);
					pGif.setShouldPlay(true);
				}

				if (gifElem.isResized()) {
					pGif.resize((int) size.getWidth(), (int) size.getHeight());
				}

				drawImage(gifElem.getPosition(), pGif);
			}
		}

		/**
		 * Gets the frame rate of the Applet
		 *
		 * @return The frame rate of the Applet
		 */
		public float getFrameRate() {
			return frameRate;
		}

		/**
		 * Sets the frame rate of the Applet
		 *
		 * @param frameRate The frame rate
		 */
		public void setFrameRate(float frameRate) {
			this.frameRate = frameRate;
			if (isVisible()) frameRate(frameRate);
		}

		/**
		 * Gets the keys pressed on the Applet
		 *
		 * @return The keys pressed
		 */
		public Set<Integer> getKeysPressed() {
			return Collections.unmodifiableSet(keysPressed);
		}

	}

}
