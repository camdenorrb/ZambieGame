package me.camdenorrb.zambiegame.utils;

import me.camdenorrb.zambiegame.base.tryblock.TryBlock;
import me.camdenorrb.zambiegame.base.tryblock.TypedTryBlock;


public final class TryUtils {

    private TryUtils() {}


    public static <T> T attemptOrNull(TypedTryBlock<T> block) {
        return attemptOrDefault(null, block);
    }

    public static <T> T attemptOrDefault(T defaultValue, TypedTryBlock<T> block) {
        try {
            return block.attempt();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return defaultValue;
    }


    public static void attemptOrPrintErr(TryBlock block) {
        try {
            block.attempt();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void attemptOrBreak(TryBlock block) {
        try {
            block.attempt();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

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
