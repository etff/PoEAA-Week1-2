package org.example.layered.presentation;

import org.example.layered.application.AddProductToCartService;
import org.example.layered.presentation.dto.CartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CartController {
    private final AddProductToCartService cartAddService;

    public CartController(AddProductToCartService cartAddService) {
        this.cartAddService = cartAddService;
    }

    @PostMapping("/carts")
    public ResponseEntity<Long> addProduct(@RequestBody CartDto cart) {
        final Long cartId = cartAddService.addLineItem(cart.cartId(), cart.productId(), cart.optionId(), cart.quantity());
        return ResponseEntity.created(URI.create("/carts/" + cartId)).build();
    }
}
