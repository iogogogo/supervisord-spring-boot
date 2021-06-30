package com.iogogogo.supervisord.base;

import org.springframework.http.HttpStatus;

/**
 * Created by tao.zeng on 2021/6/25.
 */
public interface BaseResult {

    /**
     * Result response wrapper.
     *
     * @param <T>     the type parameter
     * @param code    the code
     * @param message the message
     * @param data    the data
     * @return the response wrapper
     */
    default <T> ResponseWrapper<T> result(int code, String message, T data) {
        return ResponseWrapper.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }


    /**
     * Result response wrapper.
     *
     * @param <T>        the type parameter
     * @param httpStatus the http status
     * @param data       the data
     * @return the response wrapper
     */
    default <T> ResponseWrapper<T> result(HttpStatus httpStatus, T data) {
        return result(httpStatus.value(), httpStatus.name(), data);
    }

    /**
     * Ok response wrapper.
     *
     * @param <T>  the type parameter
     * @param data the data
     * @return the response wrapper
     */
    default <T> ResponseWrapper<T> ok(T data) {
        return result(HttpStatus.OK, data);
    }

    /**
     * Ok response wrapper.
     *
     * @param <T> the type parameter
     * @return the response wrapper
     */
    default <T> ResponseWrapper<T> ok() {
        return result(HttpStatus.OK, null);
    }
}
