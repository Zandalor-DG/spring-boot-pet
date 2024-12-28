package org.springboot.pet.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
@Schema(description = "Вход в систему пользователя")
public class GetUserDto {
    @Schema(title = "Id пользователя")
    Long id;

    @Schema(title = "Email пользователя")
    String email;

    @Schema(title = "Имя пользователя")
    String first_name;

    @Schema(title = "Фамилия пользователя")
    String second_name;
}
