package org.example.layered.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    @Test
    void testCartItemConstructorWithValidParameters() {
        CartItem cartItem = new CartItem(1L, 1L, 1);
        assertNotNull(cartItem);

        assertEquals(1, cartItem.getQuantity());
    }

    @Test
    void testCartItemConstructorWithInvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> new CartItem(null, 1L, 1));
        assertThrows(IllegalArgumentException.class, () -> new CartItem(1L, null, 1));
        assertThrows(IllegalArgumentException.class, () -> new CartItem(1L, 1L, 0));
    }

    @Test
    void testHasSameProductOptionWithMatchingIds() {
        CartItem cartItem = new CartItem(1L, 1L, 1);
        assertTrue(cartItem.hasSameProductOption(1L, 1L));
    }

    @Test
    void testHasSameProductOptionWithNonMatchingIds() {
        CartItem cartItem = new CartItem(1L, 1L, 1);
        assertFalse(cartItem.hasSameProductOption(2L, 2L));
    }

    @Test
    void testAddQuantityWithPositiveQuantity() {
        CartItem cartItem = new CartItem(1L, 1L, 1);
        cartItem.addQuantity(1);
        assertEquals(2, cartItem.getQuantity());
    }

    @Test
    void testAddQuantityWithNonPositiveQuantity() {
        CartItem cartItem = new CartItem(1L, 1L, 1);
        assertThrows(IllegalArgumentException.class, () -> cartItem.addQuantity(0));
    }
}
