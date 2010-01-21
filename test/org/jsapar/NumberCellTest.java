/**
 * 
 */
package org.jsapar;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author stejon0
 *
 */
public class NumberCellTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link org.jsapar.NumberCell#setValue(java.lang.String, java.util.Locale)}.
     * @throws ParseException 
     */
    @Test
    public void testSetValueStringLocale() throws ParseException {
        FloatCell cell = new FloatCell();
        cell.setValue("3.141,59", Locale.GERMANY);
        
        Assert.assertEquals(3141.59, cell.getNumberValue().doubleValue(), 0.001);
    }

    /**
     * Test method for {@link org.jsapar.NumberCell#getValue()}.
     */
    @Test
    public void testGetValue() {
        IntegerCell cell = new IntegerCell(42);
        Assert.assertEquals(new Integer(42), cell.getValue());
    }


    /**
     * Test method for {@link org.jsapar.NumberCell#setNumberValue(java.lang.Number)}.
     */
    @Test
    public void testSetNumberValue() {
        IntegerCell cell = new IntegerCell();
        cell.setNumberValue(new Integer(42));
        Assert.assertEquals(new Integer(42), cell.getNumberValue());
    }

    /**
     * Test method for {@link org.jsapar.NumberCell#getNumberValue()}.
     */
    @Test
    public void testGetNumberValue() {
        IntegerCell cell = new IntegerCell(42);
        Assert.assertEquals(new Integer(42), cell.getNumberValue());
    }

    /**
     * Test method for {@link org.jsapar.NumberCell#getStringValue(java.text.Format)}.
     */
    @Test
    public void testGetStringValueFormat() {
        IntegerCell cell = new IntegerCell(42);
        String result = cell.getStringValue(new DecimalFormat("0000"));
        Assert.assertEquals("0042", result);
    }

    /**
     * Test method for {@link org.jsapar.NumberCell#setValue(java.lang.String, java.text.Format)}.
     * @throws ParseException 
     */
    @Test
    public void testSetValueStringFormat() throws ParseException {
        FloatCell cell = new FloatCell();
        DecimalFormat format = new DecimalFormat("#,###.##", DecimalFormatSymbols.getInstance(Locale.GERMANY));
        cell.setValue("3.141,59", format);
        
        Assert.assertEquals(3141.59, cell.getNumberValue().doubleValue(), 0.001);
    }

}