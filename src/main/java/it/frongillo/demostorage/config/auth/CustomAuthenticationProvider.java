package it.frongillo.demostorage.config.auth;

import it.frongillo.demostorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CustomAuthenticationProvider implements AuthenticationManager {

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        final UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken) authentication;
        String name = userToken.getName();
        String password = (String)userToken.getCredentials();

        return userService.login(name, password);

    }
}
