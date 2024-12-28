package org.springboot.pet.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
@Schema(description = "Регистрация пользователя")
public class CreateUserDto {
    @Schema(title = "Email пользователя")
    String email;

    @Schema(title = "Пароль пользователя")
    String password;
}
