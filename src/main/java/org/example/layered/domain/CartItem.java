package org.example.layered.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import static jakarta.persistence.FetchType.LAZY;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long optionId;
    private Integer quantity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    protected CartItem() {
    }

    public CartItem(Long productId, Long optionId, int quantity) {
        this(null, productId, optionId, quantity);
    }

    public CartItem(Long id, Long productId, Long optionId, int quantity) {
        if (productId == null) {
            throw new IllegalArgumentException("productId is required");
        }

        if (optionId == null) {
            throw new IllegalArgumentException("optionId is required");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }

        this.id = id;
        this.productId = productId;
        this.optionId = optionId;
        this.quantity = quantity;
    }

    public boolean hasSameProductOption(Long productId, Long optionId) {
        return this.productId.equals(productId) && this.optionId.equals(optionId);
    }

    public void addQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }
        this.quantity += quantity;
    }

    public void setCartItem(Cart cart) {
        this.cart = cart;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
