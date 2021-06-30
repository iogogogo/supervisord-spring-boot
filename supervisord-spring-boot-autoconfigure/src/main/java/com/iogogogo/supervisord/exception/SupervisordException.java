package com.iogogogo.supervisord.exception;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tao.zeng on 2021/06/11.
 */
public abstract class SupervisordException extends Exception {

    private Code code;

    /**
     * Instantiates a new Supervisord exception.
     *
     * @param code the code
     */
    public SupervisordException(Code code) {
        this.code = code;
    }

    /**
     * Create supervisord exception.
     *
     * @param code the code
     * @return the supervisord exception
     */
    public static SupervisordException create(Code code) {

        switch (code) {
            case UNKNOWN_METHOD:
                return new UnknownMethodException();
            case ALREADY_STARTED:
                return new AlreadyStartedException();
            case NOT_RUNNING:
                return new NotRunningException();
            default:
                throw new IllegalArgumentException("Invalid exception code");
        }
    }

    /**
     * The type Unknown method exception.
     */
    public static class UnknownMethodException extends SupervisordException {
        /**
         * Instantiates a new Unknown method exception.
         */
        public UnknownMethodException() {
            super(Code.UNKNOWN_METHOD);
        }
    }

    /**
     * The type Already started exception.
     */
    public static class AlreadyStartedException extends SupervisordException {
        /**
         * Instantiates a new Already started exception.
         */
        public AlreadyStartedException() {
            super(Code.ALREADY_STARTED);
        }
    }

    /**
     * The type Not running exception.
     */
    public static class NotRunningException extends SupervisordException {
        /**
         * Instantiates a new Not running exception.
         */
        public NotRunningException() {
            super(Code.NOT_RUNNING);
        }
    }

    /**
     * The enum Code.
     */
    public enum Code {

        /**
         * Returned on a start request for a process that is already started
         */
        UNKNOWN_METHOD(1),
        /**
         * Already started code.
         */
        ALREADY_STARTED(60),
        /**
         * Not running code.
         */
        NOT_RUNNING(70);

        private static final Map<Integer, Code> lookup = new HashMap<>();

        static {
            for (Code c : EnumSet.allOf(Code.class))
                lookup.put(c.code, c);
        }

        private final int code;

        Code(int code) {
            this.code = code;
        }

        /**
         * Get the int value for a particular Code.
         *
         * @return error code as integer
         */
        public int intValue() {
            return code;
        }

        /**
         * Get the Code value for a particular integer error code
         *
         * @param code int error code
         * @return Code value corresponding to specified int code, or null
         */
        public static Code get(int code) {
            return lookup.get(code);
        }
    }

}