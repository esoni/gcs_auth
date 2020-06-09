package it.frongillo.demostorage.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails, Serializable {

    @JsonProperty("username")
    private String username;

    @JsonIgnore()
    @JsonProperty("password")
    private String password;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("userId")
    private long userId;

    @JsonIgnore()
    private List<GrantedAuthority> authorities = new ArrayList<>();

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public User() {}

    @JsonIgnore()
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @JsonIgnore()
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    @JsonIgnore()
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore()
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore()
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore()
    public boolean isEnabled() {
        return true;
    }
}
