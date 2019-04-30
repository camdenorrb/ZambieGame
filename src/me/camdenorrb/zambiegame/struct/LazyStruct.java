package me.camdenorrb.zambiegame.struct;

import com.sun.istack.internal.NotNull;


/**
 * A strict structure for lazy values
 *
 * @param <T> The type of lazy value
 */
public abstract class LazyStruct<T> {

    private T value;


    /**
     * Handles getting the value
     *
     * @return The handled value
     */
    @NotNull
    protected abstract T onGet();


    /**
     * Submits the value
     *
     * @return The instance of a value
     */
    public final T get() {

        if (value == null) {
            value = onGet();
        }

        return value;
    }

}