package org.example.layered.application;

import org.example.layered.domain.Cart;
import org.example.layered.query.dto.CartView;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public CartView toCartView(Cart cart) {
        return new CartView(cart);
    }
}
