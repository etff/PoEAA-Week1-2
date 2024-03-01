package org.example.layered.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineItemTest {

    @Test
    void constructor_setsFieldsCorrectly() {
        // Arrange
        Product product = new Product(1L, "product1", new Option(1L));
        Long optionId = 1L;
        Integer quantity = 1;

        // Act
        LineItem lineItem = new LineItem(product, optionId, quantity);

        // Assert
        assertEquals(quantity, lineItem.getQuantity());
    }

    @Test
    void validateOption_throwsExceptionForInvalidOptionId() {
        // Arrange
        Product product = new Product(1L, "product1", new Option(1L));
        Long invalidOptionId = 2L;

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new LineItem(product, invalidOptionId, 1));
    }
}
