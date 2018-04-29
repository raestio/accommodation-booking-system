package com.rasto.accommodationbookingsystem.security;

import com.rasto.accommodationbookingsystem.backend.data.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserToUserDetails implements Converter<User, UserDetails> {

    @Override
    public UserDetails convert(User source) {
        UserDetailsImpl userDetails = new UserDetailsImpl();

        if (source != null) {
            userDetails.setEmail(source.getEmail());
            userDetails.setPassword(source.getPassword());
            userDetails.setName(source.getName());
            userDetails.setId(source.getId());
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(source.getRole()));
            userDetails.setAuthorities(authorities);
        }

        return userDetails;
    }
}
