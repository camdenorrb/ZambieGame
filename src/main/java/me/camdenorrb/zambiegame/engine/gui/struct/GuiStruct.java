package me.camdenorrb.zambiegame.engine.gui.struct;

import me.camdenorrb.zambiegame.engine.gui.base.GuiBase;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * The strict structure for GUI's
 */
public abstract class GuiStruct implements GuiBase {

	private boolean isVisible, isInitialized;

	//private final Set<Element> elements = new HashSet<>();
	// TODO: Make layers List<List<Element>>, 0 being background
	protected final List<Element> elements = new ArrayList<>();


	/**
	 * Handles initialization
	 */
	protected void onInit() {}


	/**
	 * Handles the showing of the GUI
	 */
	protected abstract void onShow();

	/**
	 * Handles the hiding of the GUI
	 */
	protected abstract void onHide();


	/**
	 * Initiates the GUI
	 */
	private void init() {

		if (isInitialized) return;

		onInit();
		isInitialized = true;
	}

	@Override
	public final void show() {

		if (isVisible) return;

		init();
		onShow();
		isVisible = true;
	}

	@Override
	public final void hide() {

		if (!isVisible) return;

		onHide();
		isVisible = false;
	}


	@Override
	public final boolean isVisible() {
		return isVisible;
	}

	/**
	 * Tells whether or not the GUI is initialized
	 *
	 * @return If the GUI is initialized
	 */
	public final boolean isInitialized() {
		return isInitialized;
	}

	/**
	 * Retrieves the elements in the GUI
	 *
	 * @return The elements of the GUI
	 */
	public final List<Element> getElements() {
		return Collections.unmodifiableList(elements);
	}

	/**
	 * Adds elements to the GUI
	 *
	 * @param elements The elements to add to the GUI
	 */
	public final void addElements(List<Element> elements) {
		this.elements.addAll(elements);
	}

	/**
	 * Adds elements to the GUI
	 *
	 * @param elements The elements to add to the GUI
	 */
	public final void addElements(Element... elements) {
		this.elements.addAll(Arrays.asList(elements));
	}

	/**
	 * Removes elements to the GUI
	 *
	 * @param elements The elements to remove from the GUI
	 */
	public final void remElements(List<Element> elements) {
		this.elements.removeAll(elements);
	}

	/**
	 * Removes elements to the GUI
	 *
	 * @param elements The elements to remove from the GUI
	 */
	public final void remElements(Element... elements) {
		this.elements.removeAll(Arrays.asList(elements));
	}

}
