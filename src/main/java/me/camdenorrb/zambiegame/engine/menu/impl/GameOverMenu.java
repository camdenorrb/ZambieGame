package me.camdenorrb.zambiegame.engine.menu.impl;

import me.camdenorrb.zambiegame.ZambieGame;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.engine.menu.struct.MenuStruct;
import me.camdenorrb.zambiegame.impl.Size;
import me.camdenorrb.zambiegame.impl.pos.Pos;

import java.awt.*;


/**
 * Represents a Game Over Menu
 */
public class GameOverMenu extends MenuStruct {

    private final ZambieGame game;


    /**
     * Constructs a Game Over Menu
     *
     * @param game The game that is over
     */
    public GameOverMenu(ZambieGame game) {
        super(game.getGui());
        this.game = game;
    }


    /**
     * Handles the building of the Menu
     */
    @Override
    public void onBuild() {

        final double halfGuiWidth  = ZambieGame.CANVAS_WIDTH  / 2.0;
        final double halfGuiHeight = ZambieGame.CANVAS_HEIGHT / 2.0;

        final Pos centerPos = new Pos(halfGuiWidth, halfGuiHeight);

        //centerPos.sub(halfGuiWidth / 2, halfGuiHeight / 2);

        addPart(
            new Element.Rectangle(
                Color.CYAN.darker(),
                new Pos(centerPos.getX() - (halfGuiWidth / 2), centerPos.getY() - (halfGuiHeight / 2)),
                new Size(halfGuiWidth, halfGuiHeight)
            )
        );

        //addPart(new Element.Rectangle(Color.GREEN, textPos, new Size(110, 30)));

        addPart(
            new Element.Text("Game Over", 20, new Pos(centerPos.getX() - 55, centerPos.getY() - 100))
        );

        addPart(
            new Element.Button(
                new Element.Rectangle(
                    Color.GREEN.darker(),
                    new Pos(centerPos.getX() - 50, centerPos.getY() - 25), new Size(100, 50)
                ),
                () -> {
                    game.stop();
                    game.start();
                }
            )
        );

        addPart(
            new Element.Text(
                "Restart",
                20,
                new Pos(centerPos.getX() - 35, centerPos.getY() + 5)
            )
        );

        addPart(
            new Element.Button(
                new Element.Rectangle(
                    Color.RED.darker(),
                    new Pos(centerPos.getX() - 50, centerPos.getY() - 25 + 70),
                    new Size(100, 50)
                ),
                () -> System.exit(0)
            ));

        addPart(
            new Element.Text(
                "Exit",
                20,
                new Pos(centerPos.getX() - 20, centerPos.getY() + 75)
            )
        );
    }

}
