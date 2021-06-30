package com.iogogogo.supervisord.exception;

/**
 * The exception is thrown whenever the remote procedure call fails in some point.
 *
 * @author Tim Roes {#link https://github.com/gturri/aXMLRPC/blob/master/src/main/java/de/timroes/axmlrpc/XMLRPCException.java}
 */
public class XMLRPCException extends RuntimeException {

    /**
     * Instantiates a new Xmlrpc exception.
     */
    public XMLRPCException() {
        super();
    }

    /**
     * Instantiates a new Xmlrpc exception.
     *
     * @param ex the ex
     */
    public XMLRPCException(Exception ex) {
        super(ex);
    }

    /**
     * Instantiates a new Xmlrpc exception.
     *
     * @param ex the ex
     */
    public XMLRPCException(String ex) {
        super(ex);
    }

    /**
     * Instantiates a new Xmlrpc exception.
     *
     * @param msg the msg
     * @param ex  the ex
     */
    public XMLRPCException(String msg, Exception ex) {
        super(msg, ex);
    }

}