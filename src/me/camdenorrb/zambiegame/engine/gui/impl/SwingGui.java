package me.camdenorrb.zambiegame.engine.gui.impl;

/*
public class SwingGui extends GuiStruct {

	private JFrame frame;

	private final LazyStruct<Graphics2D> graphics2D = LazyUtils.lazy(() ->
		(Graphics2D) frame.getGraphics()
	);


	public SwingGui(Dimension size) {
		this("", size);
	}

	public SwingGui(Dimension size, GraphicsConfiguration gConfig) {
		this("", size, gConfig);
	}

	public SwingGui(String title, Dimension size) {
		frame = new JFrame(title);
		frame.setSize(size);
	}

	public SwingGui(String title, Dimension size, GraphicsConfiguration gConfig) {
		frame = new JFrame(title, gConfig);
		frame.setSize(size);
	}


	@Override
	public String getName() {
		return frame.getTitle();
	}


	@Override
	protected void onInit() {
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	@Override
	public void onShow() {
		frame.setVisible(true);
	}

	@Override
	public void onHide() {
		frame.setVisible(false);
	}


	private void draw(Element element) {

		if (element instanceof Element.Text) {

			final Element.Text text = (Element.Text) element;

			final Pos<Integer> pos = text.getPosition();

			graphics2D.get().drawString(text.getData(), pos.getX(), pos.getY());
		}

		else if (element instanceof Element.Line) {

			final Element.Line line = (Element.Line) element;

			final Pos<Integer> posA = line.getA();
			final Pos<Integer> posB = line.getB();

			graphics2D.get().drawLine(posA.getX(), posA.getY(), posB.getX(), posB.getY());
		}

		else if (element instanceof Element.Oval) {

			final Element.Oval oval = (Element.Oval) element;

			final Pos<Integer> topLeft = oval.getPosition();
			final Dimension dimension = oval.getDimension();

			graphics2D.get().drawOval(topLeft.getX(), topLeft.getY(), dimension.width, dimension.height);
		}

		else if (element instanceof Element.Triangle) {

			final Element.Triangle triangle = (Element.Triangle) element;

			final Pos<Integer> left = triangle.getLeft();
			final Pos<Integer> middle = triangle.getMiddle();
			final Pos<Integer> right = triangle.getRight();

			final int[] xPoints = { left.getX(), middle.getX(), right.getX() };
			final int[] yPoints = { left.getY(), middle.getY(), right.getY() };

			graphics2D.get().drawPolygon(xPoints, yPoints, 3);
		}

		else if (element instanceof Element.Rectangle) {

			final Element.Rectangle rectangle = (Element.Rectangle) element;

			final Pos<Integer> topLeft = rectangle.getPosition();
			final Dimension dimension = rectangle.getDimension();

			graphics2D.get().drawRect(topLeft.getX(), topLeft.getY(), dimension.width, dimension.height);
		}

		else if (element instanceof Element.Image) {

			final Element.Image image = (Element.Image) element;
			final Pos<Integer> position = image.getPosition();

			graphics2D.get().drawImage(image.getData(), null, position.getX(), position.getY());
		}

	}


	public JFrame getFrame() {
		return frame;
	}

}*/