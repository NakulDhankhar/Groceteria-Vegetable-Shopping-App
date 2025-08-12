package com.groceteria.controllerTest;
 
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
import java.util.List;
 
import com.groceteria.entity.User;
import com.groceteria.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
 
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private UserService userService;
 
    @Test
    public void getAllUsersTest() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(new User()));
 
        mockMvc.perform(get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
 
    @Test
    public void getUserByIdTest() throws Exception {
        Integer userId = 1;
        when(userService.getUserById(userId)).thenReturn(new User());
 
        mockMvc.perform(get("/api/v1/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void registerUserTest() throws Exception {
        User user = new User();
        when(userService.registerUser(any())).thenReturn(user);
 
        mockMvc.perform(post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test User\",\"email\":\"test@example.com\",\"password\":\"password123\"}")) 
                .andExpect(status().isCreated());
    }
    
    @Test
    public void updateUserTest() throws Exception {
        Integer userId = 1;
        User user = new User();
        when(userService.updateUser(user, userId)).thenReturn(user);
 
        mockMvc.perform(put("/api/v1/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated User\",\"email\":\"updated@example.com\"}")) 
                .andExpect(status().isOk());
    }
 
    @Test
    public void deleteUserTest() throws Exception {
        Integer userId = 1;
        doNothing().when(userService).deleteUser(userId);
 
        mockMvc.perform(delete("/api/v1/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void loginUserTest() throws Exception {
        User user = new User();
        when(userService.loginUser(anyString(), anyString())).thenReturn(user);
 
        mockMvc.perform(post("/api/v1/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\",\"password\":\"password123\"}")) 
                .andExpect(status().isOk());
    }
    
    @Test
    public void getUserByEmailTest() throws Exception {
        String email = "test@example.com";
        when(userService.getUserByEmail(email)).thenReturn(new User());
 
        mockMvc.perform(post("/api/v1/users/forgot-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"" + email + "\"}"))
                .andExpect(status().isOk());
    }
 
} 