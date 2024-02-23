package org.example.layered.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineItemTest {

    @Test
    void testCartItemConstructorWithValidParameters() {
        LineItem lineItem = new LineItem(1L, 1L, 1);
        assertNotNull(lineItem);

        assertEquals(1, lineItem.getQuantity());
    }

    @Test
    void testCartItemConstructorWithInvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> new LineItem(null, 1L, 1));
        assertThrows(IllegalArgumentException.class, () -> new LineItem(1L, null, 1));
        assertThrows(IllegalArgumentException.class, () -> new LineItem(1L, 1L, 0));
    }

    @Test
    void testHasSameProductOptionWithMatchingIds() {
        LineItem lineItem = new LineItem(1L, 1L, 1);
        assertTrue(lineItem.hasSameProductOption(1L, 1L));
    }

    @Test
    void testHasSameProductOptionWithNonMatchingIds() {
        LineItem lineItem = new LineItem(1L, 1L, 1);
        assertFalse(lineItem.hasSameProductOption(2L, 2L));
    }

    @Test
    void testAddQuantityWithPositiveQuantity() {
        LineItem lineItem = new LineItem(1L, 1L, 1);
        lineItem.addQuantity(1);
        assertEquals(2, lineItem.getQuantity());
    }

    @Test
    void testAddQuantityWithNonPositiveQuantity() {
        LineItem lineItem = new LineItem(1L, 1L, 1);
        assertThrows(IllegalArgumentException.class, () -> lineItem.addQuantity(0));
    }
}
