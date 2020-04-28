package ru.avn.otus.hw.users.exceptions;

public class UserIncorrectVersionException extends ServiceException {

    static final long serialVersionUID = 8591800294391435801L;

    public UserIncorrectVersionException(long userId, int expectedVersion, int currentVersion) {
        super(String.format("A version of user with that id '%d' was changed. " +
                        "Expected version '%d'. Current current '%d'.", userId, expectedVersion, currentVersion));
    }

    public UserIncorrectVersionException(long id, Throwable t) {
        super(String.format("A version of user with that id '%d' was changed.", id), t);
    }
}
