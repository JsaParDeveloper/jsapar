package org.jsapar.error;

/**
 * Defines the type of the cause that occurred. This can be used by the library
 * user to filter different kind of causes when processing the ErrorSet.<br>
 * When the library user is only interested in parsing causes, the user filters
 * the available causes by selecting a causeType of cause.PARSING;
 * 
 * @author JsaPar Developer
 * 
 * @see org.jsapar.error.ErrorSet
 */
public enum CauseType {
	// FIXME find good definition names for the possible causes.
	PARSING, CONVERTING, COMPOSING, SPLITTING, CELL, LINE, DOC;
}
