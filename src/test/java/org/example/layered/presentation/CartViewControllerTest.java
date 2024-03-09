package org.example.layered.presentation;

import org.example.layered.query.CartFetcher;
import org.example.layered.query.dto.CartLineView;
import org.example.layered.query.dto.CartView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(CartViewController.class)
class CartViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartFetcher cartFetcher;

    @Test
    void list() throws Exception {
        // Arrange
        CartView cartView = new CartView(1L, List.of(new CartLineView(1L, 2L, 3)));
        when(cartFetcher.getCartView(anyLong())).thenReturn(cartView);

        // Act & Assert
        mockMvc.perform(get("/carts?id=1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
