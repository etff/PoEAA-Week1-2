package org.example.layered.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record Quantity(int value) {

    public Quantity {
        if (value <= 0) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }
    }

    public Quantity add( int addition) {
        return new Quantity(value + addition);
    }

    public Quantity subtract(int subtraction) {
        return new Quantity(value - subtraction);
    }
}
