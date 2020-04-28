package ru.avn.otus.hw.users.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;
import ru.avn.otus.hw.users.dto.*;
import ru.avn.otus.hw.users.entities.User;
import ru.avn.otus.hw.users.exceptions.UserConflictException;
import ru.avn.otus.hw.users.exceptions.UserNotFoundException;
import ru.avn.otus.hw.users.repositories.UserRepository;

import javax.persistence.OptimisticLockException;
import javax.validation.Valid;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ProjectionFactory projectionFactory;

    @Override
    public CreateUserResultDTO createUser(@Valid CreateUserDTO createUserDTO) {
        log.debug("Starting creating a user '{}'", createUserDTO);

        User user = new User();
        user.setUsername(createUserDTO.getUsername());
        user.setFirstName(createUserDTO.getFirstName());
        user.setLastName(createUserDTO.getLastName());
        user.setEmail(createUserDTO.getEmail());
        user.setPhone(createUserDTO.getPhone());

        userRepository.save(user);
        log.info("Finished creating a user. User with that username '{}' saved with that id '{}'", user.getUsername(),
                user.getId());

        return projectionFactory.createProjection(CreateUserResultDTO.class, user);
    }

    @Override
    public Versionable<GetUserResultDTO> getUser(long userId) throws UserNotFoundException {
        log.debug("Starting getting a user by that id '{}'", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        log.info("Finished getting a user by that id '{}'. A user is found that username '{}'", user.getId(),
                user.getUsername());

        GetUserResultDTO projection = projectionFactory.createProjection(GetUserResultDTO.class, user);

        return Versionable.of(projection, user.getVersion());
    }

    @Override
    public void deleteUser(long userId) throws UserNotFoundException {
        log.debug("Starting deleting user by that id '{}'", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userRepository.delete(user);

        log.info("Finished deleting user by that id '{}'. A user was deleted with that username '{}' ", user.getId(),
                user.getUsername());
    }

    @Override
    public void updateUser(long userId, int userVersion, UpdateUserDTO updateUserDTO) throws UserNotFoundException, UserConflictException {
        log.debug("Starting updating user by that id '{}'. Updating to '{}'", userId, updateUserDTO);

        User user = userRepository.fetchByIdWithOptimisticLock(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        log.info("Updating user by that id '{}'. Current user version '{}'. Expected user version '{}'",
                user.getId(), user.getVersion(), userVersion);

        if (userVersion != user.getVersion()) {
            throw new UserConflictException(
                    String.format("User version was changed. Expected '%d'. Current '%d'", userVersion,
                            user.getVersion()));
        }

        user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(updateUserDTO.getLastName());
        user.setEmail(updateUserDTO.getEmail());
        user.setPhone(updateUserDTO.getPhone());

        try {
            userRepository.saveAndFlush(user);
        }
        catch (OptimisticLockException e) {
            throw new UserConflictException("User version was changed:" + e.getMessage());
        }

        log.info("Finished updating user by that id '{}'. A user was updated with username '{}'. New user version '{}'",
                user.getId(),
                user.getUsername(), user.getVersion());
    }
}