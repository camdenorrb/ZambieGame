package me.camdenorrb.zambiegame.type;

import me.camdenorrb.zambiegame.impl.pos.Pos;


/**
 * A type that represents ranged elements
 */
public interface Ranged {

    /**
     * Checks if this is in range with a position
     *
     * @param pos The position to check
     *
     * @return If this is in range with the position
     */
    boolean isInRange(Pos pos);

}
