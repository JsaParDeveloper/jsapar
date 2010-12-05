package org.jsapar.exceptions;

import org.jsapar.error.ErrorHandling;
import org.jsapar.error.ErrorSet;

/**
 * The base {@link java.lang.Throwable} type for the JsaPar library.
 * 
 * @author JsaPar Developer
 */
public class JsaParException extends Exception implements ErrorHandling {

    /**
     * The Serial version ID for this class.
     */
    private static final long serialVersionUID = 9213627616255108950L;

    /**
     * Constructs a new JsaPar exception with the specified cause and a detail message of
     * <tt>(cause==null ? null : cause.toString())</tt> (which typically contains the class and
     * detail message of <tt>cause</tt>).
     * 
     * @param cause
     *            the cause (which is saved for later retrieval by the {@link #getCause()} method).
     *            (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent
     *            or unknown.)
     */
    public JsaParException(Throwable cause) {
        // TO BE REMOVED???
        super(cause);
    }

    /**
     * Constructs a new JsaPar exception with the specified detail message and cause.
     * <p>
     * Note that the detail message associated with <code>cause</code> is <i>not</i> automatically
     * incorporated in this exception's detail message.
     * 
     * @param message
     *            the detail message (which is saved for later retrieval by the
     *            {@link #getMessage()} method).
     * @param cause
     *            the cause (which is saved for later retrieval by the {@link #getCause()} method).
     *            (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent
     *            or unknown.)
     */
    public JsaParException(String message, Throwable cause) {
        // TO BE REMOVED???
        super(message, cause);
    }

    /**
     * Constructs a new JsaPar exception with the specified detail message. The cause is not
     * initialized, and may subsequently be initialized by a call to {@link #initCause}.
     * 
     * @param message
     *            the detail message. The detail message is saved for later retrieval by the
     *            {@link #getMessage()} method.
     */
    public JsaParException(String message) {
        // TO BE REMOVED???
        super(message);
    }
    
    
    protected JsaParException(ErrorSet errorSet) {
        super(errorSet.explain());
        // TODO errorSet.getErrors();
    }
    
    

    // ------------------------------------------------------------------------

    @Override
    public String toString() {
        // TODO
        return "classname, detail message"; // + the errorset causes as string
    }

    // ------------------------------------------------------------------------

    @Override
    public ErrorSet getErrorSet() {
        // TODO Auto-generated method stub

        return null;
    }

    protected void setErrorSet(ErrorSet errorSet) {
        // TODO

    }
    
    // ------------------------------------------------------------------------
}