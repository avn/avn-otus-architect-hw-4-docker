package ru.avn.otus.hw.users.profiles.dto;

import lombok.Data;
import ru.avn.otus.hw.common.validators.annotations.ValidUserEmail;
import ru.avn.otus.hw.common.validators.annotations.ValidUserFirstName;
import ru.avn.otus.hw.common.validators.annotations.ValidUserLastName;
import ru.avn.otus.hw.common.validators.annotations.ValidUserPhone;

@Data
public class UpdateUserProfileDTO {

    @ValidUserFirstName
    private String firstName;

    @ValidUserLastName
    private String lastName;

    @ValidUserEmail
    private String email;

    @ValidUserPhone
    private String phone;

}
