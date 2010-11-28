package exp.jsapar.filters;

import java.util.Date;

public class DateRestriction extends Restriction {
	// As of JDK 1.1, the Calendar class should be used to convert between dates
	// and time fields and the DateFormat class should be used to format and
	// parse date strings. The corresponding methods in Date are deprecated.

	private Date begin;
	private Date end;

	@Override
	public boolean validate() {
		// Date diff = end - begin;
		return false;
	}

	public void beginDate(Date begin) {
		// check if begin < end
		// if end date not set (yet) -> end = current date.
		this.begin = begin;
	}

	public void endDate(Date end) {
		// check if end > begin
		// if begin date not set (yet) -> begin = date since 1970 ???. there is some default begin time.
		this.end = end;
	}
}
