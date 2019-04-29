package me.camdenorrb.zambiegame.engine.gui.impl.element;

import me.camdenorrb.zambiegame.impl.pos.MutablePos;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.type.Named;

import java.awt.*;
import java.io.File;


public abstract class Element implements Named {

	private Element() {}


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


		public String getData() {
			return data;
		}

		public MutablePos<Float> getPosition() {
			return position;
		}


		public void setData(String data) {
			this.data = data;
		}

	}

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


		public Pos<Float> getA() {
			return a;
		}

		public Pos<Float> getB() {
			return b;
		}


		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

	}

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


		public MutablePos<Float> getPosition() {
			return position;
		}

		public Dimension getDimension() {
			return dimension;
		}


		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

	}

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


		public MutablePos<Float> getPosition() {
			return position;
		}

		public Dimension getDimension() {
			return dimension;
		}


		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

	}

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


		public MutablePos<Float> getLeft() {
			return left;
		}

		public MutablePos<Float> getMiddle() {
			return middle;
		}

		public MutablePos<Float> getRight() {
			return right;
		}


		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

	}

	public final static class Image extends Element {

		private String path;

		private final MutablePos<Float> position;


		public Image(Pos<Float> position, File file) {
			this.path = file.getPath();
			this.position = position.toMutable();
		}


		@Override
		public String getName() {
			return "Image";
		}


		public String getPath() {
			return path;
		}

		public MutablePos<Float> getPosition() {
			return position;
		}


	}


}
