package it.frongillo.demostorage.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface UserService {
    Authentication login(String username, String password) throws AuthenticationException;
}
