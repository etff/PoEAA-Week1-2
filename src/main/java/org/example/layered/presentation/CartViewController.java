package org.example.layered.presentation;

import org.example.layered.query.CartViewListService;
import org.example.layered.query.dto.CartView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartViewController {

    private final CartViewListService cartViewListService;

    public CartViewController(CartViewListService cartViewListService) {
        this.cartViewListService = cartViewListService;
    }

    @GetMapping("/carts/{id}")
    public CartView list(@PathVariable(value = "id") Long id) {
        return cartViewListService.findById(id);
    }
}
