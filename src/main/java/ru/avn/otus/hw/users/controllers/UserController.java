package ru.avn.otus.hw.users.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.avn.otus.hw.users.dto.*;
import ru.avn.otus.hw.users.exceptions.UserConflictException;
import ru.avn.otus.hw.users.exceptions.UserNotFoundException;
import ru.avn.otus.hw.users.services.UserService;
import ru.avn.otus.hw.users.validators.annotations.ValidIfMatchUserVersion;
import ru.avn.otus.hw.utils.ETagUtils;

@Slf4j
@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @PostMapping
    CreateUserResultDTO createUser(@RequestBody CreateUserDTO createUserDTO) {
        return userService.createUser(createUserDTO);
    }

    @GetMapping("/{userId}")
    ResponseEntity<GetUserResultDTO> getUser(@PathVariable long userId) throws UserNotFoundException {
        Versionable<GetUserResultDTO> user = userService.getUser(userId);
        return ResponseEntity.ok()
                .eTag(ETagUtils.intToETagHeader(user.getVersion()))
                .body(user.getObject());
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deleteUser(@PathVariable long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void updateUser(@PathVariable long userId,
                    @ValidIfMatchUserVersion @RequestHeader("If-Match") String ifMatchHeaderUserVersion,
                    @RequestBody UpdateUserDTO updateUserDTO)
            throws UserNotFoundException, UserConflictException {
        userService.updateUser(userId, ETagUtils.ifMatchHeaderToInt(ifMatchHeaderUserVersion), updateUserDTO);
    }

}
