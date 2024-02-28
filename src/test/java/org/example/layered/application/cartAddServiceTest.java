package org.example.layered.application;

import org.example.layered.domain.Cart;
import org.example.layered.domain.LineItem;
import org.example.layered.domain.Product;
import org.example.layered.infra.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class cartAddServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private AddProductToCartService cartAddService;

    @Test
    void addLineItem() {
        Cart cart = new Cart();
        LineItem lineItem = new LineItem(new Product(1L, "product1"), 1L, 1);
        cart.addProduct(lineItem);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Long cartId = cartAddService.addProduct(1L, 1L,  1);

        verify(cartRepository, times(1)).save(any(Cart.class));
        assertEquals(cart.getId(), cartId);
    }

    @Test
    void updateLineItem() {
        Cart cart = new Cart();
        LineItem lineItem = new LineItem(new Product(1L, "product1"), 1L, 1);
        cart.addProduct(lineItem);
        when(cartRepository.findById(any(Long.class))).thenReturn(Optional.of(cart));

        Long cartId = cartAddService.updateProduct(1L, 1L,  1L, 1);

        assertEquals(cart.getId(), cartId);
    }
}
