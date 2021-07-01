package com.iogogogo.supervisord.tuple;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by tao.zeng on 2021/6/30.
 */
public class Tuple2<T1, T2> implements Tuple, Comparable<Tuple2<T1, T2>>, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The 1st element of this tuple.
     */
    public final T1 _1;

    /**
     * The 2nd element of this tuple.
     */
    public final T2 _2;

    /**
     * Constructs a tuple of two elements.
     *
     * @param t1 the 1st element
     * @param t2 the 2nd element
     */
    public Tuple2(T1 t1, T2 t2) {
        this._1 = t1;
        this._2 = t2;
    }


    @SuppressWarnings("unchecked")
    private static <U1 extends Comparable<? super U1>, U2 extends Comparable<? super U2>> int compareTo(Tuple2<?, ?> o1, Tuple2<?, ?> o2) {
        final Tuple2<U1, U2> t1 = (Tuple2<U1, U2>) o1;
        final Tuple2<U1, U2> t2 = (Tuple2<U1, U2>) o2;

        final int check1 = t1._1.compareTo(t2._1);
        if (check1 != 0) {
            return check1;
        }

        return t1._2.compareTo(t2._2);

        // all components are equal
    }

    @Override
    public int compareTo(Tuple2<T1, T2> that) {
        return Tuple2.compareTo(this, that);
    }

    /**
     * Getter of the 1st element of this tuple.
     *
     * @return the 1st element of this Tuple.
     */
    public T1 _1() {
        return _1;
    }

    /**
     * Sets the 1st element of this tuple to the given {@code value}.
     *
     * @param value the new value
     * @return a copy of this tuple with a new value for the 1st element of this Tuple.
     */
    public Tuple2<T1, T2> update1(T1 value) {
        return new Tuple2<>(value, _2);
    }

    /**
     * Getter of the 2nd element of this tuple.
     *
     * @return the 2nd element of this Tuple.
     */
    public T2 _2() {
        return _2;
    }

    /**
     * Sets the 2nd element of this tuple to the given {@code value}.
     *
     * @param value the new value
     * @return a copy of this tuple with a new value for the 2nd element of this Tuple.
     */
    public Tuple2<T1, T2> update2(T2 value) {
        return new Tuple2<>(_1, value);
    }

    /**
     * Swaps the elements of this {@code Tuple}.
     *
     * @return A new Tuple where the first element is the second element of this Tuple
     * and the second element is the first element of this Tuple.
     */
    public Tuple2<T2, T1> swap() {
        return Tuple.of(_2, _1);
    }

    /**
     * Converts the tuple to java.util.Map.Entry {@code Tuple}.
     *
     * @return A  java.util.Map.Entry where the first element is the key and the second
     * element is the value.
     */
    public Map.Entry<T1, T2> toEntry() {
        return new AbstractMap.SimpleEntry<>(_1, _2);
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Tuple2)) {
            return false;
        } else {
            final Tuple2<?, ?> that = (Tuple2<?, ?>) o;
            return Objects.equals(this._1, that._1)
                    && Objects.equals(this._2, that._2);
        }
    }

    @Override
    public int hashCode() {
        return Tuple.hash(_1, _2);
    }

    @Override
    public String toString() {
        return "(" + _1 + ", " + _2 + ")";
    }

}
