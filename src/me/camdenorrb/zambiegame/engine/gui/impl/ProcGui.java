package me.camdenorrb.zambiegame.engine.gui.impl;

import gifAnimation.Gif;
import me.camdenorrb.zambiegame.engine.gui.impl.element.Element;
import me.camdenorrb.zambiegame.engine.gui.struct.GuiStruct;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.utils.JavaUtils;
import me.camdenorrb.zambiegame.utils.PosUtils;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Processing implementation of a GUI
 */
public class ProcGui extends GuiStruct {

	private String title;

	private final Applet applet;

	private final Dimension size;


	private final Map<String, PImage> imageCache = new HashMap<>();


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
	 * The Applet used in the backend of the GUI
	 */
	private class Applet extends PApplet {

		private float frameRate;


		public Applet(float frameRate) {
			this.frameRate = frameRate;
		}


		@Override
		public void settings() {
			size(size.width, size.height);
		}

		@Override
		public void draw() {
			frameRate(frameRate);
			background(128, 218, 235);
			new ArrayList<>(getElements()).forEach(this::draw);
		}


		/**
		 * Draws an ambiguous element
		 *
		 * @param element The ambiguous element
		 */
		void draw(Element element) {

			if (element instanceof Element.Text) {

				final Element.Text text = (Element.Text) element;

				final Dimension size = text.getSize();
				final Pos<Float> pos = PosUtils.toFloat(text.getPosition());

				text(text.getData(), pos.getX(), pos.getY(), (float) size.width, (float) size.height);
			}

			else if (element instanceof Element.Line) {

				final Element.Line line = (Element.Line) element;

				final Pos<Float> posA = PosUtils.toFloat(line.getA());
				final Pos<Float> posB = PosUtils.toFloat(line.getB());

				stroke(line.getColor().getRGB());
				line(posA.getX(), posA.getY(), posB.getX(), posB.getY());
			}

			else if (element instanceof Element.Oval) {

				final Element.Oval oval = (Element.Oval) element;

				final Pos<Float> position = PosUtils.toFloat(oval.getPosition());
				final Dimension dimension = oval.getSize();

				fill(oval.getColor().getRGB());
				ellipse(position.getX(), position.getY(), dimension.width, dimension.height);
			}

			else if (element instanceof Element.Triangle) {

				final Element.Triangle triangle = (Element.Triangle) element;

				final Pos<Float> left = PosUtils.toFloat(triangle.getLeft());
				final Pos<Float> middle = PosUtils.toFloat(triangle.getMiddle());
				final Pos<Float> right = PosUtils.toFloat(triangle.getRight());

				fill(triangle.getColor().getRGB());
				triangle(left.getX(), left.getY(), middle.getX(), middle.getY(), right.getX(), right.getY());
			}

			else if (element instanceof Element.Rectangle) {

				final Element.Rectangle rectangle = (Element.Rectangle) element;

				final Pos<Float> position = PosUtils.toFloat(rectangle.getPosition());
				final Dimension dimension = rectangle.getSize();

				noStroke();

				fill(rectangle.getColor().getRGB());
				rect(position.getX(), position.getY(), (float) dimension.getWidth(), (float) dimension.getHeight());
			}

			else if (element instanceof Element.Image) {

				final Element.Image image = (Element.Image) element;

				final Pos<Float> position = PosUtils.toFloat(image.getPosition());
				final Dimension dimension = image.getSize();

				final PImage pImage = imageCache.computeIfAbsent(image.getFile().getPath(), (path) -> {
					if (!path.endsWith(".gif")) return loadImage(path);
					return JavaUtils.apply(new Gif(this, path), Gif::play);
				});


				if (dimension != null) {
					pImage.resize(dimension.width, dimension.height);
				}

				image(pImage, position.getX(), position.getY());
			}
		}

		public void setFrameRate(float frameRate) {
			this.frameRate = frameRate;
			if (isVisible()) frameRate(frameRate);
		}

		public float getFrameRate() {
			return frameRate;
		}
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

}