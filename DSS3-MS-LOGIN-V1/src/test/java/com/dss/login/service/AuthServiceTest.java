package com.dss.login.service;

import com.dss.login.exception.AbstractException;
import com.dss.login.model.Usr;
import com.dss.login.model.UsrAuth;
import com.dss.login.repository.AuthDao;
import com.dss.login.util.PasswordEncoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AuthServiceTest {

    @MockBean private AuthDao authDao;

    @Autowired private AuthService authService;

    @Test
    void Login_with_valid_request_and_valid_credentials_is_valid() throws AbstractException {
        UsrAuth usrAuth = new UsrAuth();
        usrAuth.setEmail("authuser@gmail.com");
        usrAuth.setPassword("M0nd@y333");

        Usr usr = new Usr(usrAuth.getEmail(), usrAuth.getPassword());
        String encodedPassword = PasswordEncoder.getEncodedPassword(usrAuth.getPassword());
        Mockito.when(authDao.countByEmailAndPassword(usrAuth.getEmail(), encodedPassword))
                .thenReturn(1);

        Assertions.assertTrue(authService.login(usrAuth));
    }

    @Test
    void Login_with_valid_request_and_invalid_credentials_is_invalid() throws AbstractException {
        UsrAuth usrAuth = new UsrAuth();
        usrAuth.setEmail("nonexistent@gmail.com");
        usrAuth.setPassword("testpassword");

        String encodedPassword = PasswordEncoder.getEncodedPassword(usrAuth.getPassword());
        Mockito.when(authDao.countByEmailAndPassword(usrAuth.getEmail(), encodedPassword))
                .thenReturn(0);

        Assertions.assertFalse(authService.login(usrAuth));
    }

    @Test
    void Login_with_valid_request_but_empty_credentials_is_invalid() {
        UsrAuth usrAuth = new UsrAuth();
        usrAuth.setEmail(null);
        usrAuth.setPassword(null);

        Exception exception = Assertions.assertThrows(AbstractException.class, () -> {
            authService.login(usrAuth);
        });
    }

    @Test
    void Login_with_empty_request_is_invalid() {
        Exception exception = Assertions.assertThrows(AbstractException.class, () -> {
            authService.login(null);
        });
    }

    @Test
    void Saving_new_user_with_valid_credentials_is_valid() throws AbstractException {
        Usr usr = new Usr();
        usr.setId("id");
        usr.setAlias("alias");
        usr.setContactNo("123567890");
        usr.setEmail("email");
        usr.setPassword("M0nd@y333");

        Mockito.when(authDao.save(usr)).thenReturn(usr);
        Assertions.assertTrue(authService.register(usr));
    }

    @Test
    void Saving_new_user_with_existing_credentials_is_invalid() throws AbstractException {
        Usr usr = new Usr();
        usr.setId("4028c4ec844c881901844c885dea0000");
        usr.setAlias("admin");
        usr.setContactNo("09687456429");
        usr.setEmail("authuser@gmail.com");
        usr.setPassword("M0nd@y333");

        Mockito.when(authDao.countByEmail(usr.getEmail())).thenReturn(1);
        Mockito.when(authDao.countByContactNo(usr.getContactNo())).thenReturn(1);
        Exception exception = Assertions.assertThrows(AbstractException.class, () -> {
            authService.register(usr);
        });
    }

    @Test
    void Saving_new_user_with_invalid_credentials_is_invalid() throws AbstractException {
        Usr usr = new Usr();
        usr.setId("id");
        usr.setAlias("123567890");
        usr.setContactNo("contactno");
        usr.setEmail("email");
        usr.setPassword("password");

        Exception exception = Assertions.assertThrows(AbstractException.class, () -> {
            authService.register(usr);
        });
    }

    @Test
    void Saving_new_user_with_empty_credentials_is_invalid() throws AbstractException {
        Usr usr = new Usr();
        usr.setId("id");
        usr.setAlias(null);
        usr.setContactNo(null);
        usr.setEmail(null);
        usr.setPassword(null);

        Exception exception = Assertions.assertThrows(AbstractException.class, () -> {
            authService.register(usr);
        });
    }
}