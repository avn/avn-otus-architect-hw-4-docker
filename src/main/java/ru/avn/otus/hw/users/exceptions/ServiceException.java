package ru.avn.otus.hw.users.exceptions;

public class ServiceException extends Exception {

    static final long serialVersionUID = -5355507249581915755L;

    public ServiceException(String message) {
        super(message, null, true, false);
    }

    public ServiceException(String message, Throwable t) {
        super(message, t);
    }
}

