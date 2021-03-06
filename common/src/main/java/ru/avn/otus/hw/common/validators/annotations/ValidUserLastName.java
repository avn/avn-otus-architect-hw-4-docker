package ru.avn.otus.hw.common.validators.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

@NotNull
@NotBlank
@Size(max = 255)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = {})
public @interface ValidUserLastName {

    String message() default "{javax.validation.constraints.Size.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
