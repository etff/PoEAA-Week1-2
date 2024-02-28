package org.example.layered.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private final List<Option> options = new ArrayList<>();

    protected Product() {
    }

    public Product(Long id, String productName) {
        this.id = id;
        this.productName = productName;
    }

    public Long getId() {
        return id;
    }

    public void addOption(Option option) {
        options.add(option);
        option.setProduct(this);
    }

    public List<Option> getOptions() {
        return Collections.unmodifiableList(options);
    }
}
