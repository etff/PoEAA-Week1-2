package org.example.layered.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartTest {

    @Test
    void addLineItem_cartIsEmpty() {
        // Arrange
        Cart cart = new Cart();
        Product product1 = new Product(1L, "product1", new Option(1L));
        LineItem lineItem = new LineItem(product1, 1L, 1);

        // Act
        cart.addProduct(lineItem);

        // Assert
        assertEquals(1, cart.getCartItems().size());
        assertEquals(lineItem, cart.getCartItems().get(0));
    }

    @Test
    void addLineItem_cartHasSameProductOption() {
        // Arrange
        Cart cart = new Cart();
        Product product1 = new Product(1L, "product1", new Option(1L));
        Product product2 = new Product(1L, "product2", new Option(1L));
        LineItem lineItem1 = new LineItem(product1, 1L, 1);
        LineItem lineItem2 = new LineItem(product2, 1L, 2);

        // Act
        cart.addProduct(lineItem1);
        cart.addProduct(lineItem2);

        // Assert
        assertEquals(1, cart.getCartItems().size());
        assertEquals(3, cart.getCartItems().get(0).getQuantity());
    }
}
