package me.camdenorrb.zambiegame.engine.gui.impl;

import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.engine.gui.struct.GuiStruct;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

import java.awt.*;
import java.nio.IntBuffer;
import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public final class OpenGLGui extends GuiStruct {

	private long windowID;


	private final String title;

	private final Dimension size;

	private final boolean isResizable;


	public OpenGLGui(String title, Dimension size, boolean isResizable) {
		this.title = title;
		this.size = size;
		this.isResizable = isResizable;
	}


	@Override
	protected void onShow() {
		new Thread(() -> {
			init();
			loop();
		}).start();
	}

	@Override
	protected void onHide() {
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwSetWindowShouldClose(windowID, true);
	}


	@Override
	public String getName() {
		return "OpenGLGui";
	}


	private void init() {

		GLFWErrorCallback.createPrint(System.err).set();

		if (!glfwInit()) {
			throw new IllegalStateException("Can't initialize GLFW!");
		}

		glfwDefaultWindowHints();

		glfwWindowHint(GLFW_SAMPLES, 20);
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, isResizable ? 1 : 0);

		windowID = glfwCreateWindow(size.width, size.height, title, NULL, NULL);
		glfwMakeContextCurrent(windowID);

		if (windowID == NULL) {
			throw new RuntimeException("Failed to create window!");
		}

		glfwSetMouseButtonCallback(windowID, (windowID, button, action, mods) -> {

			final double[] xPos = new double[1];
			final double[] yPos = new double[1];

			glfwGetCursorPos(windowID, xPos, yPos);

			System.out.println(Arrays.toString(xPos) + Arrays.toString(yPos));
		});

		glfwSetKeyCallback(windowID, (windowID, key, scanCode, action, mods) -> {
			if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
				glfwSetWindowShouldClose(windowID, true);
			}
		});

		final GLFWVidMode vidMode = requireNonNull(glfwGetVideoMode(glfwGetPrimaryMonitor()));
		System.out.println("[LWJGL] Refresh rate: " + vidMode.refreshRate());

		glfwSetWindowPos(windowID, (vidMode.width() - size.width) / 2, (vidMode.height() - size.height) / 2);

		glfwSwapInterval(1); // VSYNC

		glfwShowWindow(windowID);

		createCapabilities();

		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

		final IntBuffer buffer = BufferUtils.createIntBuffer(1);
		glGenBuffers(buffer);

		final int id = buffer.get(0);

		glBindBuffer(GL_ARRAY_BUFFER, id);
		glGenBuffers();
	}


	private void loop() {


		while (!glfwWindowShouldClose(windowID)) {
			update();
			//clear();
			//glfwSwapBuffers(windowID); // Update display
			//glfwWaitEvents();
		}

		glfwFreeCallbacks(windowID);
		glfwDestroyWindow(windowID);

		glfwTerminate();
		requireNonNull(glfwSetErrorCallback(null)).free();

		// Enable pixel coords

		//glViewport(0, 0, size.width, size.height);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, size.width, size.height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);


		//glMatrixMode(GL_PROJECTION);
		//glLoadIdentity();
		//glOrtho(0, size.width, 0, size.height, -1, 1);
		//glMatrixMode(GL_MODELVIEW); // Draw on modelview rather than projection
	}


	private void update() {
		clear();
		elements.forEach(this::draw);
		glfwSwapBuffers(windowID); // Update display
		glfwPollEvents();
	}

	private void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}


	private void draw(Element element) {

		glBegin(GL_TRIANGLES);

		glColor3d(0, 1, 0);

		glVertex2d(0, size.height);
		glVertex2d(size.width / 2, 0);
		glVertex2d(size.width, size.height);

		glEnd();
		glFlush();

		/*
		if (element instanceof Element.Rectangle) {

			final Element.Rectangle rectangle = (Element.Rectangle) element;

			final Pos topLeft = rectangle.getTopLeft();
			final Pos topRight = rectangle.getTopRight();
			final Pos bottomLeft = rectangle.getBottomLeft();
			final Pos bottomRight = rectangle.getBottomRight();

			glBegin(GL_QUADS);
			glColor3d(.5f, .5f, 1.0f);

			glVertex2d(topLeft.getX(), topLeft.getY());
			glVertex2d(topRight.getX(), topRight.getY());
			glVertex2d(bottomRight.getX(), bottomRight.getY());
			glVertex2d(bottomLeft.getX(), bottomLeft.getY());

			glEnd();
			glFlush();
		}*/


		//element.
	}


	public String getTitle() {
		return title;
	}

	public Dimension getSize() {
		return size;
	}

	public boolean isResizable() {
		return isResizable;
	}

}
