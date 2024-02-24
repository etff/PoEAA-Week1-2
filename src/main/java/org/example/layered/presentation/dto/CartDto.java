package org.example.layered.presentation.dto;

public record CartDto(Long productId, Long optionId, Integer quantity){
    public CartDto {

        if (productId == null) {
            throw new IllegalArgumentException("productId is required");
        }

        if (optionId == null) {
            throw new IllegalArgumentException("optionId is required");
        }

        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("invalid quantity");
        }
    }
}
