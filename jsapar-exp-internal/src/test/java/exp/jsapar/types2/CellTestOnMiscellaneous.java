package exp.jsapar.types2;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

/**
 * Miscellaneous unit tests for testing Cell objects. A mixture of unit tests
 * that have no particular classification and which can not be accommodated in
 * other cell testing classes.
 * 
 * @author JsaPar Developer
 */
public class CellTestOnMiscellaneous {

  @Test
  public void testCloneDateCell_originalChange() throws CloneNotSupportedException {

          Date value = new Date();
          Date expected = (Date)value.clone();
          Cell cell = new Cell("a date", value);
          Cell clone = (Cell)cell.clone();
          Date cloneDate = clone.getValue();
          cloneDate.setTime(0);
          assertEquals(expected, cell.getValue());
  }
	
}
