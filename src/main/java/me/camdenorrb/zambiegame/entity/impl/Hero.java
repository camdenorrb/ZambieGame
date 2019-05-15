package me.camdenorrb.zambiegame.entity.impl;

import me.camdenorrb.zambiegame.ZambieGame;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Layer;
import me.camdenorrb.zambiegame.engine.physics.impl.Distance;
import me.camdenorrb.zambiegame.entity.struct.EntityStruct;
import me.camdenorrb.zambiegame.impl.pos.MutablePos;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.struct.game.ZambieGameStruct;
import me.camdenorrb.zambiegame.struct.lazy.LazyStruct;
import me.camdenorrb.zambiegame.type.Killable;
import me.camdenorrb.zambiegame.utils.ResourceUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static me.camdenorrb.zambiegame.utils.GifUtils.lazyLoadElem;
import static me.camdenorrb.zambiegame.utils.JavaUtils.apply;


public class Hero extends EntityStruct {

    protected volatile boolean isAttacking;

    protected Element.GifElem body;


    public static final int HITBOX_WIDTH = 48;

    public static final int HITBOX_HEIGHT = 100;


    private final MutablePos hitboxCenterPos = new MutablePos(0, 0);


    protected static final String LEFT_WALK_PATH = "hero/left-walk.gif";

    protected static final String RIGHT_WALK_PATH = "hero/right-walk.gif";

    protected static final String UP_WALK_PATH = "hero/up-walk.gif";

    protected static final String DOWN_WALK_PATH = "hero/down-walk.gif";


    protected static final String ATTACK_SWORD_PATH = "hero/attack-sword.gif";


    protected final LazyStruct<Element.GifElem> upWalkGif = lazyLoadElem(ResourceUtils.get(UP_WALK_PATH));
    protected final LazyStruct<Element.GifElem> downWalkGif = lazyLoadElem(ResourceUtils.get(DOWN_WALK_PATH));
    protected final LazyStruct<Element.GifElem> leftWalkGif = lazyLoadElem(ResourceUtils.get(LEFT_WALK_PATH));
    protected final LazyStruct<Element.GifElem> rightWalkGif = lazyLoadElem(ResourceUtils.get(RIGHT_WALK_PATH));

    protected final LazyStruct<Element.GifElem> attackSwordGif = lazyLoadElem(ResourceUtils.get(ATTACK_SWORD_PATH));


    public Hero(ZambieGameStruct game) {

        super(game);

        this.body = apply(rightWalkGif.get(), it ->
            it.getPosition().setXY(pos.getX(), pos.getY())
        );
    }


    @Override
    protected void onSpawn(Pos pos) {
        super.onSpawn(pos);

        hitboxCenterPos.setX(pos.getX() + (getWidth() / 2));
        hitboxCenterPos.setY(pos.getY() + (getHeight() / 2));

        body.getPosition().setXY(pos.getX(), pos.getY());

        //hitboxCenterPos.setXY(pos.getX() + Math.round(HITBOX_WIDTH / 2.0), pos.getY() + Math.round(HITBOX_HEIGHT / 2.0));
    }

    @Override
    protected void onRemove() {
        super.onRemove();
    }

    @Override
    public Pos getCenter() {
        return body.getCenter();
    }

    @Override
    protected void onTick() {

        final Set<Character> keysPressed = game.getGui().getKeysPressed();

        double changeX = 0;
        double changeY = 0;

        if (keysPressed.contains('w')) {
            changeY = -1;
        }
        else if (keysPressed.contains('s')) {
            changeY = 1;
        }

        if (keysPressed.contains('a')) {
            changeX = -1;
        }

        else if (keysPressed.contains('d')) {
            changeX = 1;
        }

        isAttacking = keysPressed.contains(' ');

        moveBy(changeX, changeY);
    }


    @Override
    public String getName() {
        return "Hero";
    }

    @Override
    public List<Element> getParts() {
        return Collections.singletonList(body);
    }

    @Override
    public boolean isInRange(Pos pos) {
        final Distance distance = hitboxCenterPos.distTo(pos);
        return distance.getX() <= attackSwordGif.get().getSize().getWidth() && distance.getY() <= getHeight() / 3;
    }

    @Override
    public double getWidth() {
        return body.getSize().width;
    }

    @Override
    public double getHeight() {
        return body.getSize().height;
    }



    @Override
    public void moveBy(double x, double y) {

        final double newY = pos.getY() + y;
        final double newX = pos.getX() + x;

        if (newX <= 0) {
            moveBy(0, y);
            return;
        }

        if (newX + HITBOX_WIDTH > ZambieGame.CANVAS_WIDTH / 2.0) {
            moveBy(0, y);
            return;
        }

        if (newY < ZambieGame.CANVAS_HEIGHT - ZambieGame.FLOOR_HEIGHT - HITBOX_HEIGHT) {
            moveBy(x, 0);
            return;
        }

        if (newY > ZambieGame.CANVAS_HEIGHT - HITBOX_HEIGHT) {
            moveBy(x, 0);
            return;
        }


        super.moveBy(x, y);

        hitboxCenterPos.add(x, y);
        body.getPosition().add(x, y);


        if (isAttacking) {

            if (!body.equals(attackSwordGif.get())) {
                onStartAttacking();
                changeBody(attackSwordGif.get());
            }

            return;
        }



        if (x < 0) {
            if (!body.equals(leftWalkGif.get())) {
                changeBody(leftWalkGif.get());
            }
        }
        else if (x > 0) {
            if (!body.equals(rightWalkGif.get())) {
                changeBody(rightWalkGif.get());
            }
        }
        else if (y < 0) {
            if (!body.equals(upWalkGif.get())) {
                changeBody(upWalkGif.get());
            }
        }
        else if (y > 0) {
            if (!body.equals(downWalkGif.get())) {
                changeBody(downWalkGif.get());
            }
        }
        else if (body.equals(attackSwordGif.get())) {
            onStopAttacking();
            changeBody(rightWalkGif.get());
        }
    }

    private void onStartAttacking() {

        game.getEntities().stream()
                .filter(it -> it instanceof Zambie)
                .filter(it -> isInRange(((Zambie) it).getCenter()))
                .forEach(Killable::kill);
    }

    private void onStopAttacking() {
    }

    private void changeBody(Element.GifElem newBody) {

        final Element.GifElem oldBody = body;

        newBody.getPosition().setXY(oldBody.getPosition().getX(), oldBody.getPosition().getY());

        /*
        final MutablePos bodyPos = JavaUtils.apply(newBody.getPosition(), it ->
            it.setXY(oldBody.getPosition().getX(), oldBody.getPosition().getY())
        );
        */

        //final Dimension size = newBody.getSize();
        //final Dimension lastSize = body.getSize();

        //bodyPos.add(lastSize.width - size.width, lastSize.height - size.height);

        body = newBody;
        game.getGui().replaceElement(Layer.ENTITY, oldBody, newBody);
    }
}
