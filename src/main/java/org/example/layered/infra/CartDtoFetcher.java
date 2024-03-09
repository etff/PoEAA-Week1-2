package org.example.layered.infra;

import org.example.layered.query.CartFetcher;
import org.example.layered.query.dto.CartView;
import org.springframework.stereotype.Component;

@Component
public class CartDtoFetcher implements CartFetcher {

    private final CartRedisRepository cartRedisRepository;

    public CartDtoFetcher(CartRedisRepository cartRedisRepository) {
        this.cartRedisRepository = cartRedisRepository;
    }

    @Override
    public CartView getCartView(Long id) {
        return cartRedisRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("cart not found"));
    }
}
