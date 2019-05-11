package me.camdenorrb.zambiegame.base.tryblock;

@FunctionalInterface
public interface TryBlock {

    void attempt() throws Exception;

}
