package org.example.layered.application;

import org.example.layered.domain.Cart;
import org.example.layered.domain.CartPublishedEvent;
import org.example.layered.infra.CartRedisRepository;
import org.example.layered.query.dto.CartView;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AddCartEventListener {
    private final CartRedisRepository cartRedisRepository;

    public AddCartEventListener(CartRedisRepository cartRedisRepository) {
        this.cartRedisRepository = cartRedisRepository;
    }

    @EventListener
    public void onApplicationEvent(CartPublishedEvent event) {
        Cart cart = event.getCart();
        cartRedisRepository.save(new CartView(cart));
    }
}
