package ru.avn.otus.hw.users.profiles.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.avn.otus.hw.commons.web.utils.ETagUtils;
import ru.avn.otus.hw.users.profiles.dto.*;
import ru.avn.otus.hw.users.profiles.exceptions.UserProfileAlreadyExistsException;
import ru.avn.otus.hw.users.profiles.exceptions.UserProfileIncorrectVersionException;
import ru.avn.otus.hw.users.profiles.exceptions.UserProfileNotFoundException;
import ru.avn.otus.hw.users.profiles.services.UserProfileService;
import ru.avn.otus.hw.users.profiles.validators.annotations.ValidIfMatchUserVersion;

@Slf4j
@Validated
@RestController
@RequestMapping("/users/profiles")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping
    public CreateUserProfileResultDTO createUser(@RequestBody CreateUserProfileDTO createUserProfileDTO) throws UserProfileAlreadyExistsException {
        return userProfileService.create(createUserProfileDTO);
    }

    @GetMapping("/{userProfileId}")
    public ResponseEntity<GetUserProfileResultDTO> getUser(@PathVariable long userProfileId) throws UserProfileNotFoundException {
        Versionable<GetUserProfileResultDTO> userProfile = userProfileService.get(userProfileId);
        return ResponseEntity.ok()
                .eTag(ETagUtils.intToETagHeader(userProfile.getVersion()))
                .body(userProfile.getObject());
    }

    @DeleteMapping("/{userProfileId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long userProfileId) throws UserProfileNotFoundException {
        userProfileService.delete(userProfileId);
    }

    @PutMapping("/{userProfileId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable long userProfileId,
                    @ValidIfMatchUserVersion @RequestHeader("If-Match") String ifMatchHeaderUserVersion,
                    @RequestBody UpdateUserProfileDTO updateUserProfileDTO)
            throws UserProfileNotFoundException, UserProfileIncorrectVersionException {
        userProfileService.update(userProfileId, ETagUtils.ifMatchHeaderToInt(ifMatchHeaderUserVersion),
                updateUserProfileDTO);
    }

}
