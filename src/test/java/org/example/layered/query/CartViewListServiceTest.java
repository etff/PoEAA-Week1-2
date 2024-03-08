package org.example.layered.query;

import org.example.layered.infra.CartRedisRepository;
import org.example.layered.query.dto.CartView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CartViewListServiceTest {

    private CartRedisRepository cartRedisRepository;
    private CartViewListService cartViewListService;

    @BeforeEach
    void setUp() {
        cartRedisRepository = Mockito.mock(CartRedisRepository.class);
        cartViewListService = new CartViewListService(cartRedisRepository);
    }

    @Test
    void findById() {
        CartView cartView = new CartView();
        when(cartRedisRepository.findById(anyLong())).thenReturn(Optional.of(cartView));

        CartView result = cartViewListService.findById(1L);

        assertEquals(cartView, result);
    }
}
