package org.example.layered.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @Test
    void constructor_setsValueCorrectly() {
        // Arrange
        Integer value = 1;

        // Act
        Quantity quantity = new Quantity(value);

        // Assert
        assertEquals(value, quantity.value());
    }

    @Test
    void constructor_throwsExceptionForNegativeValue() {
        // Arrange
        Integer negativeValue = -1;

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Quantity(negativeValue));
    }

    @Test
    void add_increasesQuantityByGivenValue() {
        // Arrange
        Quantity quantity = new Quantity(1);
        int additionalValue = 2;

        // Act
        Quantity newQuantity = quantity.add(additionalValue);

        // Assert
        assertEquals(3, newQuantity.value());
    }

    @Test
    void subtract_decreasesQuantityByGivenValue() {
        // Arrange
        Quantity quantity = new Quantity(3);
        int subtractValue = 2;

        // Act
        Quantity newQuantity = quantity.subtract(subtractValue);

        // Assert
        assertEquals(1, newQuantity.value());
    }

    @Test
    void subtract_throwsExceptionForValueGreaterThanQuantity() {
        // Arrange
        Quantity quantity = new Quantity(1);
        int subtractValue = 2;

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> quantity.subtract(subtractValue));
    }
}
