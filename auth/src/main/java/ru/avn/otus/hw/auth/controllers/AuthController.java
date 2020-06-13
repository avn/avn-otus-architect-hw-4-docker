package ru.avn.otus.hw.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.avn.otus.hw.auth.entities.User;
import ru.avn.otus.hw.auth.services.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void register(@RequestParam String username, @RequestParam String password) {
        authService.register(username, password);
    }


    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void login(@RequestParam String username, @RequestParam String password) {
        authService.login(username, password);
    }

    @GetMapping
    public ResponseEntity<Void> get() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication();
        User user = authService.get(userDetails.getUsername());
        return ResponseEntity.ok()
                .header("X-User-Id", String.valueOf(user.getId()))
                .build();
    }

}
