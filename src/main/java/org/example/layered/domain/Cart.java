package org.example.layered.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Cart extends AbstractAggregateRoot<Cart> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private final List<LineItem> lineItems = new ArrayList<>();

    public Cart() {
    }

    public void setLineItems(LineItem lineItem) {
        lineItems.add(lineItem);
        lineItem.setCartItem(this);
    }

    public Long getId() {
        return id;
    }

    public List<LineItem> getLineItems() {
        return Collections.unmodifiableList(lineItems);
    }

    public void addProduct(LineItem lineItem) {
        if (lineItems.isEmpty()) {
            setLineItems(lineItem);
            return;
        }

        for (LineItem item : lineItems) {
            if (item.hasSameProductOption(lineItem)) {
                item.addQuantity(lineItem.getQuantity());
                return;
            }
            setLineItems(lineItem);
        }
    }

    public Cart publish(){
        this.registerEvent(new CartPublishedEvent(this));
        return this;
    }
}
