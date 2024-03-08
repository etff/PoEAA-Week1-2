package org.example.layered.query;

import org.example.layered.infra.CartRedisRepository;
import org.example.layered.query.dto.CartView;
import org.springframework.stereotype.Service;

@Service
public class CartViewListService {

  private final CartRedisRepository cartRedisRepository;

    public CartViewListService(CartRedisRepository cartRedisRepository) {
        this.cartRedisRepository = cartRedisRepository;
    }

    public CartView findById(Long id) {
        return cartRedisRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("cart not found"));
    }
}
