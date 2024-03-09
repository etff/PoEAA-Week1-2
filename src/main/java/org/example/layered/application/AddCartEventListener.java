package org.example.layered.application;

import org.example.layered.domain.Cart;
import org.example.layered.domain.CartPublishedEvent;
import org.example.layered.infra.CartRedisRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AddCartEventListener implements ApplicationListener<CartPublishedEvent> {
    private final CartRedisRepository cartRedisRepository;
    private final CartMapper cartMapper;

    public AddCartEventListener(CartRedisRepository cartRedisRepository, CartMapper cartMapper) {
        this.cartRedisRepository = cartRedisRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public void onApplicationEvent(CartPublishedEvent event) {
        Cart cart = event.getCart();
        cartRedisRepository.save(cartMapper.toCartView(cart));
    }
}
