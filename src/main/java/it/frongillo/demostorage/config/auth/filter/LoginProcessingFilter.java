package it.frongillo.demostorage.config.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.frongillo.demostorage.model.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private void setAntPathRequestMatcher(AntPathRequestMatcher antPathRequestMatcher) {
        super.setRequiresAuthenticationRequestMatcher(antPathRequestMatcher);
    }


    @Autowired
    @Override
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
        super.setAuthenticationSuccessHandler(successHandler);
    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    public LoginProcessingFilter() {
        super("/j_spring_security_check");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException {
        LoginRequest loginRequest = null;
        try {
            loginRequest = mapper.readValue(httpServletRequest.getReader(), LoginRequest.class);
        } catch (Exception ex) {
            throw new AuthenticationServiceException("Invalid Authentication Parameter");
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }
}
