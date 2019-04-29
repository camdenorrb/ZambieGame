package me.camdenorrb.zambiegame.engine.gui.struct;

import me.camdenorrb.zambiegame.engine.gui.base.GuiBase;
import me.camdenorrb.zambiegame.engine.gui.impl.element.Element;

import java.util.*;


public abstract class GuiStruct implements GuiBase {

	private boolean isVisible, isInitialized;

	private final Set<Element> elements = new HashSet<>();


	protected void onInit() {}


	protected abstract void onShow();

	protected abstract void onHide();


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

	public final boolean isInitialized() {
		return isInitialized;
	}


	public final Set<Element> getElements() {
		return Collections.unmodifiableSet(elements);
	}


	public final void addElements(List<Element> elements) {
		this.elements.addAll(elements);
	}

	public final void addElements(Element... elements) {
		this.elements.addAll(Arrays.asList(elements));
	}

	public final void remElements(List<Element> elements) {
		this.elements.removeAll(elements);
	}

	public final void remElements(Element... elements) {
		this.elements.removeAll(Arrays.asList(elements));
	}

}
