package ru.avn.otus.hw.users.profiles.exceptions;

public class UserProfileNotFoundException extends ServiceException {

    static final long serialVersionUID = -8925672226324077815L;

    public UserProfileNotFoundException(long userId) {
        super(String.format("A user profile is not found with that id '%d'", userId));
    }

}
