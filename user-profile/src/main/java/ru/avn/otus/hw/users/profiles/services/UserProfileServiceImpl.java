package ru.avn.otus.hw.users.profiles.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;
import ru.avn.otus.hw.users.profiles.dto.*;
import ru.avn.otus.hw.users.profiles.entities.UserProfile;
import ru.avn.otus.hw.users.profiles.exceptions.UserProfileAlreadyExistsException;
import ru.avn.otus.hw.users.profiles.exceptions.UserProfileIncorrectVersionException;
import ru.avn.otus.hw.users.profiles.exceptions.UserProfileNotFoundException;
import ru.avn.otus.hw.users.profiles.repositories.UserProfileRepository;

import javax.persistence.OptimisticLockException;
import javax.validation.Valid;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    private final ProjectionFactory projectionFactory;

    @Override
    public CreateUserProfileResultDTO create(@Valid CreateUserProfileDTO createUserProfileDTO) throws UserProfileAlreadyExistsException {
        log.debug("Starting creating a user '{}'", createUserProfileDTO);

        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(createUserProfileDTO.getUsername());
        userProfile.setFirstName(createUserProfileDTO.getFirstName());
        userProfile.setLastName(createUserProfileDTO.getLastName());
        userProfile.setEmail(createUserProfileDTO.getEmail());
        userProfile.setPhone(createUserProfileDTO.getPhone());

        try {
            userProfileRepository.saveAndFlush(userProfile);
            log.info("Finished creating a user profile. A user profile with that username '{}' saved with that id '{}'",
                    userProfile.getUsername(),
                    userProfile.getId());
        }
        catch (DataIntegrityViolationException e) {
            throw new UserProfileAlreadyExistsException(createUserProfileDTO.getUsername(), e);
        }

        return projectionFactory.createProjection(CreateUserProfileResultDTO.class, userProfile);
    }

    @Override
    public Versionable<GetUserProfileResultDTO> get(long userProfileId) throws UserProfileNotFoundException {
        log.debug("Starting getting a user profile by that id '{}'", userProfileId);

        UserProfile userProfile = userProfileRepository.findByIdAndIsDeletedFalse(userProfileId)
                .orElseThrow(() -> new UserProfileNotFoundException(userProfileId));

        log.info("Finished getting a user profile by that id '{}'. A user profile is found that username '{}'",
                userProfile.getId(), userProfile.getUsername());

        GetUserProfileResultDTO projection = projectionFactory.createProjection(GetUserProfileResultDTO.class,
                userProfile);

        return Versionable.of(projection, userProfile.getVersion());
    }

    @Override
    public void delete(long userProfileId) throws UserProfileNotFoundException {
        log.debug("Starting deleting a user profile by that id '{}'", userProfileId);

        UserProfile userProfile = userProfileRepository.findByIdAndIsDeletedFalse(userProfileId)
                .orElseThrow(() -> new UserProfileNotFoundException(userProfileId));

        userProfile.setDeleted(true);
        userProfileRepository.saveAndFlush(userProfile);

        log.info("Finished deleting a user profile by that id '{}'. " +
                "A user profile was deleted with that username '{}' ", userProfile.getId(), userProfile.getUsername());
    }

    @Override
    public void update(long userProfileId, int userProfileVersion, UpdateUserProfileDTO updateUserProfileDTO)
            throws UserProfileNotFoundException, UserProfileIncorrectVersionException {

        log.debug("Starting updating a user profile by that id '{}'. Updating to '{}'", userProfileId,
                updateUserProfileDTO);

        UserProfile userProfile = userProfileRepository.fetchByIdWithOptimisticLock(userProfileId)
                .orElseThrow(() -> new UserProfileNotFoundException(userProfileId));

        log.info("Updating a user profile by that id '{}'. Current user profile version '{}'. Expected version is '{}'",
                userProfileId, userProfile.getVersion(), userProfileVersion);

        if (userProfileVersion != userProfile.getVersion()) {
            throw new UserProfileIncorrectVersionException(userProfileId, userProfileVersion, userProfile.getVersion());
        }

        userProfile.setFirstName(updateUserProfileDTO.getFirstName());
        userProfile.setLastName(updateUserProfileDTO.getLastName());
        userProfile.setEmail(updateUserProfileDTO.getEmail());
        userProfile.setPhone(updateUserProfileDTO.getPhone());

        try {
            userProfileRepository.saveAndFlush(userProfile);
        }
        catch (OptimisticLockException e) {
            throw new UserProfileIncorrectVersionException(userProfileId, e);
        }

        log.info("Finished updating a user profile by that id '{}'. A user profile was updated with username '{}'. " +
                "New version is '{}'", userProfile.getId(), userProfile.getUsername(), userProfile.getVersion());
    }
}
