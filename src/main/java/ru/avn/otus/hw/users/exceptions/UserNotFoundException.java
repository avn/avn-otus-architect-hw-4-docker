package ru.avn.otus.hw.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ServiceException {

    static final long serialVersionUID = -8925672226324077815L;

    public UserNotFoundException(long id) {
        super("User not found by ID: " + id);
    }
}
