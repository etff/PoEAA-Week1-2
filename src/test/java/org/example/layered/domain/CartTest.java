package org.example.layered.domain;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void addLineItem_cartIsEmpty() {
        // Arrange
        Cart cart = new Cart();
        LineItem lineItem = new LineItem(1L, 1L, 1);

        // Act
        cart.addLineItem(lineItem);

        // Assert
        assertEquals(1, cart.getCartItems().size());
        assertEquals(lineItem, cart.getCartItems().get(0));
    }

    @Test
    void addLineItem_cartHasSameProductOption() {
        Cart cart = new Cart();
        LineItem lineItem1 = new LineItem(1L, 1L, 1);
        LineItem lineItem2 = new LineItem(1L, 1L, 2);

        cart.addLineItem(lineItem1);
        cart.addLineItem(lineItem2);

        assertEquals(1, cart.getCartItems().size());
        assertEquals(3, cart.getCartItems().get(0).getQuantity());
    }
}
