package org.example.layered.presentation;

import org.example.layered.query.CartFetcher;
import org.example.layered.query.dto.CartView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartViewController {

    private final CartFetcher cartFetcher;

    public CartViewController(CartFetcher cartFetcher) {
        this.cartFetcher = cartFetcher;
    }

    @GetMapping("/carts")
    public CartView list(@RequestParam("id") Long id) {
        return cartFetcher.getCartView(id);
    }
}
