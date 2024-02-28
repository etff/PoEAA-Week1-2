package org.example.layered.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionTest {
    @Test
    void constructor_setsIdCorrectly() {
        // Arrange
        Long id = 1L;

        // Act
        Option option = new Option(id);

        // Assert
        assertEquals(id, option.getId());
    }
}
