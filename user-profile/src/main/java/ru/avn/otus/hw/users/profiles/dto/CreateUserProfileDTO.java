package ru.avn.otus.hw.users.profiles.dto;

import lombok.Data;
import ru.avn.otus.hw.users.profiles.validators.annotations.*;

@Data
public class CreateUserProfileDTO {

    @ValidUsername
    private String username;

    @ValidUserFirstName
    private String firstName;

    @ValidUserLastName
    private String lastName;

    @ValidUserEmail
    private String email;

    @ValidUserPhone
    private String phone;

}
