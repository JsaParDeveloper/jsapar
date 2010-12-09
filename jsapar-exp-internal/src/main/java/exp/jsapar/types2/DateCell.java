package exp.jsapar.types2;

import java.util.Date;

import exp.jsapar.utils.DateUtil;

public class DateCell extends Cell {

    /**
     * 
     */
    private static final long serialVersionUID = 7884009195841697755L;

    protected <T> DateCell(String name, T value) {
        super(name, value);
    }

    /**
     * Returns the textual representation of a exp.jsapar.types2.Cell.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String valueAsString() {
        Date dateValue = (Date) getValue();
        return DateUtil.formattedDateAsString(dateValue);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Cell clone = (Cell) super.clone();
        clone.setValue(((Date) this.getValue()).clone());
        return clone;
    }

}
