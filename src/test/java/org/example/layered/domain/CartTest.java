package org.example.layered.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void addProductWithEmptyCartItems() {
        // given
        final Cart cart = new Cart(1L, 1L, 1);
        final Long productId = 1L;
        final Long optionId = 1L;
        final int quantity = 1;

        // when
        cart.addProduct(productId, optionId, quantity);

        // then
        assertEquals(cart.getCartItems().size(), 1);
    }

    @Test
    void addProductWithExistCartItems() {
        final Cart cart = new Cart(List.of(new LineItem(1L, 1L, 1)));
        final Long productId = 1L;
        final Long optionId = 2L;
        final int quantity = 1;

        cart.addProduct(productId, optionId, quantity);

        assertEquals(cart.getCartItems().size(), 2);
    }
}
