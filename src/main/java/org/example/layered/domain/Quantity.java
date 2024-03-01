package org.example.layered.domain;

import jakarta.persistence.Embeddable;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity quantity)) return false;
        return getValue() == quantity.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
