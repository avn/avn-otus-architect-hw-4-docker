package ru.avn.otus.hw.users.profiles.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.avn.otus.hw.users.profiles.exceptions.UserProfileAlreadyExistsException;
import ru.avn.otus.hw.users.profiles.exceptions.UserProfileIncorrectVersionException;
import ru.avn.otus.hw.users.profiles.exceptions.UserProfileNotFoundException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice
public class UserProfileExceptionControllerAdvice {

    @ExceptionHandler(value = ConstraintViolationException.class)
    void handleConstraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(value = {UserProfileAlreadyExistsException.class, UserProfileIncorrectVersionException.class})
    void handleConflicts(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler(value = {UserProfileNotFoundException.class})
    void handleNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
