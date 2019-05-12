package me.camdenorrb.zambiegame.engine.gui.impl;


import me.camdenorrb.zambiegame.engine.gif.processing.PGif;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.engine.gui.struct.GuiStruct;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static me.camdenorrb.zambiegame.utils.JavaUtils.apply;


/**
 * Processing implementation of a GUI
 */

public class ProcGui extends GuiStruct {

	private String title;


	private final Applet applet;

	private final Dimension size;


	private final Map<UUID, PImage> imageCache = new HashMap<>();



	public ProcGui(String title, float frameRate, Dimension size) {
		this.size = size;
		this.title = title;
		this.applet = new Applet(frameRate);
	}


	@Override
	public String getName() {
		return "Processing GUI";
	}


	@Override
	protected void onInit() {
		PApplet.runSketch(new String[] { Applet.class.getName() }, applet);
		setTitle(title);
	}

	@Override
	public void onShow() {
		applet.getSurface().setVisible(true);
	}

	@Override
	public void onHide() {
		applet.getSurface().setVisible(false);
	}


	public void setFrameRate(float frameRate) {
		applet.setFrameRate(frameRate);
	}

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
	public Dimension getSize() {
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

	public Applet getApplet() {
		return applet;
	}


	/**
	 * The Applet used in the backend of the GUI
	 */
	private class Applet extends PApplet {

		private float frameRate;


		public Applet(float frameRate) {
			this.frameRate = frameRate;
		}


		@Override
		public void settings() {
			size(size.width, size.height/*, FX2D*/);
		}

		@Override
		public void setup() {
			hint(ENABLE_STROKE_PURE);
			hint(ENABLE_ASYNC_SAVEFRAME);
		}

		@Override
		public void draw() {
			frameRate(frameRate);
			background(128, 218, 235);
			new ArrayList<>(elements).forEach(this::draw);
		}


		private void drawRect(Pos pos, Color color, Dimension dimensions) {
			fill(color.getRGB());
			rect((float) pos.getX(), (float) pos.getY(), (float) dimensions.width, (float) dimensions.height);
			noFill();
		}

		private void drawOval(Pos pos, Color color, Dimension dimensions) {
			fill(color.getRGB());
			ellipse((float) pos.getX(), (float) pos.getY(), (float) dimensions.width, (float) dimensions.height);
			noFill();
		}

		private void drawText(Pos pos, String content, Dimension dimensions) {
			text(content, (float) pos.getX(), (float) pos.getY(), (float) dimensions.width, (float) dimensions.height);
		}

		private void drawLine(Pos start, Pos end, Color color) {
			stroke(color.getRGB());
			line((float) start.getX(), (float) start.getY(), (float) end.getX(), (float) end.getY());
			noStroke();
		}

		private void drawImage(Pos pos, PImage image) {
			image(image, (float) pos.getX(), (float) pos.getY());
		}

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

			if (element instanceof Element.Text) {

				final Element.Text text = (Element.Text) element;

				drawText(text.getPosition(), text.getData(), text.getSize());
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

				final Dimension dimension = image.getSize();

				final PImage pImage = imageCache.computeIfAbsent(image.getUUID(), (id) ->
					new PImage(image.getImage())
				);

				if (image.isResized()) {
					pImage.resize(dimension.width, dimension.height);
				}

				drawImage(image.getPosition(), pImage);
			}

			else if (element instanceof Element.GifElem) {

				final Element.GifElem gifElem = (Element.GifElem) element;

				final Dimension dimension = gifElem.getSize();

				final PImage pImage = imageCache.computeIfAbsent(gifElem.getUUID(), (id) ->
					apply(new PGif(gifElem.getGif(), this), PGif::start)
				);

				if (gifElem.isResized()) {
					pImage.resize(dimension.width, dimension.height);
				}

				drawImage(gifElem.getPosition(), pImage);
			}
		}

		public float getFrameRate() {
			return frameRate;
		}

		public void setFrameRate(float frameRate) {
			this.frameRate = frameRate;
			if (isVisible()) frameRate(frameRate);
		}

	}

}
