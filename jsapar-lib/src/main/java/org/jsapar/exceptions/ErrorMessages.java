package org.jsapar.exceptions;

import java.awt.Dimension;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class ErrorMessages {
    // TODO define Resource(s) in this specific class for the English language.
    private ResourceBundle rb = new JsaParErrorMessages();

    // ChoiceFormat
    // MessageFormat
    
    private Object[][] obj = {
            // LOCALIZE THIS
            {"s1", "The disk \"{1}\" contains {0}."},  // MessageFormat pattern
            {"s2", "1"},                               // location of {0} in pattern
            {"s3", "My Disk"},                         // sample disk name
            {"s4", "no files"},                        // first ChoiceFormat choice
            {"s5", "one file"},                        // second ChoiceFormat choice
            {"s6", "{0,number} files"},                // third ChoiceFormat choice
            {"s7", "3 Mar 96"},                        // sample date
            {"s8", new Dimension(1,5)}                 // real object, not just string
        // END OF MATERIAL TO LOCALIZE
        };
    
    
    
    private class JsaParErrorMessages extends ListResourceBundle {
        protected Object[][] getContents() {
            return obj;
        }
    }
}
