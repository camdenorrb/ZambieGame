package me.camdenorrb.zambiegame.base.tryblock;

/**
 * Represents a try block
 */
@FunctionalInterface
public interface TryBlock {

    /**
     * Attempts to run the following method
     *
     * @throws Exception In order to be catched elsewhere
     */
    void attempt() throws Exception;

}
