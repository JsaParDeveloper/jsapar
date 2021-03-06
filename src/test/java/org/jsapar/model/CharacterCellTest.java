package org.jsapar.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CharacterCellTest {

    @Test
    public void testCompareValueTo() {
        CharacterCell cell1 = new CharacterCell("test-a", 'a');
        CharacterCell cell2 = new CharacterCell("test-b", 'b');
        assertEquals(0, cell1.compareValueTo(cell1));
        assertTrue(cell1.compareValueTo(cell2) < 0);
        assertTrue(cell2.compareValueTo(cell1) > 0);

    }

    @Test
    public void testGetSetCharacterValue() {
        CharacterCell cell = new CharacterCell("test", 'a');
        assertEquals(Character.valueOf('a'), cell.getValue());
    }

    @Test
    public void testGetStringValue() {
        CharacterCell cell = new CharacterCell("test", 'a');
        assertEquals("a", cell.getStringValue());
    }

}