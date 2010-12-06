package org.jsapar.error;

import java.util.List;

/**
 * The implementation class for the ErrorSet. The errors that occurred during the execution of the
 * library tool(s) are stored in this error set and can be retrieved by the client code.<br>
 * <br>
 * Note: This class is not part of the JsaPar API and should remain package-private.
 * 
 * @version $Revision: 1.0 $
 * @author JsaPar Developer
 */
class ErrorSetImpl implements ErrorSet {
    private List<Cause> causes;

    /**
     * Package-private constructor prevents instantiation from external classes.
     * 
     */
    ErrorSetImpl() {
    }

    // TODO the ErrorSet should be added to the exception that holds the information of the current process
    // in which the errors occurred. For example: when the library parses a file and some error occurs, then
    // the errorset should be created and added to the ParseException exception class, and thrown.
    
    public String getProblem() {
        // TODO "Parser could not finish due to the following causes: "

        return null;
    }

    // setProblem does not exits...

    public List<Cause> getCauses() {
        // FIXME return unmodifiable list!
        return causes;
    }

    @Override
    public List<String> getErrors() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String explain() {
        // TODO Auto-generated method stub
        return null;
    }

    // in this class add the extra methods that may not be seen by the client code.
    // define or implement methods

    public void saveError() {

    }

    public String loadError() {
        return null;

    }
    
    @Override
    public String toString() {
        return "todo"; // TODO
    }
    
    // TODO look up the messages that can be given within the jsapar code from a predefined message-store
    // sort of string array that is defined as a String[] -> Now we can refer to a number instead of 
    // a lookup key value. Point your code to the correct location by typing: getErrorSetMsg(4);
    // This should only be done with internal code. It is faster than a map!
    
    
}
