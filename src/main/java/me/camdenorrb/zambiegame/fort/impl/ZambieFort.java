package me.camdenorrb.zambiegame.fort.impl;

import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.fort.struct.FortStruct;
import me.camdenorrb.zambiegame.impl.pos.MutablePos;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.struct.game.ZambieGameStruct;
import me.camdenorrb.zambiegame.utils.ResourceUtils;

import java.util.Collections;
import java.util.List;

public class ZambieFort extends FortStruct {

    private Element.Image body = new Element.Image(pos, ResourceUtils.get("fort/destroyed-castle.png"));



    public ZambieFort(ZambieGameStruct game) {
        super(game);
    }


    @Override
    public String getName() {
        return "ZambieFort";
    }


    @Override
    public Pos getCenter() {
        return body.getCenter();
    }

    @Override
    protected void onSpawn(Pos pos) {
        body.getPosition().setXY(pos.getX(), pos.getY());
        super.onSpawn(pos);
    }

    @Override
    protected void onTeleport(Pos toPos) {

        if (!isSpawned()) return;

        final MutablePos bodyPos = body.getPosition();

        bodyPos.setX(toPos.getX());
        bodyPos.setY(toPos.getY());
    }

    @Override
    public double getHeight() {
        return body.getSize().height;
    }

    @Override
    public double getWidth() {
        return body.getSize().width;
    }

    @Override
    public List<Element> getParts() {
        return Collections.singletonList(body);
    }

}
