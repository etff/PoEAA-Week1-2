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
    private final List<LineItem> lineItems = new ArrayList<>();

    protected Cart() {
    }

    public Cart(List<LineItem> lineItems) {
        this.id = null;
        this.lineItems.addAll(lineItems);
        lineItems.forEach(it -> it.setCartItem(this));
    }

    public Cart(Long productId, Long optionId, int quantity) {
        this.id = null;
        addCartItem(new LineItem(productId, optionId, quantity));
    }

    public void addProduct(Long productId, Long optionId, int quantity) {
        lineItems.stream()
                .filter(it -> it.hasSameProductOption(productId, optionId))
                .findFirst()
                .ifPresentOrElse(
                        it -> it.addQuantity(quantity),
                        () -> lineItems.add(new LineItem(productId, optionId, quantity))
                );
    }

    public void addCartItem(LineItem lineItem) {
        lineItems.add(lineItem);
        lineItem.setCartItem(this);
    }

    public Long getId() {
        return id;
    }

    public List<LineItem> getCartItems() {
        return lineItems;
    }
}
