package org.example.layered.application;

import org.example.layered.domain.Cart;
import org.example.layered.domain.LineItem;
import org.example.layered.domain.Product;
import org.example.layered.infra.CartRepository;
import org.example.layered.infra.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddProductToCartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public AddProductToCartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Long addProduct(Long productId, Long optionId, Integer quantity) {
        Cart cart = new Cart();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("product not found"));

        LineItem lineItem = new LineItem(product, optionId, quantity);
        cart.addProduct(lineItem);

        Cart savedCart = cartRepository.save(cart);
        return savedCart.getId();
    }

    @Transactional
    public Long updateProduct(Long cartId, Long productId, Long optionId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("cart not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("product not found"));

        LineItem lineItem = new LineItem(product, optionId, quantity);
        cart.addProduct(lineItem);

        return cart.getId();
    }
}
