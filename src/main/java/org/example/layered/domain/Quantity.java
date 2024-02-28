package org.example.layered.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Quantity {
    private int value;

    protected Quantity() {
    }

    public Quantity(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }
        this.value = value;
    }

    public Quantity add(int quantity) {
        return new Quantity(this.value + quantity);
    }

    public Quantity subtract(int quantity) {
        return new Quantity(this.value - quantity);
    }

    public int getValue() {
        return value;
    }
}
