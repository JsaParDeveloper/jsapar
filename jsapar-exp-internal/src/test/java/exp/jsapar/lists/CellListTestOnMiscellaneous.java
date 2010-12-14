package exp.jsapar.lists;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * TODO 
 * 
 * @author JsaPar Developer
 */
public class CellListTestOnMiscellaneous {
    
    @Test
    public void containingOfCellsInEmptyCellList() {
        CellList cl = new CellList();
        assertFalse(cl.contains("test"));
    }
}
