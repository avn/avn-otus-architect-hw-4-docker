package ru.avn.otus.hw.users.dto;

import lombok.Data;
import ru.avn.otus.hw.users.validators.annotations.*;

@Data
public class CreateUserDTO {

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
