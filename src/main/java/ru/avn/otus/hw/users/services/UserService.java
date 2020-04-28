package ru.avn.otus.hw.users.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.avn.otus.hw.users.dto.*;
import ru.avn.otus.hw.users.exceptions.UserAlreadyExistsException;
import ru.avn.otus.hw.users.exceptions.UserIncorrectVersionException;
import ru.avn.otus.hw.users.exceptions.UserNotFoundException;

import javax.validation.Valid;

@Validated
public interface UserService {

    @Transactional(rollbackFor = UserAlreadyExistsException.class)
    CreateUserResultDTO createUser(@Valid CreateUserDTO createUserDTO) throws UserAlreadyExistsException;

    @Transactional
    Versionable<GetUserResultDTO> getUser(long userId) throws UserNotFoundException;

    @Transactional
    void deleteUser(long userId) throws UserNotFoundException;

    @Transactional
    void updateUser(long userId, int userVersion, @Valid UpdateUserDTO updateUserDTO)
            throws UserNotFoundException, UserIncorrectVersionException;

}
