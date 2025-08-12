package com.groceteria.controllerTest;
 
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
import java.util.List;
 
import com.groceteria.entity.Cart;
import com.groceteria.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
 
@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private CartService cartService;
 
    @Test
    public void getAllCartsTest() throws Exception {
        when(cartService.getAllCarts()).thenReturn(List.of(new Cart()));
 
        mockMvc.perform(get("/api/v1/cart")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
 
    @Test
    public void getCartByIdTest() throws Exception {
        long cartId = 1;
        when(cartService.getCartById(cartId)).thenReturn(new Cart());
 
        mockMvc.perform(get("/api/v1/cart/" + cartId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void addCartTest() throws Exception {
        long itemId = 1;
        Integer userId = 1;
        Cart cart = new Cart();
        when(cartService.addToCart(any(), itemId, userId)).thenReturn(cart);
 
        mockMvc.perform(post("/api/v1/cart")
                .param("itemId", String.valueOf(itemId))
                .param("userId", String.valueOf(userId))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"quantity\": 1}")) 
                .andExpect(status().isCreated());
    }
    
    @Test
    public void updateCartTest() throws Exception {
        long cartId = 1;
        Cart cart = new Cart();
        when(cartService.updateCart(any(), cartId)).thenReturn(cart);
 
        mockMvc.perform(put("/api/v1/cart/" + cartId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"quantity\": 2}")) 
                .andExpect(status().isOk());
    }
 
    @Test
    public void deleteCartTest() throws Exception {
        long cartId = 1;
        doNothing().when(cartService).deleteCart(cartId);
 
        mockMvc.perform(delete("/api/v1/cart/" + cartId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void updateCartQuantityTest() throws Exception {
        long cartId = 1;
        long quantity = 5;
        Cart cart = new Cart();
        when(cartService.updateCartQuantity(cartId, quantity)).thenReturn(cart);
 
        mockMvc.perform(put("/api/v1/cart/" + cartId + "/quantity")
                .param("quantity", String.valueOf(quantity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
 
} 