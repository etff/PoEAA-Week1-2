package org.example.layered.domain;

import org.springframework.context.ApplicationEvent;

public class CartPublishedEvent extends ApplicationEvent {
    private Cart cart;
    public CartPublishedEvent(Object source) {
        super(source);
        this.cart = (Cart) source;
    }

    public Cart getCart() {
        return cart;
    }
}
