package org.example.layered.query.dto;

import org.example.layered.domain.Cart;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RedisHash("cart")
public class CartView implements Serializable {
    private Long id;
    private List<CartLineView> cartLineViews;

    public CartView() {
    }

    public CartView(Long id, List<CartLineView> cartLineViews) {
        this.id = id;
        this.cartLineViews = cartLineViews;
    }

    public CartView(Cart cart) {
        this.id = cart.getId();
        this.cartLineViews = cart.getLineItems().stream()
                .map(lineItem -> new CartLineView(lineItem.getProductId(), lineItem.getOptionId(), lineItem.getQuantity()))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public List<CartLineView> getCartLineViews() {
        return Collections.unmodifiableList(cartLineViews);
    }
}
