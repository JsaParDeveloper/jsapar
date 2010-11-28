package exp.jsapar.filters;

public class NumericRestriction extends Restriction {

	// TODO set the default lower and upper limits according to the specified type.
	// you can get the limits of the type in the class itself (i guess) 
	
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	// TODO change the long into a typed variable T
	public void exactValue(long value) {
		// sets the exactValue. lower and upper limit are ignored when exact value is set!
	}

	public void lowerLimit(long lower) {
		// find out the upper limit of the type, if not yet set.
	}

	public void upperLimit(long upper) {
		// find out the lower limit of the type, if not yet set.
	}
	
	// Convenience method to set lower and upper at the same time.
	public void range(long lower, long upper) {
		// find out if lower, upper limit are valid for this type.
		// if not throw IllegalArgumentException
	}
}
