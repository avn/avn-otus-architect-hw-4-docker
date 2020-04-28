package ru.avn.otus.hw.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserConflictException extends ServiceException {

    static final long serialVersionUID = -8925672226324077815L;

    public UserConflictException(String message) {
        super(message);
    }
}
