package org.jsapar;

import java.util.UUID;

import org.jsapar.error.ErrorSet;

/**
 * Base class for the JsaPar tools: Parser, Converter, Composer and Splitter.<br>
 * <br>
 * Note: This class is not part of the JsaPar API and should remain package-private.
 * 
 * @author JsaPar Developer
 */
abstract class Tool {
    /**
     * The unique identification of the tool instance that is being used. This id is used for
     * logging purposes!
     */
    protected final UUID id = UUID.randomUUID();
    /**
     * A reference to the configuration of the jsapar library.
     */
    protected ConfigRef cfg = null;

    /**
     * The error set for situations when something went wrong.
     */
    protected ErrorSet errorSet = null;

    /**
     * Indicates if the setup method successfully finished.
     */
    protected boolean setupCompleted = false;

    // TODO read the configuration class -> get the total of numbers to ignore before error is
    // thrown

    /**
     * Package-private constructor prevents instantiation from external classes.
     */
    Tool() {
        // Intentional left blank.
    }

    @Override
    public String toString() {
        return id.toString();
    }
}