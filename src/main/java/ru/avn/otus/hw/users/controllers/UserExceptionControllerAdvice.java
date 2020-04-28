package ru.avn.otus.hw.users.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.avn.otus.hw.users.exceptions.UserAlreadyExistsException;
import ru.avn.otus.hw.users.exceptions.UserIncorrectVersionException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice
public class UserExceptionControllerAdvice {

    @ExceptionHandler(value = ConstraintViolationException.class)
    void handleConstraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }


    @ExceptionHandler(value = {UserAlreadyExistsException.class, UserIncorrectVersionException.class})
    void handleConflicts(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.CONFLICT.value());
    }
}
