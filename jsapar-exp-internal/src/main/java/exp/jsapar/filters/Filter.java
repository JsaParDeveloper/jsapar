package exp.jsapar.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Block / Pass
// Accept / Reject
// Reveal / Conceal
// Show / Hide
// Absorb / Reflect
// Attract / Repel

/**
 * Defines the basic functions of a filter. A filter reveals or conceals data
 * with a filtering pattern.
 * 
 * @author JsaPar Developer
 */
public abstract class Filter {
	/**
	 * The logger for this class.
	 */
	private static final Logger log = LoggerFactory.getLogger(Filter.class);

	/**
	 * The logical operator indicating if the filter is switched on/off.<br>
	 * False means: the filter is off. "Off" is set as the default.
	 */
	private boolean filterActive = false;

	/**
	 * The logical operator indicating if the filter should reveal or conceal
	 * the information that is given.<br>
	 * 
	 * False means: the information is revealed. Mnemonic: keep information to
	 * yourself = no -> false == revealed. "Conceal" is set as the default.
	 */
	private boolean filterMode = true;

	/**
	 * Sets the filter into "reveal" filter mode, meaning: the information is
	 * passed through this filter.
	 * 
	 * @deprecated
	 */
	@Deprecated
	public void reveal() {
		filterMode = false;
		log.debug("Filter is set into reveal mode.");
	}

	/**
	 * Sets the filter into "conceal" filter mode, meaning: the information is
	 * blocked by this filter.
	 * 
	 * @deprecated
	 */
	@Deprecated
	public void conceal() {
		filterMode = true;
		log.debug("Filter is set into conceal mode.");
	}

	/**
	 * TODO BIG todo here: we need to find out if the filter matches for the
	 * current element in the list. This matching is based on the reveal/conceal
	 * idea, but with one difference: the reveal and conceal idea is dropped
	 * which means that the element is filtered based on a combination of
	 * matches() and inverse().<br>
	 * matches() returns true when restriction is met.
	 * matches()+inverse()==true, returns false when restriction is met.
	 * 
	 * 
	 * @return
	 */
	public boolean matches() {
		return false;
	}

	// inverses the filter: reveal->conceal, conceal->reveal
	public void inverse() {
		// TODO
	}

	public boolean isInversed() {
		// TODO
		return false;
	}
	
	/**
	 * Checks if the filter is set to reveal or to conceal.
	 * 
	 * @return {@code true} when revealed or {@code false} when concealed.
	 */
	public boolean isRevealed() {
		return filterMode;
	}

	/**
	 * Switches the filter on.
	 */
	public void on() {
		filterActive = true;
		log.debug("Filter is switched on.");
	}

	/**
	 * Switches the filter off.
	 */
	public void off() {
		filterActive = false;
		log.debug("Filter is switched off.");
	}

	/**
	 * Checks if the filter is switched on.
	 * 
	 * @return {@code true} when filter on, {@code false} when filter off.
	 */
	public boolean isOn() {
		return filterActive;
	}

	public void addRestriction(Restriction restriction) {
		// don't make methods like this, just add a restriction based on the
		// given type in the filter.
		// what i mean is: filter.addRestriction() <--- don't do this!
		// do this: create a filter with a type: Filter<Integer>, the moment the
		// filter type is set,
		// get to know at runtime what type it is and lookup the restriction
		// class that matches the type.
		// like: Integer -> NumericRestriction. This sets the correct
		// restriction class right away.
		// we now can use the restriction class to add the actual restrictions.
		// Is this true?
		// how do we set the restriction class when different method
		// combinations are possible?

	}

	public void removeRestriction() {
		// should we add more than one restriction?
	}

	public boolean hasRestriction() {
		// TODO
		return false;
	}

	public Restriction getRestriction() {
		// TODO
		return null;
	}

	public Object getName() {
		// TODO Auto-generated method stub
		return null;
	}

	// -------------------------------------------------------------------------

}