package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class AppControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "dm1", roles = { "USER" }) // ROLE_USER
    public void testUserAccessWithUserRole() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().string("User endpoint"));
    }

    @Test
    @WithMockUser(username = "dm1", roles = { "USER" }) // not ADMIN
    public void testAdminAccessWithUserRole() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" }) // ROLE_ADMIN
    public void testAdminAccessWithAdminRole() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Admin endpoint"));
    }

}
