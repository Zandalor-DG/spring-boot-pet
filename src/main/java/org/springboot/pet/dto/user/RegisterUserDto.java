package org.springboot.pet.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
@Setter
@Getter
@Schema(description = "Регистрация пользователя")

public class RegisterUserDto {
    @Schema(title = "Email пользователя")
    String userEmail;

    @Schema(title = "Пароль пользователя")
    String password;

    @Schema(title = "Подтвердить пароль пользователя")
    String confirmPassword;
}
