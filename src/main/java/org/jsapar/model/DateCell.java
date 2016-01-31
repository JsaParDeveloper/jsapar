package org.jsapar.model;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.jsapar.schema.SchemaException;

/**
 * Class containging the cell dateValue as a string representation. Each line contains a list of
 * cells.
 * 
 * @author Jonas
 * 
 */
public class DateCell extends Cell implements Comparable<DateCell> {
    private final static java.text.DateFormat xmlDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    /**
     * 
     */
    private static final long serialVersionUID = -4950587241666521775L;
    /**
     * The string representation of the dateValue of this cell.
     */
    private Date dateValue;

    public DateCell(String sName, Date value) {
        super(sName, CellType.DATE);
        this.dateValue = value;
    }

    public DateCell(String name, String value, Format format) throws ParseException {
        super(name, CellType.DATE);
        setValue(value, format);
    }

    public DateCell(String name, String value, Locale locale) throws ParseException {
        super(name, CellType.DATE);
        setValue(value, locale);
    }

    /**
     * @return the dateValue
     */
    @Override
    public Object getValue() {
        return this.dateValue;
    }

    /**
     * @param value
     *            the decimal value to set
     */
    public void setDateValue(Date value) {
        this.dateValue = value;
    }

    /**
     * @return The string dateValue of this cell.
     */
    public Date getDateValue() {
        return this.dateValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jsapar.model.Cell#getStringValue(java.text.Format)
     */
    @Override
    public String getStringValue(Format format) throws IllegalArgumentException {
        if(this.dateValue == null)
            return null;
        if (format != null)
            return format.format(this.dateValue);
        else
            return this.dateValue.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jsapar.model.Cell#setValue(java.lang.String, java.text.Format)
     */
    @Override
    public void setValue(String value, Format format) throws ParseException {
        this.dateValue = (Date) format.parseObject(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jsapar.model.Cell#getXmlValue()
     */
    @Override
    public String getXmlValue() {
        String sTime = xmlDateFormat.format(this.dateValue);
        return sTime.substring(0, sTime.length() - 2) + ":" + sTime.substring(sTime.length() - 2, sTime.length());
    }

    @Override
    public int compareTo(DateCell right) {
        return this.getDateValue().compareTo(right.getDateValue());
    }

    @Override
    public void setValue(String value, Locale locale) throws ParseException {
        this.dateValue = DateFormat.getDateInstance(DateFormat.SHORT, locale).parse(value);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public DateCell clone() {
        DateCell clone = (DateCell)super.clone();
        clone.dateValue = (Date)this.dateValue.clone();
        return clone;
    }

    /* (non-Javadoc)
     * @see org.jsapar.model.Cell#compareValueTo(org.jsapar.model.Cell)
     */
    @Override
    public int compareValueTo(Cell right) throws SchemaException {
        if(right instanceof DateCell){
            Date dateRight = ((DateCell)right).getDateValue();
            return getDateValue().compareTo(dateRight);
        }
        else{
            throw new SchemaException("Value of cell of type " + getCellType() + " can not be compared to value of cell of type " + right.getCellType());
        }
    }

    
}
