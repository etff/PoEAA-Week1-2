package org.example.layered.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void constructor_setsFieldsCorrectly() {
        // Arrange
        Long id = 1L;
        String productName = "product1";

        // Act
        Product product = new Product(id, productName);

        // Assert
        assertEquals(id, product.getId());
    }
}
