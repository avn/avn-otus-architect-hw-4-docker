package ru.avn.otus.hw.auth.components;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ru.avn.otus.hw.auth.entities.User;
import ru.avn.otus.hw.auth.services.AuthService;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authService.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user exists with that username: " + username);
        }
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}
