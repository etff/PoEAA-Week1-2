package org.example.layered.presentation;

import org.example.layered.query.CartDtoFetcher;
import org.example.layered.query.dto.CartView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartViewController {

    private final CartDtoFetcher cartViewListService;

    public CartViewController(CartDtoFetcher cartViewListService) {
        this.cartViewListService = cartViewListService;
    }

    @GetMapping("/carts/{id}")
    public CartView list(@PathVariable(value = "id") Long id) {
        return cartViewListService.getCartView(id);
    }
}
