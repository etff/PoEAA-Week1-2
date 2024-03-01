package org.example.layered.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected Option() {
    }

    public Option(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
