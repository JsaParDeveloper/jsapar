/**
 * 
 */
package org.jsapar.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import org.jsapar.model.BigDecimalCell;
import org.jsapar.model.FloatCell;
import org.jsapar.model.IntegerCell;
import org.jsapar.model.NumberCell;
import org.jsapar.schema.SchemaException;
import org.junit.After;
import org.junit.Assert;
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
     * Test method for {@link NumberCell#setValue(java.lang.String, java.util.Locale)}.
     * @throws ParseException 
     */
    @Test
    public void testSetValueStringLocale() throws ParseException {
        FloatCell cell = new FloatCell("test", 2.7);
        cell.setValue("3.141,59", Locale.GERMANY);
        
        Assert.assertEquals(3141.59, cell.getNumberValue().doubleValue(), 0.001);
    }

    /**
     * Test method for {@link NumberCell#getValue()}.
     */
    @Test
    public void testGetValue() {
        IntegerCell cell = new IntegerCell("test", 42);
        Assert.assertEquals(new Integer(42), cell.getValue());
    }


    /**
     * Test method for {@link NumberCell#setNumberValue(java.lang.Number)}.
     */
    @Test
    public void testSetNumberValue() {
        IntegerCell cell = new IntegerCell("test", 17);
        cell.setNumberValue(new Integer(42));
        Assert.assertEquals(new Integer(42), cell.getNumberValue());
    }

    /**
     * Test method for {@link NumberCell#getNumberValue()}.
     */
    @Test
    public void testGetNumberValue() {
        IntegerCell cell = new IntegerCell("test", 42);
        Assert.assertEquals(new Integer(42), cell.getNumberValue());
    }

    /**
     * Test method for {@link NumberCell#getStringValue(java.text.Format)}.
     */
    @Test
    public void testGetStringValueFormat() {
        IntegerCell cell = new IntegerCell("test", 42);
        String result = cell.getStringValue(new DecimalFormat("0000"));
        Assert.assertEquals("0042", result);
    }

    /**
     * Test method for {@link NumberCell#setValue(java.lang.String, java.text.Format)}.
     * @throws ParseException 
     */
    @Test
    public void testSetValueStringFormat() throws ParseException {
        FloatCell cell = new FloatCell("test", 2.7);
        DecimalFormat format = new DecimalFormat("#,###.##", DecimalFormatSymbols.getInstance(Locale.GERMANY));
        cell.setValue("3.141,59", format);
        
        Assert.assertEquals(3141.59, cell.getNumberValue().doubleValue(), 0.001);
    }

    @Test
    public void testCompareValueTo_gt() throws SchemaException{
        FloatCell left = new FloatCell("test", 10.1);
        IntegerCell right = new IntegerCell("test", 10);
        Assert.assertEquals(true, left.compareValueTo(right) > 0 );
    }
    
    @Test
    public void testCompareValueTo_eq() throws SchemaException{
        NumberCell left = new FloatCell("test", 10.1);
        NumberCell right = new FloatCell("test", 10.1);
        Assert.assertEquals(true, left.compareValueTo(right) == 0 );
    }

    @Test
    public void testCompareValueTo_lt() throws SchemaException{
        NumberCell left = new FloatCell("test", 0.1);
        NumberCell right = new FloatCell("test", 10.1);
        Assert.assertEquals(true, left.compareValueTo(right) < 0 );
    }

    @Test
    public void testCompareValueTo_lt_big() throws SchemaException{
        NumberCell left = new FloatCell("test", 10.1);
        NumberCell right = new BigDecimalCell("test", new BigDecimal(1000011010100.1321));
        Assert.assertEquals(true, left.compareValueTo(right) < 0 );
    }
    
}
