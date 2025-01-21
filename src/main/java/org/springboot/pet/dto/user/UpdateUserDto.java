package org.springboot.pet.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Value;

@Data
@Schema(description = "Данные на обновление пользователя")
public class UpdateUserDto {
    @Schema(title = "Новое имя пользователя")
    String newFirstName;

    @Schema(title = "Новая фамилия пользователя")
    String newSecondName;

    @Schema(title = "Новый пароль пользователя")
    String newPassword;

}
