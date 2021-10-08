package me.camdenorrb.zambiegame.utils;

import me.camdenorrb.zambiegame.base.tryblock.TryBlock;
import me.camdenorrb.zambiegame.base.tryblock.TypedTryBlock;


/**
 * Utilities for making TryBlocks clean
 */
public final class TryUtils {

    /**
     * Constructs TryUtils
     */
    private TryUtils() {}


    /**
     * Attempts to do an action or returns null if failed
     *
     * @param block The block to run
     * @param <T> The type to return
     *
     * @return The returned value
     */
    public static <T> T attemptOrNull(TypedTryBlock<T> block) {
        return attemptOrDefault(null, block);
    }

    /**
     * Attempts to do an action or returns default if failed
     *
     * @param defaultValue The default value
     * @param block The block to run
     * @param <T> The type to return
     *
     * @return The returned value
     */
    public static <T> T attemptOrDefault(T defaultValue, TypedTryBlock<T> block) {
        try {
            return block.attempt();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return defaultValue;
    }


    /**
     * Attempts to do an action or prints if failed
     *
     * @param block The block to run
     */
    public static void attemptOrPrintErr(TryBlock block) {
        try {
            block.attempt();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Attempts to do an action or breaks if failed
     *
     * @param block The block to run
     */
    public static void attemptOrBreak(TryBlock block) {
        try {
            block.attempt();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    /**
     * Attempts to do an action and return or breaks if failed
     *
     * @param block The block to run
     * @param <T> The type to return
     */
    public static <T> T attemptOrBreak(TypedTryBlock<T> block) {

        try {
            return block.attempt();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.exit(1);

        return null;
    }

}
