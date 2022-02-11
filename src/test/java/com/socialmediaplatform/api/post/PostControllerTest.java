package com.socialmediaplatform.api.post;

import com.socialmediaplatform.api.user.UserController;
import com.socialmediaplatform.domain.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureJdbc
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    void contextLoads() {
        assertThat(userController).isNotNull();
    }

    @Test
    @WithMockUser(username = "user", password = "pass",roles = {"ROLE_CLIENT"})
    public void shouldCreateNewPost(){

    }
}