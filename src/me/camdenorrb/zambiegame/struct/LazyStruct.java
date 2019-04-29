package me.camdenorrb.zambiegame.struct;

import com.sun.istack.internal.NotNull;

public abstract class LazyStruct<T> {

    private T value;


    @NotNull
    protected abstract T onGet();


    public final T get() {

        if (value == null) {
            value = onGet();
        }

        return value;
    }

}