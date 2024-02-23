package org.example.layered.application;

import org.example.layered.domain.Cart;
import org.example.layered.infra.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartAddService {
    private final CartRepository cartRepository;

    public CartAddService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Transactional
    public Long addProduct(Long cartId, Long productId, Long optionId, Integer quantity) {
        if (cartId == null) {
            final Cart savedCart = cartRepository.save(new Cart(productId, optionId, quantity));
            return savedCart.getId();
        }

        final Cart cart = cartRepository.findById(cartId)
                .map(it -> {
                    it.addProduct(productId, optionId, quantity);
                    return it;
                })
                .orElseGet(() -> new Cart(productId, optionId, quantity));

        final Cart savedCart = cartRepository.save(cart);
        return savedCart.getId();
    }
}
