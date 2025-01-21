package org.springboot.pet.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Setter
@Getter
@Schema(description = "Регистрация пользователя")
public class RegisterUserDto {
    @Schema(title = "Email пользователя")
    String userEmail;

    @Schema(title = "Пароль пользователя")
    private String password;

    @Schema(title = "Подтвердить пароль пользователя")
    String confirmPassword;

    @Schema(title = "Имя пользователя")
    String firstName;

    @Schema(title = "Фамилия пользователя")
    String secondName;
}
