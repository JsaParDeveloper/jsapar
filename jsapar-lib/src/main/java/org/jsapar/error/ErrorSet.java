package org.jsapar.error;

import java.util.List;

// This is the user-level error message. Not to be compared with the exception.
/**
 * Interface for retrieving the error set after one or more error(s) has/have occurred.
 * 
 * Returns the error set in which the cause or causes of the error is/are explained. Before this
 * method is called by the user, a valid ErrorSet must have been constructed.
 * 
 */
public interface ErrorSet {

    /**
     * Retrieves the error message(s) associated with the tool that is being used.
     * 
     * @return the error set explaining the error or the exception that was thrown.
     */
    public List<String> getErrors();

    /**
     * Explains in normal non-technical language the error message associated with the tool that is
     * being used.
     */
    public String explain();
}
