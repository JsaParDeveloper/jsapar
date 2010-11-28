package exp.jsapar.lists;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * TODO
 * 
 * @author JsaPar Developer
 */
public class CellListTestOnInstantiations {

	/**
	 * Tests the instantiation of an empty cell list.
	 */
	@Test
	public void instantiateEmptyCellList() {
		CellList cl = new CellList();

		assertTrue(cl.isEmpty());
	}
}
