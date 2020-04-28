package ru.avn.otus.hw.users.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.avn.otus.hw.users.dto.*;
import ru.avn.otus.hw.users.exceptions.UserConflictException;
import ru.avn.otus.hw.users.exceptions.UserNotFoundException;

import javax.validation.Valid;

@Validated
@Transactional
public interface UserService {

    CreateUserResultDTO createUser(@Valid CreateUserDTO createUserDTO);

    Versionable<GetUserResultDTO> getUser(long userId) throws UserNotFoundException;

    void deleteUser(long userId) throws UserNotFoundException;

    void updateUser(long userId, int userVersion, @Valid UpdateUserDTO updateUserDTO)
            throws UserNotFoundException, UserConflictException;

}
