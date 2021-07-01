package com.iogogogo.supervisord.tuple;

import java.util.Map;
import java.util.Objects;

/**
 * Created by tao.zeng on 2021/6/30.
 */
public interface Tuple {


    /**
     * Creates a {@code Tuple2} from a {@link Map.Entry}.
     *
     * @param <T1>  Type of first component (entry key)
     * @param <T2>  Type of second component (entry value)
     * @param entry A {@link java.util.Map.Entry}
     * @return a new {@code Tuple2} containing key and value of the given {@code entry}
     */
    static <T1, T2> Tuple2<T1, T2> fromEntry(Map.Entry<? extends T1, ? extends T2> entry) {
        Objects.requireNonNull(entry, "entry is null");
        return new Tuple2<>(entry.getKey(), entry.getValue());
    }

    /**
     * Creates a tuple of two elements.
     *
     * @param <T1> type of the 1st element
     * @param <T2> type of the 2nd element
     * @param t1   the 1st element
     * @param t2   the 2nd element
     * @return a tuple of two elements.
     */
    static <T1, T2> Tuple2<T1, T2> of(T1 t1, T2 t2) {
        return new Tuple2<>(t1, t2);
    }

    /**
     * Return the order-dependent hash of the one given value.
     *
     * @param o1 the 1st value to hash
     * @return the same result as {@link Objects#hashCode(Object)}
     */
    static int hash(Object o1) {
        return Objects.hashCode(o1);
    }

    /**
     * Return the order-dependent hash of the two given values.
     *
     * @param o1 the 1st value to hash
     * @param o2 the 2nd value to hash
     * @return the same result as {@link Objects#hash(Object...)}
     */
    static int hash(Object o1, Object o2) {
        int result = 1;
        result = 31 * result + hash(o1);
        result = 31 * result + hash(o2);
        return result;
    }
}
