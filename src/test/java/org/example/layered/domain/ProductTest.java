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

    @Test
    void addOption_addsOptionToProduct() {
        // Arrange
        Product product = new Product(1L, "product1");
        Option option = new Option(1L);

        // Act
        product.addOption(option);

        // Assert
        assertEquals(1, product.getOptions().size());
        assertTrue(product.getOptions().contains(option));
    }
}
