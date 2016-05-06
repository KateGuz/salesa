package com.salesa.controller;

/*
import com.salesa.controller.rest.RestUserController;
import com.salesa.entity.User;
import com.salesa.security.UserSecurity;
import com.salesa.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


public class RestUserControllerTest {
    @Mock
    private UserSecurity userSecurity;
    @Mock
    private UserService userService;
    @InjectMocks
    private RestUserController restUserController = new RestUserController();
    private MockMvc mvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(restUserController).build();
    }

    @Test
    public void testSignInREST() throws Exception {
        //prepare
//        MockHttpSession session = mock(MockHttpSession.class);
        User user = new User();
        String email = "Alex@gmail.com";
        String pass = "qwe";


        //when
        when(userService.get(email)).thenReturn(user);
        mvc.perform(put("/signIn").param("email", email).param("pass", pass)).andReturn();

        //verify
        verify(userService, times(1)).get(email);
//        verify(userSecurity, times(1)).addSession(session.getId(), user);
    }

    @Test
    public void testSignUpREST() throws Exception {

    }
}*/
