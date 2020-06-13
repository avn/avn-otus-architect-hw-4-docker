package ru.avn.otus.hw.users.profiles.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.avn.otus.hw.users.profiles.dto.*;
import ru.avn.otus.hw.users.profiles.exceptions.UserProfileAlreadyExistsException;
import ru.avn.otus.hw.users.profiles.exceptions.UserProfileIncorrectVersionException;
import ru.avn.otus.hw.users.profiles.exceptions.UserProfileNotFoundException;

import javax.validation.Valid;

@Validated
public interface UserProfileService {

    @Transactional(rollbackFor = UserProfileAlreadyExistsException.class)
    CreateUserProfileResultDTO create(@Valid CreateUserProfileDTO createUserProfileDTO) throws UserProfileAlreadyExistsException;

    @Transactional
    Versionable<GetUserProfileResultDTO> get(long userProfileId) throws UserProfileNotFoundException;

    @Transactional
    void delete(long userProfileId) throws UserProfileNotFoundException;

    @Transactional
    void update(long userProfileId, int userProfileVersion, @Valid UpdateUserProfileDTO updateUserProfileDTO)
            throws UserProfileNotFoundException, UserProfileIncorrectVersionException;

}
