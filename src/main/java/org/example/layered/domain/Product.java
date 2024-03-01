package org.example.layered.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private final List<Option> options = new ArrayList<>();

    protected Product() {
    }

    public Product(Long id, String productName) {
        this.id = id;
        this.productName = productName;
    }

    public Product(Long id, String productName, Option... options) {
        this.id = id;
        this.productName = productName;
        this.options.addAll(Arrays.asList(options));
    }

    public Long getId() {
        return id;
    }

    public List<Option> getOptions() {
        return Collections.unmodifiableList(options);
    }
}
