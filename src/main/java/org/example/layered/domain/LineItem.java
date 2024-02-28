package org.example.layered.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;

@Entity
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long optionId;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "quantity"))
    private Quantity quantity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    protected LineItem() {
    }

    public LineItem(Product product, Long optionId, Integer quantity) {
        validateOption(product, optionId);
        this.productId = product.getId();
        this.optionId = optionId;
        this.quantity = new Quantity(quantity);
    }

    private void validateOption(Product product, Long optionId) {
        boolean hasOption = product.getOptions().stream()
                .filter(Objects::nonNull)
                .anyMatch(option -> option.getId().equals(optionId));
        if (!hasOption) {
            throw new IllegalArgumentException("invalid optionId");
        }
    }

    public void addQuantity(int quantity) {
        this.quantity = this.quantity.add(quantity);
    }

    public void setCartItem(Cart cart) {
        this.cart = cart;
    }

    public Integer getQuantity() {
        return quantity.getValue();
    }

    public boolean hasSameProductOption(LineItem lineItem) {
        return this.productId.equals(lineItem.productId) && this.optionId.equals(lineItem.optionId);
    }
}
