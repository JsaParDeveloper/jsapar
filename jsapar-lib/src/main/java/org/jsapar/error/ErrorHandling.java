package org.jsapar.error;

/**
 * Defines the error handling mechanism for the JsaPar tools. This interface is implemented by the
 * Parser, Converter, Composer and Splitter tools to allow the user to be informed about the cause
 * of the error(s).
 * 
 * @author JsaPar Developer
 */
public interface ErrorHandling {

    /**
     * Retrieves the error message(s) associated with the tool that is being used.
     * 
     * @return the error set explaining the error(s).
     */
    public ErrorSet getErrorSet();
}