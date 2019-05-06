package me.camdenorrb.zambiegame.fort.impl;

import me.camdenorrb.zambiegame.engine.gui.impl.element.Element;
import me.camdenorrb.zambiegame.fort.struct.FortStruct;
import me.camdenorrb.zambiegame.impl.pos.Pos;

import java.io.File;
import java.util.Collections;
import java.util.List;


public class HueminFort extends FortStruct {

	public HueminFort(Pos<Double> pos) {
		super(pos);
	}


	@Override
	public String getName() {
		return "HueminFort";
	}


	@Override
	public List<Element> getParts() {
		return Collections.singletonList(new Element.Image(pos, new File("resources/fort/huemin-castle.png")));
	}

}