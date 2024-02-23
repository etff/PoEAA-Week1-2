package org.example.layered.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private final List<CartItem> cartItems = new ArrayList<>();

    protected Cart() {
    }

    public Cart(List<CartItem> cartItems) {
        this.id = null;
        this.cartItems.addAll(cartItems);
        cartItems.forEach(it -> it.setCartItem(this));
    }

    public Cart(Long productId, Long optionId, int quantity) {
        this.id = null;
        addCartItem(new CartItem(productId, optionId, quantity));
    }

    public void addProduct(Long productId, Long optionId, int quantity) {
        cartItems.stream()
                .filter(it -> it.hasSameProductOption(productId, optionId))
                .findFirst()
                .ifPresentOrElse(
                        it -> it.addQuantity(quantity),
                        () -> cartItems.add(new CartItem(productId, optionId, quantity))
                );
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setCartItem(this);
    }

    public Long getId() {
        return id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
