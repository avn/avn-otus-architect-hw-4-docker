package ru.avn.otus.hw.users.dto;

import lombok.Data;
import ru.avn.otus.hw.users.validators.annotations.ValidUserEmail;
import ru.avn.otus.hw.users.validators.annotations.ValidUserFirstName;
import ru.avn.otus.hw.users.validators.annotations.ValidUserLastName;
import ru.avn.otus.hw.users.validators.annotations.ValidUserPhone;

@Data
public class UpdateUserDTO {

    @ValidUserFirstName
    private String firstName;

    @ValidUserLastName
    private String lastName;

    @ValidUserEmail
    private String email;

    @ValidUserPhone
    private String phone;

}
