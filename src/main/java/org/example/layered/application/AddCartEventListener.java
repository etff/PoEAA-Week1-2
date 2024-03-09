package org.example.layered.application;

import org.example.layered.domain.Cart;
import org.example.layered.domain.CartPublishedEvent;
import org.example.layered.infra.CartRedisRepository;
import org.example.layered.query.dto.CartView;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AddCartEventListener implements ApplicationListener<CartPublishedEvent> {
    private final CartRedisRepository cartRedisRepository;

    public AddCartEventListener(CartRedisRepository cartRedisRepository) {
        this.cartRedisRepository = cartRedisRepository;
    }

    @Override
    public void onApplicationEvent(CartPublishedEvent event) {
        Cart cart = event.getCart();
        cartRedisRepository.save(new CartView(cart));
    }
}
