package org.example.layered.query;

import org.example.layered.query.dto.CartView;

public interface CartDtoFetcher {
    CartView getCartView(Long id);
}
