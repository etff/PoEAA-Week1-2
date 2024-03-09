package org.example.layered.presentation;

import org.example.layered.query.CartDtoFetcher;
import org.example.layered.query.dto.CartView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartViewController {

    private final CartDtoFetcher cartViewListService;

    public CartViewController(CartDtoFetcher cartViewListService) {
        this.cartViewListService = cartViewListService;
    }

    @GetMapping("/carts")
    public CartView list(@RequestParam("id") Long id) {
        return cartViewListService.getCartView(id);
    }
}
