package me.camdenorrb.zambiegame.base.tryblock;

@FunctionalInterface
public interface TypedTryBlock<T> {

	T attempt() throws Exception;

}