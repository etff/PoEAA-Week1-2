package org.example.layered.query.dto;

import java.io.Serializable;

public class CartLineView implements Serializable {

    private Long productId;
    private Long optionId;
    private Integer quantity;

    public CartLineView(Long productId, Long optionId, Integer quantity) {
        this.productId = productId;
        this.optionId = optionId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
