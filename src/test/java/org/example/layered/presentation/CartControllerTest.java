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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
        final CartDto cartDto = new CartDto(1L, 2L, 3);
        final Long expectedCartId = 1L;
        given(cartAddService.addProduct(cartDto.productId(), cartDto.optionId(), cartDto.quantity()))
                .willReturn(expectedCartId);

        // 실행 & 검증
        mockMvc.perform(post("/carts/cart-line-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartDto)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/carts/" + expectedCartId));
    }

    @Test
    void updateProductToCartShouldReturnOkStatus() throws Exception {
        // Arrange
        final CartDto cartDto = new CartDto(1L, 2L, 3);
        final Long cartId = 1L;

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.patch("/carts/" + cartId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartDto)))
                .andExpect(status().isOk());

        verify(cartAddService, times(1)).updateProduct(anyLong(), anyLong(), anyLong(), any(Integer.class));
    }
}
