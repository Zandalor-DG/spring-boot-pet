package org.springboot.pet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
@Schema(description = "Информация по интерактивной справке")
public class UserDto {
    @Schema(title = "Id пользователя")
    Long id;

    @Schema(title = "Email пользователя")
    String email;

    @Schema(title = "Password позьзователя")
    String password;
}
