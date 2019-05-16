package me.camdenorrb.zambiegame.engine.gui.struct;

import me.camdenorrb.zambiegame.engine.gui.base.GuiBase;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Layer;
import me.camdenorrb.zambiegame.listener.base.KeyListenerBase;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Stream;


/**
 * The strict structure for GUI's
 */
public abstract class GuiStruct implements GuiBase {

	private boolean isVisible, isInitialized;

	//private final Set<Element> elements = new HashSet<>();

	protected final Set<KeyListenerBase> keyListeners = new HashSet<>();

	protected final Map<Layer, ConcurrentLinkedDeque<Element>> elements = new ConcurrentSkipListMap<>();


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
	 * Handles the adding of an Element
	 *
	 * @param element The Element to add
	 */
	protected void onAdd(Element element) {}

	/**
	 * Handles the removing of an Element
	 *
	 * @param element The Element to remove
	 */
	protected void onRemove(Element element) {}


	/**
	 * Initiates the GUI
	 */
	private void init() {

		if (isInitialized) return;

		onInit();
		isInitialized = true;
	}

	/**
	 * Shows the GUI
	 */
	@Override
	public final void show() {

		if (isVisible) return;

		init();
		onShow();
		isVisible = true;
	}

	/**
	 * Hides the GUI
	 */
	@Override
	public final void hide() {

		if (!isVisible) return;

		onHide();
		isVisible = false;
	}


	/**
	 * Checks if the GUI is visible
	 *
	 * @return If the GUI is visible
	 */
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
	public final Stream<Element> getElements() {
		return elements.values().stream().flatMap(ConcurrentLinkedDeque::stream);
	}

	/**
	 * Adds elements to the GUI
	 *
	 * @param layer The layer to remove from
	 * @param elements The elements to add to the GUI
	 */
	public final void addElements(Layer layer, List<Element> elements) {
		elements.forEach(this::onAdd);
		this.elements.computeIfAbsent(layer, ignored -> new ConcurrentLinkedDeque<>()).addAll(elements);
	}

	/**
	 * Replaces an Element on a Layer
	 *
	 * @param layer The layer to replace on
	 * @param fromElement The previous element
	 * @param toElement The new element
	 */
	public final void replaceElement(Layer layer, Element fromElement, Element toElement) {
		addElements(layer, toElement);
		remElements(layer, fromElement);
	}

	/**
	 * Adds elements to the GUI
	 *
	 * @param layer The layer to add to
	 * @param elements The elements to add to the GUI
	 */
	public final void addElements(Layer layer, Element... elements) {
		addElements(layer, Arrays.asList(elements));
	}

	/**
	 * Removes elements on the GUI
	 *
	 * @param layer The layer to remove from
	 * @param elements The elements to remove from the GUI
	 */
	public final void remElements(Layer layer, List<Element> elements) {
		elements.forEach(this::onRemove);
		this.elements.get(layer).removeAll(elements);
	}

	/**
	 * Removes elements on the GUI
	 *
	 * @param layer The layer to remove from
	 * @param elements The elements to remove from the GUI
	 */
	public final void remElements(Layer layer, Element... elements) {
		remElements(layer, Arrays.asList(elements));
	}

	/**
	 * Removes all elements on the GUI Layer
	 *
	 * @param layer The layer to remove from
	 */
	public final void remElements(Layer layer) {
		this.elements.remove(layer).forEach(this::onRemove);
	}

	/**
	 * Clears the GUI of all Elements
	 */
	public void clear() {
		this.elements.clear();
	}

	/**
	 * Adds a key listener
	 *
	 * @param listener The listener to add
	 */
	public void addKeyListener(KeyListenerBase listener) {
		keyListeners.add(listener);
	}

	/**
	 * Removes a key listener
	 *
	 * @param listener The listener to remove
	 */
	public void remKeyListener(KeyListenerBase listener) {
		keyListeners.remove(listener);
	}


	/**
	 * Gets the key listeners
	 *
	 * @return The key listeners
	 */
	public Set<KeyListenerBase> getKeyListeners() {
		return keyListeners;
	}

}
