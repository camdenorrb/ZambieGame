package me.camdenorrb.zambiegame.engine.gui.impl;

import gifAnimation.Gif;
import me.camdenorrb.zambiegame.engine.gui.impl.element.Element;
import me.camdenorrb.zambiegame.engine.gui.struct.GuiStruct;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.utils.JavaUtils;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class ProcGui extends GuiStruct {

	private String title;


	private final Dimension size;


	private final Applet applet = new Applet();

	private final Map<String, PImage> imageCache = new HashMap<>();


	public ProcGui(String title, Dimension size) {
		this.title = title;
		this.size = size;
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


	private class Applet extends PApplet {

		@Override
		public void settings() {
			size(size.width, size.height);
		}

		@Override
		public void draw() {
			background(color(255, 0, 0));
			new HashSet<>(getElements()).forEach(this::draw);
		}


		void draw(Element element) {

			if (element instanceof Element.Text) {

				final Element.Text text = (Element.Text) element;

				final Pos<Float> pos = text.getPosition();

				text(text.getData(), pos.getX(), pos.getY());
			}

			else if (element instanceof Element.Line) {

				final Element.Line line = (Element.Line) element;

				final Pos<Float> posA = line.getA();
				final Pos<Float> posB = line.getB();

				stroke(line.getColor().getRGB());
				line(posA.getX(), posA.getY(), posB.getX(), posB.getY());
			}

			else if (element instanceof Element.Oval) {

				final Element.Oval oval = (Element.Oval) element;

				final Pos<Float> position = oval.getPosition();
				final Dimension dimension = oval.getDimension();

				fill(oval.getColor().getRGB());
				ellipse(position.getX(), position.getY(), dimension.width, dimension.height);
			}

			else if (element instanceof Element.Triangle) {

				final Element.Triangle triangle = (Element.Triangle) element;

				final Pos<Float> left = triangle.getLeft();
				final Pos<Float> middle = triangle.getMiddle();
				final Pos<Float> right = triangle.getRight();

				fill(triangle.getColor().getRGB());
				triangle(left.getX(), left.getY(), middle.getX(), middle.getY(), right.getX(), right.getY());
			}

			else if (element instanceof Element.Rectangle) {

				final Element.Rectangle rectangle = (Element.Rectangle) element;

				final Pos<Float> position = rectangle.getPosition();
				final Dimension dimension = rectangle.getDimension();

				fill(rectangle.getColor().getRGB());
				rect(position.getX(), position.getY(), (float) dimension.getWidth(), (float) dimension.getHeight());
			}

			else if (element instanceof Element.Image) {

				final Element.Image image = (Element.Image) element;
				final Pos<Float> position = image.getPosition();

				final PImage pImage = imageCache.computeIfAbsent(image.getPath(), (path) -> {
					if (!path.endsWith(".gif")) return loadImage(path);
					return JavaUtils.apply(new Gif(this, path), Gif::play);
				});

				image(pImage, position.getX(), position.getY());
			}
		}

	}


	public String getTitle() {
		return title;
	}

	public Dimension getSize() {
		return size;
	}


	public void setTitle(String title) {
		applet.getSurface().setTitle(title);
		this.title = title;
	}

}
