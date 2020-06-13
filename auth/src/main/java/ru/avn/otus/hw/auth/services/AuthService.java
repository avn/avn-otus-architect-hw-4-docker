package ru.avn.otus.hw.auth.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.avn.otus.hw.auth.entities.User;
import ru.avn.otus.hw.common.validators.annotations.ValidPassword;
import ru.avn.otus.hw.common.validators.annotations.ValidUsername;

@Validated
@Transactional
public interface AuthService {

    void register(@ValidUsername String username, @ValidPassword String password);

    User get(@ValidUsername String username);

    void login(@ValidUsername String username, @ValidPassword String password);

}