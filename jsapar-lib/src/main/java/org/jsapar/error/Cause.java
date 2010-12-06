package org.jsapar.error;

import java.util.Date;

/**
 * An abstract class representing various kinds of causes used in a ErrorSet.
 * 
 * @author JsaPar Developer
 */
public abstract class Cause {
	/**
	 * Indicates the cause of the problem as text.
	 */
	private String cause;
	/**
	 * Indicates where the cause originated from.
	 */
	private String origin; // full path maybe? stack trace?
	/**
	 * Indicates the type of the cause. The cause type is used for filtering
	 * purposes and grouping the different kind of causes.
	 * 
	 * @see org.jsapar.errors.CauseType
	 */
	private CauseType causeType;
	/**
	 * Indicates the date and time when the problem occurred.<br>
	 * This can be used for logging purposes.
	 */
	private Date dateTime;

	/**
	 * Getter for the cause.
	 * 
	 * @return the cause.
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * Setter for the cause.
	 * 
	 * @param cause
	 *            the cause.
	 */
	protected void setCause(String cause) {
		this.cause = cause;
	}

	/**
	 * Getter for the origin.
	 * 
	 * @return the origin.
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Setter for the origin.
	 * 
	 * @param cause
	 *            the origin.
	 */
	protected void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * Getter for the cause type.
	 * 
	 * @return the cause type.
	 */
	public CauseType getCauseType() {
		return causeType;
	}

	/**
	 * Setter for the cause type.
	 * 
	 * @param cause
	 *            the cause type.
	 */
	protected void setCauseType(CauseType causeType) {
		this.causeType = causeType;
	}

	/**
	 * Getter for the dateTime.
	 * 
	 * @return the dateTime.
	 */
	public Date getDateTime() {
		return dateTime;
	}

	/**
	 * Setter for the dateTime.
	 * 
	 * @param cause
	 *            the dateTime.
	 */
	protected void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}