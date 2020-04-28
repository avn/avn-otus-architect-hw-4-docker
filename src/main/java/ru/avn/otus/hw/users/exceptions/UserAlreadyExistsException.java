package ru.avn.otus.hw.users.exceptions;

public class UserAlreadyExistsException extends ServiceException {

    static final long serialVersionUID = -484924452383125407L;

    public UserAlreadyExistsException(String username, Throwable t) {
        super(String.format("A user already exists with that username '%s'", username), t);
    }

}
