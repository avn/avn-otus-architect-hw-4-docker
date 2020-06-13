package ru.avn.otus.hw.users.profiles.exceptions;

public class UserProfileIncorrectVersionException extends ServiceException {

    static final long serialVersionUID = 8591800294391435801L;

    public UserProfileIncorrectVersionException(long userId, int expectedVersion, int currentVersion) {
        super(String.format("A version of user profile with that id '%d' was changed. " +
                        "Expected version is '%d', but current is '%d'.", userId, expectedVersion, currentVersion));
    }

    public UserProfileIncorrectVersionException(long id, Throwable t) {
        super(String.format("A version of user profile with that id '%d' was changed.", id), t);
    }
}
