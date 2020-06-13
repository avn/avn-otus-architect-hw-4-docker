package ru.avn.otus.hw.users.profiles.exceptions;

public class UserProfileAlreadyExistsException extends ServiceException {

    static final long serialVersionUID = -484924452383125407L;

    public UserProfileAlreadyExistsException(String username, Throwable t) {
        super(String.format("A user profile already exists with that username '%s'", username), t);
    }

}
