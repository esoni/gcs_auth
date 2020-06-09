package it.frongillo.demostorage.service.impl;

import it.frongillo.demostorage.model.dto.User;
import it.frongillo.demostorage.model.entity.UserEntity;
import it.frongillo.demostorage.repository.UserRepository;
import it.frongillo.demostorage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public Authentication login(String username, String password) throws AuthenticationException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Invalid Username");
        }
        if (!bCryptPasswordEncoder.matches(password, userEntity.getPassword())) {
            throw new BadCredentialsException("Invalid Password");
        }
        User currentUser = new User();
        currentUser.setUsername(userEntity.getUsername());
        currentUser.setPassword(userEntity.getPassword());
        currentUser.setUserId(userEntity.getUserId());
        //roles not managed
        currentUser.setAuthorities(new ArrayList<>());
        currentUser.setFullName(userEntity.getFullName());
        return new UsernamePasswordAuthenticationToken(currentUser, password, new ArrayList<>());
    }
}
