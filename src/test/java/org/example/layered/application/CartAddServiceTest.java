package org.example.layered.application;

import org.example.layered.domain.Cart;
import org.example.layered.infra.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CartAddServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartAddService cartAddService;

    @Test
    void whenCartIdIsNull_thenCreateNewCart() {
        // Given
        Long productId = 1L;
        Long optionId = 1L;
        Integer quantity = 1;

        Cart expectedCart = new Cart(productId, optionId, quantity);
        when(cartRepository.save(any(Cart.class))).thenReturn(expectedCart);

        // When
        Long savedCartId = cartAddService.addProduct(null, productId, optionId, quantity);

        // Then
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void whenCartIdIsNotNull_thenAddProductToExistingCart() {
        // Given
        Long cartId = 1L;
        Long productId = 1L;
        Long optionId = 1L;
        Integer quantity = 1;
        Cart existingCart = new Cart(1L, 1L,1);
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(existingCart));
        when(cartRepository.save(any(Cart.class))).thenReturn(existingCart);

        // When
        Long savedCartId = cartAddService.addProduct(cartId, productId, optionId, quantity);

        // Then
        verify(cartRepository, times(1)).findById(cartId);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }
}
