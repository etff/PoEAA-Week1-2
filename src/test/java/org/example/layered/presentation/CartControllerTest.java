package org.example.layered.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.layered.application.AddProductToCartService;
import org.example.layered.presentation.dto.CartDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;


@WebMvcTest(CartController.class)
class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AddProductToCartService cartAddService;

    @Test
    void addProductToCartShouldReturnCreatedStatus() throws Exception {
        // 가정 설정
        final CartDto cartDto = new CartDto(1L, 2L, 3L, 10);
        final Long expectedCartId = 1L;
        given(cartAddService.addLineItem(cartDto.cartId(), cartDto.productId(), cartDto.optionId(), cartDto.quantity()))
                .willReturn(expectedCartId);

        // 실행 & 검증
        mockMvc.perform(post("/carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartDto)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/carts/" + expectedCartId));
    }
}
