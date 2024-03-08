package org.example.layered.application;

import org.example.layered.domain.Cart;
import org.example.layered.domain.LineItem;
import org.example.layered.domain.Product;
import org.example.layered.infra.CartRedisRepository;
import org.example.layered.infra.CartRepository;
import org.example.layered.infra.ProductRepository;
import org.example.layered.query.dto.CartView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddProductToCartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartRedisRepository cartRedisRepository;

    public AddProductToCartService(CartRepository cartRepository, ProductRepository productRepository, CartRedisRepository cartRedisRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartRedisRepository = cartRedisRepository;
    }

    @Transactional
    public Long addProduct(Long productId, Long optionId, Integer quantity) {
        Cart cart = new Cart();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("product not found"));

        LineItem lineItem = new LineItem(product, optionId, quantity);
        cart.addProduct(lineItem);

        Cart savedCart = cartRepository.save(cart);
        cartRedisRepository.save(new CartView(cart));

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
        cartRedisRepository.save(new CartView(cart));

        return cart.getId();
    }
}
