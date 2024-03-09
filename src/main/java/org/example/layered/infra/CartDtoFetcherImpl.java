package org.example.layered.infra;

import org.example.layered.query.CartDtoFetcher;
import org.example.layered.query.dto.CartView;
import org.springframework.stereotype.Component;

@Component
public class CartDtoFetcherImpl implements CartDtoFetcher {

    private final CartRedisRepository cartRedisRepository;

    public CartDtoFetcherImpl(CartRedisRepository cartRedisRepository) {
        this.cartRedisRepository = cartRedisRepository;
    }

    @Override
    public CartView getCartView(Long id) {
        return cartRedisRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("cart not found"));
    }
}
