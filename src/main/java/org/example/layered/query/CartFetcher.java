package org.example.layered.query;

import org.example.layered.query.dto.CartView;

public interface CartFetcher {
    CartView getCartView(Long id);
}
